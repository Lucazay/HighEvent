package br.com.curso.dao;

import br.com.curso.model.Despesa;
import br.com.curso.model.Fornecedor;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO implements GenericDAO {

    private Connection conexao;

    public DespesaDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Despesa oDespesa = (Despesa) objeto;
        Boolean retorno=false;
        if (oDespesa.getIdDespesa() == 0) {
            retorno = this.inserir(oDespesa);
        }else{
            retorno = this.alterar(oDespesa);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Despesa oDespesa = (Despesa) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into despesa (idfornecedor, valordespesa, vencimentodespesa, pagamentodespesa, descricao, situacao) values (?, ?, ?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oDespesa.getFornecedor().getIdFornecedor());
            stmt.setDouble(2, oDespesa.getValorDespesa());
            stmt.setDate(3, new java.sql.Date(oDespesa.getVencimentoDespesa().getTime()));
            stmt.setDate(4, new java.sql.Date(oDespesa.getPagamentoDespesa().getTime()));
            stmt.setString(5, oDespesa.getDescricao());
            stmt.setString(6, "A");
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar Despesa! Erro: " + ex.getMessage());
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
    public Boolean alterar(Object objeto) {
        Despesa oDespesa = (Despesa) objeto;
        PreparedStatement stmt = null;
        String sql = "update despesa set idfornecedor=?, valordespesa=?, vencimentodespesa=?, pagamentodespesa=?, descricao=?, situacao=? where iddespesa=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oDespesa.getFornecedor().getIdFornecedor());
            stmt.setDouble(2, oDespesa.getValorDespesa());
            stmt.setDate(3, new java.sql.Date(oDespesa.getVencimentoDespesa().getTime()));
            stmt.setDate(4, new java.sql.Date(oDespesa.getPagamentoDespesa().getTime()));
            stmt.setString(5, oDespesa.getDescricao());
            stmt.setString(6, oDespesa.getSituacao());
            stmt.setInt(7, oDespesa.getIdDespesa());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar o Despesa! Erro: "+ex.getMessage());
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
        int idDespesa = numero;
        PreparedStatement stmt = null;

        String sql = "update despesa set situacao=? where iddespesa=?";
        try{
            Despesa oDespesa = (Despesa) this.carregar(idDespesa);
            stmt = conexao.prepareStatement(sql);
            if(oDespesa.getSituacao().equals("A"))
               stmt.setString(1, "I");
            else stmt.setString(1,"A");
            stmt.setInt(2, idDespesa);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Despesa! Erro " +ex.getMessage());
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
        int idDespesa = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Despesa oDespesa = null;
        String sql = "select * from despesa where iddespesa=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDespesa);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor oFornecedor = null;
                try {
                    FornecedorDAO oFornecedorDAO = new FornecedorDAO();
                    int idFornecedor = rs.getInt("idfornecedor");
                    oFornecedor = (Fornecedor) oFornecedorDAO.carregar(idFornecedor);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Fornecedor! Erro:" + ex.getMessage());
                }

                oDespesa = new Despesa(rs.getInt("iddespesa"),
                        oFornecedor,
                        rs.getDouble("valordespesa"),
                        rs.getDate("vencimentodespesa"),
                        rs.getDate("pagamentodespesa"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
            }
        } catch(SQLException e){
            System.out.println("Problemas ao carregar Despesa! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return oDespesa;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Despesa oDespesa = null;
        String sql = "select * from despesa";

        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor oFornecedor = null;
                try {
                    FornecedorDAO oFornecedorDAO = new FornecedorDAO();
                    int idFornecedor = rs.getInt("idfornecedor");
                    oFornecedor = (Fornecedor) oFornecedorDAO.carregar(idFornecedor);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Fornecedor! Erro:" + ex.getMessage());
                }

                oDespesa = new Despesa(rs.getInt("iddespesa"),
                        oFornecedor,
                        rs.getDouble("valordespesa"),
                        rs.getDate("vencimentodespesa"),
                        rs.getDate("pagamentodespesa"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));

                resultado.add(oDespesa);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao Listar despesa!Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
}
