
package br.com.curso.controller.inscricao;

import br.com.curso.dao.InscricaoDAO;
import br.com.curso.model.Evento;
import br.com.curso.model.Inscricao;
import br.com.curso.model.Participante;
import br.com.curso.utils.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InscricaoCadastrar", urlPatterns = {"/InscricaoCadastrar"})
public class InscricaoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        String mensagem = null;
        
        try{
            int idInscricao = Integer.parseInt(request.getParameter("idinscricao"));
            String nroPulseira = request.getParameter("nropulseira");
            String chaveQRCode = request.getParameter("chaveqrcode");
            Date dataInscricao = Date.valueOf(request.getParameter("datainscricao"));
            String situacao = request.getParameter("situacao");
            Double valorInscricao = Conversao.valorDinheiro(request.getParameter("valorinscricao"));
            int nroParcelas = Integer.parseInt(request.getParameter("nroparcelas"));
            Double parelasPagas = Conversao.valorDinheiro(request.getParameter("parcelaspagas"));
            Double parcelasPagar = Conversao.valorDinheiro(request.getParameter("parcelaspagar"));
            Double parcelasCanceladas = Conversao.valorDinheiro(request.getParameter("parcelascanceladas"));
            Double parcelasEstorno = Conversao.valorDinheiro(request.getParameter("parcelasestorno"));
            int idEvento = Integer.parseInt(request.getParameter("idevento"));
            int idParticipante = Integer.parseInt(request.getParameter("idparticipante"));
            
            Evento oEvento = new Evento();
            oEvento.setIdEvento(idEvento);

            Participante oParticipante = new Participante();
            oParticipante.setIdParticipante(idParticipante);
            

            Inscricao oInscricao = new Inscricao(idInscricao, nroPulseira, chaveQRCode, dataInscricao, situacao, valorInscricao, nroParcelas, parelasPagas, 
            parcelasPagar, parcelasCanceladas, parcelasEstorno, oEvento, oParticipante);
            
            InscricaoDAO dao = new InscricaoDAO();
            
            if (dao.cadastrar(oInscricao)){
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
