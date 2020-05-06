/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewEntraEmSalaAction;
import bombapatch.controller.impl.view.CallViewHomeAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.CampeonatoEstatisticaDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.CampeonatoEstatistica;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class saveSalaAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        
        Campeonato camp = new Campeonato(request.getParameter("nome"));
        new CampeonatoDao().inserir(camp);
        
        Campeonato c = new CampeonatoDao().findByNomeSala(camp.getSala());
        
        new CampeonatoEstatisticaDao().inserir(new CampeonatoEstatistica(c));
        CampeonatoEstatistica ce = new CampeonatoEstatisticaDao().findByCampeonato(c);

        
        UsuarioLoginDTO u = (UsuarioLoginDTO)request.getSession().getAttribute("user");
        
        Usuario us = new UsuarioDao().findByLogin(u.getLogin());
        
        Time t = new TimeDao().findByNome(request.getParameter("escolhaTime"));
        if(t == null && u.getCampeonato() == null){
            request.setAttribute("err", "É necessário escolher um time");
            new CallViewEntraEmSalaAction().executar(request, response);
        }

        
      
        if(t.getUsuario() == null){
         us.setTime(t);
         us.setCampeonato(c);
         t.setCampeonatoEstatistica(ce);
        new TimeDao().alterar(t);
        new UsuarioDao().alterar(us);
        request.setAttribute("succ", "Sucesso! Sala " + camp.getSala()+" criada");
         new CallViewHomeAction().executar(request, response);
        }
        else{
            request.setAttribute("err", "Time já escolhido por outro usuario para este campeonato");
            new CallViewEntraEmSalaAction().executar(request, response);
        }
        
       
        
        
       
        
        
    }
    
}
