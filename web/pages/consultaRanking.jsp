<%-- 
    Document   : consultaRanking
    Created on : 26/04/2020, 16:48:18
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="divFundo" class="w3-container" id="contact" style="margin-top:10px;height:570px;overflow-y:scroll; 
display:block;width: 100%;margin-bottom: 20px">
    <h1 class="w3-text-green"><b>Ranking</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">
    <h3 class=" w3-text-green">Resultados</h3>
    <!--    todo-->
    <form action="home?ac=criarSala" method="post"> 
        <div style=" overflow-y:scroll; 
height:200px; 
display:block; margin-bottom: 20px;">
            <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Time</th>
                            <th scope="col">Placar</th>
                        </tr>
                    </thead>
                    <tbody>
                  
                                <c:forEach items="${requestScope.lista}" var="p"> 
                                    <tr>
                                    <td value="${p.time1.nome}"><span class="label">${p.time1.nome}</span></td>
                                    <td value="${p.pontuacaoTime1}"><span class="label">${p.pontuacaoTime1}</span></td></tr>
                                    <tr>
                                    <td value="${p.time2.nome}"><span class="label">${p.time2.nome}</span></td>
                                    <td value="${p.pontuacaoTime2}"><span class="label">${p.pontuacaoTime2}</span></td>
                                    </tr><td style="background-color: #4CAF50"></td><td style="background-color: #4CAF50"></td>
                                    
                                    </c:forEach>
                        </tbody>

                </table>
            </div>
        <!--COLOCACAO-------------------------------------------------------------------->
                <h3 class="w3-text-green">Colocação</h3>
                <div class="w3-section" style=" overflow-y:scroll; 
height:130px; 
display:block;width: 70%;margin-bottom: 20px">
                
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="w-25 p-3">Time</th>
                            <th scope="w-25 p-3">Pontuação</th>
                        </tr>
                    </thead>
                     <tbody>
                  
                         <c:forEach items="${requestScope.ranking}" var="p">
                             <tr>
                                    <td value="${p.nome}"><span class="label">${p.nome}</span></td>
                                    <td value="${p.pontuacaoTotal}"><span class="label">${p.pontuacaoTotal}</span></td>
                                   
                                    </tr>
                         </c:forEach>
                                    </tbody>
                                    </table>
             
          
      </div>
        <br>
            <h2 style="font-weight: bold;font-size: 20px;"class=" w3-text-green">Usuario vencedor: <span style="color:lightgray;">${requestScope.winner.login}</span></h2>


            <h2 style="font-weight: bold;font-size: 20px;"class=" w3-text-green">Time: <span style="color:lightgray;">${requestScope.winner.time.nome}</span></h2> 
            
       
        <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Novo campeonato?</button>

    </form>
            </div>
</div>

<!-- End page content -->
</div>
