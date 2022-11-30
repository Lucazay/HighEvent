
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;


public class Doacao {

private int idDoacao;
private Pessoa Patrocinador;
private Double valorDoacao;
private Date dataDoacao;
private String descricao;
private String situacao;

    public Doacao() {
        this.idDoacao = 0;
        this.Patrocinador = new Pessoa();
        this.valorDoacao = 0.0;
        this.dataDoacao = Conversao.dataAtual();
        this.descricao = "";
        this.situacao = "";
    }

    public Doacao(int idDoacao, Pessoa Patrocinador, Double valorDoacao, Date dataDoacao, String descricao, String situacao) {
        this.idDoacao = idDoacao;
        this.Patrocinador = Patrocinador;
        this.valorDoacao = valorDoacao;
        this.dataDoacao = dataDoacao;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Doacao(int idDoacao, int Patrocinador, Double valorDoacao, java.sql.Date dataDoacao, String descricao, String situacao) {
    }

    public int getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(int idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Pessoa getPatrocinador() {
        return Patrocinador;
    }

    public void setPatrocinador(Pessoa Patrocinador) {
        this.Patrocinador = Patrocinador;
    }

    public Double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(Double valorDoacao) {
        this.valorDoacao = valorDoacao;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
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
