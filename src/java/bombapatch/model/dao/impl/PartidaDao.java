/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.impl;

import bombapatch.model.dao.GenericsDAO;
import bombapatch.model.domain.Partida;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author iohan
 */
public class PartidaDao extends GenericsDAO<Partida,Integer>{
    @Override
    public Partida inserir(Partida obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.persist(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public Partida alterar(Partida obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.merge(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
       Partida u = new Partida(key);

        conexao.getTransaction().begin();
        conexao.remove(u);
        conexao.getTransaction().commit();
    }

    @Override
    public Partida buscarUm(Integer key) throws SQLException {
        Query q = conexao.createQuery("SELECT u FROM Partida u WHERE u.idPartida = :idPartida");

        try {
            q.setParameter("idPartida", key);
            return (Partida) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Override
    public List<Partida> buscarTodos() throws SQLException {
        Query q = conexao.createNamedQuery("Partida.findAll");

        return q.getResultList();
    }
    
    public List<Partida> findByCampeonato(Object camp) throws SQLException {
        Query q = conexao.createQuery("SELECT u FROM Partida u WHERE u.campeonato = :camp");

       
        try {
            q.setParameter("camp", camp);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
