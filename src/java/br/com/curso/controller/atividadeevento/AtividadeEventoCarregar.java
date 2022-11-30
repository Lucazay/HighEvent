
package br.com.curso.controller.atividadeevento;

import br.com.curso.dao.AtividadeEventoDAO;
import br.com.curso.dao.EventoDAO;
import br.com.curso.dao.TipoAtividadeDAO;
import br.com.curso.model.AtividadeEvento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtividadeEventoCarregar", urlPatterns = {"/AtividadeEventoCarregar"})
public class AtividadeEventoCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            int idAtividadeEvento = Integer.parseInt(request.getParameter("idAtividadeEvento"));
            
            AtividadeEventoDAO dao = new AtividadeEventoDAO();
            AtividadeEvento oAte = (AtividadeEvento) dao.carregar(idAtividadeEvento);
            
            Gson ogson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jSon = ogson.toJson(oAte);
            response.getWriter().write(jSon);
            
        } catch (Exception ex){
            System.out.println("Erro carregar AtividadeEvento"+ex.getMessage());
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
