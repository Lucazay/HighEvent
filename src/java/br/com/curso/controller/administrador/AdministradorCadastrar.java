
package br.com.curso.controller.administrador;

import br.com.curso.dao.AdministradorDAO;
import br.com.curso.model.Administrador;
import br.com.curso.model.Cidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdministradorCadastrar", urlPatterns = {"/AdministradorCadastrar"})
public class AdministradorCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try{
            int idPessoa = Integer.parseInt(request.getParameter("idpessoa"));
            int idAdministrador = Integer.parseInt(request.getParameter("idadministrador"));
            String nomeRazaoPessoa = request.getParameter("nomerazaopessoa");
            String rglePessoa = request.getParameter("rglepessoa");
            String cpfCnpjPessoa = request.getParameter("cpfcnpjpessoa");
            Date dataNascFundPessoa = Date.valueOf(request.getParameter("datanascfundpessoa"));
            String telefonePessoa = request.getParameter("telefonepessoa");
            int idCidade = Integer.parseInt(request.getParameter("idcidade"));
            String logradouroPessoa = request.getParameter("logradouropessoa");
            String numLogradouroPessoa = request.getParameter("numlogradouropessoa");
            String bairroPessoa = request.getParameter("bairropessoa");
            String cepPessoa = request.getParameter("ceppessoa");
            String emailPessoa = request.getParameter("emailpessoa");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String permiteLogin = request.getParameter("permitelogin");
            String situacao = request.getParameter("situacao");
            String fotoPessoa = request.getParameter("fotopessoa");
            
            Cidade oCidade = new Cidade();
            oCidade.setIdCidade(idCidade);
            
            Administrador oAdministrador = new Administrador(idAdministrador, permiteLogin, situacao, idPessoa, nomeRazaoPessoa, 
                    rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, oCidade, logradouroPessoa, numLogradouroPessoa, 
                    bairroPessoa, cepPessoa, emailPessoa, login, senha, fotoPessoa);
            
            AdministradorDAO dao = new AdministradorDAO();
        
            if(dao.cadastrar(oAdministrador)){
                //mensagem = "Cadastrado com Sucesso!";
                response.getWriter().write("1");
            }else{
                //mensagem = "Problemas ao cadastrar Despesa!";
                response.getWriter().write("0");
            }
        } catch (Exception e) {
            System.out.println("Problemas no servelet Cadastrar Administrador!Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
