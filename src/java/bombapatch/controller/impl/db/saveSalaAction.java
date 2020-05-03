/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewHomeAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.CampeonatoEstatisticaDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.CampeonatoEstatistica;
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
        
        Campeonato c = new CampeonatoDao().findLast();
        
        new CampeonatoEstatisticaDao().inserir(new CampeonatoEstatistica(c));
        
        UsuarioLoginDTO u = (UsuarioLoginDTO)request.getSession().getAttribute("user");
        
        Usuario us = new UsuarioDao().findByLogin(u.getLogin());

        us.setCampeonato(c);
        new UsuarioDao().alterar(us);
        
        request.setAttribute("succ", "Sala criada com sucesso! Aguarde outros jogadores entrar para inciar o campeonato");
        new CallViewHomeAction().executar(request, response);
        
        
    }
    
}
