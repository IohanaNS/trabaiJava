<%-- 
    Document   : campeonato
    Created on : 12/04/2020, 18:29:58
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Campeonato</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">
    <h2 class="w3-xxlarge w3-text-green">Usuários disponíveis para jogar</h2>
<!--    todo-->
    <form action="home" method="post"> 
        <input type="hidden" name="ac" value="votacaoTimes"/>
        
      <div class="w3-section">
          <fieldset style="text-align: center;width: 20%; ">
              <tr>
              <c:forEach items="${requestScope.users}" var="p"> 
                  <td value="${p.login}">${p.login}</td><BR>
          </c:forEach>
              </tr>
          </fieldset>
      </div>
        
      <c:if test="${sessionScope.user.ehAdmin}">
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Iniciar</button>
      </c:if>
    </form>
<form action="home?ac=consultaRanking" method="post">
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Consultar ranking</button>
    
</form>
  </div>

<!-- End page content -->
</div>