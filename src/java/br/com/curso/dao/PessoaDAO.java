
package br.com.curso.dao;

import br.com.curso.model.Cidade;
import br.com.curso.model.Pessoa;
import br.com.curso.utils.Conversao;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class PessoaDAO {
    
    private Connection conexao;
    
    public PessoaDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
        
    public int cadastrar(Object objeto) throws ParseException {
        Pessoa oPessoa = (Pessoa) objeto;
        int retorno = 0;
        if (oPessoa.getIdPessoa()==0)
        {
            Pessoa objPessoa = this.carregarCpf(oPessoa.getCpfCnpjPessoa());
            if (objPessoa.getIdPessoa()==0)
                retorno = this.inserir(oPessoa);
            else
                retorno = objPessoa.getIdPessoa();
        }
        else {
            retorno = this.alterar(oPessoa);
        }
        return retorno;
    }
    
    public int inserir(Object objeto) {
        Pessoa oPessoa = (Pessoa) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idPessoa = null;
        String sql = "insert into pessoa(nomerazaopessoa, rglepessoa, cpfcnpjpessoa, datanascfundpessoa, telefonepessoa, idcidade, logradouropessoa,"
                + "numlogradouropessoa, bairropessoa, ceppessoa, emailpessoa, login, senha, foto) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?) returning idPessoa";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNomeRazaoPessoa());
            stmt.setString(2, oPessoa.getRglePessoa());
            stmt.setString(3, oPessoa.getCpfCnpjPessoa());
            stmt.setDate(4, new java.sql.Date(oPessoa.getDataNascFundPessoa().getTime()));
            stmt.setString(5, oPessoa.getTelefonePessoa());
            stmt.setInt(6, oPessoa.getCidade().getIdCidade());
            stmt.setString(7, oPessoa.getLogradouroPessoa());
            stmt.setString(8, oPessoa.getNumLogradouroPessoa());
            stmt.setString(9, oPessoa.getBairroPessoa());
            stmt.setString(10, oPessoa.getCepPessoa());
            stmt.setString(11, oPessoa.getEmailPessoa());
            stmt.setString(12, oPessoa.getLogin());
            stmt.setString(13, oPessoa.getSenha());
            stmt.setString(14, oPessoa.getFoto());
            rs = stmt.executeQuery();
            conexao.commit();
            
            while(rs.next()){
                idPessoa = rs.getInt("idPessoa");
            }
        }catch (SQLException e){
            try {
                System.out.println("Problemas ao cadastrar Pessoa!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback();  
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }
    
    public int alterar(Object objeto) {
        Pessoa oPessoa = (Pessoa) objeto;
        PreparedStatement stmt = null;
        Integer idPessoa = oPessoa.getIdPessoa();
        String sql = "update pessoa set nomerazaopessoa=?, rglepessoa=?, datanascfundpessoa=?, telefonepessoa=?, "
                + "idcidade=?, logradouropessoa=?, numlogradouropessoa=?, bairropessoa=?, "
                + "ceppessoa=?, emailpessoa=?, login=?, senha=?, foto=? where idpessoa=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNomeRazaoPessoa());
            stmt.setString(2, oPessoa.getRglePessoa());
            stmt.setDate(3, new java.sql.Date(oPessoa.getDataNascFundPessoa().getTime()));
            stmt.setString(4, oPessoa.getTelefonePessoa());
            stmt.setInt(5, oPessoa.getCidade().getIdCidade());
            stmt.setString(6, oPessoa.getLogradouroPessoa());
            stmt.setString(7, oPessoa.getNumLogradouroPessoa());
            stmt.setString(8, oPessoa.getBairroPessoa());
            stmt.setString(9, oPessoa.getCepPessoa());
            stmt.setString(10, oPessoa.getEmailPessoa());
            stmt.setString(11, oPessoa.getLogin());
            stmt.setString(12, oPessoa.getSenha());
            stmt.setString(13, oPessoa.getFoto());
            stmt.setInt(14, oPessoa.getIdPessoa());
            stmt.execute();
            conexao.commit();
            
        } catch (SQLException e){
            try {
                System.out.println("Problemas ao alterar Pessoa!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback();  
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }
    
    public Pessoa carregar(int id){
        int idPessoa = id;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa oPessoa = null;
        String sql = "Select * from pessoa where idpessoa=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            rs=stmt.executeQuery();            

            while(rs.next()){
                
                Cidade oCidade = null;
                try{
                   CidadeDAO oCidadeDAO = new CidadeDAO();
                   int idCidade = rs.getInt("idcidade");
                   oCidade = (Cidade) oCidadeDAO.carregar(idCidade);
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar usuario! Erro:"+ex.getMessage());
                }
                
                oPessoa = new Pessoa(rs.getInt("idpessoa"),
                                    rs.getString("nomerazaopessoa"),
                                    rs.getString("rglepessoa"),
                                    rs.getString("cpfcnpjpessoa"),
                                    rs.getDate("datanascfundpessoa"),
                                    rs.getString("telefonepessoa"),
                                    oCidade,
                                    rs.getString("logradouropessoa"),
                                    rs.getString("numlogradouropessoa"),
                                    rs.getString("bairropessoa"),
                                    rs.getString("ceppessoa"),
                                    rs.getString("emailpessoa"),
                                    rs.getString("login"),
                                    rs.getString("senha"),
                                    rs.getString("foto"));
            }
            return oPessoa;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar pessoa! Erro "+ex.getMessage());
            return null;
        }   
    }
    
    public Pessoa carregarCpf(String cpf) throws ParseException {
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Pessoa oPessoa = null;
       String sql = "Select * from pessoa where cpfcnpjpessoa=?;";

       try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs=stmt.executeQuery();
            while (rs.next()){
               oPessoa = this.carregar(rs.getInt("idpessoa"));
            }
            if (oPessoa == null)
            {
                Date novaData = Conversao.dataAtual();
                Cidade oCidade = new Cidade();
                oPessoa = new Pessoa(0,"","","",novaData,"",oCidade,"","","","","","","", null);
            }
       }
       catch(SQLException ex){
           System.out.println("Problemas ao carregar pessoa! Erro:"+ex.getMessage());
       }
       return oPessoa;
    }
}
