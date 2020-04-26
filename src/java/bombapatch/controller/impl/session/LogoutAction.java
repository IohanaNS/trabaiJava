/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.session;


import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewLoginAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iow
 */
public class LogoutAction implements ICommanderAction {
    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        request.getSession().invalidate();
        
        new CallViewLoginAction().executar(request, response);
    
    }

    
}
