<%-- 
    Document   : index
    Created on : 12/04/2020, 17:55:02
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <title>Bombapatch</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <style>
        body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif;}
        body {font-size:16px;overflow-y : scroll !important;overflow-x: scroll !important;}
        table {width: 100%;display:block;}
        thead {display: inline-block;width: 100%;height: 10px;}
        tbody {color: lightgray;display: inline-block;width: 100%;overflow: auto;}
        .w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer;padding: 10px;}
        .w3-half img:hover{opacity:1}
        #teste{background-image: url(img/campo_futebol2.jpg);background-size: cover;}
        .selectProd{margin: 15px 0px 0px 5px;width: 50px;}
        #bombapatch{font-size: 35px;}
        th{font-size: 20px;}
        td{font-size: 20px;} 
        .inputran{margin: 20px;width: 80px;}
        .label{color:lightgray !important;}
        #divFundo{background-color: rgba(0, 0, 0, 0.3);}
        #trAltura{height: 300px;}
        #trAltura2{height:160px}
        .livav{font-size: 15px;}
        ul{list-style-type: none;}
    </style>
    <body>

        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-green w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
            <c:if test="${sessionScope.user !=null}">
                <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
                <div class="w3-container">
                    <h3 class="w3-padding-64" id="bombapatch"><b>Bombapatch</h3>
                </div>
                <div class="w3-bar-block">
                    <a href="home?ac=campeonato" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Campeonato</a>  
                    <a href="home?ac=consultaRanking" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Ranking</a>
                    <c:if test="${sessionScope.user.ehAdmin}">
                        <ul class="w3-bar-item "><li>Área do administrador</li>
                            <li>
                                <a class=" w3-bar-item w3-button w3-hover-white livav" href="home?ac=cadastroTime" onclick="w3_close()">Cadastro de times</a></li>
                            <li>
                                <a class=" w3-bar-item w3-button w3-hover-white livav" href="home?ac=cadastroJogTime" onclick="w3_close()">Cadastro de Jogadores de times</a></li>
                        </ul>
                    </c:if>
                    <a href="home?ac=logout" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Sair</a>
                </div>
            </c:if>
        </nav>

        <!-- Top menu on small screens -->
        <header class="w3-container w3-top w3-hide-large w3-green w3-xlarge w3-padding">
            <a href="javascript:void(0)" class="w3-button w3-green w3-margin-right" onclick="w3_open()">☰</a>
            <span>Bombapatch</span>
        </header>

        <div id="teste" class="w3-top" style="height: 800px">

            <!-- Overlay effect when opening sidebar on small screens -->
            <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

            <!-- !PAGE CONTENT! -->

            <div class="w3-main" style="margin-left:340px;margin-right:40px">



                <c:if test="${param.page == null }">
                    <jsp:include page="pages/login.jsp" />
                </c:if>                            
                <c:if test="${param.page != null }">
                    <jsp:include page="pages/${param.page}.jsp" />
                </c:if>


                <c:if test="${ex != null}" >
                    <h1>ERRO</h1>
                    <p>A página solicitada não existe</p> 
                </c:if>



                <script>
                    // Script to open and close sidebar
                    function w3_open() {
                        document.getElementById("mySidebar").style.display = "block";
                        document.getElementById("myOverlay").style.display = "block";
                    }

                    function w3_close() {
                        document.getElementById("mySidebar").style.display = "none";
                        document.getElementById("myOverlay").style.display = "none";
                    }

                    // Modal Image Gallery
                    function onClick(element) {
                        document.getElementById("img01").src = element.src;
                        document.getElementById("modal01").style.display = "block";
                        var captionText = document.getElementById("caption");
                        captionText.innerHTML = element.alt;
                    }
                </script>

            </div>

    </body>
</html>

