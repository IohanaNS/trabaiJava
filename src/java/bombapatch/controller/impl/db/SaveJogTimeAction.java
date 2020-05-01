/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewCadJogTimeAction;
import bombapatch.controller.impl.view.CallViewCadTimeAction;
import bombapatch.model.dao.impl.JogadorTimeDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.domain.JogadorTime;
import bombapatch.model.domain.Time;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class SaveJogTimeAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
         Time t = new TimeDao().findByNome(request.getParameter("escolhaTime"));
         
         JogadorTime jt = new JogadorTime();
         jt.setNome(request.getParameter("nome"));
         jt.setTime(t);
         if(!jt.getTime().getNome().isEmpty() && !jt.getNome().isEmpty()){
             new JogadorTimeDao().inserir(jt);
             request.setAttribute("succ", "Cadastro de jogador feito com sucesso!");
            new CallViewCadJogTimeAction().executar(request, response);
         }else{
              
             request.setAttribute("succ", "É necessário escolher um time e preencher o nome");
              new CallViewCadJogTimeAction().executar(request, response);
         }
         
    }
    
}
