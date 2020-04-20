/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public interface ICommanderAction {
    public boolean ehLiberado();  //se for liberado para todos os user

    
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
