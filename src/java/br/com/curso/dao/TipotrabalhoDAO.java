
package br.com.curso.dao;

import br.com.curso.model.Tipotrabalho;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipotrabalhoDAO implements GenericDAO {
    
    private Connection conexao;
    
    public TipotrabalhoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Boolean retorno = false;
        try{
            Tipotrabalho oTipotrabalho = (Tipotrabalho) objeto;
            if(oTipotrabalho.getIdTipotrabalho()== 0){
                retorno = this.inserir(oTipotrabalho);
            }else{
                retorno = this.alterar(oTipotrabalho);
            }
        } catch (Exception ex){
            System.out.println("Problemas ao incluir Tipo trabalho! Erro "+ex.getMessage());            
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Tipotrabalho oTipotrabalho = (Tipotrabalho) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into tipotrabalho (descricao,  situacao) values (?, ?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipotrabalho.getDescricao());
            stmt.setString(2, "A");
            stmt.execute();
            return true;
        } catch (Exception e){
            
            try{
                System.out.println("Problemas ao cadastrar o Tipo trabalho! Erro: "+e.getMessage());
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
        Tipotrabalho oTipotrabalho = (Tipotrabalho) objeto;
        PreparedStatement stmt = null;
        String sql = "update tipotrabalho set descricao=?, situacao=? where idtipotrabalho=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipotrabalho.getDescricao());
            stmt.setString(2, oTipotrabalho.getSituacao());
            stmt.setInt(3, oTipotrabalho.getIdTipotrabalho());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception e){
            try{
                System.out.println("Problemas ao alterar o Tipo trabalho! Erro: "+e.getMessage());
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
            TipotrabalhoDAO oTipotrabalhoDAO = new TipotrabalhoDAO();
            Tipotrabalho oTipotrabalho = (Tipotrabalho) oTipotrabalhoDAO.carregar(numero);
            String situacao="A";//verifica e troca a situação do administrador
            if(oTipotrabalho.getSituacao().equals(situacao))
                situacao = "I";
            else situacao = "A";
            
            String sql = "update tipotrabalho set situacao=? where idtipotrabalho=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oTipotrabalho.getIdTipotrabalho());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas ao excluir o Tipo trabalho!Erro: " + e.getMessage());
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
        int idTipotrabalho = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tipotrabalho oTipotrabalho = null;
        String sql = "select * from tipotrabalho where idtipotrabalho=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipotrabalho);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oTipotrabalho = new Tipotrabalho( rs.getInt("idtipotrabalho"),
                                        rs.getString("descricao"),
                                        rs.getString("situacao"));  
            }
        } catch (Exception e){
            System.out.println("Problemas ao carregar o Tipo trabalho! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return oTipotrabalho;
    }

    @Override
    public List<Object> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Tipotrabalho oTipotrabalho = null;
        String sql = "select * from tipotrabalho;";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                oTipotrabalho = new Tipotrabalho( rs.getInt("idtipotrabalho"),
                                        rs.getString("descricao"),
                                        rs.getString("situacao"));
                resultado.add(oTipotrabalho);
            }
        } catch (Exception e){
            System.out.println("Problemas ao listar o Tipo trabalho! Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
    
}
