<%-- 
    Document   : consultaRanking
    Created on : 26/04/2020, 16:48:18
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="divFundo" class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Ranking</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">
    <h2 class="w3-xxlarge w3-text-green">Resultados</h2>
    <!--    todo-->
    <form action="home" method="post"> 
        <div class="w3-section">
            <table style="width: 100%;text-align: center ;height:90%;">
                <TH><span style="color:#4CAF50;">Time</span></TH>
                <TH><span style="color:#4CAF50;">Placar</span></TH>
                <TH>&nbsp;&nbsp;&nbsp;<span style="color:#4CAF50;">Gols de artilheiro</span></TH>
                    <c:forEach items="${requestScope.lista}" var="p">

                    <tr>
                        <td>${p.time1.nome}</td>
                        <td>${p.pontuacaoTime1}</td>
                        <td>${p.golsdeArtilheiroTime1}</td>

                    </tr>
                    <tr>
                        <td>${p.time2.nome}</td>
                        <td>${p.pontuacaoTime2}</td>
                        <td>${p.golsdeArtilheiroTime2}</td>
                    </tr>
                    <td>____________________</td>

                </c:forEach>

            </table>

            <h2 style="font-weight: bold;"class="w3-xxlarge w3-text-green">Usuario vencedor: <span style="color:lightgray;">${requestScope.winner.login}</span></h2>


            <h2 style="font-weight: bold;"class="w3-xxlarge w3-text-green">Time: <span style="color:lightgray;">${requestScope.winner.time.nome}</span></h2> 

        </div>

        <input type="hidden" name="ac" value="calculaRanking"/>
        <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Outro campeonato?</button>

    </form>
</div>

<!-- End page content -->
</div>
