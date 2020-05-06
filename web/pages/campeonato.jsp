<%-- 
    Document   : campeonato
    Created on : 12/04/2020, 18:29:58
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="divFundo"class="w3-container" id="contact" style="margin-top:50px;height:550px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Campeonato</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">
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
    <h2 class="w3-xxlarge w3-text-green">Usuários na sala</h2>
    <!--    todo-->
    <div >
        <form action="home" method="post"> 
            <input type="hidden" name="ac" value="votacaoTimes"/>

            <div class="w3-section" >
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Usuário</th>
                            <th scope="col">Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${requestScope.users != null}">                   
                                <c:forEach items="${requestScope.users}" var="p"> 
                                    <tr>
                                    <td value="${p.login}"><span class="label">${p.login}</span></td>
                                    <td value="${p.time.nome}"><span class="label">${p.time.nome}</span></td>
                                    </tr>
                                    </c:forEach>
                        </tbody>
                    </c:if>
                </table>
            </div>

            <c:if test="${sessionScope.user.ehAdmin}">
                <c:if test="${count == true}">
                    <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Iniciar</button>
                </c:if>

            </c:if> 
        </form>  
        <form action="home?ac=consultaRanking" method="post">
            <c:if test="${count == true}">
                <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Consultar ranking</button>
            </c:if>
        </form>
        <form action="home?ac=criarSala" method="post">

            <c:if test="${count == false}">
                <c:if test="${sessionScope.user.ehAdmin}">
                    <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Criar uma sala</button>
                </c:if>
            </c:if>
        </form>
        <c:if test="${sessionScope.user.ehAdmin == false && sessionScope.user.campeonato == null}">
            <form action="home?ac=entrarEmSala" method="post">
                <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Entre em uma sala</button>
            </form>    
        </c:if>
        <c:if test="${sessionScope.user.ehAdmin == false && sessionScope.user.campeonato != null}">
            <form action="home?ac=entrarEmSala" method="post">
                <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Entre em uma outra sala</button>
            </form>    
        </c:if>
        <c:if test="${sessionScope.user.campeonato != null && count == false && sessionScope.user.ehAdmin ==false}">
            <h3 class="w3-xlarge w3-text-green">Volte mais tarde para checar os resultados!</h3>
        </c:if>
    </div>

</div>

<!-- End page content -->
</div>