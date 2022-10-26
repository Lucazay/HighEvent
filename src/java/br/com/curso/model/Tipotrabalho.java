package br.com.curso.model;


public class Tipotrabalho {
    
    private int idTipotrabalho;
    private String descricao;
    private String situacao;

    public Tipotrabalho() {
        this.idTipotrabalho = 0;
        this.descricao = "";
        this.situacao = "A";
    }

    public Tipotrabalho(int idTipotrabalho, String descricao, String situacao) {
        this.idTipotrabalho = idTipotrabalho;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public int getIdTipotrabalho() {
        return idTipotrabalho;
    }

    public void setIdTipotrabalho(int idTipotrabalho) {
        this.idTipotrabalho = idTipotrabalho;
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
