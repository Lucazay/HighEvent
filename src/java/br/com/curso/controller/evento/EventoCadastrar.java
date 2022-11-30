
package br.com.curso.controller.evento;

import br.com.curso.dao.EventoDAO;
import br.com.curso.model.Curso;
import br.com.curso.model.Evento;
import br.com.curso.utils.Conversao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EventoCadastrar", urlPatterns = {"/EventoCadastrar"})
public class EventoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        String mensagem = null;
        
        try{

            int idEvento = Integer.parseInt(request.getParameter("idevento"));
            String nomeEvento = request.getParameter("nomeevento");
            Double valorEvento = Conversao.valorDinheiro(request.getParameter("valorevento"));
            Double valorEventoPrazo = Conversao.valorDinheiro(request.getParameter("valoreventoprazo"));
            Date dataInicioEvento = Date.valueOf(request.getParameter("datainicioevento"));
            Date dataTerminoEvento = Date.valueOf(request.getParameter("dataterminoevento"));
            String informacaoEvento = request.getParameter("informacaoevento");
            String situacaoEvento = request.getParameter("situacaoevento");
            Double saldoCaixa = Conversao.valorDinheiro(request.getParameter("saldocaixa"));
            String situacaoCaixa = request.getParameter("situacaocaixa");
            String foto = request.getParameter("foto");
            int idCurso = Integer.parseInt(request.getParameter ("idcurso"));
            
            Curso oCurso = new Curso();
            oCurso.setIdCurso(idCurso);
            
            Evento oEvento = new Evento(idEvento, nomeEvento, valorEvento, valorEventoPrazo,
            dataInicioEvento, dataTerminoEvento, informacaoEvento, situacaoEvento, saldoCaixa, situacaoCaixa, foto, oCurso); 
            
            EventoDAO dao = new EventoDAO();
            
            int idEventoAtual = dao.cadastrar(oEvento);

            if(idEventoAtual > 0){

            Evento oEventoAtual = (Evento) dao.carregar(idEventoAtual);
            Gson ogson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String jSon = ogson.toJson(oEventoAtual);
            response.getWriter().write(jSon);
            } else {
              response.getWriter().write("0");
            }
        } catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Evento! Erro: "+ ex.getMessage());
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
