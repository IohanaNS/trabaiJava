<%-- 
    Document   : login
    Created on : 11/04/2020, 19:35:05
    Author     : iohan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Contact -->
<div  id="divFundo" class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Entrar</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">

    <form action="home" method="post">
        <div class="w3-section">
            <c:if test="${requestScope.err != null}">
                <div class="alert alert-danger">
                    ${requestScope.err}
                </div>
            </c:if>
                <label class="label">Login</label>
            <input class="w3-input w3-border" type="text" name="login" required>
        </div>
        <div class="w3-section">
            <label class="label">Senha</label>
            <input class="w3-input w3-border" type="password" name="senha" required>
        </div>
        <input type="hidden" name="ac" value="checkLogin" />
        <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Entrar</button>

    </form>
    <form action="home?ac=cadUser" method="post">
        <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Cadastrar</button>
    </form>
</div>

<!-- End page content -->
</div>
