/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewCadTimeAction;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.domain.Time;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class SaveTimeAction implements ICommanderAction {

    public SaveTimeAction() {
    }

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Time t = new Time();
        t.setNome(request.getParameter("nome"));
  
        if(!t.getNome().equals("")){
            new TimeDao().inserir(t);
            request.setAttribute("succ", "Cadastro de time feito com sucesso!");
            new CallViewCadTimeAction().executar(request, response);
            
        }else{
            request.setAttribute("err", "Insira um nome");
            new CallViewCadTimeAction().executar(request, response);
        }
    }
    
}
