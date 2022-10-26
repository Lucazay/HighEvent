
package br.com.curso.dao;

import br.com.curso.model.Estado;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public EstadoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try{
            Estado oEstado = (Estado) objeto;
            if(oEstado.getIdEstado() == 0){
                retorno = this.inserir(oEstado);
            }else{
                retorno = this.alterar(oEstado);
            }
        } catch (Exception ex){
            System.out.println("Problemas ao incluir estado! Erro "+ex.getMessage());            
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Estado oEstado = (Estado) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into estado (nomeestado, siglaestado, situacao) values (?, ?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEstado.getNomeEstado());
            stmt.setString(2, oEstado.getSiglaEstado());
            stmt.setString(3, "A");
            stmt.execute();
            return true;
        } catch (Exception e){
            
            try{
                System.out.println("Problemas ao cadastrar estado! Erro: "+e.getMessage());
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
        Estado oEstado = (Estado) objeto;
        PreparedStatement stmt = null;
        String sql = "update estado set nomeestado=?, siglaestado=?, situacao=? where idestado=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEstado.getNomeEstado());
            stmt.setString(2, oEstado.getSiglaEstado());
            stmt.setString(3, oEstado.getSituacao());
            stmt.setInt(4, oEstado.getIdEstado());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try{
                System.out.println("Problemas ao alterar estado! Erro: "+e.getMessage());
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
            EstadoDAO oEstadoDAO = new EstadoDAO();
            Estado oEstado = (Estado) oEstadoDAO.carregar(numero);
            String situacao="A";//verifica e troca a situação do administrador
            if(oEstado.getSituacao().equals(situacao))
                situacao = "I";
            else situacao = "A";
            
            String sql = "update estado set situacao=? where idestado=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oEstado.getIdEstado());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas ao excluir estado!Erro: " + e.getMessage());
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
        int idEstado = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estado oEstado = null;
        String sql = "select * from estado where idestado=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEstado);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oEstado = new Estado( rs.getInt("idestado"),
                                        rs.getString("nomeestado"),
                                        rs.getString("siglaestado"),
                                        rs.getString("situacao"));  
            }
        } catch (Exception e){
            System.out.println("Problemas ao carregar estado! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return oEstado;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Estado oEstado = null;
        String sql = "select * from estado;";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oEstado = new Estado( rs.getInt("idestado"),
                                        rs.getString("nomeestado"),
                                        rs.getString("siglaestado"),
                                        rs.getString("situacao"));
                resultado.add(oEstado);
            }
        } catch (Exception e){
            System.out.println("Problemas ao listar estado! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
    
}
