package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

public class Evento {

    private int idEvento;
    private String nomeEvento ;
    private double valorEvento ;
    private double valorEventoPrazo;
    private Date dataInicioEvento ;
    private Date dataTerminoEvento;
    private String informacaoEvento;
    private String situacaoEvento;
    private double saldoCaixa;
    private String situacaoCaixa;
    private String foto;
    private Curso curso;

    public Evento() {
        this.idEvento = 0;
        this.nomeEvento = "";
        this.valorEvento = 0;
        this.valorEventoPrazo = 0;
        this.dataInicioEvento = Conversao.dataAtual();
        this.dataTerminoEvento = Conversao.dataAtual();
        this.informacaoEvento = "";
        this.situacaoEvento = "A";
        this.saldoCaixa = 0;
        this.situacaoCaixa = "";
        this.foto = "";
        this.curso = new Curso();
    }

    public Evento(int idEvento, String nomeEvento, double valorEvento, double valorEventoPrazo, Date dataInicioEvento, Date dataTerminoEvento, String informacaoEvento, String situacaoEvento, double saldoCaixa, String situacaoCaixa, String foto, Curso curso) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.valorEvento = valorEvento;
        this.valorEventoPrazo = valorEventoPrazo;
        this.dataInicioEvento = dataInicioEvento;
        this.dataTerminoEvento = dataTerminoEvento;
        this.informacaoEvento = informacaoEvento;
        this.situacaoEvento = situacaoEvento;
        this.saldoCaixa = saldoCaixa;
        this.situacaoCaixa = situacaoCaixa;
        this.foto = foto;
        this.curso = curso;
    }


    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public double getValorEvento() {
        return valorEvento;
    }

    public void setValorEvento(double valorEvento) {
        this.valorEvento = valorEvento;
    }

    public double getValorEventoPrazo() {
        return valorEventoPrazo;
    }

    public void setValorEventoPrazo(double valorEventoPrazo) {
        this.valorEventoPrazo = valorEventoPrazo;
    }

    public Date getDataInicioEvento() {
        return dataInicioEvento;
    }

    public void setDataInicioEvento(Date dataInicioEvento) {
        this.dataInicioEvento = dataInicioEvento;
    }

    public Date getDataTerminoEvento() {
        return dataTerminoEvento;
    }

    public void setDataTerminoEvento(Date dataTerminoEvento) {
        this.dataTerminoEvento = dataTerminoEvento;
    }

    public String getInformacaoEvento() {
        return informacaoEvento;
    }

    public void setInformacaoEvento(String informacaoEvento) {
        this.informacaoEvento = informacaoEvento;
    }

    public String getSituacaoEvento() {
        return situacaoEvento;
    }

    public void setSituacaoEvento(String situacaoEvento) {
        this.situacaoEvento = situacaoEvento;
    }

    public double getSaldoCaixa() {
        return saldoCaixa;
    }

    public void setSaldoCaixa(double saldoCaixa) {
        this.saldoCaixa = saldoCaixa;
    }

    public String getSituacaoCaixa() {
        return situacaoCaixa;
    }

    public void setSituacaoCaixa(String situacaoCaixa) {
        this.situacaoCaixa = situacaoCaixa;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


}
