
package br.com.curso.dao;

import br.com.curso.model.Doacao;
import br.com.curso.model.Patrocinador;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO implements GenericDAO{
        
    private Connection conexao;
    
    public DoacaoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Doacao oDoacao = (Doacao) objeto;
        Boolean retorno=false;
        if (oDoacao.getIdDoacao()== 0) {
            retorno = this.inserir(oDoacao);
        }else{
            retorno = this.alterar(oDoacao);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Doacao oDoacao = (Doacao) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into doacao(idpatrocinador, valordoacao, datadoacao, descricao, situacao) values (?, ?, ?, ?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oDoacao.getPatrocinador().getIdPessoa());
            stmt.setDouble(2, oDoacao.getValorDoacao());
            stmt.setDate(3, new java.sql.Date(oDoacao.getDataDoacao().getTime()));
            stmt.setString(4, oDoacao.getDescricao());
            stmt.setString(5, "A");
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
        Doacao oDoacao = (Doacao) objeto;
        PreparedStatement stmt = null;
        String sql = "update doacao set idpatrocinador=?,valordoacao=?,datadoacao=?, descricao=?, situacao=? where iddoacao=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oDoacao.getPatrocinador().getIdPessoa());
            stmt.setDouble(2, oDoacao.getValorDoacao());
            stmt.setDate(3, new java.sql.Date(oDoacao.getDataDoacao().getTime()));
            stmt.setString(4, oDoacao.getDescricao());
            stmt.setString(5, oDoacao.getSituacao());
            stmt.setInt(6, oDoacao.getIdDoacao());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar a Doacao! Erro: "+ex.getMessage());
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
        int idDoacao = numero;
        PreparedStatement stmt = null;
        
        String sql = "update doacao set situacao=? where iddoacao=?";
        try{
            Doacao oDoacao = (Doacao) this.carregar(idDoacao);
            stmt = conexao.prepareStatement(sql);
            if(oDoacao.getSituacao().equals("A"))
               stmt.setString(1, "I");
            else stmt.setString(1,"A");
            stmt.setInt(2, idDoacao);
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
        int idDoacao = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Doacao oDoacao = null;
        String sql = "select * from doacao where idDoacao=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDoacao);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oDoacao = new Doacao();
                oDoacao.setIdDoacao(rs.getInt("idDoacao"));
                oDoacao.setValorDoacao(rs.getDouble("valordoacao"));
                oDoacao.setDataDoacao(rs.getDate("datadoacao"));
                oDoacao.setDescricao(rs.getString("descricao"));
                oDoacao.setSituacao(rs.getString("situacao"));   
                
                PatrocinadorDAO oPatrocinadorDAO = new PatrocinadorDAO();
                oDoacao.setPatrocinador((Patrocinador) oPatrocinadorDAO.carregar(rs.getInt("idpatrocinador")));
            }
            return oDoacao;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Doacao! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
         List<Object> resultado = new ArrayList<>();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String sql = "Select * from doacao";
         try {
             stmt = conexao.prepareStatement(sql);
             rs=stmt.executeQuery();
             while (rs.next()) {
                Patrocinador oPatrocinador = null;
                try{
                   PatrocinadorDAO oPatrocinadorDAO = new PatrocinadorDAO();
                   oPatrocinador = (Patrocinador) oPatrocinadorDAO.carregar(rs.getInt("idpatrocinador"));
                }catch(Exception ex){
                   System.out.println("Problemas ao carregar Patrocinador!Erro:"+ex.getMessage());
                }

                 Doacao oDoacao = new Doacao(rs.getInt("idDoacao"),
                                                oPatrocinador,
                                                rs.getDouble("valordoacao"),
                                                rs.getDate("datadoacao"),
                                                rs.getString("descricao"),
                                                rs.getString("situacao"));
                 resultado.add(oDoacao);
             }        
     }catch (SQLException ex) {
         System.out.println("Problemas ao listar Doação! ERRO: " 
                 +ex.getMessage());
     }
         return resultado;
    }
}
