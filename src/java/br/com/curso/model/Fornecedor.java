
package br.com.curso.model;

import br.com.curso.utils.Conversao;
import java.text.ParseException;
import java.util.Date;

public class Fornecedor extends Pessoa{
    
    private int idFornecedor;
    private Tipotrabalho tipoTrabalho;
    private String permiteLogin;
    private String situacao;

    public Fornecedor(int idFornecedor, Tipotrabalho tipoTrabalho, String permiteLogin, String situacao, int idPessoa, String nomeRazaoPessoa, String rglePessoa, 
            String cpfCnpjPessoa, Date dataNascFundPessoa, String telefonePessoa, Cidade cidade, String logradouroPessoa, 
            String numLogradouroPessoa, String bairroPessoa, String cepPessoa, String emailPessoa, String login, String senha, String foto) {
        super(idPessoa, nomeRazaoPessoa, rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, cidade, logradouroPessoa, 
                numLogradouroPessoa, bairroPessoa, cepPessoa, emailPessoa, login, senha, foto);
        this.idFornecedor = idFornecedor;
        this.tipoTrabalho = new Tipotrabalho();
        this.permiteLogin = permiteLogin;
        this.situacao = situacao;
    }
    
    public static Fornecedor fornecedorVazio() throws ParseException{
        Cidade oCidade = new Cidade();
        Tipotrabalho oTipotrabalho = new Tipotrabalho();
        Date dataNascFundPessoa = Conversao.dataAtual();
        Fornecedor oFornecedor = new Fornecedor(0,oTipotrabalho,"S","A",0,"","","", dataNascFundPessoa,"",oCidade,"","","","","","","", null);
        return oFornecedor;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Tipotrabalho getTipoTrabalho() {
        return tipoTrabalho;
    }

    public void setTipoTrabalho(Tipotrabalho tipoTrabalho) {
        this.tipoTrabalho = tipoTrabalho;
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
