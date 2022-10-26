package br.com.curso.controller.pessoa;

import br.com.curso.dao.AdministradorDAO;
import br.com.curso.dao.FornecedorDAO;
import br.com.curso.dao.OrganizadorDAO;
import br.com.curso.dao.ParticipanteDAO;
import br.com.curso.dao.PatrocinadorDAO;
import br.com.curso.dao.PessoaDAO;
import br.com.curso.model.Administrador;
import br.com.curso.model.Fornecedor;
import br.com.curso.model.Organizador;
import br.com.curso.model.Participante;
import br.com.curso.model.Patrocinador;
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

@WebServlet(name = "PessoaBuscarCpfCnpj", urlPatterns = {"/PessoaBuscarCpfCnpj"})
public class PessoaBuscarCpfCnpj extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try{
            String cpfCnpj = request.getParameter("cpfcnpjpessoa");
            String tipoPessoa = request.getParameter("tipopessoa");
            int id = 0;
            String jsonRetorno="";
            if (tipoPessoa.equals("administrador")){
                AdministradorDAO oAdmDAO = new AdministradorDAO();
                //busca Adm por cpf.
                Administrador oAdm = (Administrador) oAdmDAO.carregar(oAdmDAO.verificarCpf(cpfCnpj));
                //gera retorno
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                jsonRetorno = gson.toJson(oAdm);
                
            } else if (tipoPessoa.equals("organizador")){
                OrganizadorDAO oOrgDAO = new OrganizadorDAO();
                //busca CLI por cpf.
                Organizador oOrg = (Organizador) oOrgDAO.carregar(oOrgDAO.verificarCpf(cpfCnpj));
                //gera retorno
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                jsonRetorno = gson.toJson(oOrg);
                
            } else if (tipoPessoa.equals("patrocinador")){
                PatrocinadorDAO oPatDAO = new PatrocinadorDAO();
                //busca CLI por cpf.
                Patrocinador oPat = (Patrocinador) oPatDAO.carregar(oPatDAO.verificarCpf(cpfCnpj));
                //gera retorno
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                jsonRetorno = gson.toJson(oPat);
                
            } else if (tipoPessoa.equals("fornecedor")){
                FornecedorDAO oForDAO = new FornecedorDAO();
                //busca CLI por cpf.
                Fornecedor oFor = (Fornecedor) oForDAO.carregar(oForDAO.verificarCpf(cpfCnpj));
                //gera retorno
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                jsonRetorno = gson.toJson(oFor);
                
            } else if (tipoPessoa.equals("participante")){
                ParticipanteDAO oParDAO = new ParticipanteDAO();
                //busca CLI por cpf.
                Participante oPar = (Participante) oParDAO.carregar(oParDAO.verificarCpf(cpfCnpj));
                //gera retorno
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                jsonRetorno = gson.toJson(oPar);
                
            } else {
                //não tem ADM/Cliente ou Forn. -- então verifica Pessoa por CPF
                PessoaDAO oPessoaDAO = new PessoaDAO();
                Pessoa oPessoa = oPessoaDAO.carregarCpf(cpfCnpj);

                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                jsonRetorno = gson.toJson(oPessoa);
            }
         
            response.setCharacterEncoding("iso-8859-1");
            response.getWriter().write(jsonRetorno);
            
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar pessoa por CPF/CNPJ"
                    + " Erro: " + ex.getMessage());
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