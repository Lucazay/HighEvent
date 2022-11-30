
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Evento</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/OrganizadorEventoListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>     
        <div class="col-lg-9">
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="form-group">
                        <label>Evento:</label>
                            <select class="form-control" name="idevento" id="idevento" required>
                                <option value="nulo">Selecione</option>
                                    <c:forEach var="evento" items="${eventos}">
                                        <option value="${evento.idEvento}"
                                            ${organizadorevento.evento.idEvento == evento.idEvento ? "selected" : ""}>
                                            ${evento.nomeEvento} 
                                        </option>
                                    </c:forEach>
                            </select>
                    </div>   
                    
                    <div class="form-group">
                        <label>Organizador:</label>
                            <select class="form-control" name="idorganizador" id="idorganizador" required>
                                <option value="nulo">Selecione</option>
                                    <c:forEach var="organizador" items="${organizadores}">
                                        <option value="${organizador.idOrganizador}"
                                            ${organizadorevento.organizador.idOrganizador == organizador.idOrganizador ? "selected" : ""}>
                                            ${organizador.nomeRazaoPessoa} 
                                        </option>
                                    </c:forEach>
                            </select>
                    </div> 
                    
                    <div class="form-group">
                        <label>Função:</label>
                            <select class="form-control" name="idfuncao" id="idfuncao" required>
                                <option value="nulo">Selecione</option>
                                    <c:forEach var="funcao" items="${funcoes}">
                                        <option value="${funcao.idFuncao}"
                                            ${organizadorevento.funcao.idFuncao == funcao.idFuncao ? "selected" : ""}>
                                            ${funcao.descricao} 
                                        </option>
                                    </c:forEach>
                            </select>
                    </div> 
                    <!-- Botão de Confirmação --> 
                    <div class="form-group">
                        <button class="btn btn-success" type="submit" id="submit" onclick="validarCamposEvento()">
                            Salvar Documento</button>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>

<style type="text/css">
    .inputfile {
        /* visibility: hidden etc. wont work */
        width: 0.1px;
        height: 0.1px;
        opacity: 0;
        overflow: hidden;
        position: absolute;
        z-index: -1;    }
    .inputfile:focus + label {
        /* keyboard navigation */
        outline: 1px dotted #000;
        outline: -webkit-focus-ring-color auto 5px;    }
    .inputfile + label * {
        pointer-events: none;    }
    .borda{
        position: relative;
        margin: 0 20px 30px 0;
        padding: 10px;
        border: 1px solid #e1e1e1;
        border-radius: 3px;
        background: #fff;
        -webkit-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        -moz-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        box-shadow: 0px 0px 3px rgba(0,0,0,0.06);    }
</style>

<script>
    
    function validarCamposEvento() {
        console.log("entrei na validação de campos");
        if (document.getElementById("idorganizador").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Organizador!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idorganizador").focus();
        } else if (document.getElementById("idevento").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Evento!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idevento").focus();
        }  else if (document.getElementById("idfuncao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a Função!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idfuncao").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        var target = document.getElementById("target").src;
        $.ajax({
            type: 'post',
            url: 'OrganizadorEventoCadastrar',
            data: {
                idorganizadorevento: $('#idorganizadorevento').val(),
                idorganizador: $('#idorganizador').val(),
                idevento: $('#idevento').val(),
                idfuncao: $('#idfuncao').val()
            },
            success:
                    function (data) {
                        console.log("reposta servlet->");
                        console.log(data);
                        if (data == 1) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'OrganizadorEvento gravado com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar o OrganizadorEvento!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar";
                    }
        });
    }

</script>   

<%@include file="/footer.jsp" %>                     
