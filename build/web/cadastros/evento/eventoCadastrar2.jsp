<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>

<div class="container-fluid" >
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Evento</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/EventoListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>     
    <div class="col-lg-9" >
        <div class="card shadow mb-4">
            <div class="card-body">
                <div class="form-group">
                    <img alt="imagem" class="" 
                         name="foto" id="foto" width="100" heigth="150">
                    <input type="file" id="gallery-photo-add" class="inputfile" onchange="uploadFile();"/>
                    <br/>
                    <label for="gallery-photo-add" class="btn btn-success">
                        <i class="fas fa-file-upload"></i> 
                        Selecionar Foto...
                    </label>
                </div>
                <div class="form-group">
                    <label>Id</label>
                    <input class="form-control" type="text" name="idevento" id="idevento" 
                           value="${evento.idEvento}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>Nome Evento</label>
                    <input class="form-control" type="text" name="nomeevento" id="nomeevento" 
                           value="${evento.nomeEvento}" size="100" maxlength="100"/>
                </div>
                <div class="form-group">
                    <div class="form-line row">
                        <div class="col-sm">
                            <label>Data de Início</label>
                            <input class="form-control" type="date" name="datainicioevento" id="datainicioevento" 
                                   value=""/>
                        </div>
                        <div class="col-sm">
                            <label>Data de Término</label>
                            <input class="form-control" type="date" name="dataterminoevento" id="dataterminoevento" 
                                   value=""/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label>Valor do Evento</label>
                    <input class="form-control" type="text" style="text-align:right;" 
                           name="valorevento" id="valorevento" 
                           value="<fmt:formatNumber value='${evento.valorEvento}' type='currency'/>" />
                </div>
                <div class="form-group">
                    <label>Valor do Evento à prazo</label>
                    <input class="form-control" type="text" style="text-align:right;" 
                           name="valoreventoprazo" id="valoreventoprazo" 
                           value="<fmt:formatNumber value='${evento.valorEventoPrazo}' type='currency'/>" />
                </div>
                <div class="form-group">
                    <label>informações sobre o Evento</label>
                    <input class="form-control" type="text" name="informacaoevento" id="informacaoevento" 
                           value="${evento.informacaoEvento}" size="100" maxlength="100"/>
                </div>
                <div class="form-group">
                    <label>Situação Evento</label>
                    <input class="form-control" type="text" name="situacaoevento" id="situacaoevento" 
                           value="${evento.situacaoEvento}" size="100" maxlength="100" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>Saldo do Caixa</label>
                    <input class="form-control" type="text" style="text-align:right;" 
                           name="saldocaixa" id="saldocaixa" 
                           value="<fmt:formatNumber value='${evento.saldoCaixa}' type='currency'/>" />
                </div>
                <div class="form-group">
                    <label>Situação do Caixa</label>
                    <input class="form-control" type="text" name="situacaocaixa" id="situacaocaixa" 
                           value="${evento.situacaoCaixa}" size="100" maxlength="100"/>
                </div>
                <div class="form-group">
                    <label>Curso:</label>
                    <select class="form-control" name="idcurso" id="idcurso" required>
                        <option value="nulo">Selecione</option>
                        <c:forEach var="curso" items="${cursos}">
                            <option value="${curso.idCurso}"
                                    ${evento.curso.idCurso == curso.idCurso ? "selected" : ""}>
                                ${curso.nomeCurso} 
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <!-- Botão de Confirmação --> 
                <div class="form-group">
                    <button class="btn btn-success" type="submit" id="submit" onclick="validarCamposEvento()">
                        Salvar Evento</button>
                </div>
                <div id="cadastroprofissional" style="display:none" align="center">
                    <input class="form-control" type="hidden" name="idorganizadorevento" id="idorganizadorevento" 
                           value="0" readonly="readonly"/>
                    <div class="col-lg-9">
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <!-- <div class="form-group">
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
                        </div>-->   

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
                                <!-- Botão de Confirmação --> 
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

        $("#valoreventoprazo").maskMoney({
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

        $("#saldocaixa").maskMoney({
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

    function chamarTelaListarOrganizadorEvento(){
        var id = document.getElementById("idevento").value;
        window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar?idevento="+id;
    }
    function validarCamposEvento() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomeevento").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Nome do Evento!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomeevento").focus();
        } else if (document.getElementById("datainicioevento").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a Data de Início!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#datainicioevento").focus();
        } else if (document.getElementById("dataterminoevento").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a Data de Término!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#dataterminoevento").focus();
        } else if (document.getElementById("valorevento").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o valor do Evento!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#valorevento").focus();
        } else {
            gravarDadosEvento();
        }
    }

    function gravarDadosEvento() {
        console.log("Gravando dados ....");
        var target = document.getElementById("foto").src;
        $.ajax({
            type: 'post',
            url: 'EventoCadastrar',
            data: {
                idevento: $('#idevento').val(),
                nomeevento: $('#nomeevento').val(),
                valorevento: $('#valorevento').val(),
                valoreventoprazo: $('#valoreventoprazo').val(),
                datainicioevento: $('#datainicioevento').val(),
                dataterminoevento: $('#dataterminoevento').val(),
                informacaoevento: $('#informacaoevento').val(),
                situacaoevento: $('#situacaoevento').val(),
                saldocaixa: $('#saldocaixa').val(),
                situacaocaixa: $('#situacaocaixa').val(),
                idcurso: $('#idcurso').val(),
                foto: target
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
                                text: 'Não foi possível gravar o evento!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar";
                            })
                        } else {
                            var jSon = JSON.parse(data);
                            var id = jSon.idEvento;
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'Evento gravado com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                $('#idevento').val(id);
                                document.getElementById("cadastroprofissional").style.display = "block";

                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/OrganizadorEventoListar";
                    }
        });
    }

    function uploadFile() {
        var target = document.getElementById("foto");
        target.src = "";
        var file = document.querySelector("input[type='file']").files[0];
        if (file) {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function () {
                target.src = reader.result;
            };
        } else {
            target.src = "";
        }
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
                        if (data != 0) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'OrganizadorEvento gravado com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                limparDadosOrganizador();

                            })
                        } else {
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

<%@include file="/footer.jsp" %>                     
