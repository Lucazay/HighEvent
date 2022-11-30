<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header-crud.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>

<div id="layoutSidenav_content">
    
<div class="container-fluid">
    <h2>Eventos</h2>
    <table id="datatable" class="table table-striped table-bordered basic-datatable">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Nome do Evento</th>
                <th align="left">Valor do Evento</th>
                <th align="left">Curso</th>
                <th align="left">Situação</th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>           
        </thead>
        <tbody>
            <c:forEach var="evento" items="${eventos}">
                <tr>
                    <td align="left">${evento.idEvento}</td>
                    <td align="left">${evento.nomeEvento}</td>
                    <td align="left">${evento.valorEvento}</td>
                    <td align="left">${evento.curso.nomeCurso}</td>
                    <td align="left">${evento.situacaoEvento}</td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/EventoExcluir?idEvento=${evento.idEvento}">
                            <button class="btn btn-group-lg
                                    <c:out value="${evento.situacaoEvento == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                <c:out value="${evento.situacaoEvento == 'A' ? 'Inativar': 'Ativar'}"/>
                            </button>                                    
                        </a>
                    </td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/EventoCarregar?idEvento=${evento.idEvento}">
                            <button class="btn-success btn"/>Alterar</button>                       
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>   
    
    <div align="center">
    <a href="${pageContext.request.contextPath}/EventoNovo"><button class="btn btn-success"/>Novo</button></a>
</div>
    
</div>

</div>
    <script>
        $(document).ready(function(){
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
        
        $(document).ready(function () {
        console.log("entrei na ready do documento");
        $("#valorevento").maskMoney({
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
