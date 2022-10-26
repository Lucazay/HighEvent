package br.com.curso.model;

public class Curso {
    
    private int idCurso;
    private String nomeCurso;
    private String situacao;
    private Instituicao instituicao;

    public Curso() {
        this.idCurso = 0;
        this.nomeCurso = "";
        this.situacao = "A";
        this.instituicao = new Instituicao();
    }

    public Curso(int idCurso, String nomeCurso, String situacao, Instituicao instituicao) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.situacao = situacao;
        this.instituicao = instituicao;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    
    
}
