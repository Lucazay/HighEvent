
package br.com.curso.dao;

import br.com.curso.model.Cidade;
import br.com.curso.model.Patrocinador;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatrocinadorDAO implements GenericDAO{
    
    private Connection conexao;
    
    public PatrocinadorDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try {
            Patrocinador oPatrocinador = (Patrocinador) objeto;
            if (oPatrocinador.getIdPatrocinador()==0) { //inserção
                //verifica se já existe pessoa com este CPF cadastrada.
                int idPatrocinador = this.verificarCpf(oPatrocinador.getCpfCnpjPessoa());
                if (idPatrocinador==0) {
                    //se não encontrou insere
                    retorno = this.inserir(oPatrocinador);
                }else{
                    //se encontrou Patrocinador com o cpf altera
                    oPatrocinador.setIdPatrocinador(idPatrocinador);
                    retorno = this.alterar(oPatrocinador);
                }
            } else {
              retorno = this.alterar(oPatrocinador);
            }
        } catch (Exception ex){
            System.out.println("Problemas ao incluir Patrocinador! Erro "+ex.getMessage());            
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Patrocinador oPatrocinador = (Patrocinador) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into patrocinador (idPessoa, situacao, permitelogin)"
                + " values (?, ?, ?)";
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            //manda informações para o cadastrar de pessoa.
            int idPessoa = oPessoaDAO.cadastrar(oPatrocinador);
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, "A");
            stmt.setString(3, oPatrocinador.getPermiteLogin());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao cadastrar Patrocinador!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback(); 
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Patrocinador oPatrocinador = (Patrocinador) objeto;
        PreparedStatement stmt = null;
        String sql = "update patrocinador set permitelogin=? where idPatrocinador=?";
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oPatrocinador); //envia para classe PessoaDAO
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPatrocinador.getPermiteLogin());
            stmt.setInt(2, oPatrocinador.getIdPatrocinador());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao alterar Patrocinador!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback(); 
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        PreparedStatement stmt = null;
        try{
            //carrega dados de Patrocinador
            PatrocinadorDAO oPatrocinadorDAO = new PatrocinadorDAO();
            Patrocinador oPatrocinador = (Patrocinador) oPatrocinadorDAO.carregar(numero);
            String situacao="A";//verifica e troca a situação do Patrocinador
            if(oPatrocinador.getSituacao().equals(situacao))
                situacao = "I";
            else situacao = "A";
            
            String sql = "update patrocinador set situacao=? where idPatrocinador=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oPatrocinador.getIdPatrocinador());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas ao excluir Patrocinador!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback(); 
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idPatrocinador = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Patrocinador oPatrocinador = null;
        String sql = "Select * from patrocinador d, pessoa p "
                + "where d.idpessoa = p.idpessoa and d.idpatrocinador=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPatrocinador);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Cidade oCidade = null;
                try{
                   CidadeDAO oCidadeDAO = new CidadeDAO();
                   oCidade = (Cidade) oCidadeDAO.carregar(rs.getInt("idcidade"));
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar cidade! Erro:"+ex.getMessage());
                }
                
                oPatrocinador = new Patrocinador(rs.getInt("idpatrocinador"),
                                        rs.getString("permitelogin"),
                                        rs.getString("situacao"),
                                        rs.getInt("idpessoa"),
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
        }catch(SQLException e){
            System.out.println("Problemas ao carregar Patrocinador!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        return oPatrocinador;
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql= "Select p.*, d.idpatrocinador, d.situacao, d.permitelogin "
                + "from patrocinador d, pessoa p "
                + "where d.idpessoa = p.idpessoa order by idPessoa";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                
                Cidade oCidade = null;
                try{
                   CidadeDAO oCidadeDAO = new CidadeDAO();
                   oCidade = (Cidade) oCidadeDAO.carregar(rs.getInt("idcidade"));
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar cidade!Erro:"+ex.getMessage());
                }
                
                Patrocinador oPatrocinador = new Patrocinador(rs.getInt("idpatrocinador"),
                                        rs.getString("permitelogin"),
                                        rs.getString("situacao"),
                                        rs.getInt("idpessoa"),
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
            resultado.add(oPatrocinador);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Patrocinador! Erro "+ex.getMessage());
        }
        return resultado;
    }
    
    public int verificarCpf(String cpf){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idPatrocinador = 0;
        String sql = "Select d.* from patrocinador d, pessoa p "
                + "where d.idpessoa = p.idPessoa and p.cpfcnpjpessoa=?;";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs=stmt.executeQuery();
            while(rs.next()){
                idPatrocinador = rs.getInt("idpatrocinador");
            }
            return idPatrocinador;
        }catch(SQLException ex){
            System.out.println("Problemas ai carregar pessoa! Erro: "+ex.getMessage());
            return idPatrocinador;
        }
    }
}
