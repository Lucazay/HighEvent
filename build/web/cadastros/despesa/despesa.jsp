<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header-crud.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>


<div id="layoutSidenav_content">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Despesas</h1>
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
                        <th align="center">Fornecedor</th>
                        <th align="center">Valor</th>
                        <th align="center">Vencimento</th>
                        <th align="center">Pagamento</th>
                        <th align="center">Descri??o</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Inativar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="despesa" items="${despesas}">                    
                        <tr>
                            <td align="center">${despesa.idDespesa}</td>                    
                            <td align="center">${despesa.fornecedor.nomeRazaoPessoa}</td>
                            <td align="center">${despesa.valorDespesa}</td>
                            <td align="center">${despesa.vencimentoDespesa}</td>
                            <td align="center">${despesa.pagamentoDespesa}</td>
                            <td align="center">${despesa.descricao}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${despesa.idDespesa})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${despesa.idDespesa})">
                                    <button class="btn
                                            <c:out value="${despesa.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw
                                           <c:out value="${despesa.situacao == 'A' ? 'fa-times' : ' fa-plus'}"/>"></i>
                                        <Strong>
                                            <c:out value="${despesa.situacao == 'A' ? 'Invalidar': 'Validar'}"/>
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
                        <label>ID</label> <input class="form-control" type="text" name="iddespesa" id="iddespesa" value="" 
                                                         readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <label>Descri??o</label>
                        <input class="form-control" type="text" name="descricao" id="descricao"/>
                    </div>

                    <div class="form-group">
                        <label>Fornecedor</label>
                        <select class="form-control" name="idfornecedor" id="idfornecedor" required>
                            <option value="nulo">Selecione</option>
                            <c:forEach var="fornecedor" items="${fornecedores}">
                                <option value="${fornecedor.idFornecedor}"
                                        ${despesa.fornecedor.idFornecedor == fornecedor.idFornecedor ? "selected" : ""}>
                                    ${fornecedor.nomeRazaoPessoa} 
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Valor da Despesa</label>
                        <input class="form-control" type="text" style="text-align:right;" 
                               name="valordespesa" id="valordespesa" 
                               value="<fmt:formatNumber value='${despesa.valorDespesa}' type='currency'/>" />
                    </div>
                    <div class="form-group">
                        <div class="col-sm">
                            <label>Vencimento</label>
                            <input class="form-control" type="date" name="vencimentodespesa" id="vencimentodespesa" 
                                   value=""/>
                        </div>

                        <div class="col-sm">
                            <label>Pagamento</label>
                            <input class="form-control" type="date" name="pagamentodespesa" id="pagamentodespesa" 
                                   value=""/>
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
    $(document).ready(function () {
        console.log('entrei ready');
        //Carregamos a datatable
        //$("#datatable").DataTable({});
        $('#datatable').DataTable({
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
    
    $(document).ready(function () {
        console.log("entrei na ready do documento");
        $("#valordespesa").maskMoney({
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

    function limparDadosModal() {
        $('#iddespesa').val("0");
        $('#valordespesa').val("");
        $('#vencimentodespesa').val("");
        $('#pagamentodespesa').val("");
        $('#descricao').val("");
        $('#situacao').val("A");
        $('#idfornecedor').val("0");
    }

    function setDadosModal(valor) {
        console.log("Entrei no setDadosModal()");
        limparDadosModal();
        document.getElementById('iddespesa').value = valor;
        document.getElementById('idfornecedor').value = valor;

        var idDes = valor;
        if (idDes != "0") {
            $.getJSON('DespesaCarregar', {idDespesa: idDes}, function (respostaServlet) {
                console.log(respostaServlet);
                var id = respostaServlet.idDespesa;
                if (id != "0") {
                    $('#iddespesa').val(respostaServlet.idDespesa);
                    $('#idfornecedor').val(respostaServlet.fornecedor.idFornecedor);
                    $('#valordespesa').val(respostaServlet.valorDespesa);
                    $('#vencimentodespesa').val(respostaServlet.vencimentoDespesa);
                    $('#pagamentodespesa').val(respostaServlet.pagamentoDespesa);
                    $('#descricao').val(respostaServlet.descricao);
                    $('#situacao').val(respostaServlet.situacao);
                }
            });
        }
    }

    function deletar(codigo) {
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Voc? tem certeza?',
            text: "Voc? deseja trocar a situa??o?",
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
                    url: '${pageContext.request.contextPath}/DespesaExcluir',
                    data: {
                        idDespesa: id
                    },
                    success:
                            function (data) {
                                if (data == 1) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: 'Sucesso',
                                        text: 'Situa??o trocada com sucesso!',
                                        showConfirmButton: true,
                                        timer: 10000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                                    })
                                } else {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'error',
                                        title: 'Erro',
                                        text: 'N?o foi poss?vel trocar a situa??o!',
                                        showConfirmButton: true,
                                        timer: 10000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                                    })
                                }
                            },
                    error:
                            function (data) {
                                window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                            }
                });
            }
            ;
        });
    }

    function validarCampos() {
        console.log("entrei na valida??o de campos");
        if (document.getElementById("idfornecedor").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Fornecedor!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#idfornecedor").focus();
        } else if (document.getElementById("descricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descri??o!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#descricao").focus();
        } else if (document.getElementById("valordespesa").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o valor!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#valordespesa").focus();
        } else {
            gravarDados();
        }
    }

    function gravarDados() {
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'DespesaCadastrar',
            data: {
                iddespesa: $('#iddespesa').val(),
                idfornecedor: $('#idfornecedor').val(),
                valordespesa: $('#valordespesa').val(),
                vencimentodespesa: $('#vencimentodespesa').val(),
                pagamentodespesa: $('#pagamentodespesa').val(),
                descricao: $('#descricao').val(),
                situacao: $('#situacao').val()
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
                                text: 'Despesa gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'N?o foi poss?vel gravar a despesa!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/DespesaListar";
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