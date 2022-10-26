
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

public class Administrador extends Pessoa{
    
    private int idAdministrador;
    private String permiteLogin;
    private String situacao;

    public Administrador(int idAdministrador, String permiteLogin, String situacao, int idPessoa, String nomeRazaoPessoa, String rglePessoa, 
            String cpfCnpjPessoa, Date dataNascFundPessoa, String telefonePessoa, Cidade cidade, String logradouroPessoa, String numLogradouroPessoa, 
            String bairroPessoa, String cepPessoa, String emailPessoa, String login, String senha, String foto) {
        super(idPessoa, nomeRazaoPessoa, rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, cidade, logradouroPessoa, 
                numLogradouroPessoa, bairroPessoa, cepPessoa, emailPessoa, login, senha, foto);
        this.idAdministrador = idAdministrador;
        this.permiteLogin = permiteLogin;
        this.situacao = situacao;
    }
    
    public static Administrador administradorVazio() throws ParseException{
        Cidade oCidade = new Cidade();
        Date dataNascFundPessoa = Conversao.dataAtual();
        Administrador oAdministrador = new Administrador(0,"S","A",0,"","","", dataNascFundPessoa,"",oCidade,"","","","","","","", null);
        return oAdministrador;
    }


    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
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
