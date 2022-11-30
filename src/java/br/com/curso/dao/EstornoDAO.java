package br.com.curso.dao;

import br.com.curso.model.Estorno;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstornoDAO implements GenericDAO{

private Connection conexao;
    
    public EstornoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Estorno oEstorno = (Estorno) objeto;
        Boolean retorno=false;
        if (oEstorno.getIdEstorno()== 0) {
            retorno = this.inserir(oEstorno);
        }else{
            retorno = this.alterar(oEstorno);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Estorno oEstorno = (Estorno) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into estorno (valorestorno, dataestorno, datapagamento, descricao, situacao) values(?, ?, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, oEstorno.getValorEstorno());
            stmt.setDate(2, new java.sql.Date(oEstorno.getDataEstorno().getTime()));
            stmt.setDate(3, new java.sql.Date(oEstorno.getDataPagamento().getTime()));
            stmt.setString(4, oEstorno.getDescricao());
            stmt.setString(5, "A");
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar a Estorno! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Estorno oEstorno = (Estorno) objeto;
        PreparedStatement stmt = null;
        String sql = "update estorno set valorestorno=?, dataestorno=?, datapagamento=?, descricao=?, situacao=? where idestorno=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, oEstorno.getValorEstorno());
            stmt.setDate(2, new java.sql.Date(oEstorno.getDataEstorno().getTime()));
            stmt.setDate(3, new java.sql.Date(oEstorno.getDataPagamento().getTime()));
            stmt.setString(4, oEstorno.getDescricao());
            stmt.setString(5, oEstorno.getSituacao());
            stmt.setInt(6, oEstorno.getIdEstorno());
            stmt.execute ();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar o Estorno! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idEstorno = numero;
        PreparedStatement stmt = null;
        
        String sql = "update estorno set situacao=? where idestorno=?";
        try{
            Estorno oEstorno = (Estorno) this.carregar(idEstorno);
            stmt = conexao.prepareStatement(sql);
            if(oEstorno.getSituacao().equals("A"))
               stmt.setString(1, "I");
            else stmt.setString(1,"A");
            stmt.setInt(2, idEstorno);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Estorno! Erro " +ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rolback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }  
    }

    @Override
    public Object carregar(int numero) {
        int idEstorno = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estorno oEstorno = null;
        String sql = "select * from estorno where idestorno=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEstorno);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oEstorno = new Estorno(rs.getInt("idestorno"),
                                        rs.getDouble("valorestorno"),
                                        rs.getDate("dataestorno"),
                                        rs.getDate("datapagamento"),
                                        rs.getString("descricao"),
                                        rs.getString("situacao"));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Estorno! Erro:"+ex.getMessage());
        }
        return oEstorno;
    }

    @Override
    public List<Object> listar() {
         List<Object> resultado = new ArrayList<>();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String sql = "Select * from estorno";
         try {
             stmt = conexao.prepareStatement(sql);
             rs=stmt.executeQuery();
             while (rs.next()) {
                
                Estorno oEstorno = new Estorno(rs.getInt("idestorno"),
                                        rs.getDouble("valorestorno"),
                                        rs.getDate("dataestorno"),
                                        rs.getDate("datapagamento"),
                                        rs.getString("descricao"),
                                        rs.getString("situacao"));
            resultado.add(oEstorno);
             }        
     }catch (SQLException ex) {
         System.out.println("Problemas ao listar Estorno! ERRO: " 
                 +ex.getMessage());
     }
         return resultado;
    }
}
