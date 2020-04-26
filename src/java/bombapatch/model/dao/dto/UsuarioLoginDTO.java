/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.dao.dto;

/**
 *
 * @author daves
 */
public class UsuarioLoginDTO {
    
    private Integer idUsuario;
    private String login;
    private boolean ehAdmin;
    private String senha;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(Integer idUsuario, String login, boolean ehAdmin) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.ehAdmin = ehAdmin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isEhAdmin() {
        return ehAdmin;
    }

    public void setEhAdm(boolean ehAdmin) {
        this.ehAdmin = ehAdmin;
    }
   
    
   
    
}
