package br.com.curso.controller.evento;

import br.com.curso.dao.CursoDAO;
import br.com.curso.dao.EventoDAO;
import br.com.curso.dao.FuncaoDAO;
import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.OrganizadorDAO;
import br.com.curso.dao.OrganizadorEventoDAO;
import br.com.curso.model.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EventoNovo2", urlPatterns = {"/EventoNovo2"})
public class EventoNovo2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=iso-8859-1");
        Evento oEvento = new Evento();
        request.setAttribute("evento", oEvento);
        CursoDAO oCursoDAO = new CursoDAO();
        request.setAttribute("cursos", oCursoDAO.listar());

OrganizadorEventoDAO dao = new OrganizadorEventoDAO();
            request.setAttribute("organizadoreventos",dao.listar(0));
            GenericDAO oOrganizadorDAO = new OrganizadorDAO();
            request.setAttribute("organizadores", oOrganizadorDAO.listar());
            GenericDAO oFuncaoDAO = new FuncaoDAO();
            request.setAttribute("funcoes", oFuncaoDAO.listar());
        request.getRequestDispatcher("/cadastros/evento/eventoCadastrar2.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EventoNovo2.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EventoNovo2.class.getName()).log(Level.SEVERE, null, ex);
        }
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
