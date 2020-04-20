/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iohan
 */
@Entity
@Table(name = "jogador_time", catalog = "bombapatch", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JogadorTime.findAll", query = "SELECT j FROM JogadorTime j")
    , @NamedQuery(name = "JogadorTime.findByIdJogador", query = "SELECT j FROM JogadorTime j WHERE j.idJogador = :idJogador")
    , @NamedQuery(name = "JogadorTime.findByNome", query = "SELECT j FROM JogadorTime j WHERE j.nome = :nome")
    
    , @NamedQuery(name = "JogadorTime.findByEhArtilheiro", query = "SELECT j FROM JogadorTime j WHERE j.ehArtilheiro = :ehArtilheiro")})
public class JogadorTime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idJogador", nullable = false)
    private Integer idJogador;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nome;

    @Basic(optional = false)
    @Column(name = "eh_artilheiro", nullable = false)
    private boolean ehArtilheiro;
    
    @JoinColumn(name = "idTime", referencedColumnName = "idTime")
    @ManyToOne
    private Time time;

    public JogadorTime() {
    }

    public JogadorTime(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public JogadorTime(Integer idJogador, String nome, boolean ehArtilheiro) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.ehArtilheiro = ehArtilheiro;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getEhArtilheiro() {
        return ehArtilheiro;
    }

    public void setEhArtilheiro(boolean ehArtilheiro) {
        this.ehArtilheiro = ehArtilheiro;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time idTime) {
        this.time = idTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJogador != null ? idJogador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JogadorTime)) {
            return false;
        }
        JogadorTime other = (JogadorTime) object;
        if ((this.idJogador == null && other.idJogador != null) || (this.idJogador != null && !this.idJogador.equals(other.idJogador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bombapatch.model.domain.JogadorTime[ idJogador=" + idJogador + " ]";
    }
    
}
