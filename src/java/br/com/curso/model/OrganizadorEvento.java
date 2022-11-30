
package br.com.curso.model;

public class OrganizadorEvento {

private int idOrganizadorEvento;
private Organizador organizador;
private Evento evento;
private Funcao funcao;

    public OrganizadorEvento() {
        this.idOrganizadorEvento = 0;
        this.organizador = null;
        this.evento = new Evento();
        this.funcao = new Funcao();
      }

    public OrganizadorEvento(int idOrganizadorEvento, Organizador organizador, Evento evento, Funcao funcao) {
        this.idOrganizadorEvento = idOrganizadorEvento;
        this.organizador = organizador;
        this.evento = evento;
        this.funcao = funcao;
    }

    public int getIdOrganizadorEvento() {
        return idOrganizadorEvento;
    }

    public void setIdOrganizadorEvento(int idOrganizadorEvento) {
        this.idOrganizadorEvento = idOrganizadorEvento;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }


}
