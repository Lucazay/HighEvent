<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Estorno</h1>
    <p class="mb-4">Planilha de Registros</p>
    <a href="#modaladicionar" class="btn btn-success mb-4 adicionar" data-toggle="modal" 
              data-id="" onclick="setDadosModal(${0})">
        <i class="fas fa-plus fa-fw"></i> Adicionar </a>
    <div class="card shadow">
        <div class="card-body">
            <table id="datatable" class="display">
                <thead>
                    <tr>
                        <th align="center">ID</th>
                        <th align="center">Valor do Estorno</th>
                        <th align="center">Data do Estorno</th>
                        <th align="center">Data do Pagamento</th>
                        <th align="center">Descrição</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Inativar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="estorno" items="${estornos}">                    
                        <tr>
                            <td align="center">${estorno.idEstorno}</td>                    
                            <td align="center">${estorno.valorEstorno}</td>
                            <td align="center">${estorno.dataEstorno}</td>
                            <td align="center">${estorno.dataPagamento}</td>
                            <td align="center">${estorno.descricao}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${estorno.idEstorno})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${estorno.idEstorno})">
                                    <button class="btn 
                                          <c:out value="${estorno.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw 
                                           <c:out value="${estorno.situacao == 'A' ? 'fa-times' : ' fa-plus'}"/>"></i>
                                        <Strong>
                                            <c:out value="${estorno.situacao == 'A' ? 'Invalidar': 'Validar'}"/>
                                        </Strong>
                                    </button></a>
                            </td>
                        </tr>                
                    </c:forEach>
                </tbody>
            </table>
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
                        <label>ID Estorno</label> <input class="form-control" type="text" name="idestorno" id="idestorno" value="" 
                               readonly="readonly"/>
                    </div>
                    
                    <div class="form-group">
                        <label>Descrição</label>
                        <input class="form-control" type="text" name="descricao" id="descricao"/>
                    </div>
                    
                    <div class="form-group">
                                <label>Valor do Estorno</label>
                                <input class="form-control" type="text" style="text-align:right;" 
                                           name="valorestorno" id="valorestorno" 
                                           value="<fmt:formatNumber value='${estorno.valorEstorno}' type='currency'/>" />
                    </div>
                    
                    <div class="col-sm">
                                <label>Data do Estorno</label>
                                <input class="form-control" type="date" name="dataestorno" id="dataestorno" 
                                       value=""/>
                            </div>
                    
                    <div class="col-sm">
                                <label>Data do Pagamento</label>
                                <input class="form-control" type="date" name="datapagamento" id="datapagamento" 
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
        
        $(document).ready(function () {
        console.log("entrei na ready do documento");
        $("#valorestorno").maskMoney({
            prefix: 'R$',
            suffix: '',
            allowZero: false,
            allowNegative: true,
            allowEmpty: false,
            doubleClickSelection: true,
            selectAllOnFocus: true,
            thousands: '.',
            decimal: ",",
            precision: 2,
            affixesStay: true,
            bringCareAtEndOnFocus: true
        })
    })
        
    function limparDadosModal(){
        $('#idestorno').val("0");
        $('#valorestorno').val("");
        $('#dataestorno').val("");
        $('#datapagamento').val("");
        $('#descricao').val("");
        $('#situacao').val("");
    }
    
    function setDadosModal(valor){
        console.log("Entrei no setDadosModal()");
        limparDadosModal();
        document.getElementById('idestorno').value = valor;
        
        var idEst = valor;
        if(idEst != "0"){
            $.getJSON('EstornoCarregar', {idEstorno: idEst}, function(respostaServlet){
                var id = respostaServlet.idEstorno;
                if(id != "0"){
                    $('#idestorno').val(respostaServlet.idEstorno);
                    $('#valorestorno').val(respostaServlet.valorEstorno);
                    $('#dataestorno').val(respostaServlet.dataEstorno);
                    $('#datapagamento').val(respostaServlet.dataEstorno);
                    $('#descricao').val(respostaServlet.descricao);
                    $('#situacao').val(respostaServlet.situacao);
                }
            });
        }
    }
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você deseja trocar a situação?",
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
                    url: '${pageContext.request.contextPath}/EstornoExcluir',
                    data:{
                        idEstorno: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'center',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Situação trocada com sucesso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/EstornoListar";
                                })
                            } else {
                                Swal.fire({
                                    position: 'center',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possível trocar a situação!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/EstornoListar";
                                })
                            }
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/EstornoListar";
                        }
                });
            };
        });
    }
    
    function validarCampos() {
        console.log("entrei na validação de campos");
         if (document.getElementById("descricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descrição!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#descricao").focus();
        } else if (document.getElementById("valorestorno").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o estado!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#valorestorno").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados(){
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'EstornoCadastrar',
            data:{
                idestorno: $('#idestorno').val(),
                valorestorno: $('#valorestorno').val(),
                dataestorno: $('#dataestorno').val(),
                datapagamento: $('#datapagamento').val(),
                descricao: $('#descricao').val(),
                situacao: $('#situacao').val()
            },
            success:
                    function (data) {
                        console.log("resposta servlet->");
                        console.log(data);
                        if (data == 1) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'Estorno gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/EstornoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a estorno!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/EstornoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/EstornoListar";
                    }
        });
    }
    
</script>