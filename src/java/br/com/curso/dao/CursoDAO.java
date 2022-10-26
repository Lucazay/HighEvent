
package br.com.curso.dao;

import br.com.curso.model.Instituicao;
import br.com.curso.model.Curso;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public CursoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Curso oCurso = (Curso) objeto;
        Boolean retorno=false;
        if (oCurso.getIdCurso()== 0) {
            retorno = this.inserir(oCurso);
        }else{
            retorno = this.alterar(oCurso);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Curso oCurso = (Curso) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into curso (nomecurso,idinstituicao,situacao) values (?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCurso.getNomeCurso());
            stmt.setInt(2, oCurso.getInstituicao().getIdInstituicao());
            stmt.setString(3, "A");
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar o Curso! Erro: "+ex.getMessage());
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
        Curso oCurso = (Curso) objeto;
        PreparedStatement stmt = null;
        String sql = "update curso set nomecurso=?,idinstituicao=?,situacao=? where idcurso=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCurso.getNomeCurso());
            stmt.setInt(2, oCurso.getInstituicao().getIdInstituicao());
            stmt.setString(3, oCurso.getSituacao());
            stmt.setInt(4, oCurso.getIdCurso());
            stmt.execute ();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar o Curso! Erro: "+ex.getMessage());
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
        int idCurso = numero;
        PreparedStatement stmt = null;
        
        String sql = "update curso set situacao=? where idcurso=?";
        try{
            Curso oCurso = (Curso) this.carregar(idCurso);
            stmt = conexao.prepareStatement(sql);
            if(oCurso.getSituacao().equals("A"))
               stmt.setString(1, "I");
            else stmt.setString(1,"A");
            stmt.setInt(2, idCurso);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Curso! Erro " +ex.getMessage());
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
        int idCurso = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Curso oCurso = null;
        String sql = "select * from curso where idcurso=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCurso);
            rs=stmt.executeQuery();
            while (rs.next()) {
                
                Instituicao oInstituicao = null;
                try{
                    InstituicaoDAO oInstituicaoDAO = new InstituicaoDAO();
                    oInstituicao = (Instituicao) oInstituicaoDAO.carregar(rs.getInt("idinstituicao"));
                }catch(Exception ex){
                    System.out.println("Problemas ao carregar Instituicao! Erro: "+ex.getMessage());
                }
                
                oCurso = new Curso(rs.getInt("idcurso"),
                                        rs.getString("nomecurso"),
                                        rs.getString("situacao"),
                                        oInstituicao);

            }
        }catch(SQLException e){
            System.out.println("Problemas ao carregar Curso! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return oCurso;
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String sql = "Select * from curso order by nomecurso";
         try {
             stmt = conexao.prepareStatement(sql);
             rs=stmt.executeQuery();
             while (rs.next()) {
                 Curso oCurso = new Curso();
                 oCurso.setIdCurso(rs.getInt("idCurso"));
                 oCurso.setNomeCurso(rs.getString("nomecurso"));
                 oCurso.setSituacao(rs.getString("situacao"));
                 
                 InstituicaoDAO oInstituicaoDAO = null;
                 try {
                     oInstituicaoDAO = new InstituicaoDAO();
                 } catch (Exception ex) {
                     System.out.println("Erro buscar Instituicao"+ex.getMessage());
                     ex.printStackTrace();
                 }
                 oCurso.setInstituicao((Instituicao) oInstituicaoDAO.carregar(rs.getInt("idinstituicao")));
                 resultado.add(oCurso);
             }        
     }catch (SQLException ex) {
         System.out.println("Problemas ao listar Curso! ERRO: " 
                 +ex.getMessage());
     }
         return resultado;
    
    }
    
}
