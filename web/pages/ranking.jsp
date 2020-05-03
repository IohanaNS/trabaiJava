<%-- 
    Document   : Ranking
    Created on : 12/04/2020, 18:36:00
    Author     : iohan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="divFundo" class="w3-container" id="contact" style="margin-top:30px;">
    <h1 class="w3-xxxlarge w3-text-green"><b>Ranking</b></h1>
    <hr style="width:50px;border:5px green;" class="w3-round">

    <!--    todo-->
    <form action="home" method="post"> 
        <div class="w3-section">
            <table style="height: 330px;text-align: center;">



                <tbody id="trAltura">
                    <c:forEach items="${requestScope.partidas}" var="p">


                    <th>${p.time1.nome}</th>
                    <input type="hidden" name="nometime1" value="${p.time1.nome}"/>
                    <td>&nbsp;</td>
                    <th>&nbsp;${p.time2.nome}</th>
                    <input type="hidden" name="nometime2" value="${p.time2.nome}"/>

                    <tr>

                        <td><input class="inputran"  type="text" placeholder="placar" name="placarTime1" required></td>
                        <td>X</td>
                        <td><input class="inputran" type="text" placeholder="placar" name="placarTime2" required></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <label class="label">Qual time tem o artilheiro?</label>
            <select name="times">
            <c:forEach items="${requestScope.times}" var="p">
                
                    <option value="${p.nome}">${p.nome}</option>
               
            </c:forEach>
            </select>
        </div>

        <input type="hidden" name="ac" value="calculaRanking"/>
        <button type="submit" class="w3-button w3-block w3-padding-large w3-green w3-margin-bottom">Enviar</button>

    </form>
</div>

<!-- End page content -->
</div>

