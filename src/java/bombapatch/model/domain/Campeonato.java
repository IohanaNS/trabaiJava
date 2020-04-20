/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iohan
 */
@Entity
@Table(catalog = "bombapatch", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campeonato.findAll", query = "SELECT c FROM Campeonato c")
    , @NamedQuery(name = "Campeonato.findByIdCampeonato", query = "SELECT c FROM Campeonato c WHERE c.idCampeonato = :idCampeonato")})
public class Campeonato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCampeonato", nullable = false)
    private Integer idCampeonato;

    
    @OneToMany(mappedBy = "campeonato")
    private List<CampeonatoEstatistica> campeonatoEstatisticaList;
   


    public Campeonato() {
    }

    public Campeonato(Integer idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public Integer getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(Integer idCampeonato) {
        this.idCampeonato = idCampeonato;
    }



    @XmlTransient
    public List<CampeonatoEstatistica> getCampeonatoEstatisticaList() {
        return campeonatoEstatisticaList;
    }

    public void setCampeonatoEstatisticaList(List<CampeonatoEstatistica> campeonatoEstatisticaList) {
        this.campeonatoEstatisticaList = campeonatoEstatisticaList;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampeonato != null ? idCampeonato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campeonato)) {
            return false;
        }
        Campeonato other = (Campeonato) object;
        if ((this.idCampeonato == null && other.idCampeonato != null) || (this.idCampeonato != null && !this.idCampeonato.equals(other.idCampeonato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bombapatch.model.domain.Campeonato[ idCampeonato=" + idCampeonato + " ]";
    }
    
}
