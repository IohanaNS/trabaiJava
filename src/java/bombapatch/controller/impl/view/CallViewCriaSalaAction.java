/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.view;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
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
public class CallViewCriaSalaAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=criaSala");
        
        UsuarioLoginDTO us = (UsuarioLoginDTO) request.getSession().getAttribute("user");
        Usuario u = new UsuarioDao().findByLogin(us.getLogin());
        

        if(us.getCampeonato() != null){
            Campeonato ca = new CampeonatoDao().findByUser(u);
            List<Usuario> users = new UsuarioDao().findByCampeonato(ca);
            for(Usuario t : users){
                t.setTime(null);
                //t.getTime().setPontuacaoTotal(0.0);
                new UsuarioDao().alterar(t);
           //     new TimeDao().alterar(t.getTime());
            }
            
            List<Time> times = new TimeDao().buscarTodos();
            request.setAttribute("times", times);
        
            rd.forward(request, response);
        }else{
            List<Time> times = new TimeDao().buscarTodos();
            request.setAttribute("times", times);
        
            rd.forward(request, response);
        }
        
        
       
        
        
        
    }
    
}
