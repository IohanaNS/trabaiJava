/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.view;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
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
public class CallViewHomeAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=campeonato");
    
        
        UsuarioLoginDTO userLog = (UsuarioLoginDTO)request.getSession().getAttribute("user");
        
        Usuario u = new UsuarioDao().findByLogin(userLog.getLogin());
        
        if(u.getCampeonato() != null){
            Campeonato c = new CampeonatoDao().findByUser(userLog);
            List<Usuario> users = new UsuarioDao().findByCampeonato(c);
             boolean count = true;
             
            if(users.size() < 4){
                count = false;
            }
            
            request.setAttribute("count", count);
            request.setAttribute("users", users);
             rd.forward(request, response);
        }else{
            boolean count = false;
            ArrayList<Usuario> users = new ArrayList<>();
            request.setAttribute("count", count);
            request.setAttribute("users", users);
             rd.forward(request, response);
        }
        
       
  
            
    }
    
}
