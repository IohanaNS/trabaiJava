/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewConsultaRankingAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.CampeonatoEstatisticaDao;
import bombapatch.model.dao.impl.PartidaDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.CampeonatoEstatistica;
import bombapatch.model.domain.Partida;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class CalculaRankingAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {    //// t1 x t1 
                                     //// t1 x t3
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String[] placarTime1 = request.getParameterValues("placarTime1");
        String[] placarTime2 = request.getParameterValues("placarTime2");
        String timeComArt = request.getParameter("times");
                
        String[] nometime1 = request.getParameterValues("nometime1");
        String[] nometime2 = request.getParameterValues("nometime2");
        
        UsuarioLoginDTO us = (UsuarioLoginDTO)request.getSession().getAttribute("user");
        Usuario u = new UsuarioDao().findByLogin(us.getLogin());
        Campeonato c = new CampeonatoDao().findByUser(u);
        
        ArrayList<Time> times1 = new ArrayList<>();
        ArrayList<Time> times2 = new ArrayList<>();
        ArrayList<Partida> partidas = new ArrayList<>(); 
        
        CampeonatoEstatistica ce = new CampeonatoEstatisticaDao().findByCampeonato(c);
        
        //achando os times de acordo com nome
        for (int i = 0; i < nometime1.length; i++) {
            Time t = new TimeDao().findByNome(nometime1[i]);
            times1.add(t);
            Time t2 = new TimeDao().findByNome(nometime2[i]);
             times2.add(t2);       
        } 

       //criando as partidas
        for (int i = 0; i < times1.size(); i++) {
            Time a = times1.get(i); Time b = times2.get(i);
            Partida p = new Partida();
            p.setTime1(a);
            p.setTime2(b);
            p.setCampeonato(c);
            p.setPontuacaoTime1(Integer.parseInt(placarTime1[i]));
            p.setPontuacaoTime2(Integer.parseInt(placarTime2[i]));
            
           partidas.add(p);
        }
        //colocando partidas em times
        for(Time t : times1){
            for(Partida p : partidas){
                if(t.getNome().equals(p.getTime1().getNome()) || t.getNome().equals(p.getTime2().getNome())){
                    t.addPartidas(p);
                }
            }
        }
        for(Time t : times2){
            for(Partida p : partidas){
                if(t.getNome().equals(p.getTime2().getNome())|| t.getNome().equals(p.getTime1().getNome())){
                    t.addPartidas(p);
                }
            }
        }    
        
        ArrayList<Time> timxs = new ArrayList<>();
        
        timxs.add(times1.get(0));
        for (int i = 1; i < times1.size(); i++) { 
            if(!times1.get(i).getNome().equals(times1.get(i-1).getNome())){
                 timxs.add(times1.get(i));
            }
        } //nome do time 1 != nome do time -1
        int cont = 0;
        for (int i = 0; i < times2.size(); i++) {
            for(int j = 0; j < timxs.size(); j++){
                if(times2.get(i).getNome().equals(timxs.get(j).getNome())){
                    cont++;
                }
            }
            if(cont == 0){
                timxs.add(times2.get(i));
            }
            cont = 0;
        }
        for(Time t : timxs){
            if(timeComArt.equals(t.getNome())){
                t.setTemArtilheiro(true);
            }else  t.setTemArtilheiro(false);
           t.calculaPontuacaoTotal();
           t.setCampeonatoEstatistica(ce);
           new TimeDao().alterar(t);
        }
     
      
        //COMENTAR PARA TESTES PARA NÃO FICAR SALVANDO PARTIDAS DESNECESSARIAMENTE
        for(Partida p : partidas){
            p.setCampeonato(c);
            new PartidaDao().inserir(p);
        }

        
          List<Time> listanova = new TimeDao().findByCa(c);
       
        //calculando o ranking baseando nos times atualizados
        for(Time t : listanova)ce.addTime(t);
       
        List<Time> ranking = ce.calculaRanking();
        
        Usuario winner = new UsuarioDao().findByTeam(ranking.get(0));
        
        ce.setUsuario(winner);
        
        new CampeonatoEstatisticaDao().alterar(ce);
        

        
        request.setAttribute("lista", partidas);
        request.setAttribute("ranking", ranking);
        request.setAttribute("winner", winner);
        
        new CallViewConsultaRankingAction().executar(request, response);
        
        
    }
    
}
