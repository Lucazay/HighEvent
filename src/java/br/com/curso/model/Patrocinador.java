
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

public class Patrocinador extends Pessoa{
    
    private int idPatrocinador;
    private String permiteLogin;
    private String situacao;

    public Patrocinador(int idPatrocinador, String permiteLogin, String situacao, int idPessoa, String nomeRazaoPessoa, String rglePessoa, 
            String cpfCnpjPessoa, Date dataNascFundPessoa, String telefonePessoa, Cidade cidade, String logradouroPessoa, 
            String numLogradouroPessoa, String bairroPessoa, String cepPessoa, String emailPessoa, String login, String senha, String foto) {
        super(idPessoa, nomeRazaoPessoa, rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, cidade, logradouroPessoa, 
                numLogradouroPessoa, bairroPessoa, cepPessoa, emailPessoa, login, senha, foto);
        this.idPatrocinador = idPatrocinador;
        this.permiteLogin = permiteLogin;
        this.situacao = situacao;
    }
    
    public static Patrocinador patrocinadorVazio() throws ParseException{
        Cidade oCidade = new Cidade();
        Date dataNascFundPessoa = Conversao.dataAtual();
        Patrocinador oPatrocinador = new Patrocinador(0,"S","A",0,"","","", dataNascFundPessoa,"",oCidade,"","","","","","","", null);
        return oPatrocinador;
    }

    public Patrocinador() {
    }

    public int getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(int idPatrocinador) {
        this.idPatrocinador = idPatrocinador;
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
