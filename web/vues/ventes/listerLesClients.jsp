<%-- 
    Document   : listerLesClients
    Created on : 22 juin 2017, 10:23:05
    Author     : Zakina
--%>

<%@page import="modele.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des clients</title>
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
        <h1>LISTE DES CLIENTS POUR UNE CATEGORIE DE VENTE</h1>
         <%
        ArrayList<Client> lesClients = (ArrayList)request.getAttribute("pLesClients");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>nom</th>
                    <th>prenom</th>
                    <th>Rue</th>
                    <th>Ville</th>
                    <th>Code Postal</th>
                    <th>pays</th>
                    <th>Adresse Mail</th>
                    <th></th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesClients.size();i++)
                    {
                        
                        Client leClient = lesClients.get(i);
                        out.println("<tr><td>");
                        out.println(leClient.getId());
                        out.println("</a></td>");

                         out.println("<td>");
                         out.println(leClient.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(leClient.getPrenom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getRue());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getCopos());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getVille());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(leClient.getUnPays().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getMail());
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesChevaux?codeClient="+leClient.getId()+"'</a>Lister les chevaux de ce client");
                        out.println("</td>");
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
