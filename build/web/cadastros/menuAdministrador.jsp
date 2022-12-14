
<jsp:include page="/header.jsp"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
        integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
        crossorigin="anonymous" />
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
        integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
        crossorigin="anonymous" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/sidebar.css" rel="stylesheet" />
        <link href="../css/styles.css" rel="stylesheet" />
        <link href="../css/footer.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
         <script src="../js/scripts.js" type="text/javascript"></script>
        
        <!--<h2> Menu Principal </h2>
        <a href="${pageContext.request.contextPath}/EstadoListar">Estado </a>
        <a href="${pageContext.request.contextPath}/CidadeListar">Cidade </a>
        <a href="${pageContext.request.contextPath}/FuncaoListar">Funcao </a>
        <a href="${pageContext.request.contextPath}/InstituicaoListar">Instituicao </a>
        <a href="${pageContext.request.contextPath}/CursoListar">Curso </a>
        <a href="${pageContext.request.contextPath}/TipotrabalhoListar">TipoTrabalho </a>
        <a href="${pageContext.request.contextPath}/TipoAtividadeListar">TipoAtividade </a>
        <a href="${pageContext.request.contextPath}/EventoListar">Evento </a>
        <a href="${pageContext.request.contextPath}/OrganizadorEventoListar?idevento=0">OrganizadorEvento </a>
        <a href="${pageContext.request.contextPath}/DoacaoListar">Doa??o </a>
        <a href="${pageContext.request.contextPath}/DespesaListar">Despesa </a>
        <a href="${pageContext.request.contextPath}/InscricaoListar">Inscricao </a>
        <a href="${pageContext.request.contextPath}/AtividadeEventoListar">AtividadeEvento </a>
        <br/>
        <a href="${pageContext.request.contextPath}/AdministradorListar">Administrador </a>
        <a href="${pageContext.request.contextPath}/OrganizadorListar">Organizador </a>
        <a href="${pageContext.request.contextPath}/PatrocinadorListar">Patrocinador </a>
        <a href="${pageContext.request.contextPath}/FornecedorListar">Fornecedor </a>
        <a href="${pageContext.request.contextPath}/ParticipanteListar">Participante </a>-->
        
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/cadastros/homeLogado.jsp" style="margin-left: 0px;"><img src="img/logoOficial.png" alt=""/>igh Event</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
     <!--  <div class="input-group">
        <li class="nav-item" id="nome_usuario">Luiz Filipe</li>
    
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
    
        </div>  --> 


    </form>
    <!-- Navbar
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">

            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#!">Settings</a></li>
                <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="#!">Logout</a></li>
            </ul>
        </li>


    </ul>-->
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    
                    
                    <div class="sb-sidenav-menu-heading" style="color: white;">Nome: ${sessionScope.nomeusuario}</div>
                    <div class="sb-sidenav-menu-heading" style="color: white;">Tipo: ${sessionScope.tipousuario}</div>
                    <div class="sb-sidenav-menu-heading" style="color: white;"><a href="${pageContext.request.contextPath}/UsuarioDeslogar">Sair do Sistema </a></div>
                    <a class="nav-link" href="${pageContext.request.contextPath}/EstadoListar">
                        <div class="sb-nav-link-icon"><i class='bx bxs-city'></i></div>
                       Estado
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/CidadeListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-buildings'></i></div>
                        Cidade
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/FuncaoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-briefcase'></i></div>
                        Fun??o 
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/InstituicaoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-bookmarks' ></i></div>
                        Institui??o
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/CursoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-book'></i></div>
                        Curso
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/TipotrabalhoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-briefcase-alt'></i></div>
                        Tipo Trabaho
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/TipoAtividadeListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-edit'></i></div>
                        Tipo Atividade
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/EventoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-calendar-event' ></i></div>
                        Evento
                    </a>

                    <a class="nav-link" href="${pageContext.request.contextPath}/OrganizadorEventoListar?idevento=0">
                        <div class="sb-nav-link-icon"><i <i class='bx bx-clipboard'></i></div>
                        Organizador Evento
                    </a>
                    
                     <a class="nav-link" href="${pageContext.request.contextPath}/DoacaoListar">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        Doa??o
                    </a>
                    
                     <a class="nav-link" href="${pageContext.request.contextPath}/DespesaListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-money-withdraw' ></i></div>
                        Despesa
                    </a>
                    
                     <a class="nav-link" href="${pageContext.request.contextPath}/InscricaoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-bookmark-alt-minus'></i></div>
                        Inscri??o
                    </a>
                        
                     <a class="nav-link" href="${pageContext.request.contextPath}/AtividadeEventoListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-calendar-exclamation'></i></div>
                        Atividade Evento
                    </a>
                        
                    <a class="nav-link" href="${pageContext.request.contextPath}/AdministradorListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-user-circle'></i></div>
                        Administrador
                    </a>
                        
                        <a class="nav-link" href="${pageContext.request.contextPath}/OrganizadorListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-user-pin' ></i></div>
                        Organizador
                    </a>
                        
                        <a class="nav-link" href="${pageContext.request.contextPath}/PatrocinadorListar">
                        <div class="sb-nav-link-icon"><i class='bx bx-user' ></i></div>
                        Patrocinador
                    </a> 
                        
                        <a class="nav-link" href="${pageContext.request.contextPath}/FornecedorListar">
                        <div class="sb-nav-link-icon"><i class='bx bxs-user-circle' ></i></div>
                        Fornecedor
                    </a> 
                        
                        <a class="nav-link" href="${pageContext.request.contextPath}/ParticipanteListar">
                        <div class="sb-nav-link-icon"><i class='bx bxs-user-account'></i></div>
                        Participante
                    </a>

                  <!-- <div class="sb-sidenav-menu-heading">Interface</div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        Layouts
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="layout-static.html">Static Navigation</a>
                            <a class="nav-link" href="layout-sidenav-light.html">Light Sidenav</a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                        <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                        Pages
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                Authentication
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="login.html">Login</a>
                                    <a class="nav-link" href="register.html">Register</a>
                                    <a class="nav-link" href="password.html">Forgot Password</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                Error
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="401.html">401 Page</a>
                                    <a class="nav-link" href="404.html">404 Page</a>
                                    <a class="nav-link" href="500.html">500 Page</a>
                                </nav>
                            </div>
                        </nav>
                    </div>
                    <div class="sb-sidenav-menu-heading">Addons</div>
                    <a class="nav-link" href="charts.html">
                        <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                        Charts
                    </a>
                    <a class="nav-link" href="tables.html">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        Tables
                    </a>-->


                </div>
            </div>

        </nav>
    </div>

