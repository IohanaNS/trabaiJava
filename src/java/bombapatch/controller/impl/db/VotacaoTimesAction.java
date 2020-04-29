/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewRankingAction;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.CampeonatoEstatisticaDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.CampeonatoEstatistica;
import bombapatch.model.domain.Partida;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class VotacaoTimesAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
       return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        List<Usuario> users = new UsuarioDao().findLast4();
        ArrayList<Time> times = new ArrayList<>();
        
        Campeonato camp = new Campeonato();
        new CampeonatoDao().inserir(camp);
        new CampeonatoEstatisticaDao().inserir(new CampeonatoEstatistica());
        
        for (int i = 0; i < users.size(); i++) {
            times.add(users.get(i).getTime());
        }
        
        Partida p = new Partida();
         ArrayList<Partida> partidas = p.ordenaPartidas(times);
         
         request.setAttribute("partidas", partidas);
         request.setAttribute("camp", camp);
         new CallViewRankingAction().executar(request, response);
    }
    
}
