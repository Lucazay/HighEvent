<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800" align="center">Fun??o</h1>
    <a href="#modaladicionarfuncao" class="btn btn-success mb-4 adicionar" data-toggle="modal" 
              data-id="" onclick="setDadosModal(${0})">
        <i class="fas fa-plus fa-fw"></i> Adicionar </a>
    <div class="card shadow">
        <div class="card-body">
            <table id="datatable1" class="display">
                <thead>
                    <tr>
                        <th align="center">ID</th>
                        <th align="center">Descri??o</th>
                        <th align="center">Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="funcao" items="${funcoes}">                    
                        <tr>
                            <td align="center">${funcao.idFuncao}</td>                    
                            <td align="center">${funcao.descricao}</td>
                            <td align="center">
                                <a href="#" onclick="deletar(${funcao.idFuncao})" class="btn btn-success adicionar">
                                    <i class="fas fa-plus fa-fw"></i> Excluir </a>
                            </td>
                        </tr>                
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
        
    <div class="modal fade" id="modaladicionarfuncao" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                        <label>ID Func?o</label> <input class="form-control" type="text" name="idfuncao" id="idfuncao" value="" 
                               readonly="readonly"/>
                    </div>
                    <div class="form-group">
                            <label>Descri??o</label>
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
            $('#datatable1').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ at? _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 at? 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "?ltimo"
                    }
                }
            });
        });
        
    function limparDadosModal(){
        $('#idfuncao').val("0");
        $('#descricao').val("");
    }    
    
    function setDadosModal(valor){
        console.log("Entrou no setDadosModal()");
        limparDadosModal();
        document.getElementById('idfuncao').value = valor;
        var idFun = valor;
        if(idFun != "0"){
            $.getJSON('FuncaoCarregar', {idFuncao: idFun}, function(respostaServlet){
                console.log(respostaServlet);
                var id = respostaServlet.idFuncao;
                if(id != "0"){
                    $('#idfuncao').val(respostaServlet.idFuncao);
                    $('#descricao').val(respostaServlet.descricao);
                }
            });
        }
    }
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Voc? tem certeza?',
            text: "Voc? deseja realmente excluir Fun??o?",
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
                    url: '${pageContext.request.contextPath}/FuncaoExcluir',
                    data:{
                        idFuncao: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'center',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Fun??o excluida com sucesso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/FuncaoListar";
                                })
                            } else {
                                Swal.fire({
                                    position: 'center',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'N?o foi poss?vel excluir fun??o!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/FuncaoListar";
                                })
                            }
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/FuncaoListar";
                        }
                });
            };
        });
    }
    
    function validarCampos() {
        console.log("entrei na valida??o de campos");
        if (document.getElementById("descricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descri??o da fun??o!',
                showConfirmButton: true,
                timer: 200
            });
            $("#descricao").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'FuncaoCadastrar',
            data: {
                idfuncao: $('#idfuncao').val(),
                descricao: $('#descricao').val()
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
                                text: 'Fun??o gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/EventoNovo";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'N?o foi poss?vel gravar a Fun??o!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/EventoNovo";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/EventoNovo";
                    }
        });
    }
</script>