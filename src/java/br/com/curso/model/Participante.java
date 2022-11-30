
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

public class Participante extends Pessoa{
    
    private int idParticipante;
    private String sexoParticipante;
    private String permiteLogin;
    private String situacao;

    public Participante(int idParticipante, String sexoParticipante, String permiteLogin, String situacao, int idPessoa, String nomeRazaoPessoa, 
            String rglePessoa, String cpfCnpjPessoa, Date dataNascFundPessoa, String telefonePessoa, Cidade cidade, String logradouroPessoa, 
            String numLogradouroPessoa, String bairroPessoa, String cepPessoa, String emailPessoa, String login, String senha, String foto) {
        super(idPessoa, nomeRazaoPessoa, rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, cidade, logradouroPessoa, 
                numLogradouroPessoa, bairroPessoa, cepPessoa, emailPessoa, login, senha, foto);
        this.idParticipante = idParticipante;
        this.sexoParticipante = sexoParticipante;
        this.permiteLogin = permiteLogin;
        this.situacao = situacao;
    }
    
    public static Participante participanteVazio() throws ParseException{
        Cidade oCidade = new Cidade();
        Date dataNascFundPessoa = Conversao.dataAtual();
        Participante oParticipante = new Participante(0,"","S","A",0,"","","", dataNascFundPessoa,"",oCidade,"","","","","","","", null);
        return oParticipante;
    }

    public Participante() {
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getSexoParticipante() {
        return sexoParticipante;
    }

    public void setSexoParticipante(String sexoParticipante) {
        this.sexoParticipante = sexoParticipante;
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
