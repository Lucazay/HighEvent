
package br.com.curso.dao;

import br.com.curso.model.Cidade;
import br.com.curso.model.Participante;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteDAO implements GenericDAO{
    
    private Connection conexao;
    
    public ParticipanteDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try {
            Participante oParticipante = (Participante) objeto;
            if (oParticipante.getIdParticipante()==0) { //inserção
                //verifica se já existe pessoa com este CPF cadastrada.
                int idParticipante = this.verificarCpf(oParticipante.getCpfCnpjPessoa());
                if (idParticipante==0) {
                    //se não encontrou insere
                    retorno = this.inserir(oParticipante);
                }else{
                    //se encontrou Participante com o cpf altera
                    oParticipante.setIdParticipante(idParticipante);
                    retorno = this.alterar(oParticipante);
                }
            } else {
              retorno = this.alterar(oParticipante);
            }
        } catch (Exception ex){
            System.out.println("Problemas ao incluir Participante! Erro "+ex.getMessage());            
        }
        return retorno;    
    }

    @Override
    public Boolean inserir(Object objeto) {
        Participante oParticipante = (Participante) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into participante (idPessoa, sexoparticipante, situacao, permitelogin)"
                + " values (?, ?, ?, ?)";
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            //manda informações para o cadastrar de pessoa.
            int idPessoa = oPessoaDAO.cadastrar(oParticipante);
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, oParticipante.getSexoParticipante());
            stmt.setString(3, "A");
            stmt.setString(4, oParticipante.getPermiteLogin());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao cadastrar Participante! Erro: " + e.getMessage());
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
        Participante oParticipante = (Participante) objeto;
        PreparedStatement stmt = null;
        String sql = "update participante set permitelogin=? where idParticipante=?";
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oParticipante); //envia para classe PessoaDAO
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oParticipante.getPermiteLogin());
            stmt.setInt(2, oParticipante.getIdParticipante());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao alterar Participante! Erro: " + e.getMessage());
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
            //carrega dados de Participante
            ParticipanteDAO oParticipanteDAO = new ParticipanteDAO();
            Participante oParticipante = (Participante) oParticipanteDAO.carregar(numero);
            String situacao="A";//verifica e troca a situação do Participante
            if(oParticipante.getSituacao().equals(situacao))
                situacao = "I";
            else situacao = "A";
            
            String sql = "update participante set situacao=? where idParticipante=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oParticipante.getIdParticipante());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas ao excluir Participante! Erro: " + e.getMessage());
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
        int idParticipante = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Participante oParticipante = null;
        String sql = "Select * from participante t, pessoa p "
                + "where t.idpessoa = p.idpessoa and t.idparticipante=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idParticipante);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Cidade oCidade = null;
                try{
                   CidadeDAO oCidadeDAO = new CidadeDAO();
                   oCidade = (Cidade) oCidadeDAO.carregar(rs.getInt("idcidade"));
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar cidade! Erro:"+ex.getMessage());
                }
                
                oParticipante = new Participante(rs.getInt("idparticipante"),
                                        rs.getString("sexoparticipante"),
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
            System.out.println("Problemas ao carregar Participante! Erro: " + e.getMessage());
            e.printStackTrace();
        }
        return oParticipante;
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql= "Select p.*, t.idparticipante, t.sexoparticipante, t.situacao, t.permitelogin "
                + "from participante t, pessoa p "
                + "where t.idpessoa = p.idpessoa order by idPessoa";
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
                
                Participante oParticipante = new Participante(rs.getInt("idparticipante"),
                                        rs.getString("sexoparticipante"),
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
            resultado.add(oParticipante);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Participante! Erro "+ex.getMessage());
        }
        return resultado;
    }
    
    public int verificarCpf(String cpf){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idParticipante = 0;
        String sql = "Select t.* from participante t, pessoa p "
                + "where t.idpessoa = p.idPessoa and p.cpfcnpjpessoa=?;";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs=stmt.executeQuery();
            while(rs.next()){
                idParticipante = rs.getInt("idparticipante");
            }
            return idParticipante;
        }catch(SQLException ex){
            System.out.println("Problemas ai carregar pessoa! Erro: "+ex.getMessage());
            return idParticipante;
        }
    }
}
