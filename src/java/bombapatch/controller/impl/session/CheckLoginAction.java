/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.session;


import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewHomeAction;
import bombapatch.controller.impl.view.CallViewLoginAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.util.Crypto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daves
 */
public class CheckLoginAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
     
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        UsuarioLoginDTO user = new UsuarioDao().buscarPeloLoginaESenha(login, Crypto.md5(senha) );

        
        if (user != null) {
            request.getSession().setAttribute("user", user);            
            new CallViewHomeAction().executar(request, response);
        } else {
            request.setAttribute("err", "Login ou senha inválido(a)");
            new CallViewLoginAction().executar(request, response);            
        }
    }

}
