<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header-crud.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>

<div id="layoutSidenav_content">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Função</h1>
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
                        <th align="center">Descrição</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="funcao" items="${funcoes}">                    
                        <tr>
                            <td align="center">${funcao.idFuncao}</td>                    
                            <td align="center">${funcao.descricao}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${funcao.idFuncao})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
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
                        <label>ID Funcão</label> <input class="form-control" type="text" name="idfuncao" id="idfuncao" value="" 
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
            title: 'Você tem certeza?',
            text: "Você deseja realmente excluir Função?",
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
                                    text: 'Função excluida com sucesso!',
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
                                    text: 'Não foi possível excluir função!',
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
        console.log("entrei na validação de campos");
        if (document.getElementById("descricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descrição da função!',
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
                                text: 'Função gravada com sucesso!',
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
                                text: 'Não foi possível gravar a Função!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/FuncaoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/FuncaoListar";
                    }
        });
    }
</script>


<script src="js/cardjs.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
            integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
            crossorigin="anonymous"></script>
        <script>
            $('.owl-carousel').owlCarousel({
                loop: true,
                margin: 15,
                nav: true,
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 2
                    },
                    1000: {
                        items: 3
                    }
                }
            })
        </script>
<%@include file="/footer.jsp"%>