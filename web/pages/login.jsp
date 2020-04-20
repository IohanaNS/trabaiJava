<%-- 
    Document   : login
    Created on : 11/04/2020, 19:35:05
    Author     : iohan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!-- Contact -->
  <div class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Entrar</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">

    <form action="index.jsp?page=campeonato" method="post">
      <div class="w3-section">
        <label>E-mail</label>
        <input class="w3-input w3-border" type="email" name="email" required>
      </div>
      <div class="w3-section">
        <label>Senha</label>
        <input class="w3-input w3-border" type="password" name="senha" required>
      </div>
      
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Entrar</button>
      
    </form>
    <form action="home?ac=cadUser" method="post">
        <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Cadastrar</button>
    </form>
  </div>

<!-- End page content -->
</div>
