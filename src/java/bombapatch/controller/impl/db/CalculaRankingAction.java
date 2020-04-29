/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewConsultaRankingAction;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class CalculaRankingAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
          
        
        String[] placarTime1 = request.getParameterValues("placarTime1");
        String[] golsArTime1 = request.getParameterValues("golsArTime1");
        String[] placarTime2 = request.getParameterValues("placarTime2");
        String[] golsArTime2 = request.getParameterValues("golsArTime2");
        
        String[] nometime1 = request.getParameterValues("nometime1");
        String[] nometime2 = request.getParameterValues("nometime2");
        
        Campeonato c = new CampeonatoDao().findLast();
        ArrayList<Time> times1 = new ArrayList<>();
        ArrayList<Time> times2 = new ArrayList<>();
        ArrayList<Partida> partidas = new ArrayList<>(); //tem Id de times e de campeonato
        
        CampeonatoEstatistica ce = new CampeonatoEstatisticaDao().findLast();
        ce.setCampeonato(c);
               

        
        //achando os times de acordo com nome
        for (int i = 0; i < nometime1.length; i++) {
            Time t = new TimeDao().findByNome(nometime1[i]);
            times1.add(t);
        }
        for (int i = 0; i < nometime2.length; i++) {
            Time t = new TimeDao().findByNome(nometime2[i]);
            times2.add(t);
        }
        
       //criando as partidas
        for (int i = 0; i < times1.size(); i++) {
            Time a = times1.get(i); Time b = times2.get(i);
            Partida p = new Partida();
            p.setTime1(a);
            p.setTime2(b);
            p.setCampeonato(c);
            p.setGolsdeArtilheiroTime1(Integer.parseInt(golsArTime1[i]));
            p.setGolsdeArtilheiroTime2(Integer.parseInt(golsArTime2[i]));
            p.setPontuacaoTime1(Integer.parseInt(placarTime1[i]));
            p.setPontuacaoTime2(Integer.parseInt(placarTime2[i]));
            
            partidas.add(p);
        }
        
        //calculando pontuação total de todas as partidas
        for(Time t : times1){
            for(Partida p : partidas){
                if(t.getNome().equals(p.getTime1().getNome())){
                    t.addPartidas(p);
                }
            }
            t.calculaPontuacaoTotal();
            ce.addTime(t);
        }
        for(Time t : times2){
            for(Partida p : partidas){
                if(t.getNome().equals(p.getTime2().getNome())){
                    t.addPartidas(p);
                }
            }
            t.calculaPontuacaoTotal();
            ce.addTime(t);
        }
        
        for(Partida p : partidas){
            new PartidaDao().inserir(p);
        }
        
        
        List<Time> ranking = ce.calculaRanking();
        for(Time t : ranking){
            t.setCampeonatoEstatistica(ce);
        }
        
        Usuario winner = new UsuarioDao().findByTeam(ranking.get(0));
        
        ce.setUsuario(winner);
        
        new CampeonatoEstatisticaDao().alterar(ce);
        
        
        
        //atualizando as partidas
        ArrayList<Partida> lista = new ArrayList<>();
        
        for (int i = 0; i < times1.size(); i++) {
            Time a = times1.get(i); Time b = times2.get(i);
            Partida p = new Partida();
            p.setTime1(a);
            p.setTime2(b);
            p.setCampeonato(c);
            p.setGolsdeArtilheiroTime1(Integer.parseInt(golsArTime1[i]));
            p.setGolsdeArtilheiroTime2(Integer.parseInt(golsArTime2[i]));
            p.setPontuacaoTime1(Integer.parseInt(placarTime1[i]));
            p.setPontuacaoTime2(Integer.parseInt(placarTime2[i]));
            
            lista.add(p);
        }
        
        request.setAttribute("lista", lista);
        request.setAttribute("ranking", ranking);
        request.setAttribute("winner", winner);
        
        new CallViewConsultaRankingAction().executar(request, response);
    }
    
}
