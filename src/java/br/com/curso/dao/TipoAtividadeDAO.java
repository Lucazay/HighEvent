package br.com.curso.dao;

import br.com.curso.model.TipoAtividade;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoAtividadeDAO implements GenericDAO {
    
    private Connection conexao;
    
    public TipoAtividadeDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try{
            TipoAtividade oTipoatividade = (TipoAtividade) objeto;
            if(oTipoatividade.getIdTipoAtividade()== 0){
                retorno = this.inserir(oTipoatividade);
            }else{
                retorno = this.alterar(oTipoatividade);
            }
        } catch (Exception ex){
            System.out.println("Problemas ao incluir Tipo Atividade! Erro "+ex.getMessage());            
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        TipoAtividade oTipoatividade = (TipoAtividade) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into tipoatividade (descricao,  situacao) values (?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoatividade.getDescricao());
            stmt.setString(2, "A");
            stmt.execute();
            return true;
        } catch (Exception e){
            
            try{
                System.out.println("Problemas ao cadastrar o Tipo Atividade! Erro: "+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            } catch (SQLException ex){
                System.out.println("Problemas ao executar rollback"+ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        TipoAtividade oTipoatividade = (TipoAtividade) objeto;
        PreparedStatement stmt = null;
        String sql = "update tipoatividade set descricao=?, situacao=? where idtipoatividade=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoatividade.getDescricao());
            stmt.setString(2, oTipoatividade.getSituacao());
            stmt.setInt(3, oTipoatividade.getIdTipoAtividade());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try{
                System.out.println("Problemas ao alterar o Tipo Atividade! Erro: "+e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            }catch(SQLException ex){
                System.out.println("Problemas ao executar rollback"+ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        PreparedStatement stmt = null;
        try{
            //carrega dados de administrador
            TipoAtividadeDAO oTipoatividadeDAO = new TipoAtividadeDAO();
            TipoAtividade oTipoatividade = (TipoAtividade) oTipoatividadeDAO.carregar(numero);
            String situacao="A";//verifica e troca a situação do administrador
            if(oTipoatividade.getSituacao().equals(situacao))
                situacao = "I";
            else situacao = "A";
            
            String sql = "update tipoatividade set situacao=? where idtipoatividade=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oTipoatividade.getIdTipoAtividade());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas ao excluir o Tipo Atividade!Erro: " + e.getMessage());
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
    public Object carregar(int numero) {
        int idTipoatividade = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoAtividade oTipoatividade = null;
        String sql = "select * from tipoatividade where idtipoatividade=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipoatividade);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oTipoatividade = new TipoAtividade( rs.getInt("idtipoatividade"),
                                        rs.getString("descricao"),
                                        rs.getString("situacao"));  
            }
        } catch (Exception e){
            System.out.println("Problemas ao carregar o Tipo Atividade! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return oTipoatividade;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        TipoAtividade oTipoatividade = null;
        String sql = "select * from tipoatividade;";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oTipoatividade = new TipoAtividade( rs.getInt("idtipoatividade"),
                                        rs.getString("descricao"),
                                        rs.getString("situacao"));
                resultado.add(oTipoatividade);
            }
        } catch (Exception e){
            System.out.println("Problemas ao listar o Tipo Atividade! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
    
}
