/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.impl;

import bombapatch.model.dao.GenericsDAO;
import bombapatch.model.domain.JogadorTime;
import bombapatch.model.domain.JogadorTime;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author iohan
 */
public class JogadorTimeDao extends GenericsDAO<JogadorTime,Integer>{

   @Override
    public JogadorTime inserir(JogadorTime obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.persist(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public JogadorTime alterar(JogadorTime obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.merge(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
       JogadorTime u = new JogadorTime(key);

        conexao.getTransaction().begin();
        conexao.remove(u);
        conexao.getTransaction().commit();
    }

    @Override
    public JogadorTime buscarUm(Integer key) throws SQLException {
        Query q = conexao.createQuery("SELECT u FROM JogadorTime u WHERE u.idJogador = :idJogador");

        try {
            q.setParameter("idJogador", key);
            return (JogadorTime) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Override
    public List<JogadorTime> buscarTodos() throws SQLException {
        Query q = conexao.createNamedQuery("JogadorTime.findAll");

        return q.getResultList();
    }
    
}
