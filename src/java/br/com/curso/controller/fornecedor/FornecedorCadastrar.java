package br.com.curso.controller.fornecedor;

import br.com.curso.dao.FornecedorDAO;
import br.com.curso.model.Fornecedor;
import br.com.curso.model.Cidade;
import br.com.curso.model.Tipotrabalho;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FornecedorCadastrar", urlPatterns = {"/FornecedorCadastrar"})
public class FornecedorCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try{
            int idPessoa = Integer.parseInt(request.getParameter("idpessoa"));
            int idFornecedor = Integer.parseInt(request.getParameter("idfornecedor"));
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
            int idTipotrabalho = Integer.parseInt(request.getParameter("idtipotrabalho"));
            String permiteLogin = request.getParameter("permitelogin");
            String situacao = request.getParameter("situacao");
            String fotoPessoa = request.getParameter("fotopessoa");
            
            Cidade oCidade = new Cidade();
            oCidade.setIdCidade(idCidade);
            
            Tipotrabalho oTipotrabalho = new Tipotrabalho();
            oTipotrabalho.setIdTipotrabalho(idTipotrabalho);
            
            Fornecedor oFornecedor = new Fornecedor(idFornecedor, oTipotrabalho, permiteLogin, situacao, idPessoa, nomeRazaoPessoa, 
                    rglePessoa, cpfCnpjPessoa, dataNascFundPessoa, telefonePessoa, oCidade, logradouroPessoa, numLogradouroPessoa, 
                    bairroPessoa, cepPessoa, emailPessoa, login, senha, fotoPessoa);
            
            FornecedorDAO dao = new FornecedorDAO();
        
            if(dao.cadastrar(oFornecedor)){
                //mensagem = "Cadastrado com Sucesso!";
                response.getWriter().write("1");
            }else{
                //mensagem = "Problemas ao cadastrar Despesa!";
                response.getWriter().write("0");
            }
        } catch (Exception e) {
            System.out.println("Problemas no servelet Cadastrar Fornecedor! Erro: " + e.getMessage());
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