<%-- 
    Document   : Ranking
    Created on : 12/04/2020, 18:36:00
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Ranking</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">
    <h2 class="w3-xxlarge w3-text-green">Faça a sua votação</h2>
<!--    todo-->
    <form action="home?ac=ranking" method="post"> 
      <div class="w3-section">
          <table style="width: 100%;text-align: center ;height:100%;">
                       <TH>Time</TH>
                       <TH>Placar</TH>
                       <TH>&nbsp;&nbsp;&nbsp;Gols de artilheiro</TH>
                   <c:forEach items="${requestScope.partidas}" var="p">
                       
                  <tr>
                  
                  <td value="">${p.time1.nome}</td>
                  <td><input class="inputran" type="text" name="placarTime1"></td>
                  <td><input class="inputran" type="text" name="golsArTime1"></td>
          
              </tr>
              <tr>
                <td value="">${p.time2.nome}</td>
                  <td><input class="inputran" type="text" name="placarTime2"></td>
                   <td><input class="inputran" type="text" name="golsArTime2"></td>
              </tr>
              <td>-----------------------</td>
              </c:forEach>
          </table>
      </div>
        <input type="hidden" name="ac" value="calculaRanking"/>
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Enviar</button>
 
    </form>
  </div>

<!-- End page content -->
</div>

