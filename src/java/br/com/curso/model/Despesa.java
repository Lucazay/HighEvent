
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

public class Despesa {
    
private int idDespesa;
private Fornecedor fornecedor;
private Double valorDespesa;
private Date vencimentoDespesa;
private Date pagamentoDespesa;
private String descricao;
private String situacao;

    public Despesa() {
       this.idDespesa = 0;
       this.fornecedor = null;
       this.valorDespesa = 0.0;
       this.vencimentoDespesa = Conversao.dataAtual();
       this.pagamentoDespesa = Conversao.dataAtual();
       this.descricao = "";
       this.situacao = "A";
    }

    public Despesa(int idDespesa, Fornecedor fornecedor, Double valorDespesa, Date vencimentoDespesa, Date pagamentoDespesa, String descricao, String situacao) {
        this.idDespesa = idDespesa;
        this.fornecedor = fornecedor;
        this.valorDespesa = valorDespesa;
        this.vencimentoDespesa = vencimentoDespesa;
        this.pagamentoDespesa = pagamentoDespesa;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Double getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(Double valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Date getVencimentoDespesa() {
        return vencimentoDespesa;
    }

    public void setVencimentoDespesa(Date vencimentoDespesa) {
        this.vencimentoDespesa = vencimentoDespesa;
    }

    public Date getPagamentoDespesa() {
        return pagamentoDespesa;
    }

    public void setPagamentoDespesa(Date pagamentoDespesa) {
        this.pagamentoDespesa = pagamentoDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    
}
