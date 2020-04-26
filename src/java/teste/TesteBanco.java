/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import bombapatch.model.dao.impl.TimeDao;
import bombapatch.model.dao.impl.UsuarioDao;
import bombapatch.model.domain.Time;
import bombapatch.model.domain.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author daves
 */
public class TesteBanco {
    
    public static void main(String[] args) {
        
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bombapatch2PU");
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("COnnectou");
        
        em.getTransaction().begin();
        
//        Time t = new TimeDao().findByNome("Barcelona");
        Usuario u = new Usuario(null,"vvvcv","aacvc","mae@marte",false);
//        u.setTime(t);
        try {
            new UsuarioDao().inserir(u);
////        em.persist(u);
////
////        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
       } catch (SQLException ex) {
            Logger.getLogger(TesteBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
