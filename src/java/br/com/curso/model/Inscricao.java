
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

public class Inscricao {
    
private int idInscricao;
private String nroPulseira;
private String chaveQRCode;
private Date dataInscricao;
private String situacao;
private Double valorInscricao;
private int nroParcelas;
private Double parcelasPagas;
private Double parcelasPagar;
private Double parcelasCanceladas;
private Double parcelasEstorno;
private Evento evento;
private Participante participante;

    public Inscricao() {
        this.idInscricao = 0;
        this.nroPulseira = "";
        this.chaveQRCode = "";
        this.dataInscricao = Conversao.dataAtual();
        this.situacao = "A";
        this.valorInscricao = 0.0;
        this.nroParcelas = 0;
        this.parcelasPagas = 0.0;
        this.parcelasPagar = 0.0;
        this.parcelasCanceladas = 0.0;
        this.parcelasEstorno = 0.0;
        this.evento = new Evento();
        this.participante = null;
    }

    public Inscricao(int idInscricao, String nroPulseira, String chaveQRCode, Date dataInscricao, String situacao, Double valorInscricao, int nroParcelas, 
    Double parcelasPagas, Double parcelasPagar, Double parcelasCanceladas, Double parcelasEstorno, Evento evento, Participante participante) {
        this.idInscricao = idInscricao;
        this.nroPulseira = nroPulseira;
        this.chaveQRCode = chaveQRCode;
        this.dataInscricao = dataInscricao;
        this.situacao = situacao;
        this.valorInscricao = valorInscricao;
        this.nroParcelas = nroParcelas;
        this.parcelasPagas = parcelasPagas;
        this.parcelasPagar = parcelasPagar;
        this.parcelasCanceladas = parcelasCanceladas;
        this.parcelasEstorno = parcelasEstorno;
        this.evento = evento;
        this.participante = participante;
    }

    public int getIdInscricao() {
        return idInscricao;
    }

    public void setIdInscricao(int idInscricao) {
        this.idInscricao = idInscricao;
    }

    public String getNroPulseira() {
        return nroPulseira;
    }

    public void setNroPulseira(String nroPulseira) {
        this.nroPulseira = nroPulseira;
    }

    public String getChaveQRCode() {
        return chaveQRCode;
    }

    public void setChaveQRCode(String chaveQRCode) {
        this.chaveQRCode = chaveQRCode;
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Double getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(Double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public int getNroParcelas() {
        return nroParcelas;
    }

    public void setNroParcelas(int nroParcelas) {
        this.nroParcelas = nroParcelas;
    }

    public Double getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(Double parcelasPagas) {
        this.parcelasPagas = parcelasPagas;
    }

    public Double getParcelasPagar() {
        return parcelasPagar;
    }

    public void setParcelasPagar(Double parcelasPagar) {
        this.parcelasPagar = parcelasPagar;
    }

    public Double getParcelasCanceladas() {
        return parcelasCanceladas;
    }

    public void setParcelasCanceladas(Double parcelasCanceladas) {
        this.parcelasCanceladas = parcelasCanceladas;
    }

    public Double getParcelasEstorno() {
        return parcelasEstorno;
    }

    public void setParcelasEstorno(Double parcelasEstorno) {
        this.parcelasEstorno = parcelasEstorno;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    
}
