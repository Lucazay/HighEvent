 <!<!-- Link para as functions dos cards -->
    <script src="../js/side_bar.js" type="text/javascript"></script>
    <!--link para os icons boxicons.com-->
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <!--Link para o fontawesome-->
    <script src="https://kit.fontawesome.com/dd673adbed.js" crossorigin="anonymous"></script>
    <!-- Swiper CSS -->
    <link rel="stylesheet" href="css/swiper-bundle.min.css">
    <!--Link do css do sidebar-->
    <link href="CSS/style_adm.css" rel="stylesheet" type="text/css"/>
    <!--<!-- Link do css dos cards -->
    <link href="CSS/style_content.css" rel="stylesheet" type="text/css"/>
<hr>

        <div class="sidebar" id="sidebar1">

        <div class="logo_content">

            <div class="logo">
                <!--<i class='bx bxl-google-cloud'></i>-->
                
                <div class="logo_name">High Event</div>

            </div>

            <i class='bx bx-menu' id="btn" onclick="n1()"></i>

        </div>

            <ul class="nav_list">

                <!--<li>
                    <i class='bx bx-search'></i>
                    <input type="text" placeholder="Buscar...">
                   <span class="tooltip">Dashboard</span>
                </li> -->

                <li> 
                    <a href="${pageContext.request.contextPath}/cadastros/homeLogado.jsp">
                    <i class='bx bx-home'></i>
                    <span class="links_name">Home</span>
                    </a>
                    <span class="tooltip">Home</span>
                    </a>
                </li>

                <li><a href="${pageContext.request.contextPath}/CidadeListar">

                    <i class='bx bxs-buildings'></i>
                    <span class="links_name">Cidades</span>
                    </a>
                    <span class="tooltip">Cidades</span>
                </li>

                <li><a href="${pageContext.request.contextPath}/CursoListar">
                    <i class='bx bxs-graduation'></i>
                    <span class="links_name">Cursos</span>

                    </a>

                    <span class="tooltip">Cursos</span>
            
                </li>

                <li><a href="${pageContext.request.contextPath}/EstadoListar">

                    <i class='bx bxs-city'></i>
                    <span class="links_name">Estados</span>

                    </a>

                    <span class="tooltip">Estados</span>
            
                </li>

                <li><a href="${pageContext.request.contextPath}/FuncaoListar">

                    <i class='bx bx-list-plus'></i>
                    <span class="links_name">Funções</span>

                    </a>

                    <span class="tooltip">Funções</span>
            
                </li>

                <li><a href="${pageContext.request.contextPath}/InstituicaoListar">

                    <i class='bx bx-book'></i>
                    <span class="links_name">Instituições</span>

                    </a>

                    <span class="tooltip">Instituições</span>
            
                </li>

                <li><a href="${pageContext.request.contextPath}/TipoAtividadeListar">

                    <i class='bx bxs-notepad'></i>
                    <span class="links_name">Atividades</span>

                    </a>

                   <span class="tooltip">Atividades</span>
            
                </li>

                <li><a href="${pageContext.request.contextPath}/TipotrabalhoListar">

                    <i class='bx bxs-briefcase'></i>
                    <span class="links_name">Trabalhos</span>

                    </a>

                    <span class="tooltip">Trabalhos</span>
            
                </li>
                <!---->
                <li><a href="${pageContext.request.contextPath}/AdministradorListar">

                        <i class='bx bx-user'> </i>
                    <span class="links_name">Administradores</span>

                    </a>

                    <span class="tooltip">Administradores</span>
            
                </li>
                <!---->
                <li><a href="${pageContext.request.contextPath}/OrganizadorListar">

                    <i class='bx bxs-user-circle'></i>
                    <span class="links_name">Organizadores</span>

                    </a>

                    <span class="tooltip">Organizadores</span>
            
                </li>
                <!---->
                <li><a href="${pageContext.request.contextPath}/PatrocinadorListar">

                    <i class='bx bxs-group'></i>
                    <span class="links_name">Patrocinadores</span>

                    </a>

                    <span class="tooltip">Patrocinadores</span>
            
                </li>
                <!---->
                <li><a href="${pageContext.request.contextPath}/FornecedorListar">

                    <i class='bx bxs-user-rectangle' ></i>
                    <span class="links_name">Fornecedores</span>

                    </a>

                    <span class="tooltip">Fornecedores</span>
            
                </li>
                <!---->
                <li><a href="${pageContext.request.contextPath}/ParticipanteListar">

                    <i class='bx bx-happy'></i>
                    <span class="links_name">Participantes</span>

                    </a>

                    <span class="tooltip">Participantes</span>
            
                </li>

            </ul>

                <div class="profile_content">

                    <div class="profile">

                        <div class="profile_details">


                            <div class="name_job">
                                
                                <div class="nome">${sessionScope.nomeusuario}</div>
                                <div class="job">Patrocinador</div>


                            </div>

                        </div>

                     <a href="${pageContext.request.contextPath}/UsuarioDeslogar"><i class="bx bx-log-out" id="log_out"></i></a>
                    

                    </div>

                </div>

    </div>
 