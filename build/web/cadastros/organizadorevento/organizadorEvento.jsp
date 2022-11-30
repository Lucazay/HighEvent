<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header-crud.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>


<div id="layoutSidenav_content">
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">OrganizadorEvento</h1>
    <p class="mb-4">Planilha de Registros</p>
    <a href="#modaladicionar" class="btn btn-success mb-4 adicionar" data-toggle="modal" 
       data-id="" onclick="setDadosModal(${0})">
        <i class="fas fa-plus fa-fw"></i> Adicionar </a>
    <div class="card-body">
        <table id="datatable" class="table table-striped table-bordered basic-datatable">
            <thead>
                <tr>
                    <th align="left">Nome do Evento</th>
                    <th align="left">Organizador</th>
                    <th align="left">Função</th>
                    <th align="right"></th>
                    <th align="right"></th>
                </tr>           
            </thead>
            <tbody>
                <c:forEach var="organizadorevento" items="${organizadoreventos}">
                <td align="left">${organizadorevento.evento.nomeEvento}</td>
                <td align="left">${organizadorevento.organizador.nomeRazaoPessoa}</td>
                <td align="left">${organizadorevento.funcao.descricao}</td>
                <td align="center">
                    <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                       data-id="" onclick="setDadosModal(${organizadorevento.idOrganizadorEvento})">
                        <i class="fas fa-plus fa-fw"></i> Alterar </a>
                </td>
                <td align="center">
                    <a href= "${pageContext.request.contextPath}/OrganizadorEventoExcluir?idorganizadorevento=${organizadorevento.idOrganizadorEvento}">
                        <button class="btn btn-danger"/>Excluir</button></a>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>   
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

                    <div class="col-lg-9">
                        <div class="card-body">
                            <label>ID</label> <input class="form-control" type="text" name="idorganizadorevento" id="idorganizadorevento" value="" 
                                                     readonly="readonly"/>
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

                            <div class="form-group">
                                <button class="btn btn-success" type="submit" id="submit" onclick="validarCamposOrganizador()">
                                    Salvar Organizador</button>
                                <button class="btn btn-success" type="submit" id="submit" onclick="chamarTelaListarOrganizadorEvento()">
                                    Visualizar Organizadores</button>
                            </div> 
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div><!-- comment -->


    <script>
        $(document).ready(function () {
            console.log('entrei ready');
            $('#datatable').DataTable({
                "oLanguege": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_registros",
                    "sZeroRecords": "Nenhum registtro encontrado.",
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
            $('#idorganizadorevento').val("0");
            $('#idorganizador').val("0");
            $('#idevento').val("0");
            $('#idfuncao').val("0");
        }

        function setDadosModal(valor) {
            console.log("Entrei no setDadosModal()");
            limparDadosModal();
            document.getElementById('idorganizadorevento').value = valor;
            var idOrgEv = valor;
            if (idOrgEv != "0") {
                $.getJSON('OrganizadorEventoCarregar', {idOrganizadorEvento: idOrgEv}, function (respostaServlet) {
                    var id = respostaServlet.idOrganizadorEvento;
                    if (id != "0") {
                        $('#idorganizadorevento').val(respostaServlet.idOrganizadorEvento);
                        $('#idorganizador').val(respostaServlet.organizador.idOrganizador);
                        $('#idevento').val(respostaServlet.evento.idEvento);
                        $('#idfuncao').val(respostaServlet.funcao.idFuncao);
                    }
                });
            }
        }

        function chamarTelaListarOrganizadorEvento() {
            var id = document.getElementById("idevento").value;
            window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar?idevento=" + id;
        }

        function validarCamposOrganizador() {
            console.log("entrei na validação de campos do organizador");
            if (document.getElementById("idorganizador").value == 'nulo') {
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
            } else if (document.getElementById("idfuncao").value == 'nulo') {
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: 'Verifique a Função!',
                    showConfirmButton: false,
                    timer: 1000
                });
                $("#idfuncao").focus();
            } else {
                gravarDadosOrganizador();
            }
        }

        function gravarDadosOrganizador() {
            console.log("Gravando dados do organizador ....");
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
                            if (data == 0) {
                                Swal.fire({
                                    position: 'center',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possível gravar o OrganizadorEvento!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function () {
                                    // window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar";
                                })
                            } else {
                            var jSon = JSON.parse(data);
                            var id = jSon.idEvento;
                                Swal.fire({
                                    position: 'center',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'OrganizadorEvento gravado com sucesso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function () {
                                    window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar?idevento="+0;
                                })
                            }
                        },
                error:
                        function (data) {
                            //   window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar";
                        }
            });
        }

        function limparDadosOrganizador() {
            $('#idfuncao').val("nulo");
            $('#idorganizador').val("nulo");
            $('#idorganizadorevento').val("0");

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
    
    <%@include file="/footer.jsp" %>
