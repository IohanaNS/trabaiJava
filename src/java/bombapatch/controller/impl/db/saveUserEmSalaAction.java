/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.controller.impl.db;

import bombapatch.controller.action.ICommanderAction;
import bombapatch.controller.impl.view.CallViewEntraEmSalaAction;
import bombapatch.controller.impl.view.CallViewHomeAction;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.dao.impl.CampeonatoDao;
import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iohan
 */
public class saveUserEmSalaAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UsuarioLoginDTO userLog = (UsuarioLoginDTO) request.getSession().getAttribute("user");

        Usuario u = new UsuarioDao().findByLogin(userLog.getLogin());

        Campeonato c = new Campeonato(request.getParameter("escolhaSala"));

        Campeonato ca = new CampeonatoDao().findByNomeSala(c.getSala());

        u.setCampeonato(ca);

        Time t = new TimeDao().findByNome(request.getParameter("escolhaTime"));

        if (t.getUsuario() == null) {
            u.setTime(t);
            new UsuarioDao().alterar(u);
            request.setAttribute("succ", "Sucesso! Agora você faz parte da sala " + ca.getSala());
            new CallViewHomeAction().executar(request, response);
        } else {
            request.setAttribute("err", "Time já escolhido por outro usuario para este campeonato");
            new CallViewEntraEmSalaAction().executar(request, response);
        }

    }

}
