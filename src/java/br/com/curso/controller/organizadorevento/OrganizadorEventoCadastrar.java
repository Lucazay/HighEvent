package br.com.curso.controller.organizadorevento;

import br.com.curso.dao.OrganizadorEventoDAO;
import br.com.curso.model.Evento;
import br.com.curso.model.Funcao;
import br.com.curso.model.Organizador;
import br.com.curso.model.OrganizadorEvento;
import br.com.curso.model.Pessoa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrganizadorEventoCadastrar", urlPatterns = {"/OrganizadorEventoCadastrar"})
public class OrganizadorEventoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {

            int idOrganizadorEvento = Integer.parseInt(request.getParameter("idorganizadorevento"));
            int idOrganizador = Integer.parseInt(request.getParameter("idorganizador"));
            int idEvento = Integer.parseInt(request.getParameter("idevento"));
            int idFuncao = Integer.parseInt(request.getParameter("idfuncao"));

            Organizador oOrganizador = new Organizador();
            oOrganizador.setIdOrganizador(idOrganizador);

            Evento oEvento = new Evento();
            oEvento.setIdEvento(idEvento);

            Funcao oFuncao = new Funcao();
            oFuncao.setIdFuncao(idFuncao);

            OrganizadorEvento oOrganizadorEvento = new OrganizadorEvento(idOrganizadorEvento, oOrganizador, oEvento, oFuncao);

            OrganizadorEventoDAO dao = new OrganizadorEventoDAO();

            int idOrganizadorEventoAtual = dao.cadastrar(oOrganizadorEvento);

            if (idOrganizadorEventoAtual > 0) {

                OrganizadorEvento oOrganizadorEventoAtual = (OrganizadorEvento) dao.carregar(idOrganizadorEventoAtual);
                Gson ogson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                String jSon = ogson.toJson(oOrganizadorEventoAtual);
                response.getWriter().write(jSon);
            } else {
                response.getWriter().write("0");
            }
        } catch (Exception e) {
            System.out.println("Problemas no servelet Cadastrar OrganizadorEvento! Erro: " + e.getMessage());
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
