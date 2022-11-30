
package br.com.curso.controller.doacao;

import br.com.curso.dao.DoacaoDAO;
import br.com.curso.model.Doacao;
import br.com.curso.model.Patrocinador;
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

@WebServlet(name = "DoacaoCadastrar", urlPatterns = {"/DoacaoCadastrar"})
public class DoacaoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        String mensagem = null;
        
        try{
            int idDoacao = Integer.parseInt(request.getParameter("iddoacao"));
            int Patrocinador = Integer.parseInt(request.getParameter("idpatrocinador"));
            Double valorDoacao = Conversao.valorDinheiro(request.getParameter("valordoacao"));
            Date dataDoacao = Date.valueOf(request.getParameter("datadoacao"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");
            
            Pessoa oPatrocinador = new Pessoa();
            oPatrocinador.setIdPessoa(Patrocinador);
            
            Doacao oDoacao = new Doacao(idDoacao, oPatrocinador, valorDoacao, dataDoacao, descricao, "A");
            
            DoacaoDAO dao = new DoacaoDAO();
            
            if (dao.cadastrar(oDoacao)){
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
