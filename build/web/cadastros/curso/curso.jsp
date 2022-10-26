<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>
<div class="home_content">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Curso</h1>
    <p class="mb-4">Planilha de Registros</p>
    
    <div class="card shadow table-responsive">
        <div class="card-body">
            <table id="datatable" class="display table table-bordered table-striped">
                <thead>
                    <tr>
                        <th align="center">ID</th>
                        <th align="center">Curso</th>
                        <th align="center">Instituição</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Inativar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="curso" items="${cursos}">                    
                        <tr>
                            <td align="center">${curso.idCurso}</td>                    
                            <td align="center">${curso.nomeCurso}</td>
                            <td align="center">${curso.instituicao.nomeInstituicao}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${curso.idCurso})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${curso.idCurso})">
                                    <button class="btn 
                                          <c:out value="${curso.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw 
                                           <c:out value="${curso.situacao == 'A' ? 'fa-times' : ' fa-plus'}"/>"></i>
                                        <Strong>
                                            <c:out value="${curso.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
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
                        <label>ID Curso</label> <input class="form-control" type="text" name="idcurso" id="idcurso" value="" 
                               readonly="readonly"/>
                        <label>Situação</label> <input class="form-control" type="text" name="situacao" id="situacao" 
                               value="" readonly="readonly"/>
                    </div>
                    
                    <div class="form-group">
                        <label>Nome do Curso</label>
                        <input class="form-control" type="text" name="nomecurso" id="nomecurso"/>
                    </div>

                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Instituição</label>
                                <select class="form-control" name="idinstituicao" id="idinstituicao">
                                    <option value="">Selecione</option>
                                    <c:forEach var="instituicao" items="${instituicoes}">
                                        <option value="${instituicao.idInstituicao}"
                                                ${curso.instituicao.idInstituicao == instituicao.idInstituicao ? "selected" : ""}>
                                            ${instituicao.nomeInstituicao}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
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
    $(document).ready(function() {
            console.log('entrei ready');
            //Carregamos a datatable
            //$("#datatable").DataTable({});
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
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
        $('#idcurso').val("0");
        $('#nomecurso').val("");
        $('#situacao').val("");
        $('#idinstituicao').val("0");
    }
    
    function setDadosModal(valor){
        console.log("Entrei no setDadosModal()");
        limparDadosModal();
        document.getElementById('idcurso').value = valor;
        var idCur = valor;
        if(idCur != "0"){
            $.getJSON('CursoCarregar', {idCurso: idCur}, function(respostaServlet){
                var id = respostaServlet.idCurso;
                if(id != "0"){
                   $('#idcurso').val(respostaServlet.idCurso);
                    $('#nomecurso').val(respostaServlet.nomeCurso);
                    $('#situacao').val(respostaServlet.situacao);
                    $('#idinstituicao').val(respostaServlet.instituicao.idInstituicao); 
                }
            });
        }
    }
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você deseja realmente inativar/ativar a Curso?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/CursoExcluir',
                    data:{
                        idCurso: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'center',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Curso inativada/ativada com sucesso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/CursoListar";
                                })
                            } else {
                                Swal.fire({
                                    position: 'center',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possível inativar Curso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/CursoListar";
                                })
                            }
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/CursoListar";
                        }
                });
            };
        });
    }
    
    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomecurso").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Nome do curso!',
                showConfirmButton: true,
                timer: 10000
            });
            $("#nomecurso").focus();
        } else if (document.getElementById("idinstituicao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o instituição!',
                showConfirmButton: true,
                timer: 10000
            });
            $("#idinstituicao").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados(){
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'CursoCadastrar',
            data:{
                idcurso: $('#idcurso').val(),
                nomecurso: $('#nomecurso').val(),
                situacao: $('#situacao').val(),
                idinstituicao: $('#idinstituicao').val()
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
                                text: 'Curso gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/CursoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a Curso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/CursoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/CursoListar";
                    }
        });
    }
</script>