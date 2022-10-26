
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

public class Organizador extends Pessoa{
    
    private int idOrganizador;
    private String permiteLogin;
    private String situacao;
    
    public Organizador(int idOrganizador, String permiteLogin, String situacao, int idPessoa, String nomeRazaoPessoa, String rglePessoa, 
            String cpfCnpjPessoa, Date dataNascFundPessoa, String telefonePessoa, Cidade cidade, String logradouroPessoa, String numLogradouroPessoa, 
            String bairroPessoa, String cepPessoa, String emailPessoa, String login, String senha, String foto) {
        super(idPessoa, nomeRazaoPessoa, rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, cidade, logradouroPessoa, 
                numLogradouroPessoa, bairroPessoa, cepPessoa, emailPessoa, login, senha, foto);
        this.idOrganizador = idOrganizador;
        this.permiteLogin = permiteLogin;
        this.situacao = situacao;
    }
    
    public static Organizador organizadorVazio() throws ParseException{
        Cidade oCidade = new Cidade();
        Date dataNascFundPessoa = Conversao.dataAtual();
        Organizador oOrganizador = new Organizador(0,"S","A",0,"","","", dataNascFundPessoa,"",oCidade,"","","","","","","", null);
        return oOrganizador;
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public String getPermiteLogin() {
        return permiteLogin;
    }

    public void setPermiteLogin(String permiteLogin) {
        this.permiteLogin = permiteLogin;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
