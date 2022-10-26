package br.com.curso.model;

public class Instituicao {
    
    private int idInstituicao;
    private String nomeInstituicao;
    private String cnpj;
    private String situacao;

    public Instituicao() {
        this.idInstituicao = 0;
        this.nomeInstituicao ="";
        this.cnpj = "";
        this.situacao = "A";
    }

    public Instituicao(int idInstituicao, String nomeInstituicao, String cnpj, String situacao) {
        this.idInstituicao = idInstituicao;
        this.nomeInstituicao = nomeInstituicao;
        this.cnpj = cnpj;
        this.situacao = situacao;
    }

    public int getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(int idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
    
}
