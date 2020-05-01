<%-- 
    Document   : cadastroTime
    Created on : 01/05/2020, 17:30:19
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="divFundo" class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Cadastro de times</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">

    <form action="home" method="post">
        <input type="hidden" name="ac" value="saveTime"/>
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
        <label class="label">Nome</label>
        <input class="w3-input w3-border" type="nome" name="nome" required>
      </div>
      
      <input type="hidden" id="id" name="id" value=""/>
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Cadastrar</button>
      
    </form>