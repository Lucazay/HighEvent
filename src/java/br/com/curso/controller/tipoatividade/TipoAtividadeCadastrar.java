
package br.com.curso.controller.tipoatividade;

import br.com.curso.dao.TipoAtividadeDAO;
import br.com.curso.model.TipoAtividade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TipoAtividadeCadastrar", urlPatterns = {"/TipoAtividadeCadastrar"})
public class TipoAtividadeCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        
        try{

            int idTipoAtividade = Integer.parseInt(request.getParameter("idTipoAtividade"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");
            
            TipoAtividade oTipoAtividade = new TipoAtividade(idTipoAtividade, descricao, situacao);
            TipoAtividadeDAO dao = new TipoAtividadeDAO();      
            
            if(dao.cadastrar(oTipoAtividade)){
                response.getWriter().write("1");
            }else{
                response.getWriter().write("0");
            }
        } catch (Exception e){
            System.out.println("Problemas no servlet cadastrar Atividade! Erro: "+e.getMessage());
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
