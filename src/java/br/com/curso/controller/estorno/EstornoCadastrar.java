/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.estorno;

import br.com.curso.dao.EstornoDAO;
import br.com.curso.model.Estorno;
import br.com.curso.utils.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstornoCadastrar", urlPatterns = {"/EstornoCadastrar"})
public class EstornoCadastrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        String mensagem = null;
        
        try{
            int idEstorno = Integer.parseInt(request.getParameter("idestorno"));
            Double valorEstorno = Conversao.valorDinheiro(request.getParameter("valorestorno"));
            Date dataEstorno = Date.valueOf(request.getParameter("dataestorno"));
            Date dataPagamento = Date.valueOf(request.getParameter("datapagamento"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");
            
            Estorno oEstorno = new Estorno(idEstorno, valorEstorno, dataEstorno, dataPagamento, descricao, "A");
            
            EstornoDAO dao = new EstornoDAO();
            
            if (dao.cadastrar(oEstorno)){
                response.getWriter().write("1");
            }else{
                response.getWriter().write("0");
            }
            
        } catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Estorno! Erro: "+ ex.getMessage());
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
