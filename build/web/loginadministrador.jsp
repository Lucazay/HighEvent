<div class="login">
	<h1>Login Administrador</h1>
        <link href="logincss.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <form method="post">
    	<input type="text" name="u" placeholder="Usuário" required="required" />
        <input type="password" name="p" placeholder="Senha" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large"><a href="${pageContext.request.contextPath}/EstadoListar">Entrar</a>
</button>
 <button type="submit" class="btn btn-primary btn-block btn-large mt-2"><a href="index.jsp">Voltar</a>
</button>
    </form>
</div>