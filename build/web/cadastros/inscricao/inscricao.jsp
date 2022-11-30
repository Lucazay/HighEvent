<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header-crud.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>


<div id="layoutSidenav_content">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Inscrições</h1>
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
                        <th align="center">Evento</th>
                        <th align="center">Data</th>
                        <th align="center">Valor</th>
                        <th align="center">Nº Parcelas</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Inativar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="inscricao" items="${inscricoes}">                    
                        <tr>
                            <td align="center">${inscricao.idInscricao}</td>                    
                            <td align="center">${inscricao.evento.nomeEvento}</td>
                            <td align="center">${inscricao.dataInscricao}</td>
                            <td align="center">${inscricao.valorInscricao}</td>
                            <td align="center">${inscricao.nroParcelas}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${inscricao.idInscricao})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${inscricao.idInscricao})">
                                    <button class="btn
                                            <c:out value="${inscricao.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw
                                           <c:out value="${inscricao.situacao == 'A' ? 'fa-times' : ' fa-plus'}"/>"></i>
                                        <Strong>
                                            <c:out value="${inscricao.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
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
                        <label>ID</label> <input class="form-control" type="text" name="idinscricao" id="idinscricao" value="" 
                                                 readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <label>Número de Pulseira</label>
                        <input class="form-control" type="text" name="nropulseira" id="nropulseira"/>
                    </div>

                    <div class="form-group">
                        <label>Chave QR Code</label>
                        <input class="form-control" type="text" name="chaveqrcode" id="chaveqrcode"/>
                    </div>

                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Data de Inscricao</label>
                                <input class="form-control" type="date" name="datainscricao" id="datainscricao" 
                                       value=""/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Valor da Inscrição</label>
                        <input class="form-control" type="text" style="text-align:right;" 
                               name="valorinscricao" id="valorinscricao" 
                               value="<fmt:formatNumber value='${inscricao.valorInscricao}' type='currency'/>" />
                    </div>

                    <div class="form-group">
                        <label>Nº Parcelas</label> 
                        <input class="form-control" type="text" name="nroparcelas" id="nroparcelas" value="" />
                    </div>

                    <div>
                        <div class="form-group">
                            <label>Parcelas Pagas</label>
                            <input class="form-control" type="text" style="text-align:right;" 
                                   name="parcelaspagas" id="parcelaspagas" 
                                   value="<fmt:formatNumber value='${inscricao.parcelasPagas}' type='currency'/>" />
                        </div>

                        <div class="form-group">
                            <label>Parcelas à Pagar</label>
                            <input class="form-control" type="text" style="text-align:right;" 
                                   name="parcelaspagar" id="parcelaspagar" 
                                   value="<fmt:formatNumber value='${inscricao.parcelasPagar}' type='currency'/>" />
                        </div>

                        <div class="form-group">
                            <label>Parcelas Canceladas</label>
                            <input class="form-control" type="text" style="text-align:right;" 
                                   name="parcelascanceladas" id="parcelascanceladas" 
                                   value="<fmt:formatNumber value='${inscricao.parcelasCanceladas}' type='currency'/>" />
                        </div>

                        <div class="form-group">
                            <label>Parcelas Estorno</label>
                            <input class="form-control" type="text" style="text-align:right;" 
                                   name="parcelasestorno" id="parcelasestorno" 
                                   value="<fmt:formatNumber value='${inscricao.parcelasEstorno}' type='currency'/>" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-group">
                            <label>Evento:</label>
                            <select class="form-control" name="idevento" id="idevento" required>
                                <option value="nulo">Selecione</option>
                                <c:forEach var="evento" items="${eventos}">
                                    <option value="${evento.idEvento}"
                                            ${inscricao.evento.idEvento == evento.idEvento ? "selected" : ""}>
                                        ${evento.nomeEvento} 
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Participante:</label>
                            <select class="form-control" name="idparticipante" id="idparticipante" required>
                                <option value="nulo">Selecione</option>
                                <c:forEach var="participante" items="${participantes}">
                                    <option value="${participante.idParticipante}"
                                            ${inscricao.participante.idParticipante == participante.idParticipante ? "selected" : ""}>
                                        ${participante.nomeRazaoPessoa} 
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
    
    $(document).ready(function () {
        console.log("entrei na ready do documento");
        $("#valorinscricao").maskMoney({
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
        $('#idinscricao').val("0");
        $('#nropulseira').val("");
        $('#chaveqrcode').val("");
        $('#datainscricao').val("");
        $('#situacao').val("A");
        $('#valorinscricao').val("");
        $('#nroparcelas').val("0");
        $('#parcelaspagas').val("");
        $('#parcelaspagar').val("");
        $('#parcelascanceladas').val("");
        $('#parcelasestorno').val("");
        $('#idevento').val("0");
        $('#idparticipante').val("0");
    }

    function setDadosModal(valor) {
        console.log("Entrou no setDadosModal()");
        limparDadosModal();
        document.getElementById('idinscricao').value = valor;
        document.getElementById('idevento').value = valor;
        document.getElementById('idparticipante').value = valor;
        var idIns = valor;
        if (idIns != "0") {
            //existe administrador para buscar (alteração)
            $.getJSON('InscricaoCarregar', {idInscricao: idIns}, function (respostaServlet) {
                console.log(respostaServlet.idInscricao);
                var id = respostaServlet.idInscricao;
                if (id != "0") {
                    $('#idinscricao').val(respostaServlet.idInscricao);
                    $('#nropulseira').val(respostaServlet.nroPulseira);
                    $('#chaveqrcode').val(respostaServlet.chaveQRCode);
                    $('#datainscricao').val(respostaServlet.dataInscricao);
                    $('#situacao').val(respostaServlet.situacao);
                    $('#valorinscricao').val(respostaServlet.valorInscricao);
                    $('#nroparcelas').val(respostaServlet.nroParcelas);
                    $('#parcelaspagas').val(respostaServlet.parcelasPagas);
                    $('#parcelaspagar').val(respostaServlet.parcelasPagar);
                    $('#parcelascanceladas').val(respostaServlet.parcelasCanceladas);
                    $('#parcelasestorno').val(respostaServlet.parcelasEstorno);
                    $('#idevento').val(respostaServlet.evento.idEvento);
                    $('#idparticipante').val(respostaServlet.participante.idParticipante);
                }
            });
        }
    }

    function deletar(codigo) {
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você deseja realmente inativar/ativar a inscrição?",
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
                    url: '${pageContext.request.contextPath}/InscricaoExcluir',
                    data: {
                        idInscricao: id
                    },
                    success:
                            function (data) {
                                if (data == 1) {
                                    Swal.fire({
                                        position: 'top-end',
                                        icon: 'error',
                                        title: 'Erro',
                                        text: 'Não foi possível inativar inscrição!',
                                        showConfirmButton: true,
                                        timer: 10000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/InscricaoListar";
                                    })
                                } else {
                                    Swal.fire({
                                        position: 'top-end',
                                        icon: 'success',
                                        title: 'Sucesso',
                                        text: 'Inscrição inativado com sucesso!',
                                        showConfirmButton: true,
                                        timer: 10000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/InscricaoListar";
                                    })
                                }
                            },
                    error:
                            function (data) {
                                window.location.href = "${pageContext.request.contextPath}/InscricaoListar";
                            }
                });
            }
            ;
        });
    }

    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("datainscricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a data de inscrição!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#datainscricao").focus();
        } else if (document.getElementById("valorinscricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o valor da inscricao!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#valorinscricao").focus();
        } else if (document.getElementById("idevento").value == 'nulo') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o evento!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#idevento").focus();
        } else if (document.getElementById("idparticipante").value == 'nulo') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o participante!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#idparticipante").focus();
        }else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'InscricaoCadastrar',
            data: {
                idinscricao: $('#idinscricao').val(),
                nropulseira: $('#nropulseira').val(),
                chaveqrcode: $('#chaveqrcode').val(),
                datainscricao: $('#datainscricao').val(),
                situacao: $('#situacao').val(),
                valorinscricao: $('#valorinscricao').val(),
                nroparcelas: $('#nroparcelas').val(),
                parcelaspagas: $('#parcelaspagas').val(),
                parcelaspagar: $('#parcelaspagar').val(),
                parcelascanceladas: $('#parcelascanceladas').val(),
                parcelasestorno: $('#parcelasestorno').val(),
                idevento: $('#idevento').val(),
                idparticipante: $('#idparticipante').val()
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
                                text: 'inscrição gravado com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/InscricaoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar o inscrição!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/InscricaoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/InscricaoListar";
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