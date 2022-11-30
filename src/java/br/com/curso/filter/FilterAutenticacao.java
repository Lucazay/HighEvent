/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.filter;

import br.com.curso.model.Usuario;
import br.com.curso.utils.SingleConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns=("/*"))
public class FilterAutenticacao implements Filter {
    private static Connection conexao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
            try{
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession sessao = req.getSession(false);
            String urlParaAutenticar = req.getServletPath();
            
            if((sessao != null && sessao.getAttributeNames().hasMoreElements()) ||
                    (urlParaAutenticar.equalsIgnoreCase("/index.jsp"))||
                    urlParaAutenticar.equalsIgnoreCase("/home.jsp")||
                    urlParaAutenticar.equalsIgnoreCase("/login.jsp")||
                    urlParaAutenticar.equalsIgnoreCase("/UsuarioBuscarPorLogin")||
                    urlParaAutenticar.equalsIgnoreCase("/UsuarioLogar"))
                    {
                        if (Usuario.verificaUsuario(urlParaAutenticar, sessao)){
                    chain.doFilter(request, response);
                        }else{
                         request.getRequestDispatcher("/cadastros/homeLogado.jsp").forward(request, response);
                        }
        }else{
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }catch(Exception e){
            System.out.println("Erro no doFIlter: "+e.getMessage());
            e.printStackTrace();
        }        
    }

    @Override
    public void destroy() {
        try{
            conexao.close();
        }catch(SQLException ex){
            System.out.println("Erro no destroy: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
