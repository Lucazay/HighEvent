
package br.com.curso.model;


public class Estado {
    
    private int idEstado;
    private String nomeEstado;
    private String siglaEstado;
    private String situacao;

    public Estado(int idEstado, String nomeEstado, String siglaEstado, String situacao) {
        this.idEstado = idEstado;
        this.nomeEstado = nomeEstado;
        this.siglaEstado = siglaEstado;
        this.situacao = situacao;
    }

    public Estado() {
        this.idEstado = 0;
        this.nomeEstado = "";
        this.siglaEstado = "";
        this.situacao = "A";
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
