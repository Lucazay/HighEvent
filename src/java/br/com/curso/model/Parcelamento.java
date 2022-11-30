package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

public class Parcelamento {
    
    private int idParcelamento;
    private int nroParcela;
    private double valorParcela;
    private Date dataVencimento;
    private Date dataPagamento;
    private String situacao;
    private String descricao;

    public Parcelamento() {
        this.idParcelamento = 0;
        this.nroParcela = 0;
        this.valorParcela = 0;
        this.dataVencimento = Conversao.dataAtual();
        this.dataPagamento = Conversao.dataAtual();
        this.situacao = "A";
        this.descricao = "";
    }

    public Parcelamento(int idParcelamento, int nroParcela, double valorParcela, Date dataVencimento, Date dataPagamento, String situacao, String descricao) {
        this.idParcelamento = idParcelamento;
        this.nroParcela = nroParcela;
        this.valorParcela = valorParcela;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.situacao = situacao;
        this.descricao = descricao;
    }

    public int getIdParcelamento() {
        return idParcelamento;
    }

    public void setIdParcelamento(int idParcelamento) {
        this.idParcelamento = idParcelamento;
    }

    public int getNroParcela() {
        return nroParcela;
    }

    public void setNroParcela(int nroParcela) {
        this.nroParcela = nroParcela;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
