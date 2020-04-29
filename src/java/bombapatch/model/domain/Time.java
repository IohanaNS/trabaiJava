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
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @NamedQuery(name = "Time.findAll", query = "SELECT t FROM Time t")
    , @NamedQuery(name = "Time.findByIdTime", query = "SELECT t FROM Time t WHERE t.idTime = :idTime")
    , @NamedQuery(name = "Time.findByNome", query = "SELECT t FROM Time t WHERE t.nome = :nome")
    , @NamedQuery(name = "Time.findByPontuacaoTotal", query = "SELECT t FROM Time t WHERE t.pontuacaoTotal = :pontuacaoTotal")})
public class Time implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTime", nullable = false)
    private Integer idTime;
    @Column(length = 45)
    private String nome;

    @Column(name = "pontuacao_total", precision = 22)
    private Double pontuacaoTotal;
    @OneToMany(mappedBy = "time",cascade = CascadeType.ALL)
    private List<JogadorTime> jogadorTimeList;
    
    
    @OneToOne(mappedBy="time")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="idCampeonatoEstatistica")
    private CampeonatoEstatistica campeonatoEstatistica;
    
    @OneToMany(mappedBy = "time1",cascade = CascadeType.ALL)
    private List<Partida> partidaList;
    @OneToMany(mappedBy = "time2",cascade = CascadeType.ALL)
    private List<Partida> partidaList1;
    
    @Transient 
    private ArrayList<Partida> partidas;

    public Time() {
        this.partidas = new ArrayList<Partida>();
    }

    public Time(Integer idTime) {
        this.idTime = idTime;
    }

    public Integer getIdTime() {
        return idTime;
    }

    public CampeonatoEstatistica getCampeonatoEstatistica() {
        return campeonatoEstatistica;
    }

    public void setCampeonatoEstatistica(CampeonatoEstatistica campeonatoEstatistica) {
        this.campeonatoEstatistica = campeonatoEstatistica;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }
    
    public void setIdTime(Integer idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(Double pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    @XmlTransient
    public List<JogadorTime> getJogadorTimeList() {
        return jogadorTimeList;
    }

    public void setJogadorTimeList(List<JogadorTime> jogadorTimeList) {
        this.jogadorTimeList = jogadorTimeList;
    }

    @XmlTransient
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Partida> getPartidaList() {
        return partidaList;
   }

    public void setPartidaList(List<Partida> partidaList) {
       this.partidaList = partidaList;
   }

    @XmlTransient
   public List<Partida> getPartidaList1() {
        return partidaList1;
   }

    public void setPartidaList1(List<Partida> partidaList1) {
        this.partidaList1 = partidaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTime != null ? idTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Time)) {
            return false;
        }
        Time other = (Time) object;
        if ((this.idTime == null && other.idTime != null) || (this.idTime != null && !this.idTime.equals(other.idTime))) {
            return false;
        }
        return true;
    }

   public void calculaPontuacaoTotal(){
       
       double pontuacao = 0;
       for(Partida p : partidas){
           if(p.getTime1().getNome().equals(nome)){
               pontuacao += p.pontuacaoTotTime1();
           }else if(p.getTime2().getNome().equals(nome)){
               pontuacao += p.pontuacaoTotTime2();
           }
       }
       this.pontuacaoTotal = pontuacao;
   }

    public void addPartidas(Partida p) {
        this.partidas.add(p);
    }
    
    
}
