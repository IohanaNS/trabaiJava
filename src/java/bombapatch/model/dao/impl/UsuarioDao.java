/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.impl;

import bombapatch.model.dao.GenericsDAO;
import bombapatch.model.dao.dto.UsuarioLoginDTO;
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
public class UsuarioDao extends GenericsDAO<Usuario, Integer> {

    @Override
    public Usuario inserir(Usuario obj) throws SQLException {

        conexao.getTransaction().begin();
        conexao.persist(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public Usuario alterar(Usuario obj) throws SQLException {

        conexao.getTransaction().begin();
        conexao.merge(obj);
        conexao.getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {

        Usuario u = new Usuario(key);

        conexao.getTransaction().begin();
        conexao.remove(u);
        conexao.getTransaction().commit();
    }

    @Override
    public Usuario buscarUm(Integer key) throws SQLException {

        //Query q = conexao.createNamedQuery("Usuario.findById");        
        Query q = conexao.createQuery("SELECT u FROM Usuario WHERE u.idUsuario = :idUsuario");

        try {
            q.setParameter("idUsuario", key);
            return (Usuario) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }

    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {

        Query q = conexao.createNamedQuery("Usuario.findAll");

        return q.getResultList();

    }
    
    public List<Usuario> findLast4() throws SQLException{  //teste
        
        Query q = conexao.createQuery("SELECT u from Usuario u ORDER BY u.idUsuario DESC");
        q.setMaxResults(4);
        return q.getResultList();
    }
    
    public UsuarioLoginDTO buscarPeloLoginaESenha(String login, String senha) throws SQLException {
        

        Query q = conexao.createNamedQuery("Usuario.findByLoginAndSenha");

        try {
            q.setParameter("log", login);
            q.setParameter("sen", senha);
            
            Usuario user = (Usuario) q.getSingleResult();
            UsuarioLoginDTO udto = 
                    new UsuarioLoginDTO(user.getIdUsuario(), user.getLogin(), user.isEhAdmin());
            
            return udto;
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
        
    }
    
    
    public Usuario findByLogin (String login) throws SQLException {
        

        Query q = conexao.createNamedQuery("Usuario.findByLogin");

        try {
            q.setParameter("log", login);

            return (Usuario) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
        
    }
    
    
}
