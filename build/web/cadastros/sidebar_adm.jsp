<%-- 
    Document   : sidebar_adm
    Created on : 25/09/2022, 15:54:59
    Author     : luizg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--Icone do site-->
    <link rel="icon" type="image/x-icon" src="/Img/logo_definitiva.png">
    <link rel="icon" type="image/x-icon" href="/Img/logo_definitiva.png">
    <!--Link para as  functions do side bar-->
    <script src="JS/side_bar.js" type="text/javascript"></script>
    <!<!-- Link para as functions dos cards -->
    <script src="JS/Cards_img.js" type="text/javascript"></script>
    <!--link para os icons boxicons.com-->
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <!--Link para o fontawesome-->
    <script src="https://kit.fontawesome.com/dd673adbed.js" crossorigin="anonymous"></script>
    <!-- Swiper CSS -->
    <link rel="stylesheet" href="css/swiper-bundle.min.css">
    <!--Link do css do sidebar-->
    <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
    <!--<!-- Link do css dos cards -->
    <link href="CSS/style_content.css" rel="stylesheet" type="text/css"/>
    <title>High Event</title>

</head>
<body>
    
    <div class="sidebar" id="sidebar1">

        <div class="logo_content">

            <div class="logo">
                <!--<i class='bx bxl-google-cloud'></i>-->
                <a href=""><img src="Img/logo_definitiva.png" alt=""/></a>
                
                <div class="logo_name">igh Event</div>

            </div>

            <i class='bx bx-menu' id="btn" onclick="n1()"></i>

        </div>

            <ul class="nav_list">

                <li>

                    <!--<i class='bx bx-search'></i>
                    <input type="text" placeholder="Buscar...">

                   <span class="tooltip">Dashboard</span>-->
            
                </li>

                <li><a href="">

                    <i class='bx bx-home'></i>
                    <span class="links_name">Home</span>

                    </a>

                    <span class="tooltip">Home</span>
            
                </li>

                <li><a href="">

                    <i class='bx bxs-buildings'></i>
                    <span class="links_name">Cidades</span>

                    </a>

                    <span class="tooltip">Cidades</span>
            
                </li>

                <li><a href="">
                    <i class='bx bxs-graduation'></i>
                    <span class="links_name">Cursos</span>

                    </a>

                    <span class="tooltip">Cursos</span>
            
                </li>

                <li><a href="">

                    <i class='bx bxs-city'></i>
                    <span class="links_name">Estados</span>

                    </a>

                    <span class="tooltip">Estados</span>
            
                </li>

                <li><a href="">

                    <i class='bx bx-list-plus'></i>
                    <span class="links_name">Funções</span>

                    </a>

                    <span class="tooltip">Funções</span>
            
                </li>

                <li><a href="">

                    <i class='bx bx-book'></i>
                    <span class="links_name">Instituições</span>

                    </a>

                    <span class="tooltip">Instituições</span>
            
                </li>

                <li><a href="">

                    <i class='bx bxs-notepad'></i>
                    <span class="links_name">Atividades</span>

                    </a>

                   <span class="tooltip">Atividades</span>
            
                </li>

                <li><a href="">

                    <i class='bx bxs-notepad'></i>
                    <span class="links_name">Atividades</span>

                    </a>

                   <span class="tooltip">Atividades</span>
            
                </li>

                <li><a href="">

                    <i class='bx bxs-notepad'></i>
                    <span class="links_name">Atividades</span>

                    </a>

                   <span class="tooltip">Atividades</span>
            
                </li>

                <li><a href="">

                    <i class='bx bxs-notepad'></i>
                    <span class="links_name">Atividades</span>

                    </a>

                   <span class="tooltip">Atividades</span>
            
                </li>

                
                <li><a href="">

                    <i class='bx bxs-briefcase'></i>
                    <span class="links_name">Trabalhos</span>

                    </a>

                    <span class="tooltip">Trabalhos</span>
            
                </li>

            </ul>

                <div class="profile_content">

                    <div class="profile">

                        <div class="profile_details">

                            <img src="Img/profile.png" alt=""/>

                            <div class="name_job">

                                <!--<!-- <div class="name"> Luiz Filipe</div> -->
                                <div class="job">Adiministrador</div>


                            </div>

                        </div>

                        <a href="https://docs.google.com/forms/d/1WeKYkjDKdwGmnle3VxTfTkHiH9Yw5Fnjt7lznwfEHFY/edit#responses" ><i class="bx bx-log-out" id="log_out"></i></a>


                    </div>

                </div>

    </div>

    <div class="home_content" > <!--<!-- style="border: red solid;" -->

        <div class="text""> 
            
            <div class="title_home">
                
            
            <img src="Img/logo_definitiva.png" alt=""/>
            <li>igh Event</li>
                
            </div>
        
        </div>
        
        <div class="content_pag"> 

            <div class="geral">
                
                <h1>Informações da Semana Acadêmica</h1>
                <p>As semanas acadêmicas somam conhecimento, esclarecem conceitos, 
                   criam situações de aprendizado e promovem amizades. De fato, 
                   sua importância não é apenas em um nível acadêmico ou profissional, 
                   mas também pessoal.Seus efeitos duram por toda vida, ou seja, 
                   ela não termina no dia do encerramento ou 
                   quando chega o certificado de participação. Em outras palavras, 
                   você sempre poderá dividir o que aprendeu com colegas e amigos a qualquer momento.</p>
                
            </div>
            
            <div id="title_p"><h1>Palestrantes</h1></div>
            
            <div class="card_conten2t" >
                
                
                
                <div class="slide-container swiper">
            <div class="slide-content">
                <div class="card-wrapper swiper-wrapper">
                    <div class="card swiper-slide">
                        <div class="image-content">
                            <span class="overlay"></span>

                            <div class="card-image">
                                <!--<img src="images/profile1.jpg" alt="" class="card-img">-->
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">David Dell</h2>
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                        </div>
                    </div>
                    
                    </div>
                </div>
            </div>
                
           <div class="slide-container swiper">
            <div class="slide-content" >
                <div class="card-wrapper swiper-wrapper">
                    <div class="card swiper-slide">
                        <div class="image-content">
                            <span class="overlay"></span>

                            <div class="card-image">
                                <!--<img src="images/profile1.jpg" alt="" class="card-img">-->
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">David Dell</h2>
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                        </div>
                    </div>
                    
                    </div>
                </div>
            </div>

            <div class="swiper-button-next swiper-navBtn"></div>
            <div class="swiper-button-prev swiper-navBtn"></div>
            <div class="swiper-pagination"></div>
        </div>
                
            </div>
            
    </div>
    </div>
    <script>



    </script>
    
</body>
</html>
