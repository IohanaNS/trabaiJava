/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.view;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.domain.Time;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class CallViewCadUser implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=cadastrar");
        
        
        
        
        rd.forward(request, response);
    }
    
}
