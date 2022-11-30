
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

public class AtividadeEvento {

    private int idAtividadeEvento;
    private Double cargaHoraria;
    private String nomeAtividade;
    private String resumo;
    private Date dataAtividade;
    private String horaAtividade;
    private Evento Evento;
    private TipoAtividade TipoAtividade;

    public AtividadeEvento() {
        this.idAtividadeEvento = 0;
        this.cargaHoraria = 0.0;
        this.nomeAtividade = "";
        this.resumo = "";
        this.dataAtividade = Conversao.dataAtual();
        this.horaAtividade = "";
        this.Evento = new Evento();
        this.TipoAtividade  = new TipoAtividade();
    }

    public AtividadeEvento(int idAtividadeEvento, Double cargaHoraria, String nomeAtividade, String resumo, Date dataAtividade, String horaAtividade, Evento Evento, TipoAtividade TipoAtividade) {
        this.idAtividadeEvento = idAtividadeEvento;
        this.cargaHoraria = cargaHoraria;
        this.nomeAtividade = nomeAtividade;
        this.resumo = resumo;
        this.dataAtividade = dataAtividade;
        this.horaAtividade = horaAtividade;
        this.Evento = Evento;
        this.TipoAtividade = TipoAtividade;
    }

    public int getIdAtividadeEvento() {
        return idAtividadeEvento;
    }

    public void setIdAtividadeEvento(int idAtividadeEvento) {
        this.idAtividadeEvento = idAtividadeEvento;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Date getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(Date dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public String getHoraAtividade() {
        return horaAtividade;
    }

    public void setHoraAtividade(String horaAtividade) {
        this.horaAtividade = horaAtividade;
    }

    public Evento getEvento() {
        return Evento;
    }

    public void setEvento(Evento Evento) {
        this.Evento = Evento;
    }

    public TipoAtividade getTipoAtividade() {
        return TipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade TipoAtividade) {
        this.TipoAtividade = TipoAtividade;
    }

    
}