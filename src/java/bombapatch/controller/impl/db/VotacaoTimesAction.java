/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewRankingAction;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class VotacaoTimesAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UsuarioLoginDTO userLog = (UsuarioLoginDTO) request.getSession().getAttribute("user");
        Usuario u = new UsuarioDao().findByLogin(userLog.getLogin());
        Campeonato camp = new CampeonatoDao().findByUser(u);
       

        List<Time> times = new TimeDao().findByCa(camp);

        Partida p = new Partida();
        ArrayList<Partida> partidas = p.ordenaPartidas(times); //retorna uma lista de partidas

        request.setAttribute("times", times);
        request.setAttribute("partidas", partidas);
        request.setAttribute("camp", camp);
        new CallViewRankingAction().executar(request, response);
    }

}
