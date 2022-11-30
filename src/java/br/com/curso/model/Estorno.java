package br.com.curso.model;

import java.util.Date;
public class Estorno {
    
private int idEstorno;
private Double valorEstorno;
private Date dataEstorno;
private Date dataPagamento;
private String descricao;
private String situacao;

    public Estorno() {
    }

    public Estorno(int idEstorno, Double valorEstorno, Date dataEstorno, Date dataPagamento, String descricao, String situacao) {
        this.idEstorno = idEstorno;
        this.valorEstorno = valorEstorno;
        this.dataEstorno = dataEstorno;
        this.dataPagamento = dataPagamento;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public int getIdEstorno() {
        return idEstorno;
    }

    public void setIdEstorno(int idEstorno) {
        this.idEstorno = idEstorno;
    }

    public Double getValorEstorno() {
        return valorEstorno;
    }

    public void setValorEstorno(Double valorEstorno) {
        this.valorEstorno = valorEstorno;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEstorno() {
        return dataEstorno;
    }

    public void setDataEstorno(Date dataEstorno) {
        this.dataEstorno = dataEstorno;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
