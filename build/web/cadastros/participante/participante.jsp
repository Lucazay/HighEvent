<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/cadastros/menuLogado.jsp"/>
<div class="home_content">
    
    
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Participante</h1>
    <p class="mb-4">Planilha de Registros</p>
    
    <div class="card shadow table-responsive">
        <div class="card-body">
            <table id="datatable" class="table table-striped ">
                <thead>
                    <tr>
                        <th align="center">ID</th>
                        <th align="center">Nome</th>
                        <th align="center">Sexo</th>
                        <th align="center">CPF/CNPJ</th>
                        <th align="center">Cidade - UF</th>
                        <th Align="center">Alterar</th>
                        <th Align="center">Inativar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="participante" items="${participantes}">                    
                        <tr>
                            <td align="center">${participante.idParticipante}</td>                    
                            <td align="center">${participante.nomeRazaoPessoa}</td>
                            <td align="center">${participante.sexoParticipante}</td>
                            <td align="center">${participante.cpfCnpjPessoa}</td>
                       <td align="center">${participante.cidade.nomeCidade}-${participante.cidade.estado.siglaEstado}</td>
                            <td align="center">
                                <a href="#modaladicionar" class="btn btn-success adicionar" data-toggle="modal" 
                                   data-id="" onclick="setDadosModal(${participante.idParticipante})">
                                    <i class="fas fa-plus fa-fw"></i> Alterar </a>
                            </td>
                            <td align="center">
                                <a href="#" onclick="deletar(${participante.idParticipante})">
                                    <button class="btn 
                                          <c:out value="${participante.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw 
                                           <c:out value="${participante.situacao == 'A' ? 'fa-times' : ' fa-plus'}"/>"></i>
                                        <Strong>
                                            <c:out value="${participante.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
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
                        <label>ID PESSOA</label> <input class="form-control" type="text" name="idpessoa" id="idpessoa" value="" 
                               readonly="readonly"/>
                        <label>ID PARTICIPANTE</label> <input class="form-control" type="text" name="idparticipante" id="idparticipante" 
                               value="" readonly="readonly"/>
                        <label>SITUAÇÃO CADASTRO</label> <input class="form-control" type="text" name="situacao" id="situacao" 
                               value="" readonly="readonly"/>
                    </div>
                    
                    <div class="form-group">
                        <label>Nome/Razão Social:</label>
                        <input class="form-control" type="text" name="nomerazaopessoa" id="nomerazaopessoa"/>
                    </div>
                    
                    <div class="form-group">
                        <label>RG:</label>
                        <input class="form-control" type="text" name="rglepessoa" id="rglepessoa" 
                               value=""/>
                    </div>

                    <div class="form-group">
                        <label>CPF/Cnpj:</label>
                        <input class="form-control" type="text" name="cpfcnpjpessoa" id="cpfcnpjpessoa" 
                               value=""/>
                    </div>

                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                    <label class="m-t-0 header-title">Data Nascimento:</label>
                                    <input class="form-control" type="date" name="datanascfundpessoa" id="datanascfundpessoa" 
                                           value="" />
                            </div>
                            </div>        
                    </div>
                    
                    <div class="form-group">
                        <label>Telefone:</label>
                        <input class="form-control" type="text" name="telefonepessoa" id="telefonepessoa"/>
                    </div>
                    
                    <div class="form-group">
                        <label>Email:</label>
                        <input class="form-control" type="text" name="emailpessoa" id="emailpessoa"/>
                    </div>
                    
                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Estado:</label>
                                <select class="form-control" name="idestado" id="idestado" 
                                        onchange="BuscarCidadesPorEstado()" required>
                                    <option value="nulo">Selecione</option>
                                    <c:forEach var="estado" items="${estados}">
                                        <option value="${estado.idEstado}"
                                                ${participante.cidade.estado.idEstado == estado.idEstado ? "selected" : ""}>
                                            ${estado.nomeEstado}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm">
                                <label>Cidade:</label>
                                <select class="form-control" name="idcidade" id="idcidade" required>
                                    <option value="nulo">Selecione</option>
                                    <c:forEach var="cidade" items="${cidades}">
                                        <option value="${cidade.idCidade}"
                                                ${participante.cidade.idCidade == cidade.idCidade ? "selected" : ""}>
                                            ${cidade.nomeCidade} 
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Logradouro:</label>
                                <input class="form-control" type="text" name="logradouropessoa" id="logradouropessoa"/>
                            </div>
                            <div class="col-sm">
                                <label>Número:</label>
                                <input class="form-control" type="text" name="numlogradouropessoa" id="numlogradouropessoa"/>
                            </div>
                            <div class="col-sm">
                                <label>Bairro:</label>
                                <input class="form-control" type="text" name="bairropessoa" id="bairropessoa"/>
                            </div>
                            <div class="col-sm">
                                <label>CEP:</label>
                                <input class="form-control" type="text" name="ceppessoa" id="ceppessoa"/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Login:</label>
                                <input class="form-control" type="text" name="login" id="login" value="" size="20" 
                                       maxlength="20" required/>
                            </div>
                            <div class="col-sm">
                                <label>Senha:</label>
                                <input class="form-control" type="password" name="senha" id="senha" value="" size="20" 
                                       maxlength="20" required/>
                            </div>
                            <div class="col-sm">
                                <label>Permite Login:</label>
                                <select class="form-control" name="permitelogin" id="permitelogin">
                                    <option value="N" ${participante.permiteLogin == 'N' ? "selected" : ""}>Não</option>
                                    <option value="S" ${participante.permiteLogin == 'S' ? "selected" : ""}>Sim</option>       
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
    
    var cidade = ''; //variavel para controle do carregamento de cidades.
    function limparDadosModal(){
        $('#idpessoa').val("0");
        $('#idparticipante').val("0");
        $('#nomerazaopessoa').val("");
        $('#rglepessoa').val("");
        $('#cpfcnpjpessoa').val("");
        $('#datanascfundpessoa').val("");
        $('#telefonepessoa').val("");
        $('#logradouropessoa').val("");
        $('#numlogradouropessoa').val(""); 
        $('#bairropessoa').val(""); 
        $('#ceppessoa').val(""); 
        $('#emailpessoa').val("");
        $('#sexoparticipante').val("");
        $('#situacao').val("");
        $('#idestado').val("0");
        //cidade = 0;
        //BuscarCidadesPorEstado(); //atualiza lista de cidades
        $('#login').val("");
        $('#senha').val("");
        $('#permitelogin').val("S");
        foto.src = "";
    }
    
    function setDadosModal(valor) {
        limparDadosModal();
        var foto = document.getElementById("foto");
        document.getElementById('idpessoa').value = valor;
        document.getElementById('idparticipante').value = valor;
        var idPar = valor;
        if (idPar != "0"){
            //existe participante para buscar (alteração)
            $.getJSON('ParticipanteCarregar', {idParticipante: idPar}, function(respostaServlet){
                console.log(respostaServlet);
                var id = respostaServlet.idParticipante;
                if(id != "0"){
                    $('#idpessoa').val(respostaServlet.idPessoa);
                    $('#idparticipante').val(respostaServlet.idParticipante);
                    $('#nomerazaopessoa').val(respostaServlet.nomeRazaoPessoa);
                    $('#rglepessoa').val(respostaServlet.rglePessoa);
                    $('#cpfcnpjpessoa').val(respostaServlet.cpfCnpjPessoa);
                    $('#datanascfundpessoa').val(respostaServlet.dataNascFundPessoa);
                    $('#telefonepessoa').val(respostaServlet.telefonePessoa);
                    $('#logradouropessoa').val(respostaServlet.logradouroPessoa);
                    $('#numlogradouropessoa').val(respostaServlet.numLogradouroPessoa);
                    $('#bairropessoa').val(respostaServlet.bairroPessoa);
                    $('#ceppessoa').val(respostaServlet.cepPessoa);
                    $('#emailpessoa').val(respostaServlet.emailPessoa);
                    $('#situcao').val(respostaServlet.situacao);
                    $('#sexoparticipante').val(respostaServlet.sexoParticipante);
                    $('#idestado').val(respostaServlet.cidade.estado.idEstado);
                    cidade = respostaServlet.cidade.idCidade;
                    BuscarCidadesPorEstado(); //atualiza lista de cidades
                    $('#login').val(respostaServlet.login);
                    $('#senha').val(respostaServlet.senha);
                    $('#permitelogin').val(respostaServlet.permiteLogin);
                    foto.src = respostaServlet.foto;
                }
            });
        }
    }
    
    function carregarPessoa(v) {
        //console.log("Entrou");
        var idM = v;
        var tipoPessoa = 'participante';
        //console.log("Usuario = " + idM);
        $(document).ready(function () {
            $.getJSON('PessoaBuscarCpfCnpj', {cpfcnpjpessoa: idM, tipopessoa: tipoPessoa}, function (respostaPar) {
                console.log(respostaPar);
                //var id = respostaPar.idParticipante;
                if (respostaPar != null)
                {
                    $('#idparticipante').val(respostaPar.idParticipante);
                    $('#sexoparticipante').val(respostaPar.sexoParticipante);
                    $('#permitelogin').val(respostaPar.permiteLogin);
                    $('#situacao').val(respostaPar.situacao);
                    $('#idpessoa').val(respostaPar.idPessoa);
                    $('#nomerazaopessoa').val(respostaPar.nomeRazaoPessoa);
                    $('#rglepessoa').val(respostaPar.rglePessoa);
                    $('#telefonepessoa').val(respostaPar.telefonePessoa);
                    $('#logradouropessoa').val(respostaPar.logradouroPessoa);
                    $('#numlogradouropessoa').val(respostaPar.numLogradouroPessoa);
                    $('#bairropessoa').val(respostaPar.bairroPessoa);
                    $('#ceppessoa').val(respostaPar.cepPessoa);
                    $('#emailpessoa').val(respostaPar.emailPessoa);
                    $('#login').val(respostaPar.login);
                    var datanasc = respostaPar.dataNascFundPessoa;
                    console.log(datanasc);
                    $('#datanascfundpessoa').val(datanasc);
                    $('#idestado').val(respostaPar.cidade.estado.idEstado);
                    cidade = respostaPar.cidade.idCidade;
                    BuscarCidadesPorEstado();
                    var foto = document.getElementById("foto");                    
                    foto.src = respostaPar.foto;
                } else {
                    //se não encontrou participante busca por pessoa somente
                    tipoPessoa = 'pessoa';
                    $.getJSON('PessoaBuscarCpfCnpj', {cpfcnpjpessoa: idM, tipopessoa: tipoPessoa}, function (respostaPessoa) {
                        console.log(respostaPessoa);
                        var id = respostaPessoa.idPessoa;
                        if (id != "0")
                        {
                            $('#idpessoa').val(respostaPessoa.idPessoa);
                            $('#nomerazaopessoa').val(respostaPessoa.nomeRazaoPessoa);
                            $('#login').val(respostaPessoa.login);
                            var datanasc = respostaPessoa.dataNascFundPessoa;
                            console.log(datanasc);
                            $('#datanascfundpessoa').val(datanasc);
                            $('#idestado').val(respostaPessoa.cidade.estado.idEstado);
                            cidade = respostaPessoa.cidade.idCidade;
                            BuscarCidadesPorEstado();
                            var foto = document.getElementById("foto");                    
                            foto.src = respostaPessoa.foto;
                        }
                    });
                }
            });
        });
    }
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você deseja realmente inativar/ativar o Participante?",
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
                    url: '${pageContext.request.contextPath}/ParticipanteExcluir',
                    data:{
                        idParticipante: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Participante inativado com sucesso!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/ParticipanteListar";
                                })
                            } else {
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'Não foi possível inativar Participante!',
                                    showConfirmButton: true,
                                    timer: 10000
                                }).then(function(){
                                    window.location.href = "${pageContext.request.contextPath}/ParticipanteListar";
                                })
                            }
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/DespesaListar";
                        }
                });
            };
        });
    }
    
    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomerazaopessoa").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o Nome do Participante!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#nomerazaopessoa").focus();
        } else if (document.getElementById("datanascfundpessoa").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a Data de nascimento!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#datanascfundpessoa").focus();
        } else if (document.getElementById("idcidade").value == 'nulo') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a cidade!',
                showConfirmButton: true,
                timer: 2000
            });
            $("#idcidade").focus();
        } else {
            gravarDados();
        }
    }
    function gravarDados() {
        console.log("Gravando dados ....");
        var target = document.getElementById("foto").src;
        $.ajax({
            type: 'post',
            url: 'ParticipanteCadastrar',
            data: {
                idparticipante: $('#idparticipante').val(),
                idpessoa: $('#idpessoa').val(),
                nomerazaopessoa: $('#nomerazaopessoa').val(),
                rglepessoa: $('#rglepessoa').val(),
                cpfcnpjpessoa: $('#cpfcnpjpessoa').unmask().val(),
                datanascfundpessoa: $('#datanascfundpessoa').val(),
                telefonepessoa: $('#telefonepessoa').val(),
                logradouropessoa: $('#logradouropessoa').val(),
                numlogradouropessoa: $('#numlogradouropessoa').val(),
                bairropessoa: $('#bairropessoa').val(),
                ceppessoa: $('#ceppessoa').val(),
                emailpessoa: $('#emailpessoa').val(),
                idcidade: $("#idcidade").val(),
                login: $("#login").val(),
                senha: $("#senha").val(),
                sexoparticipante: $("#sexoparticipante").val(),
                permitelogin: $("#permitelogin").val(),
                situacao: $("#situacao").val(),
                fotopessoa: target
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
                                text: 'Participante gravado com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/ParticipanteListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar o Participante!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/ParticipanteListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/ParticipanteListar";
                    }
        });
    }
    
    function BuscarCidadesPorEstado(){
        $('#idcidade').empty();
        idEst = $('#idestado').val();
        console.log("entrou buscar estado");
        if (idEst != 'null')
        {
            console.log("estado = " + idEst);
            url = "CidadeBuscarPorEstado?idestado=" + idEst;
            //console.log(url);
            $.getJSON(url, function (result) {
                //alert(result);
                $.each(result, function (index, value) {
                    $('#idcidade').append('<option id="cidade_' + value.idCidade + '"value="' + value.idCidade + '">' + 
                            value.nomeCidade + '</option>');
                    if (cidade !== '') {
                        $('#cidade_' + cidade).prop({selected: true});
                    } else {
                        $('#cidade_').prop({selected: true});
                    }
                });
            }).fail(function (obj, textStatus, error) {
                alert('Erro do servidor: ' + textStatus + ', ' + error);
            });
        }
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
</script>
 <%@include file="/footer.jsp"%>
