
package br.com.curso.model;

import javax.servlet.http.HttpSession;

public class Usuario {
    private int idPessoa;
    private String nomeRazaoPessoa;
    private String cpfCnpjPessoa;
    private String login;
    private String senha;
    private String tipo;
    private int id;
    private String foto;
    
    //todo 
    
    public Usuario(){
        this.idPessoa = 0;
        this.id = 0;
        this.tipo = "";
        this.foto = "";
    }

    public Usuario(int idPessoa, String nomeRazaoPessoa, String cpfCnpjPessoa, String login, String senha, String tipo, int id, String foto) {
        this.idPessoa = idPessoa;
        this.nomeRazaoPessoa = nomeRazaoPessoa;
        this.cpfCnpjPessoa = cpfCnpjPessoa;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.id = id;
        this.foto = foto;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeRazaoPessoa() {
        return nomeRazaoPessoa;
    }

    public void setNomeRazaoPessoa(String nomeRazaoPessoa) {
        this.nomeRazaoPessoa = nomeRazaoPessoa;
    }

    public String getCpfCnpjPessoa() {
        return cpfCnpjPessoa;
    }

    public void setCpfCnpjPessoa(String cpfCnpjPessoa) {
        this.cpfCnpjPessoa = cpfCnpjPessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
    public static boolean verificaUsuario (String recurso, HttpSession sessao){
        boolean status = false;
        try{
            //se for um acesso liberado permite passagem
            if(recurso.equalsIgnoreCase("/index.jsp") ||
                    recurso.equalsIgnoreCase("/home.jsp") ||
                     recurso.equalsIgnoreCase("/header.jsp") ||
                     recurso.equalsIgnoreCase("/footer.jsp") ||
                    recurso.equalsIgnoreCase("/login.jsp") ||
                    recurso.equalsIgnoreCase("/UsuarioBuscarPorLogin") ||
                    recurso.equalsIgnoreCase("/UsuarioLogar") ||
                    recurso.equalsIgnoreCase("/js/jquery-3.1.1.min.js") ||
                    recurso.equalsIgnoreCase("/js/jquery.mask.min.js") ||
                    recurso.equalsIgnoreCase("/js/jquery.maskMoney.min.js") ||
                    recurso.equalsIgnoreCase("/js/app.js")){
                status = true;
            }
            
            
            if (sessao != null && sessao.getAttributeNames().hasMoreElements()){
                //pega os dados do usuario
                int idUsuario = Integer.parseInt(sessao.getAttribute("idusuario").toString());
                String tipoUsuario = sessao.getAttribute("tipousuario").toString();
                
                //verifica permissoes
                //se for administrador libera todos os recursos
                
                if (tipoUsuario.equalsIgnoreCase("Administrador")){
                    System.out.println("O tipo do usuario é: "+tipoUsuario);
                    status = true;
                }else{
                    if (tipoUsuario.equalsIgnoreCase("Organizador")){
                        System.out.println("O tipo do usuario é: "+tipoUsuario);
                        if (recurso.equalsIgnoreCase("/CidadeCarregar") ||
                                recurso.equalsIgnoreCase("/CidadeExcluir") ||
                                recurso.equalsIgnoreCase("/CidadeListar") ||
                                recurso.equalsIgnoreCase("/CidadeCadastrar") ||
                                recurso.equalsIgnoreCase("/EstadoCarregar") ||
                                recurso.equalsIgnoreCase("/EstadoExcluir") ||
                                recurso.equalsIgnoreCase("/EstadoListar") ||
                                recurso.equalsIgnoreCase("/EstadoCadastrar") ||
                                recurso.equalsIgnoreCase("/FuncaoCarregar") ||
                                recurso.equalsIgnoreCase("/FuncaoCadastrar") ||
                                recurso.equalsIgnoreCase("/FuncaoExcluir") ||
                                recurso.equalsIgnoreCase("/FuncaoListar") ||
                                recurso.equalsIgnoreCase("/InstituicaoCarregar") ||
                                recurso.equalsIgnoreCase("/InstituicaoCadastrar") ||
                                recurso.equalsIgnoreCase("/InstituicaoExcluir") ||
                                recurso.equalsIgnoreCase("/InstituicaoListar") ||
                                recurso.equalsIgnoreCase("/CursoCarregar") ||
                                recurso.equalsIgnoreCase("/CursoCadastrar") ||
                                recurso.equalsIgnoreCase("/CursoExcluir") ||
                                recurso.equalsIgnoreCase("/CursoListar") ||
                                recurso.equalsIgnoreCase("/tipotrabalhoCarregar") ||
                                recurso.equalsIgnoreCase("/tipotrabalhoCadastrar") ||
                                recurso.equalsIgnoreCase("/tipotrabalhoExcluir") ||
                                recurso.equalsIgnoreCase("/tipotrabalhoListar") ||
                                recurso.equalsIgnoreCase("/tipoAtividadeCarregar") ||
                                recurso.equalsIgnoreCase("/tipoAtividadeCadastrar") ||
                                recurso.equalsIgnoreCase("/tipoAtividadeExcluir") ||
                                recurso.equalsIgnoreCase("/tipoAtividadeListar") ||
                                recurso.equalsIgnoreCase("/OrganizadorCarregar") ||
                                recurso.equalsIgnoreCase("/OrganizadorExcluir") ||
                                recurso.equalsIgnoreCase("/OrganizadorAlterar") ||
                                recurso.equalsIgnoreCase("/OrganizadorListar") ||
                                recurso.equalsIgnoreCase("/cadastros/menuLogado.jsp") ||
                                recurso.equalsIgnoreCase("/cadastros/menuCliente.jsp") ||
                                recurso.equalsIgnoreCase("/cadastros/homeLogado.jsp")){
                            status = true; //permite acesso ao usuario tipo cliente
                        }
                            
                    }

                    if (tipoUsuario.equalsIgnoreCase("Participante")){
                        System.out.println("O tipo do usuario é: "+tipoUsuario);
                        if (recurso.equalsIgnoreCase("/ParticipanteCarregar") ||
                                recurso.equalsIgnoreCase("/ParticipanteExcluir") ||
                                recurso.equalsIgnoreCase("/ParticipanteAlterar") ||
                                recurso.equalsIgnoreCase("/ParticipanteListar") ||
                                recurso.equalsIgnoreCase("/cadastros/menuLogado.jsp") ||
                                recurso.equalsIgnoreCase("/cadastros/menuFornecedor.jsp") ||
                                recurso.equalsIgnoreCase("/cadastros/homeLogado.jsp")){
                            status = true; //permite acesso ao usuario tipo fornecedor
                        }          
                    }
                }     
            }
        } catch(Exception ex){
            System.out.println("Erro na model usuario metodo: verificaUsuario "+ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("O status retornado é: "+status);
        return status;
    }
    
    
}
