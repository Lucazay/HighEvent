<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="header.jsp"/>




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
    <link href="cadastros/CSS/style.css" rel="stylesheet" type="text/css"/>
    <!--<!-- Link do css dos cards -->
    <link href="CSS/style_content.css" rel="stylesheet" type="text/css"/>
    <title>High Event</title>

</head>
<body>
    
    <div class="sidebar" id="sidebar1">

        <div class="logo_content">

            <div class="logo">
                <!--<i class='bx bxl-google-cloud'></i>-->
                <a href=""><img src="cadastros/Img/logo_definitiva.png" alt=""/></a>
                <div class="logo_name">igh Event</div>

            </div>

            <i class='bx bx-menu' id="btn" onclick="n1()"></i>

        </div>

            <ul class="nav_list">

 <li> 
                    <a href="${pageContext.request.contextPath}/login.jsp">
                    <i class='bx bxs-user-badge'></i>
                    <span class="links_name">Login</span>
                    </a>
                    <span class="tooltip">Login</span>
                    </a>
                </li>
           

            
            </ul>

                    <div class="profile_content" style="bottom: 1px;">

                    <div class="profile">

                        <div class="profile_details">
                            <img src="cadastros/Img/usuário_não_log.png" alt=""/>
                            

                            <div class="name_job">

                                <!--<!-- <div class="name"> Luiz Filipe</div> -->
                                <div class="job">Não logado</div>


                            </div>

                        </div>

                        <a href="https://docs.google.com/forms/d/1WeKYkjDKdwGmnle3VxTfTkHiH9Yw5Fnjt7lznwfEHFY/edit#responses" ><i class="bx bx-log-out" id="log_out"></i></a>


                    </div>

                </div>

    </div>

    <div class="home_content" > <!--<!-- style="border: red solid;" -->

        <div class="text""> 
            
            <div class="title_home">
                
            
            <img src="cadastros/Img/logo_definitiva.png" alt=""/>
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
                
                  <div class="container">
      <!-- Card 1 -->
      <div class="card card">
        <!-- card-header -->
        <div class="card-header">
          <img src="https://images.unsplash.com/photo-1514565131-fce0801e5785?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=640" class="card-img" />
        </div>
        <!-- card-header -->

        <!-- card-body -->
        <div class="card-body">
          <h3 class="card-local">Estados Unidos</h3>
          <h2 class="card-titulo">Pessoa Um</h2>
          <p class="card-texto">
            
          </p>
        </div>
        <!-- card-body -->

        <!-- card-footer -->
        <!-- card-footer -->
      </div>
      <!-- Card 1 -->

      <!-- Card 2 -->
      <div class="card card-2">
        <!-- card-header -->
        <div class="card-header">
          <img src="https://images.pexels.com/photos/612419/pexels-photo-612419.jpeg?cs=srgb&fit=crop&fm=jpg&h=426&w=640" class="card-img" />
        </div>
        <!-- card-header -->

        <!-- card-body -->
        <div class="card-body">
          <h3 class="card-local">China</h3>
          <h2 class="card-titulo">Pessoa Dois</h2>
          <p class="card-texto">
           
          </p>
        </div>
        <!-- card-body -->

        <!-- card-footer -->
        <!-- card-footer -->
      </div>
      <!-- Card 2 -->

      
      
    </div>  
                
              
        </div>
                
            </div>
            
    </div>
    </div>
    <script>



    </script>
    
</body>
</html>