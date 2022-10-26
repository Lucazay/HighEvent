
package br.com.curso.controller.tipotrabalho;

import br.com.curso.dao.TipotrabalhoDAO;
import br.com.curso.model.Tipotrabalho;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TipotrabalhoCadastrar", urlPatterns = {"/TipotrabalhoCadastrar"})
public class TipotrabalhoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        
        try{

            int idTipotrabalho = Integer.parseInt(request.getParameter("idtipotrabalho"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");
            
            Tipotrabalho oTipotrabalho = new Tipotrabalho(idTipotrabalho, descricao, situacao);
            TipotrabalhoDAO dao = new TipotrabalhoDAO();      
            
            if(dao.cadastrar(oTipotrabalho)){
                response.getWriter().write("1");
            }else{
                response.getWriter().write("0");
            }
        } catch (Exception e){
            System.out.println("Problemas no servlet cadastrar Tipo trabalho! Erro: "+e.getMessage());
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
