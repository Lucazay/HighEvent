
package br.com.curso.dao;

import br.com.curso.model.Evento;
import br.com.curso.model.Inscricao;
import br.com.curso.model.Participante;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscricaoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public InscricaoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Inscricao oInscricao = (Inscricao) objeto;
        Boolean retorno=false;
        if (oInscricao.getIdInscricao() == 0) {
            retorno = this.inserir(oInscricao);
        }else{
            retorno = this.alterar(oInscricao);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Inscricao oInscricao = (Inscricao) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into inscricao (nropulseira, chaveqrcode, datainscricao, situacao, valorinscricao, nroparcelas, parcelaspagas, parcelaspagar, "
 + "parcelascanceladas, parcelasestorno, idevento, idparticipante) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oInscricao.getNroPulseira());
            stmt.setString(2, oInscricao.getChaveQRCode());
            stmt.setDate(3, new java.sql.Date(oInscricao.getDataInscricao().getTime()));
            stmt.setString(4, "A");
            stmt.setDouble(5, oInscricao.getValorInscricao());
            stmt.setInt(6, oInscricao.getNroParcelas());
            stmt.setDouble(7, oInscricao.getParcelasPagas());
            stmt.setDouble(8, oInscricao.getParcelasPagar());
            stmt.setDouble(9, oInscricao.getParcelasCanceladas());
            stmt.setDouble(10, oInscricao.getParcelasEstorno());
            stmt.setInt(11, oInscricao.getEvento().getIdEvento());
            stmt.setInt(12, oInscricao.getParticipante().getIdParticipante());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao cadastrar inscricao! Erro: " + e.getMessage());
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
        Inscricao oInscricao = (Inscricao) objeto;
        PreparedStatement stmt = null;
        String sql ="update inscricao set nropulseira=?, chaveqrcode=?, datainscricao=?, situacao=?, valorinscricao=?, nroparcelas=?, parcelaspagas=?, parcelaspagar=?, "
 + "parcelascanceladas=?, parcelasestorno=?, idevento=?, idparticipante=? where idinscricao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oInscricao.getNroPulseira());
            stmt.setString(2, oInscricao.getChaveQRCode());
            stmt.setDate(3, new java.sql.Date(oInscricao.getDataInscricao().getTime()));
            stmt.setString(4, oInscricao.getSituacao());
            stmt.setDouble(5, oInscricao.getValorInscricao());
            stmt.setInt(6, oInscricao.getNroParcelas());
            stmt.setDouble(7, oInscricao.getParcelasPagas());
            stmt.setDouble(8, oInscricao.getParcelasPagar());
            stmt.setDouble(9, oInscricao.getParcelasCanceladas());
            stmt.setDouble(10, oInscricao.getParcelasEstorno());
            stmt.setInt(11, oInscricao.getEvento().getIdEvento());
            stmt.setInt(12, oInscricao.getParticipante().getIdParticipante());
            stmt.setInt(12, oInscricao.getIdInscricao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try {
                System.out.println("Problemas ao alterar inscricao! Erro: " + e.getMessage());
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
        int idInscricao = numero;
        PreparedStatement stmt = null;
        
        String sql = "update inscricao set situacao=? where idinscricao=?";
        try{
            Inscricao oInscricao = (Inscricao) this.carregar(idInscricao);
            stmt = conexao.prepareStatement(sql);
            if(oInscricao.getSituacao().equals("A"))
               stmt.setString(1, "I");
            else stmt.setString(1,"A");
            stmt.setInt(2, idInscricao);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a Inscricao! Erro " +ex.getMessage());
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
        int idInscricao = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Inscricao oInscricao = null;
        String sql = "select * from inscricao where idinscricao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idInscricao);
            rs = stmt.executeQuery();

            while(rs.next()){

                Evento oEvento = null;
                try {
                    EventoDAO oEventoDAO = new EventoDAO();
                    int idEvento = rs.getInt("idevento");
                    oEvento = (Evento) oEventoDAO.carregar(idEvento);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar evento! Erro:" + ex.getMessage());
                }

                Participante oParticipante = null;
                try {
                    ParticipanteDAO oParticipanteDAO = new ParticipanteDAO();
                    int idParticipante = rs.getInt("idparticipante");
                    oParticipante = (Participante) oParticipanteDAO.carregar(idParticipante);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar participante! Erro:" + ex.getMessage());
                }

                oInscricao = new Inscricao(rs.getInt("idinscricao"),
                        rs.getString("nroPulseira"),
                        rs.getString("chaveqrcode"),
                        rs.getDate("datainscricao"),
                        rs.getString("situacao"),
                        rs.getDouble("valorinscricao"),
                        rs.getInt("nroparcelas"),
                        rs.getDouble("parcelaspagas"),
                        rs.getDouble("parcelaspagar"),
                        rs.getDouble("parcelascanceladas"),
                        rs.getDouble("parcelasestorno"),
                        oEvento,
                        oParticipante);
            }
        }catch (Exception ex) {
            System.out.println("Problemas ao carregar inscricao! Erro:" + ex.getMessage());
            ex.printStackTrace();
        }
        return oInscricao;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Inscricao oInscricao = null;
        String sql = "select * from inscricao";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){

                Evento oEvento = null;
                try {
                    EventoDAO oEventoDAO = new EventoDAO();
                    int idEvento = rs.getInt("idevento");
                    oEvento = (Evento) oEventoDAO.carregar(idEvento);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar evento! Erro:" + ex.getMessage());
                }

                Participante oParticipante = null;
                try {
                    ParticipanteDAO oParticipanteDAO = new ParticipanteDAO();
                    int idParticipante = rs.getInt("idparticipante");
                    oParticipante = (Participante) oParticipanteDAO.carregar(idParticipante);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar participante! Erro:" + ex.getMessage());
                }

                oInscricao = new Inscricao(rs.getInt("idinscricao"),
                        rs.getString("nroPulseira"),
                        rs.getString("chaveqrcode"),
                        rs.getDate("datainscricao"),
                        rs.getString("situacao"),
                        rs.getDouble("valorinscricao"),
                        rs.getInt("nroparcelas"),
                        rs.getDouble("parcelaspagas"),
                        rs.getDouble("parcelaspagar"),
                        rs.getDouble("parcelascanceladas"),
                        rs.getDouble("parcelasestorno"),
                        oEvento,
                        oParticipante);
            resultado.add(oInscricao);
            }
        }catch (Exception ex) {
            System.out.println("Problemas ao Listar inscricao! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
