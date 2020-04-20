<%-- 
    Document   : campeonato
    Created on : 12/04/2020, 18:29:58
    Author     : iohan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-green"><b>Campeonato</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">
    <h2 class="w3-xxlarge w3-text-green">Usuários que jogarão com você</h2>
    <form action="index.jsp?page=ranking" method="post">
      <div class="w3-section">
          <fieldset style="text-align: center;width: 20%; ">
              <tr>
                  <td>User 1</td><br>
                  <td>User 2</td><br>
                  <td>User 3</td><br>
                  <td>User 4</td><br>
              </tr>
          </fieldset>
      </div>
      
      <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Iniciar</button>
      
    </form>
  </div>

<!-- End page content -->
</div>