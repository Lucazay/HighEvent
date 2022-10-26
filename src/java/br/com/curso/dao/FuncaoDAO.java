
package br.com.curso.dao;

import br.com.curso.model.Funcao;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncaoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public FuncaoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try{
            Funcao oFuncao = (Funcao) objeto;
            if(oFuncao.getIdFuncao() == 0){
                retorno = this.inserir(oFuncao);
            }else{
                retorno = this.alterar(oFuncao);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao incluir funcao! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Funcao oFuncao = (Funcao) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into funcao (descricao) values (?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncao.getDescricao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao cadastrar funcao! Erro: " + e.getMessage());
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
        Funcao oFuncao = (Funcao) objeto;
        PreparedStatement stmt = null;
        String sql = "update funcao set descricao=? where idfuncao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncao.getDescricao());
            stmt.setInt(2, oFuncao.getIdFuncao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao alterar funcao! Erro: " + e.getMessage());
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
        int idFuncao = numero;
        PreparedStatement stmt = null;
        String sql = "delete from funcao where idfuncao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncao);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex) {
            try {
                System.out.println("Problemas ao excluir funcao! Erro " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Problemas ao executar rollback" + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idFuncao = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcao oFuncao = null;
        String sql = "select * from funcao where idfuncao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncao);
            rs = stmt.executeQuery();
            while(rs.next()){
                oFuncao = new Funcao();
                oFuncao.setIdFuncao(rs.getInt("idfuncao"));
                oFuncao.setDescricao(rs.getString("descricao"));
            }
        }catch (Exception ex) {
            System.out.println("Problemas ao carregar funcao! Erro:" + ex.getMessage());
            ex.printStackTrace();
        }
        return oFuncao;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Funcao oFuncao = null;
        String sql = "select * from funcao";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                oFuncao = new Funcao();
                oFuncao.setIdFuncao(rs.getInt("idfuncao"));
                oFuncao.setDescricao(rs.getString("descricao"));
                resultado.add(oFuncao);
            }
        }catch (Exception ex) {
            System.out.println("Problemas ao Listar funcao!Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
