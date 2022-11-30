/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.controller.parcelamento;

import br.com.curso.dao.ParcelamentoDAO;
import br.com.curso.model.Parcelamento;
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

/**
 *
 * @author Acer
 */
@WebServlet(name = "ParcelamentoCadastrar", urlPatterns = {"/ParcelamentoCadastrar"})
public class ParcelamentoCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        
        try{

            int idParcelamento = Integer.parseInt(request.getParameter("idparcelamento"));
            int nroParcela = Integer.parseInt(request.getParameter("nroparcela"));
            double valorParcela = Double.parseDouble(request.getParameter("valorparcela"));
            Date dataVencimento = Date.valueOf(request.getParameter("datavencimento"));
            Date dataPagamento = Date.valueOf(request.getParameter("datapagamento"));
            String situacao = "A";
            String descricao = request.getParameter("descricao");

            
            Parcelamento oParcelamento = new Parcelamento(idParcelamento, nroParcela, valorParcela, dataVencimento, dataPagamento, situacao, descricao);
            ParcelamentoDAO dao = new ParcelamentoDAO();      
            
            if(dao.cadastrar(oParcelamento)){
                response.getWriter().write("1");
            }else{
                response.getWriter().write("0");
            }
        } catch (Exception e){
            System.out.println("Problemas no servlet cadastrar estado! Erro: "+e.getMessage());
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
