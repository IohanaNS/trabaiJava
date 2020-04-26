/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.view;

import bombapatch.controller.action.ICommanderAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daves
 */
public class CallViewAcessoNegadoAction implements ICommanderAction {

    public CallViewAcessoNegadoAction() {
    }

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("err", "Acesso não autorizado");
        
        new CallViewLoginAction().executar(request, response);
                
    }

}
