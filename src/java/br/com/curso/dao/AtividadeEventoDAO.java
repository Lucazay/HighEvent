package br.com.curso.dao;

import br.com.curso.model.AtividadeEvento;
import br.com.curso.model.Evento;
import br.com.curso.model.TipoAtividade;
import static br.com.curso.utils.Conversao.data2String;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtividadeEventoDAO {

    private Connection conexao;

    public AtividadeEventoDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try{
            AtividadeEvento oAtividadeEvento = (AtividadeEvento) objeto;
            if(oAtividadeEvento.getIdAtividadeEvento() == 0){
                retorno = this.inserir(oAtividadeEvento);
            }else{
                retorno = this.alterar(oAtividadeEvento);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao incluir AtividadeEvento! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }
        return retorno;
    }

    public Boolean inserir(Object objeto) {
        AtividadeEvento oAtividadeEvento = (AtividadeEvento) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into atividadeevento (cargahoraria, nomeatividade, resumo, dataatividade, horaatividade, idevento,idtipoatividade) values"+
         "(?,?,?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, oAtividadeEvento.getCargaHoraria());
            stmt.setString(2, oAtividadeEvento.getNomeAtividade());
            stmt.setString(3, oAtividadeEvento.getResumo());
            stmt.setDate(4, new java.sql.Date(oAtividadeEvento.getDataAtividade().getTime()));
            stmt.setString(5, oAtividadeEvento.getHoraAtividade());
            stmt.setInt(6, oAtividadeEvento.getEvento().getIdEvento());
            stmt.setInt(7, oAtividadeEvento.getTipoAtividade().getIdTipoAtividade());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar a Atividade Evento! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:" + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    public Boolean alterar(Object objeto) {
        AtividadeEvento oAtividadeEvento = (AtividadeEvento) objeto;
        PreparedStatement stmt = null;
        String sql = "update atividadeevento set cargahoraria=?, nomeatividade=?, resumo=?, dataatividade=?, horaatividade=?, idevento=?,idtipoatividade=? where idatividadeevento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, oAtividadeEvento.getCargaHoraria());
            stmt.setString(2, oAtividadeEvento.getNomeAtividade());
            stmt.setString(3, oAtividadeEvento.getResumo());
            stmt.setDate(4, new java.sql.Date(oAtividadeEvento.getDataAtividade().getTime()));
            stmt.setString(5, oAtividadeEvento.getHoraAtividade());
            stmt.setInt(6, oAtividadeEvento.getEvento().getIdEvento());
            stmt.setInt(7, oAtividadeEvento.getTipoAtividade().getIdTipoAtividade());
            stmt.setInt(8, oAtividadeEvento.getIdAtividadeEvento());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar a Atividade Evento! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:" + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    public Boolean excluir(int numero) {
        int idAtividadeEvento = numero;
        PreparedStatement stmt = null;

        String sql = "delete from atividadeevento where idatividadeevento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAtividadeEvento);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a Atividade Evento! Erro " + ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rolback " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    public AtividadeEvento carregar(int numero) {
        int idAtividadeEvento = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AtividadeEvento oAtividadeEvento = null;
        String sql = "select * from atividadeevento where idatividadeevento=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAtividadeEvento);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Evento oEvento = null;
                TipoAtividade oTipoAtividade = null;
                try {
                    EventoDAO oEventoDAO = new EventoDAO();
                    int idEvento = rs.getInt("idevento");
                    oEvento = (Evento) oEventoDAO.carregar(idEvento);

                    TipoAtividadeDAO oTipoAtividadeDAO = new TipoAtividadeDAO();
                    int idTipoAtividade = rs.getInt("idtipoatividade");
                    oTipoAtividade = (TipoAtividade) oTipoAtividadeDAO.carregar(idTipoAtividade);

                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Atividade Evento! Erro:" + ex.getMessage());
                }

                oAtividadeEvento = new AtividadeEvento(rs.getInt("idAtividadeEvento"),
                        rs.getDouble("cargaHoraria"),
                        rs.getString("nomeAtividade"),
                        rs.getString("resumo"),
                        rs.getDate("dataAtividade"),
                        rs.getString("horaAtividade"),
                        oEvento,
                        oTipoAtividade);
            }
            return oAtividadeEvento;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Atividade Evento! Erro:" + ex.getMessage());
            return null;
        }
    }

    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from atividadeevento";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AtividadeEvento oAtividadeEvento = new AtividadeEvento();
                oAtividadeEvento.setIdAtividadeEvento(rs.getInt("idatividadeevento"));
                oAtividadeEvento.setCargaHoraria(rs.getDouble("cargahoraria"));
                oAtividadeEvento.setNomeAtividade(rs.getString("nomeatividade"));
                oAtividadeEvento.setResumo(rs.getString("resumo"));
                oAtividadeEvento.setDataAtividade(rs.getDate("dataatividade"));
                oAtividadeEvento.setHoraAtividade(rs.getString("horaatividade"));

                EventoDAO oEventoDAO = null;
                TipoAtividadeDAO oTipoAtividadeDAO = null;

                try {
                    oEventoDAO = new EventoDAO();
                    oTipoAtividadeDAO = new TipoAtividadeDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar evento " + ex.getMessage());
                    ex.printStackTrace();
                }

                oAtividadeEvento.setEvento((Evento) oEventoDAO.carregar(rs.getInt("idevento")));
                
                oAtividadeEvento.setTipoAtividade((TipoAtividade) oTipoAtividadeDAO.carregar(rs.getInt("idtipoatividade")));
                resultado.add(oAtividadeEvento);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar a Atividade Evento! ERRO: "
                    + ex.getMessage());
        }
        return resultado;
    }

}