<%-- 
    Document   : Lister les courriels par vente 
    Created on : 16/09/2019, 15:40
    Author     : Thomas
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Courriel"%>
<%@page import="modele.PieceJointe"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des pièces jointes</title>
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
        <h2>LISTE DES PIECES JOINTES</h2>
         <%
        ArrayList<PieceJointe> lesPiecesJointes = (ArrayList)request.getAttribute("pLesPiecesJointes");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>Chemin</th>
                    <th>Description</th>
                    <th>Apercu</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesPiecesJointes.size();i++)
                    {
                        
                        PieceJointe unePieceJointe = lesPiecesJointes.get(i);
                        out.println("<tr><td>");
                        out.println(i+1);
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println(unePieceJointe.getChemin());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unePieceJointe.getDescription());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println("<img height=100px width=100px src='"+unePieceJointe.getChemin()+"'/>");
                        out.println("</td>");
                        out.println("</tr>");
                       
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
