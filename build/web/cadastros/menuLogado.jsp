<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp"/>


    <c:if test="${sessionScope.tipousuario == 'Administrador'}">
        <jsp:include page="menuAdministrador.jsp"/>        
    </c:if>
    <c:if test="${sessionScope.tipousuario == 'Organizador'}">
        <jsp:include page="menuOrganizador.jsp"/>        
    </c:if>
    <c:if test="${sessionScope.tipousuario == 'Fornecedor'}">
        <jsp:include page="menuFornecedor.jsp"/>        
    </c:if>
    <c:if test="${sessionScope.tipousuario == 'Patrocinador'}">
        <jsp:include page="menuPatrocinador.jsp"/>        
    </c:if>
    <c:if test="${sessionScope.tipousuario == 'Participante'}">
        <jsp:include page="menuParticipante.jsp"/>        
    </c:if>

