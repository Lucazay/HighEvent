
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.util.Date;

public class Pessoa {
    
    private int idPessoa;
    private String nomeRazaoPessoa;
    private String rglePessoa;
    private String cpfCnpjPessoa;
    private Date dataNascFundPessoa;
    private String telefonePessoa;
    private Cidade cidade;
    private String logradouroPessoa;
    private String numLogradouroPessoa;
    private String bairroPessoa;
    private String cepPessoa;
    private String emailPessoa;
    private String login;
    private String senha;
    private String foto;

    public Pessoa() {
        this.idPessoa = 0;
        this.nomeRazaoPessoa = "";
        this.rglePessoa = "";
        this.cpfCnpjPessoa = "";
        this.dataNascFundPessoa = Conversao.dataAtual();
        this.telefonePessoa = "";
        this.cidade = new Cidade();
        this.logradouroPessoa = "";
        this.numLogradouroPessoa = "";
        this.bairroPessoa = "";
        this.cepPessoa = "";
        this.emailPessoa = "";
        this.login = "";
        this.senha = "";
        this.foto = "";

    }

    public Pessoa(int idPessoa, String nomeRazaoPessoa, String rglePessoa, String cpfCnpjPessoa, Date dataNascFundPessoa, 
            String telefonePessoa, Cidade cidade, String logradouroPessoa, String numLogradouroPessoa, String bairroPessoa, String cepPessoa, 
            String emailPessoa, String login, String senha, String foto) {
        this.idPessoa = idPessoa;
        this.nomeRazaoPessoa = nomeRazaoPessoa;
        this.rglePessoa = rglePessoa;
        this.cpfCnpjPessoa = cpfCnpjPessoa;
        this.dataNascFundPessoa = dataNascFundPessoa;
        this.telefonePessoa = telefonePessoa;
        this.cidade = cidade;
        this.logradouroPessoa = logradouroPessoa;
        this.numLogradouroPessoa = numLogradouroPessoa;
        this.bairroPessoa = bairroPessoa;
        this.cepPessoa = cepPessoa;
        this.emailPessoa = emailPessoa;
        this.login = login;
        this.senha = senha;
        this.foto = foto;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeRazaoPessoa() {
        return nomeRazaoPessoa;
    }

    public void setNomeRazaoPessoa(String nomeRazaoPessoa) {
        this.nomeRazaoPessoa = nomeRazaoPessoa;
    }

    public String getRglePessoa() {
        return rglePessoa;
    }

    public void setRglePessoa(String rglePessoa) {
        this.rglePessoa = rglePessoa;
    }

    public String getCpfCnpjPessoa() {
        return cpfCnpjPessoa;
    }

    public void setCpfCnpjPessoa(String cpfCnpjPessoa) {
        this.cpfCnpjPessoa = cpfCnpjPessoa;
    }

    public Date getDataNascFundPessoa() {
        return dataNascFundPessoa;
    }

    public void setDataNascFundPessoa(Date dataNascFundPessoa) {
        this.dataNascFundPessoa = dataNascFundPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getLogradouroPessoa() {
        return logradouroPessoa;
    }

    public void setLogradouroPessoa(String logradouroPessoa) {
        this.logradouroPessoa = logradouroPessoa;
    }

    public String getNumLogradouroPessoa() {
        return numLogradouroPessoa;
    }

    public void setNumLogradouroPessoa(String numLogradouroPessoa) {
        this.numLogradouroPessoa = numLogradouroPessoa;
    }

    public String getBairroPessoa() {
        return bairroPessoa;
    }

    public void setBairroPessoa(String bairroPessoa) {
        this.bairroPessoa = bairroPessoa;
    }

    public String getCepPessoa() {
        return cepPessoa;
    }

    public void setCepPessoa(String cepPessoa) {
        this.cepPessoa = cepPessoa;
    }

    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
