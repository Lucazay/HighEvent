/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.model.Parcelamento;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParcelamentoDAO implements GenericDAO{

    private Connection conexao;
    
    public ParcelamentoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    
    @Override
    public Boolean cadastrar(Object objeto) {
        Parcelamento oParcelamento = (Parcelamento) objeto;
        Boolean retorno=false;
        if (oParcelamento.getIdParcelamento()== 0) {
            retorno = this.inserir(oParcelamento);
        }else{
            retorno = this.alterar(oParcelamento);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Parcelamento oParcelamento = (Parcelamento) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into parcelamento(nroparcela, valorparcela, datavencimento, datapagamento, situacao, descricao) values (?, ?, ?, ?, ?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oParcelamento.getNroParcela());
            stmt.setDouble(2, oParcelamento.getValorParcela());
            stmt.setDate(3, new java.sql.Date(oParcelamento.getDataVencimento().getTime()));
            stmt.setDate(4, new java.sql.Date(oParcelamento.getDataPagamento().getTime()));
            stmt.setString(5, "A");
            stmt.setString(6, oParcelamento.getDescricao());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar a Doacao! Erro: "+ex.getMessage());
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
        Parcelamento oParcelamento = (Parcelamento) objeto;
        PreparedStatement stmt = null;
        String sql = "update parcelamento set nroparcela=?,valorparcela=?,datavencimento=?, datapagamento=?, situacao=?, descricao=? where idparcelamento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oParcelamento.getNroParcela());
            stmt.setDouble(2, oParcelamento.getValorParcela());
            stmt.setDate(3, new java.sql.Date(oParcelamento.getDataVencimento().getTime()));
            stmt.setDate(4, new java.sql.Date(oParcelamento.getDataPagamento().getTime()));
            stmt.setString(5, "A");
            stmt.setString(6, oParcelamento.getDescricao());
            stmt.setInt(7, oParcelamento.getIdParcelamento());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar a Doacao! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }    }

    @Override
    public Boolean excluir(int numero) {
        int idParcelamento = numero;
        PreparedStatement stmt = null;
        
        String sql = "update parcelamento set situacao=? where idparcelamento=?";
        try{
            Parcelamento oParcelamento = (Parcelamento) this.carregar(idParcelamento);
            stmt = conexao.prepareStatement(sql);
            if(oParcelamento.getSituacao().equals("A"))
               stmt.setString(1, "I");
            else stmt.setString(1,"A");
            stmt.setInt(2, idParcelamento);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a Doacao! Erro " +ex.getMessage());
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
        int idParcelamento = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Parcelamento oParcelamento = null;
        String sql = "select * from parcelamento where idParcelamento=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idParcelamento);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oParcelamento = new Parcelamento();
                oParcelamento.setIdParcelamento(rs.getInt("idParcelamento"));
                oParcelamento.setNroParcela(rs.getInt("nroparcela"));
                oParcelamento.setValorParcela(rs.getDouble("valorparcela"));
                oParcelamento.setDataVencimento(rs.getDate("datavencimento"));
                oParcelamento.setDataPagamento(rs.getDate("datapagamento"));
                oParcelamento.setSituacao(rs.getString("situacao"));
                oParcelamento.setDescricao(rs.getString("descricao"));
            }
            return oParcelamento;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Doacao! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
      public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Parcelamento oParcelamento = null;
        String sql = "select * from parcelamento;";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oParcelamento = new Parcelamento();
                oParcelamento.setIdParcelamento(rs.getInt("idParcelamento"));
                oParcelamento.setNroParcela(rs.getInt("nroparcela"));
                oParcelamento.setValorParcela(rs.getDouble("valorparcela"));
                oParcelamento.setDataVencimento(rs.getDate("datavencimento"));
                oParcelamento.setDataPagamento(rs.getDate("datapagamento"));
                oParcelamento.setSituacao(rs.getString("situacao"));
                oParcelamento.setDescricao(rs.getString("descricao"));
                resultado.add(oParcelamento);
            }
        } catch (Exception e){
            System.out.println("Problemas ao listar estado! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }}
