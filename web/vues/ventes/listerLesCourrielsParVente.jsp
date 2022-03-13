<%-- 
    Document   : Lister les courriels par vente 
    Created on : 16/09/2019, 09:24
    Author     : Thomas
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Courriel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des courriels par vente</title>
    <meta charset="utf-8"/>
    <!--Made with love by Mutiullah Samim-->
    <link href="../css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
    <link rel="icon" type="image/png" href="../img/horse.png" /> 
<!------ Include the above in your HEAD tag ---------->
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="../css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="../css/index.css">
</head>

<%@ include file="/vues/inc/header.jsp" %>
        <h1>LISTE DES COURRIELS</h1>
         <%
        ArrayList<Courriel> lesCourriels = (ArrayList)request.getAttribute("pLesCourriels");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>Id</th>
                    <th>Date d'envoie</th>
                    <th>Objet du message</th>
                    <th>Détails</th>  
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesCourriels.size();i++)
                    {
                        
                        Courriel unCourriel = lesCourriels.get(i);
                        out.println("<tr><td>");
                        out.println(i+1);
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println(unCourriel.getDateEnvoiMessage());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unCourriel.getObjetMessage());
                        out.println("</td>");

                        out.println("<td><a href ='../ServletVentes/listerLesDetails?codeCourriel="+unCourriel.getId()+"'</a>Détails");
                        out.println("</td>");
                        

                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
