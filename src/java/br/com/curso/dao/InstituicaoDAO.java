package br.com.curso.dao;

import br.com.curso.model.Instituicao;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstituicaoDAO implements GenericDAO {
    
    private Connection conexao;
    
    public InstituicaoDAO () throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
                Boolean retorno = false;
        try{
            Instituicao oInstituicao = (Instituicao) objeto;
            if(oInstituicao.getIdInstituicao()== 0){
                retorno = this.inserir(oInstituicao);
            }else{
                retorno = this.alterar(oInstituicao);
            }
        } catch (Exception ex){
            System.out.println("Problemas ao incluir Instituicao! Erro "+ex.getMessage());            
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Instituicao oInstituicao = (Instituicao) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into instituicao (nomeinstituicao, cnpj,situacao) values  (?, ?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oInstituicao.getNomeInstituicao());
            stmt.setString(2, oInstituicao.getCnpj());
            stmt.setString(3, "A");
            stmt.execute();
            return true;
        } catch (Exception e){
            
            try{
                System.out.println("Problemas ao cadastrar instituicao! Erro: "+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex){
                System.out.println("Problemas ao executar rollback"+ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Instituicao oInstituicao = (Instituicao) objeto;
        PreparedStatement stmt = null;
        String sql = "update instituicao set nomeinstituicao=?, cnpj=?, situacao=? where idinstituicao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oInstituicao.getNomeInstituicao());
            stmt.setString(2, oInstituicao.getCnpj());
            stmt.setString(3, oInstituicao.getSituacao());
            stmt.setInt(4, oInstituicao.getIdInstituicao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try{
                System.out.println("Problemas ao alterar instituicao! Erro: "+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            }catch(SQLException ex){
                System.out.println("Problemas ao executar rollback"+ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        PreparedStatement stmt = null;
        try{
            //carrega dados de administrador
            InstituicaoDAO oInstituicaoDAO = new InstituicaoDAO();
            Instituicao oInstituicao = (Instituicao) oInstituicaoDAO.carregar(numero);
            String situacao="A";//verifica e troca a situação do administrador
            if(oInstituicao.getSituacao().equals(situacao))
                situacao = "I";
            else situacao = "A";
            
            String sql = "update instituicao set situacao=? where idinstituicao=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oInstituicao.getIdInstituicao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas ao excluir instituicao!Erro: " + e.getMessage());
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
        int idInstituicao = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Instituicao oInstituicao = null;
        String sql = "select * from instituicao where idinstituicao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idInstituicao);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oInstituicao = new Instituicao( rs.getInt("idinstituicao"),
                                        rs.getString("nomeinstituicao"),
                                        rs.getString("cnpj"),
                                        rs.getString("situacao"));  
            }
        } catch (Exception e){
            System.out.println("Problemas ao carregar instituicao! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return oInstituicao;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Instituicao oInstituicao = null;
        String sql = "select * from instituicao;";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oInstituicao = new Instituicao( rs.getInt("idinstituicao"),
                                        rs.getString("nomeinstituicao"),
                                        rs.getString("cnpj"),
                                        rs.getString("situacao"));
                resultado.add(oInstituicao);
            }
        } catch (Exception e){
            System.out.println("Problemas ao listar instituicao! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
}
    

