/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.impl;

import bombapatch.model.dao.GenericsDAO;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author iohan
 */
public class TimeDao extends GenericsDAO<Time,Integer>{

    @Override
    public Time inserir(Time obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.persist(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public Time alterar(Time obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.merge(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
       conexao.getTransaction().begin();
        Time p = conexao.getReference(Time.class, key);
        conexao.remove(p);
        conexao.getTransaction().commit();
    }

    @Override
    public Time buscarUm(Integer key) throws SQLException {
        Query q = conexao.createQuery("SELECT u FROM time WHERE u.idTime = :idTime");

        try {
            q.setParameter("idTime", key);
            return (Time) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Override
    public List<Time> buscarTodos() throws SQLException {
        Query q = conexao.createNamedQuery("Time.findAll");

        return q.getResultList();
    }
    
    public Time findByNome(String nome){
        
        Query q = conexao.createNamedQuery("Time.findByNome");

        try {
            q.setParameter("nome", nome);
            return (Time) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    public List<Time> findByCa(Campeonato ca) {
        Query q = conexao.createQuery("SELECT ce.times FROM CampeonatoEstatistica ce WHERE ce.campeonato.idCampeonato = :ca");

        
        try {
            q.setParameter("ca", ca.getIdCampeonato());
           return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }
    
}
