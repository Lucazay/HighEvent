
package br.com.curso.controller.atividadeevento;

import br.com.curso.dao.AtividadeEventoDAO;
import br.com.curso.model.AtividadeEvento;
import br.com.curso.model.Evento;
import br.com.curso.model.TipoAtividade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtividadeEventoCadastrar", urlPatterns = {"/AtividadeEventoCadastrar"})
public class AtividadeEventoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");

        String mensagem = null;

        try {

            int idAtividadeEvento = Integer.parseInt(request.getParameter("idatividadeevento"));

            Double cargaHoraria = Double.parseDouble(request.getParameter("cargahoraria"));
            String nomeAtividade = request.getParameter("nomeatividade");
            String resumo = request.getParameter("resumo");
            Date dataAtividade = Date.valueOf(request.getParameter("dataatividade"));
            String horaAtividade = request.getParameter("horaatividade");
            int idEvento = Integer.parseInt(request.getParameter("idevento"));
            int idTipoAtividade = Integer.parseInt(request.getParameter("idtipoatividade"));

            Evento oEvento = new Evento();
            oEvento.setIdEvento(idEvento);

            TipoAtividade oTipoAtividade = new TipoAtividade();
            oTipoAtividade.setIdTipoAtividade(idTipoAtividade);

            AtividadeEvento oAtividadeEvento = new AtividadeEvento(idAtividadeEvento, cargaHoraria, nomeAtividade,
                    resumo, dataAtividade, horaAtividade, oEvento, oTipoAtividade);

            AtividadeEventoDAO oAtividadeEventoDAO = new AtividadeEventoDAO();

           if (oAtividadeEventoDAO.cadastrar(oAtividadeEvento)){
                response.getWriter().write("1");
            }else{
                response.getWriter().write("0");
            }
            
        } catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Cidade! Erro: "+ ex.getMessage());
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
