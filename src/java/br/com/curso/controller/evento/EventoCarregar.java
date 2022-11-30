package br.com.curso.controller.evento;

import br.com.curso.dao.CursoDAO;
import br.com.curso.dao.EventoDAO;
import br.com.curso.dao.FuncaoDAO;
import br.com.curso.dao.OrganizadorDAO;
import br.com.curso.model.Evento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EventoCarregar", urlPatterns = {"/EventoCarregar"})
public class EventoCarregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
            EventoDAO oEventoDAO = new EventoDAO();
            request.setAttribute("evento", oEventoDAO.carregar(idEvento));
            CursoDAO oCursoDAO = new CursoDAO();
            request.setAttribute("cursos", oCursoDAO.listar());
            OrganizadorDAO oOrganizadorDAO = new OrganizadorDAO();
            request.setAttribute("organizadores", oOrganizadorDAO.listar());
            FuncaoDAO oFuncaoDAO = new FuncaoDAO();
            request.setAttribute("funcoes", oFuncaoDAO.listar());
            request.getRequestDispatcher("/cadastros/evento/eventoCadastrar.jsp").forward(request, response);          
        } catch (Exception ex){
            System.out.println("Erro carregar evento "+ex.getMessage());
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
