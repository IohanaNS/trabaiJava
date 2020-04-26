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
        body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
        body {font-size:16px;
        }
        .w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer;padding: 10px;}
        .w3-half img:hover{opacity:1}
        #teste{
            background-image: url(img/campo_futebol2.jpg);
            background-size: cover;
        }
        .selectProd{
            margin: 15px 0px 0px 5px;
            width: 50px;
        }
        #bombapatch{
            font-size: 35px;
        }

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
                <a href="home?ac=ranking" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Ranking</a>
                <c:if test="${sessionScope.user.ehAdmin}">
                <a href="index.jsp?page=admin" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Área do administrador</a>
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
        
        <div id="teste" class="w3-top w3-padding w3-xlarge">
            
        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        
            <div class="w3-main" style="margin-left:340px;margin-right:40px">

            <c:catch var="ex">

                <c:if test="${param.page == null }">
                    <jsp:include page="pages/login.jsp" />
                </c:if>                            
                <c:if test="${param.page != null }">
                    <jsp:include page="pages/${param.page}.jsp" />
                </c:if>

            </c:catch>
            <c:if test="${ex != null}" >
                <h1>ERRO</h1>
                <p>A página solicitada não existe</p> 
            </c:if>





            <!-- W3.CSS Container -->
            <div class="w3-light-grey w3-container w3-padding-32" style="margin-top:75px;padding-right:58px"><p class="w3-right">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></p></div>

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

