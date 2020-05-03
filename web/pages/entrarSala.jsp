<%-- 
    Document   : entrarSala
    Created on : 01/05/2020, 22:53:30
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="divFundo" class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Entre em uma sala</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">

    <form action="home" method="post">
        <input type="hidden" name="ac" value="saveUserEmSala"/>
        <div class="w3-section">
            <c:if test="${requestScope.err != null}">
                <div class="alert alert-danger">
                    ${requestScope.err}
                </div>
            </c:if>
            <c:if test="${requestScope.succ != null}">
                <div class="alert alert-danger">
                    ${requestScope.succ}
                </div>
            </c:if>
       <div class="w3-section">
        <label class="label">Salas disponíveis</label>
        <select name="escolhaSala">

            <c:forEach items="${requestScope.lista}" var="p"> 
            <option value="${p}">${p}</option><BR>
          </c:forEach>
        </select>
      </div>
            <div class="w3-section">
        <label class="label">Escolha o seu time</label>
        <select name="escolhaTime">

            <c:forEach items="${requestScope.times}" var="p"> 
            <option value="${p.nome}">${p.nome}</option><BR>
          </c:forEach>

        </select>
      </div>
      </div>
      
      <input type="hidden" id="id" name="id" value=""/>
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Entrar</button>
      
    </form>
