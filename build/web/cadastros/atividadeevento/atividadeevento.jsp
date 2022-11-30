<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header-crud.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>


<div id="layoutSidenav_content">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">AtividadeEvento</h1>
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
                        <th align="center">Nome</th>
                        <th align="center">Carga Horária</th>
                        <th align="center">Data</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="atividadeevento" items="${atividadeeventos}">                    
                        <tr>
                            <td align="center">${atividadeevento.idAtividadeEvento}</td>                    
                            <td align="center">${atividadeevento.nomeAtividade}</td>
                            <td align="center">${atividadeevento.cargaHoraria}</td>
                            <td align="center">${atividadeevento.dataAtividade}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${atividadeevento.idAtividadeEvento})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${atividadeevento.idAtividadeEvento})" class="btn btn-success adicionar">
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
                        <label>ID</label> <input class="form-control" type="text" name="idatividadeevento" id="idatividadeevento" value="" 
                                                 readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <label>Nome da Atividade</label>
                        <input class="form-control" type="text" name="nomeatividade" id="nomeatividade"/>
                    </div>

                    <div class="form-group">
                        <label>Carga Horária</label>
                        <input class="form-control" type="text" style="text-align:right;" 
                               name="cargahoraria" id="cargahoraria" 
                               value="<fmt:formatNumber value='${atividadeevento.cargaHoraria}' type='currency'/>" />
                    </div>

                    <div class="form-group">
                        <label>Resumo</label>
                        <input class="form-control" type="text" name="resumo" id="resumo"/>
                    </div>

                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Data da Atividade</label>
                                <input class="form-control" type="date" name="dataatividade" id="dataatividade" 
                                       value=""/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Hora</label>
                        <input class="form-control" type="text" style="text-align:right;" 
                               name="horaatividade" id="horaatividade" 
                               value="<fmt:formatNumber value='${atividadeevento.horaAtividade}' type='currency'/>" />
                    </div>

                    <div>
                        <div class="form-group">
                            <label>Evento:</label>
                            <select class="form-control" name="idevento" id="idevento" required>
                                <option value="nulo">Selecione</option>
                                <c:forEach var="evento" items="${eventos}">
                                    <option value="${evento.idEvento}"
                                            ${atividadeevento.evento.idEvento == evento.idEvento ? "selected" : ""}>
                                        ${evento.nomeEvento} 
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Tipo de Atividade:</label>
                            <select class="form-control" name="idtipoatividade" id="idtipoatividade" required>
                                <option value="nulo">Selecione</option>
                                <c:forEach var="tipoatividade" items="${tipoatividades}">
                                    <option value="${tipoatividade.idTipoAtividade}"
                                            ${atividadeevento.tipoatividade.idTipoAtividade == tipoatividade.idTipoAtividade ? "selected" : ""}>
                                        ${tipoatividade.descricao} 
                                    </option>
                                </c:forEach>
                            </select>
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

    function limparDadosModal() {
        $('#idatividadeevento').val("0");
        $('#cargahoraria').val("");
        $('#nomeatividade').val("");
        $('#resumo').val("");
        $('#dataatividade').val("");
        $('#horaatividade').val("");
        $('#idevento').val("0");
        $('#idtipoatividade').val("0");
    }

    function setDadosModal(valor) {
        console.log("Entrei no setDadosModal()");
        limparDadosModal();
        document.getElementById('idatividadeevento').value = valor;
        document.getElementById('idevento').value = valor;
        document.getElementById('idtipoatividade').value = valor;

        var idAte = valor;
        if (idAte != "0") {
            $.getJSON('AtividadeEventoCarregar', {idAtividadeEvento: idAte}, function (respostaServlet) {
                var id = respostaServlet.idAtividadeEvento;
                if (id != "0") {
                    $('#idatividadeevento').val(respostaServlet.idAtividadeEvento);
                    $('#cargahoraria').val(respostaServlet.cargaHoraria);
                    $('#nomeatividade').val(respostaServlet.nomeAtividade);
                    $('#resumo').val(respostaServlet.resumo);
                    $('#dataatividade').val(respostaServlet.dataAtividade);
                    $('#horaatividade').val(respostaServlet.horaAtividade);
                    $('#idevento').val(respostaServlet.evento.idEvento);
                    $('#idtipoatividade').val(respostaServlet.tipoatividade.idTipoAtividade);
                }
            });
        }
    }

    function deletar(codigo) {
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você deseja realmente excluir AtividadeEvento?",
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
                    url: '${pageContext.request.contextPath}/AtividadeEventoExcluir',
                    data: {
                        idAtividadeEvento: id
                    },
                    success:
                            function (data) {
                                if (data == 1) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: 'Sucesso',
                                        text: 'AtividadeEvento excluida com sucesso!',
                                        showConfirmButton: true,
                                        timer: 10000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/AtividadeEventoListar";
                                    })
                                } else {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'error',
                                        title: 'Erro',
                                        text: 'Não foi possível excluir AtividadeEvento!',
                                        showConfirmButton: true,
                                        timer: 10000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/AtividadeEventoListar";
                                    })
                                }
                            },
                    error:
                            function (data) {
                                window.location.href = "${pageContext.request.contextPath}/AtividadeEventoListar";
                            }
                });
            }
            ;
        });
    }

    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomeatividade").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Nome da Atividade!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#nomeatividade").focus();
        } else if (document.getElementById("resumo").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o resumo!',
                showConfirmButton: true,
                timer: 4000
            });
            $("#resumo").focus();
        } else {
            gravarDados();
        }
    }

    function gravarDados() {
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'AtividadeEventoCadastrar',
            data: {
                idatividadeevento: $('#idatividadeevento').val(),
                cargahoraria: $('#cargahoraria').val(),
                nomeatividade: $('#nomeatividade').val(),
                resumo: $('#resumo').val(),
                dataatividade: $('#dataatividade').val(),
                horaatividade: $('#horaatividade').val(),
                idevento: $('#idevento').val(),
                idtipoatividade: $('#idtipoatividade').val()
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
                                text: 'AtividadeEvento gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/AtividadeEventoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a AtividadeEvento!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/AtividadeEventoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/AtividadeEventoListar";
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