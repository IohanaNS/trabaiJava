/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewLoginAction;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class SaveUserAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        
        Usuario u = new Usuario(null,request.getParameter("login"), request.getParameter("senha"), request.getParameter("email"), false);
        
        new UsuarioDao().inserir(u);
        
        
        
       Time t = new Time();
       t.setNome(request.getParameter("escolhaTime"));
        
        request.setAttribute("time", t.getNome());
        request.setAttribute("user", u.getLogin());
        
        
        new CallViewLoginAction().executar(request, response);
      //  new AlteraTimeAction().executar(request, response);
    }
    
    
}
