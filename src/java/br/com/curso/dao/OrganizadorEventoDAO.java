package br.com.curso.dao;

import br.com.curso.model.Evento;
import br.com.curso.model.Funcao;
import br.com.curso.model.Organizador;
import br.com.curso.model.OrganizadorEvento;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OrganizadorEventoDAO {

    private Connection conexao;

    public OrganizadorEventoDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    public int cadastrar(Object objeto) throws ParseException {
        OrganizadorEvento oOrganizadorEvento = (OrganizadorEvento) objeto;
        int retorno = 0;
        if (oOrganizadorEvento.getIdOrganizadorEvento() == 0) {
            retorno = this.inserir(oOrganizadorEvento);
        } else {
            retorno = this.alterar(oOrganizadorEvento);
        }
        return retorno;
    }

    public int inserir(Object objeto) {
        OrganizadorEvento oOrganizadorEvento = (OrganizadorEvento) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idOrganizadorEvento = null;
        String sql = "insert into organizadorevento(idorganizador, idevento, idfuncao) values (?, ?, ?) returning idOrganizadorEvento";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oOrganizadorEvento.getOrganizador().getIdOrganizador());
            stmt.setInt(2, oOrganizadorEvento.getEvento().getIdEvento());
            stmt.setInt(3, oOrganizadorEvento.getFuncao().getIdFuncao());
            rs = stmt.executeQuery();
            conexao.commit();

            while (rs.next()) {
                idOrganizadorEvento = rs.getInt("idOrganizadorEvento");
            }
        } catch (SQLException e) {
            try {
                System.out.println("Problemas ao cadastrar OrganizadorEvento!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idOrganizadorEvento;
    }

    public int alterar(Object objeto) {
        OrganizadorEvento oOrganizadorEvento = (OrganizadorEvento) objeto;
        PreparedStatement stmt = null;
        Integer idOrganizadorEvento = oOrganizadorEvento.getIdOrganizadorEvento();
        String sql = "update organizadorevento set idorganizador=?, idevento=?, idfuncao=? where idorganizadorevento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oOrganizadorEvento.getOrganizador().getIdOrganizador());
            stmt.setInt(2, oOrganizadorEvento.getEvento().getIdEvento());
            stmt.setInt(3, oOrganizadorEvento.getFuncao().getIdFuncao());
            stmt.setInt(4, oOrganizadorEvento.getIdOrganizadorEvento());
            stmt.execute();
            conexao.commit();

        } catch (SQLException e) {
            try {
                System.out.println("Problemas ao alterar OrganizadorEvento!Erro: " + e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex) {
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idOrganizadorEvento;
    }

    public Boolean excluir(int numero) {
        int idOrganizadorEvento = numero;
        PreparedStatement stmt = null;

        String sql = "delete from organizadorevento where idorganizadorevento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idOrganizadorEvento);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a organizadorevento! Erro " + ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rolback " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    public OrganizadorEvento carregar(int id) {
        int idOrganizadorEvento = id;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrganizadorEvento oOrganizadorEvento = null;
        String sql = "Select * from organizadorevento where idorganizadorevento=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idOrganizadorEvento);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Organizador oOrganizador = null;
                try {
                    OrganizadorDAO oOrganizadorDAO = new OrganizadorDAO();
                    oOrganizador = (Organizador) oOrganizadorDAO.carregar(rs.getInt("idorganizador"));
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Organizador! Erro:" + ex.getMessage());
                }

                Evento oEvento = null;
                try {
                    EventoDAO oEventoDAO = new EventoDAO();
                    oEvento = (Evento) oEventoDAO.carregar(rs.getInt("idEvento"));
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Evento! Erro:" + ex.getMessage());
                }

                Funcao oFuncao = null;
                try {
                    FuncaoDAO oFuncaoDAO = new FuncaoDAO();
                    oFuncao = (Funcao) oFuncaoDAO.carregar(rs.getInt("idFuncao"));
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Funcao! Erro:" + ex.getMessage());
                }

                oOrganizadorEvento = new OrganizadorEvento(rs.getInt("idorganizadorevento"),
                        oOrganizador,
                        oEvento,
                        oFuncao);
            }
            return oOrganizadorEvento;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar OrganizadorEvento! Erro " + ex.getMessage());
            return null;
        }
    }

    public List<Object> listar(int idEvento) {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        sql += "Select * from organizadorevento ";

        if (idEvento > 0) {
            sql += " where idevento = ?";
        }

        try {
            stmt = conexao.prepareStatement(sql);

            if (idEvento > 0) {
                stmt.setInt(1, idEvento);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {

                Organizador oOrganizador = null;
                try {
                    OrganizadorDAO oOrganizadorDAO = new OrganizadorDAO();
                    oOrganizador = (Organizador) oOrganizadorDAO.carregar(rs.getInt("idorganizador"));
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Organizador! Erro:" + ex.getMessage());
                }

                Evento oEvento = null;
                try {
                    EventoDAO oEventoDAO = new EventoDAO();
                    oEvento = (Evento) oEventoDAO.carregar(rs.getInt("idEvento"));
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Evento! Erro:" + ex.getMessage());
                }

                Funcao oFuncao = null;
                try {
                    FuncaoDAO oFuncaoDAO = new FuncaoDAO();
                    oFuncao = (Funcao) oFuncaoDAO.carregar(rs.getInt("idFuncao"));
                } catch (Exception ex) {
                    System.out.println("Problemas ao carregar Funcao! Erro:" + ex.getMessage());
                }

                OrganizadorEvento oOrganizadorEvento = new OrganizadorEvento(rs.getInt("idorganizadorevento"),
                        oOrganizador,
                        oEvento,
                        oFuncao);

                resultado.add(oOrganizadorEvento);

            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar! ERRO: " + ex.getMessage());
        }
        return resultado;
    }
}
