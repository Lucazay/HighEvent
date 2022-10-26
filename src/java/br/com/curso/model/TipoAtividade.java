
package br.com.curso.model;

public class TipoAtividade {
    
    private int idTipoAtividade;
    private String descricao;
    private String situacao;

    public TipoAtividade(int idTipoAtividade, String descricao, String situacao) {
        this.idTipoAtividade = idTipoAtividade;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public TipoAtividade() {
        this.idTipoAtividade = 0;
        this.descricao = "";
        this.situacao = "A";
    }

    public int getIdTipoAtividade() {
        return idTipoAtividade;
    }

    public void setIdTipoAtividade(int idTipoAtividade) {
        this.idTipoAtividade = idTipoAtividade;
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