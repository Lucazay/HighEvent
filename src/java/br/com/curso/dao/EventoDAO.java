package br.com.curso.dao;

import br.com.curso.model.Curso;
import br.com.curso.model.Evento;
import static br.com.curso.utils.Conversao.data2String;
import static br.com.curso.utils.Conversao.valorDinheiro;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private Connection conexao;

    public EventoDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    public int cadastrar(Object objeto) throws ParseException {
        Evento oEvento = (Evento) objeto;
        int retorno = 0;
        if (oEvento.getIdEvento() == 0) {
            retorno = this.inserir(oEvento);
        } else {
            retorno = this.alterar(oEvento);
        }
        return retorno;
    }

    public int inserir(Object objeto) {
        Evento oEvento = (Evento) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idEvento = null;
        String sql = "insert into evento (nomeevento, valorevento, valoreventoprazo, datainicioevento, dataterminoevento, informacaoevento,"
                + "situacaoevento, saldocaixa, situacaocaixa, foto, idcurso) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning idEvento";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEvento.getNomeEvento());
            stmt.setDouble(2, oEvento.getValorEvento());
            stmt.setDouble(3, oEvento.getValorEventoPrazo());
            stmt.setDate(4, new java.sql.Date(oEvento.getDataInicioEvento().getTime()));
            stmt.setDate(5, new java.sql.Date(oEvento.getDataTerminoEvento().getTime()));
            stmt.setString(6, oEvento.getInformacaoEvento());
            stmt.setString(7, "A");
            stmt.setDouble(8, oEvento.getSaldoCaixa());
            stmt.setString(9, oEvento.getSituacaoCaixa());
            stmt.setString(10, oEvento.getFoto());
            stmt.setInt(11, oEvento.getCurso().getIdCurso());
            rs = stmt.executeQuery();
            conexao.commit();
            while (rs.next()) {
                idEvento = rs.getInt("idEvento");
            }
        } catch (SQLException e) {
            try {
                System.out.println("Problemas ao cadastrar Evento!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idEvento;
    }

    public int alterar(Object objeto) {
        Evento oEvento = (Evento) objeto;
        PreparedStatement stmt = null;
        Integer idEvento = oEvento.getIdEvento();
        String sql = "update evento set nomeevento=?,valorevento=?,valoreventoprazo=?,datainicioevento=?,dataterminoevento=?,"
                + "informacaoevento=?,situacaoevento=?,saldocaixa=?,situacaocaixa=?,foto=?, idcurso=? where idevento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEvento.getNomeEvento());
            stmt.setDouble(2, oEvento.getValorEvento());
            stmt.setDouble(3, oEvento.getValorEventoPrazo());
            stmt.setDate(4, new java.sql.Date(oEvento.getDataInicioEvento().getTime()));
            stmt.setDate(5, new java.sql.Date(oEvento.getDataTerminoEvento().getTime()));
            stmt.setString(6, oEvento.getInformacaoEvento());
            stmt.setString(7, "A");
            stmt.setDouble(8, oEvento.getSaldoCaixa());
            stmt.setString(9, oEvento.getSituacaoCaixa());
            stmt.setString(10, oEvento.getFoto());
            stmt.setInt(11, oEvento.getCurso().getIdCurso());
            stmt.setInt(12, oEvento.getIdEvento());
            stmt.execute();
            conexao.commit();

        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar Evento! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return idEvento;
    }

    public Boolean excluir(int numero) {
        int idEvento = numero;
        PreparedStatement stmt = null;

        String sql = "update evento set situacaoevento=? where idevento=?";
        try {
            Evento oEvento = (Evento) this.carregar(idEvento);
            stmt = conexao.prepareStatement(sql);
            if (oEvento.getSituacaoEvento().equals("A")) {
                stmt.setString(1, "I");
            } else {
                stmt.setString(1, "A");
            }
            stmt.setInt(2, idEvento);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Evento! Erro " + ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rolback " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    public Evento carregar(int numero) {
        int idEvento = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Evento oEvento = null;
        String sql = "select * from evento where idEvento=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEvento);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Curso oCurso = null;
                try {
                    CursoDAO oCursoDAO = new CursoDAO();
                    int idCurso = rs.getInt("idcurso");
                    oCurso = (Curso) oCursoDAO.carregar(idCurso);
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar curso! Erro:" + ex.getMessage());
                }

                oEvento = new Evento(rs.getInt("idevento"),
                        rs.getString("nomeevento"),
                        rs.getDouble("valorevento"),
                        rs.getDouble("valoreventoprazo"),
                        rs.getDate("datainicioevento"),
                        rs.getDate("dataterminoevento"),
                        rs.getString("informacaoevento"),
                        rs.getString("situacaoevento"),
                        rs.getDouble("saldocaixa"),
                        rs.getString("situacaocaixa"),
                        rs.getString("foto"),
                        oCurso);
            }
            return oEvento;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Evento! Erro:" + ex.getMessage());
            return null;
        }
    }

    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from evento order by nomeevento";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Evento oEvento = new Evento();
                oEvento.setIdEvento(rs.getInt("idevento"));
                oEvento.setNomeEvento(rs.getString("nomeevento"));
                oEvento.setValorEvento(rs.getDouble("valorevento"));
                oEvento.setValorEventoPrazo(rs.getDouble("valoreventoprazo"));
                oEvento.setDataInicioEvento(rs.getDate("datainicioevento"));
                oEvento.setDataTerminoEvento(rs.getDate("dataterminoevento"));
                oEvento.setInformacaoEvento(rs.getString("informacaoevento"));
                oEvento.setSituacaoEvento(rs.getString("situacaoevento"));
                oEvento.setSaldoCaixa(rs.getDouble("saldocaixa"));
                oEvento.setSituacaoCaixa(rs.getString("situacaocaixa"));
                oEvento.setFoto(rs.getString("foto"));

                CursoDAO oCursoDAO = null;
                try {
                    oCursoDAO = new CursoDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar evento " + ex.getMessage());
                    ex.printStackTrace();
                }

                oEvento.setCurso((Curso) oCursoDAO.carregar(rs.getInt("idcurso")));
                resultado.add(oEvento);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Evento! ERRO: "
                    + ex.getMessage());
        }
        return resultado;
    }

    public String listarJSON() {
        String strJson = "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Evento oEvento = null;
        String sql = "select * from evento";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            strJson = "[";
            int i = 0;
            while (rs.next()) {
                if (i > 0) {
                    strJson += ",";
                }
                strJson += "{\"idEvento\":" + rs.getInt("idevento") + ","
                        + "\"nomeEvento\":\"" + rs.getString("nomeevento") + "\","
                        + "\"valorEvento\":\"" + valorDinheiro(rs.getDouble("valorevento"), "BR") + "\","
                        + "\"valorEventoPrazo\":\"" + valorDinheiro(rs.getDouble("valoreventoprazo"), "BR") + "\","
                        + "\"dataInicioEvento\":\"" + data2String(rs.getDate("datainicioevento")) + "\","
                        + "\"dataTerminoEvento\":\"" + data2String(rs.getDate("dataterminoevento")) + "\","
                        + "\"informacaoEvento\":\"" + rs.getString("informacaoevento") + "\","
                        + "\"situacaoEvento\":\"" + rs.getString("situacaoevento") + "\","
                        + "\"saldoCaixa\":\"" + valorDinheiro(rs.getDouble("saldocaixa"), "BR") + "\","
                        + "\"situacaoCaixa\":\"" + rs.getString("situacaocaixa") + "\","
                        + "\"foto\":\"" + rs.getString("foto") + "\","
                        + "{\"idCurso\":" + rs.getInt("idcurso") + "\"}";
                i++;
            }
            strJson += "]";
        } catch (Exception e) {
            System.out.println("Problemas ao Listar Evento! Erro: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(strJson);
        return strJson;

    }
}
