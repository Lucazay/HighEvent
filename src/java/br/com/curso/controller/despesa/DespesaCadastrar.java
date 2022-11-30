
package br.com.curso.controller.despesa;

import br.com.curso.dao.DespesaDAO;
import br.com.curso.model.Despesa;
import br.com.curso.model.Fornecedor;
import br.com.curso.model.Pessoa;
import br.com.curso.utils.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DespesaCadastrar", urlPatterns = {"/DespesaCadastrar"})
public class DespesaCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        String mensagem = null;

        try{
        
            int idDespesa = Integer.parseInt(request.getParameter("iddespesa"));
            int idFornecedor = Integer.parseInt(request.getParameter("idfornecedor"));
            Double valorDespesa = Conversao.valorDinheiro(request.getParameter("valordespesa"));
            Date vencimentoDespesa = Date.valueOf(request.getParameter("vencimentodespesa"));
            Date pagamentoDespesa = Date.valueOf(request.getParameter("pagamentodespesa"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");

            Fornecedor oFornecedor =  new Fornecedor();
            oFornecedor.setIdFornecedor(idFornecedor);

            Despesa oDespesa = new Despesa(idDespesa, oFornecedor, valorDespesa, vencimentoDespesa, pagamentoDespesa, descricao, situacao);

            DespesaDAO dao = new DespesaDAO();

            if (dao.cadastrar(oDespesa)){
                response.getWriter().write("1");
            }else{
                response.getWriter().write("0");
            }
            
        } catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Doacao! Erro: "+ ex.getMessage());
            ex.printStackTrace();
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
