/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.view;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.CampeonatoEstatisticaDao;
import bombapatch.model.dao.impl.PartidaDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.CampeonatoEstatistica;
import bombapatch.model.domain.Partida;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class CallViewConsultaRankingAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=consultaRanking");
        
        
        if(request.getAttribute("lista")!= null && request.getAttribute("ranking") != null 
                && request.getAttribute("winner") != null && request.getAttribute("times") != null){
            rd.forward(request, response);
        }else{
            UsuarioLoginDTO userLog = (UsuarioLoginDTO)request.getSession().getAttribute("user");
        
            Usuario u = new UsuarioDao().findByLogin(userLog.getLogin());

            Campeonato ca = new CampeonatoDao().findByUser(userLog);
            
            List<Partida> lista = new PartidaDao().findByCampeonato(ca);
            List<Time> times = new TimeDao().findByCa(ca);
            CampeonatoEstatistica ce = new CampeonatoEstatisticaDao().findByCampeonato(ca);
            times.forEach((t) -> ce.addTime(t));
            List<Time> ranking = ce.calculaRanking();
            Usuario winner = new UsuarioDao().findByCa(ca);
            
            request.setAttribute("lista", lista);
            request.setAttribute("ranking", ranking);
            request.setAttribute("winner", winner);
            
            rd.forward(request, response);

        }
        
        
        
    }
    
}
