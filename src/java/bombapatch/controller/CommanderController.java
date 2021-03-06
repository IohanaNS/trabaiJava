/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller;

import bombapatch.controller.impl.db.saveUserEmSalaAction;
import bombapatch.controller.impl.view.CallViewEntraEmSalaAction;
import bombapatch.controller.impl.db.saveSalaAction;
import bombapatch.controller.impl.view.CallViewCriaSalaAction;
import bombapatch.controller.impl.db.SaveJogTimeAction;
import bombapatch.controller.impl.view.CallViewCadJogTimeAction;
import bombapatch.controller.impl.db.SaveTimeAction;
import bombapatch.controller.impl.view.CallViewCadTimeAction;
import bombapatch.controller.impl.view.CallViewRankingAction;
import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.db.CalculaRankingAction;
import bombapatch.controller.impl.db.SaveUserAction;
import bombapatch.controller.impl.db.VotacaoTimesAction;
import bombapatch.controller.impl.session.CheckLoginAction;
import bombapatch.controller.impl.session.LogoutAction;
import bombapatch.controller.impl.view.CallViewAcessoNegadoAction;
import bombapatch.controller.impl.view.CallViewCadUser;
import bombapatch.controller.impl.view.CallViewConsultaRankingAction;
import bombapatch.controller.impl.view.CallViewHomeAction;
import bombapatch.controller.impl.view.CallViewLoginAction;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
@WebServlet(name = "CommanderController", urlPatterns = {"/home"})
public class CommanderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
     private static HashMap<String, ICommanderAction> comandos;
    
    static{
        comandos = new HashMap<>();
        //CALL VIEWS////////////////////////////////
        comandos.put("",new CallViewLoginAction());
        comandos.put("login", new CallViewLoginAction());
        comandos.put("consultaRanking", new CallViewConsultaRankingAction());
        comandos.put("acessoNegado", new CallViewAcessoNegadoAction());
        comandos.put("ranking", new CallViewRankingAction());
        comandos.put("campeonato", new CallViewHomeAction());
        comandos.put("entrarEmSala", new CallViewEntraEmSalaAction()); 
        //view de cadastros
        comandos.put("cadUser", new CallViewCadUser());
        comandos.put("cadastroTime", new CallViewCadTimeAction());
        comandos.put("cadastroJogTime", new CallViewCadJogTimeAction());
        comandos.put("criarSala", new CallViewCriaSalaAction()); 
        
        //ACTIONS//////////////////////////////////////////////////////////
        comandos.put("checkLogin", new CheckLoginAction());
        comandos.put("logout", new LogoutAction());
        comandos.put("saveUser", new SaveUserAction());
        comandos.put("saveTime", new SaveTimeAction());
        comandos.put("calculaRanking", new CalculaRankingAction());
        comandos.put("votacaoTimes", new VotacaoTimesAction()); 
        comandos.put("saveJogTime", new SaveJogTimeAction());
        comandos.put("saveSala", new saveSalaAction());
        comandos.put("saveUserEmSala", new saveUserEmSalaAction());
         

    }
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         String ac = request.getParameter("ac");
        ac = (ac == null) ? "" : ac;
        
        try {

            if (comandos.get(ac).ehLiberado()) {
                comandos.get(ac).executar(request, response);
            } else if (request.getSession().getAttribute("user") != null) {
                comandos.get(ac).executar(request, response);
            }else{
              
                comandos.get("acessoNegado").executar(request, response);
            }

        } catch (NullPointerException ex) {
            System.out.println(ex.getCause());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=erro");
            request.setAttribute("err", "Comando não Encontrado "+ex.getMessage());
            rd.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=erro");
            request.setAttribute("err", "Erro Geral do Sistema " + ex.getMessage());
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
