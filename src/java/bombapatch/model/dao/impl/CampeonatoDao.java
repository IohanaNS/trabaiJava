/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.impl;

import bombapatch.model.dao.GenericsDAO;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
import bombapatch.model.domain.Campeonato;
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
public class CampeonatoDao extends GenericsDAO<Campeonato,Integer>{

    @Override
    public Campeonato inserir(Campeonato obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.persist(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public Campeonato alterar(Campeonato obj) throws SQLException {
        conexao.getTransaction().begin();
        conexao.merge(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
       Campeonato u = new Campeonato(key);

        conexao.getTransaction().begin();
        conexao.remove(u);
        conexao.getTransaction().commit();
    }

    @Override
    public Campeonato buscarUm(Integer key) throws SQLException {
        Query q = conexao.createQuery("SELECT u FROM Campeonato u WHERE u.idCampeonato = :idCampeonato");

        try {
            q.setParameter("idCampeonato", key);
            return (Campeonato) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Override
    public List<Campeonato> buscarTodos() throws SQLException {
        Query q = conexao.createNamedQuery("Campeonato.findAll");

        return q.getResultList();
    }
    
    public Campeonato findLast() throws SQLException{
        Query q = conexao.createQuery("SELECT u FROM Campeonato u ORDER BY u.idCampeonato DESC");
        q.setMaxResults(1);

        try {
            
            return (Campeonato) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    public Campeonato findByUser(UsuarioLoginDTO u) {
        Query q = conexao.createQuery("SELECT u FROM Campeonato u WHERE u.idCampeonato = :idCampeonato");

        try {
            q.setParameter("idCampeonato", u.getCampeonato().getIdCampeonato());
            return (Campeonato) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    public List<String> findBySalas() {
        
//        Query q = conexao.createQuery("SELECT DISTINCT c.sala FROM Campeonato c JOIN c.usuarios u WHERE u.campeonato.idCampeonato = c.idCampeonato ");
//        
        Query q = conexao.createQuery("SELECT c.sala FROM Campeonato c JOIN c.usuarios u WHERE"
                + " c.idCampeonato = u.campeonato.idCampeonato "
                + "GROUP BY c.sala HAVING COUNT(c.idCampeonato) < 4");

        try {
            
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    public Campeonato findByNomeSala(String sala) {
        
        Query q = conexao.createQuery("SELECT u FROM Campeonato u WHERE u.sala = :sala");

        try {
            q.setParameter("sala", sala);
            return (Campeonato) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }
    
}
