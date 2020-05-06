/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.impl;

import bombapatch.model.dao.GenericsDAO;
import bombapatch.model.domain.Campeonato;
import bombapatch.model.domain.CampeonatoEstatistica;
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
public class CampeonatoEstatisticaDao extends GenericsDAO<CampeonatoEstatistica,Integer>{
    
    @Override
    public CampeonatoEstatistica inserir(CampeonatoEstatistica obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.persist(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public CampeonatoEstatistica alterar(CampeonatoEstatistica obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.merge(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
       conexao.getTransaction().begin();
        CampeonatoEstatistica p = conexao.getReference(CampeonatoEstatistica.class, key);
        conexao.remove(p);
        conexao.getTransaction().commit();
    }

    @Override
    public CampeonatoEstatistica buscarUm(Integer key) throws SQLException {
        Query q = conexao.createQuery("SELECT u FROM campeonato WHERE u.idCampeonatoEstatistica = :idCampeonatoEstatistica");

        try {
            q.setParameter("idCampeonatoEstatistica", key);
            return (CampeonatoEstatistica) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Override
    public List<CampeonatoEstatistica> buscarTodos() throws SQLException {
        Query q = conexao.createNamedQuery("CampeonatoEstatistica.findAll");

        return q.getResultList();
    }
    
    public CampeonatoEstatistica findLast() throws SQLException{
        Query q = conexao.createQuery("SELECT u FROM CampeonatoEstatistica u ORDER BY u.idEstatistica DESC");
        q.setMaxResults(1);

        try {
            
            return (CampeonatoEstatistica) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    public CampeonatoEstatistica findByCampeonato(Campeonato ca) {
        Query q = conexao.createQuery("SELECT u FROM CampeonatoEstatistica u WHERE u.campeonato.idCampeonato= :ca");
        q.setMaxResults(1);

        try {
            q.setParameter("ca", ca.getIdCampeonato());
            return (CampeonatoEstatistica) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }
}
