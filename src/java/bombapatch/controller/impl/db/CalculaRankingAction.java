/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class CalculaRankingAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=consultaRanking");
        
        int placarTime1 = Integer.parseInt(request.getParameter("placarTime1"));
        int golsArTime1 = Integer.parseInt(request.getParameter("golsArTime1"));
        int placarTime2 = Integer.parseInt(request.getParameter("placarTime2"));
        int golsArTime2 = Integer.parseInt(request.getParameter("golsArTime2"));
        
        
    }
    
}
