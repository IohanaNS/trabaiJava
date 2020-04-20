/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.db.AlteraTimeAction;
import bombapatch.controller.impl.db.SaveUserAction;
import bombapatch.controller.impl.view.CallViewCadUser;
import bombapatch.controller.impl.view.CallViewLoginAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        comandos.put("",new CallViewLoginAction());
        comandos.put("login", new CallViewLoginAction());
        comandos.put("cadUser", new CallViewCadUser());
        comandos.put("saveUser", new SaveUserAction());
        comandos.put("alteraTime", new AlteraTimeAction());
    }
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         String ac = request.getParameter("ac");
        ac = (ac == null) ? "" : ac;
        
        try {
            comandos.get(ac).executar(request, response);
        }catch (NullPointerException ex){
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=erro");
            request.setAttribute("err", "Comando não encontrado");
            rd.forward(request, response);
        } catch (Exception ex) {
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=erro");
            request.setAttribute("err", "Comando não encontrado");
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
