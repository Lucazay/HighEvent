<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<!--<h1> Módulo Cadastros </h1>
<h2> Menu Principal - Logado: ${sessionScope.nomeusuario} - ${sessionScope.tipousuario} - ${sessionScope.foto} -
    <a href="${pageContext.request.contextPath}/UsuarioDeslogar">Sair do Sistema </a> </h2>-->
<title></title>
    <c:if test="${sessionScope.tipousuario == 'Administrador'}">
        <jsp:include page="menuAdministrador.jsp"/>        
    </c:if>
    <c:if test="${sessionScope.tipousuario == 'Organizador'}">
        <jsp:include page="menuOrganizador.jsp"/>        
    </c:if>
    <c:if test="${sessionScope.tipousuario == 'Participante'}">
        <jsp:include page="menuParticipante.jsp"/>        
    </c:if>