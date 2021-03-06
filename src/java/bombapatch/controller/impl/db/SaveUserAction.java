/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewCadUser;
import bombapatch.controller.impl.view.CallViewLoginAction;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import bombapatch.util.Crypto;
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

       if(u.getSenha().length() < 6){
           request.setAttribute("err", "A senha deve ter no mínimo 6 caracteres");
           new CallViewCadUser().executar(request, response);
       }else{
            u.setSenha( Crypto.md5(u.getSenha()) );
        new UsuarioDao().inserir(u);
        request.setAttribute("succ", "Cadastro feito com sucesso!");
        new CallViewLoginAction().executar(request, response);
       }
      
      

        
        
        
      
    }
    
    
}
