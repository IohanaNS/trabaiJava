/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombapatch.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iohan
 */
@Entity
@Table(name = "campeonato_estatistica", catalog = "bombapatch", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampeonatoEstatistica.findAll", query = "SELECT c FROM CampeonatoEstatistica c")
    , @NamedQuery(name = "CampeonatoEstatistica.findByIdEstatistica", query = "SELECT c FROM CampeonatoEstatistica c WHERE c.idEstatistica = :idEstatistica")
    })
public class CampeonatoEstatistica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstatistica", nullable = false)
    private Integer idEstatistica;
    
   

    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Campeonato campeonato;
    
    @ManyToOne
    @JoinColumn(name = "ganhadorId")
    private Usuario usuario;

    @OneToMany(mappedBy = "campeonatoEstatistica")
    private List<Time> times;

    public CampeonatoEstatistica() {
        this.times = new ArrayList<>();
        
    }

    public List<Time> calculaRanking() {

         boolean troca = true;
         List<Time> ranking = times;
         
            do
            {
                troca = false;
                for (int i = 0; i < ranking.size() - 1; i++)
                {
                     
                    if (ranking.get(i).getPontuacaoTotal() > ranking.get(i+1).getPontuacaoTotal())
                    {
                        Time aux = ranking.get(i);
                        ranking.set(i, ranking.get(i+1));
                        ranking.set(i+1, aux);
                        

                        troca = true;
                    }
                }
            } while (troca);

        return times;
    }
    
    public CampeonatoEstatistica(Integer idEstatistica) {
        this.idEstatistica = idEstatistica;
    }

    public Integer getIdEstatistica() {
        return idEstatistica;
    }

    public void setIdEstatistica(Integer idEstatistica) {
        this.idEstatistica = idEstatistica;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }
    public void addTime(Time t){
        this.times.add(t);
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatistica != null ? idEstatistica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampeonatoEstatistica)) {
            return false;
        }
        CampeonatoEstatistica other = (CampeonatoEstatistica) object;
        if ((this.idEstatistica == null && other.idEstatistica != null) || (this.idEstatistica != null && !this.idEstatistica.equals(other.idEstatistica))) {
            return false;
        }
        return true;
    }

}
