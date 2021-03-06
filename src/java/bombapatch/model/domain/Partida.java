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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iohan
 */
@Entity
@Table(catalog = "bombapatch", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")
    , @NamedQuery(name = "Partida.findByIdPartida", query = "SELECT p FROM Partida p WHERE p.idPartida = :idPartida")})

public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPartida", nullable = false)
    private Integer idPartida;

    @Column(name = "pontuacaoTime1")
    private Integer ptoTime1;
    @Column(name = "pontuacaoTime2")
    private Integer ptoTime2;
    @JoinColumn(name = "idCampeonato", referencedColumnName = "idCampeonato")
    @ManyToOne
    private Campeonato campeonato;
    @JoinColumn(name = "time1", referencedColumnName = "idTime")
    @ManyToOne
    private Time time1;
    @JoinColumn(name = "time2", referencedColumnName = "idTime")
    @ManyToOne
    private Time time2;

    public Partida() {
    }

    public Partida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getPontuacaoTime1() {
        return ptoTime1;
    }

    public void setPontuacaoTime1(Integer ptoTime1) {
        this.ptoTime1 = ptoTime1;
    }

    public Integer getPontuacaoTime2() {
        return ptoTime2;
    }

    public void setPontuacaoTime2(Integer ptoTime2) {
        this.ptoTime2 = ptoTime2;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public ArrayList<Partida> ordenaPartidas(List<Time> times) { 

        ArrayList<Partida> partidas = new ArrayList<>();

        for (int i = 0; i < times.size(); i++) {

            for (int j = i + 1; j < times.size(); j++) {
                Partida p = new Partida();
                p.setTime1(times.get(i));
                p.setTime2(times.get(j));
                partidas.add(p);
            }

        }
        
        return partidas;

    }
    
    public double pontuacaoTotTime(String t){
        //vitoria 3pts , cada gol 1 pto, gol tomado -0.5, 
        double ptoTot = 0;
        if(time1.getNome().equals(t)){
             ptoTot =  ptoTime1 - (Double.parseDouble(Integer.toString(ptoTime2))/2);
            if(ptoTime2 < ptoTime1) ptoTot += 3;
        
        }else if(time2.getNome().equals(t)){
            ptoTot = ptoTime2 - (Double.parseDouble(Integer.toString(ptoTime1))/2);
            if(ptoTime1 < ptoTime2) ptoTot += 3;
        }
       
        
        return ptoTot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

}
