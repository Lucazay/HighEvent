<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>
<div class="home_content">
    <div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Tipo Atividade</h1>
    <p class="mb-4">Planilha de Registros</p>
   
    <div class="card shadow table-responsive">
        <div class="card-body">
            <table id="datatable" class="display table table-striped table-bordered">
                <thead class="">
                    <tr>
                        <th align="center">ID</th>
                        <th align="center">Descrição</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tipoatividade" items="${tipoatividades}">                    
                        <tr>
                            <td align="center">${tipoatividade.idTipoAtividade}</td>                    
                            <td align="center">${tipoatividade.descricao}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${tipoatividade.idTipoAtividade})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${tipoatividade.idTipoAtividade})">
                                    <button class="btn 
                                          <c:out value="${tipoatividade.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw 
                                           <c:out value="${tipoatividade.situacao == 'A' ? 'fa-times' : ' fa-plus'}"/>"></i>
                                        <Strong>
                                            <c:out value="${tipoatividade.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
                                        </Strong>
                                    </button></a>
                            </td>
                        </tr>                
                    </c:forEach>
                </tbody>
            </table>
            
            <a href="#modaladicionar" class="btn btn-success mb-4 adicionar" data-toggle="modal" 
              data-id="" onclick="setDadosModal(${0})">
        <i class="fas fa-plus fa-fw"></i> Adicionar </a>  
           
        </div>
    </div>
        
    <div class="modal fade" id="modaladicionar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Adicionar</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                  
                    <div class="form-group">
                        <label>idTipoAtividade</label> <input class="form-control" type="text" name="idTipoAtividade" id="idTipoAtividade" value="" 
                               readonly="readonly"/>
                        <label>Situação</label> <input class="form-control" type="text" name="situacao" id="situacao" value="" 
                               readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <label>Descrição</label>
                        <input class="form-control" type="text" name="descricao" id="descricao" 
                               value=""/>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <a href="#" onclick="validarCampos()">
                            <button type="button" class="btn btn-success">Salvar</button>
                        </a>
                    </div>
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
        z-index: -1;
    }
    .inputfile:focus + label {
        /* keyboard navigation */
        outline: 1px dotted #000;
        outline: -webkit-focus-ring-color auto 5px;
    }
    .inputfile + label * {
        pointer-events: none;
    }
    .borda{
        position: relative;
        margin: 0 20px 30px 0;
        padding: 10px;
        border: 1px solid #e1e1e1;
        border-radius: 3px;
        background: #fff;
        -webkit-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        -moz-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
    }
</style>
        
<script>
     $(document).ready(function(){
         console.log('entrei ready');
         
         $('#datatable').DataTable({
             "oLanguage": {
                 "sProcessing": "Processando...",
                "sLengtMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Nenhum registro encontrado.",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfoPostFix": "",
                "sSearch": "Buscar",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext": "Seguinte",
                    "sLast": "Último"
                }
            }
        });
    });  
    
    function limparDadosModal(){
        $('#idTipoAtividade').val("0");
        $('#descricao').val("");
        $('#situacao').val("");
    }
    
    function setDadosModal(valor){
        console.log("Entrou no setDadosModal()");
        limparDadosModal();
        document.getElementById('idTipoAtividade').value = valor;
        var idTipot = valor;
        if(idTipot != "0"){
            $.getJSON('TipoAtividadeCarregar', {idTipoatividade: idTipot}, function(respostaServlet){
                console.log("respostaServlet: "+respostaServlet);
                var id = respostaServlet.idTipoatividade;
                if(id != "0"){
                    $('#idTipoAtividade').val(respostaServlet.idTipoAtividade);
                    $('#descricao').val(respostaServlet.descricao);
                    $('#situacao').val(respostaServlet.situacao);
                }
            });
        }
    }
    
    function validarCampos(){
        console.log("entrei na validação de campos");
        if(document.getElementById("descricao").value == ''){
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descrição',
                showConfirmButton: true,
                timer: 10000
            });
            $("#descricao").focus();
        }else{
            gravarDados();
        }
    }
    
    function gravarDados(){
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'TipoAtividadeCadastrar',
            data: {
                idTipoAtividade: $('#idTipoAtividade').val(),
                descricao: $('#descricao').val(),
                situacao: $('#situacao').val()
            },
            success:
                    function (data) {
                        console.log("resposta servlet->");
                        console.log(data);
                        if(data == 1){
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'Tipo trabalho gravado com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/TipoAtividadeListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possivel gravar o Tipo trabalho!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/TipoAtividadeListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/TipoatividadeListar";
                    }
                        
        });
    }
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: "Você tem certeza?",
            text: "Você poderá alterar novamente!",
            icon: 'Warning',
            ShowCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, Inativar/Ativar!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if(result.isConfirmed){
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/TipoAtividadeExcluir',
                    data: {
                        idTipoAtividade: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'center',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Situação alterada com sucesso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/TipoAtividadeListar";
                            })
                            } else {
                                Swal.fire({
                                    position: 'center',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possivel alterar a situação!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/TipoatividadeListar";
                                })
                            }
                        },
                        error:
                            function(data){
                                window.location.href = "${pageContext.request.contextPath}/TipoatividadeListar";
                            }
                });
            };
        });
    }
</script>
<%@include file="/footer.jsp" %>