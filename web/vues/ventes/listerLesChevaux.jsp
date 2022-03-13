<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Thomas
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des chevaux</title>
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
        <%
        ArrayList<Cheval> lesChevaux = (ArrayList)request.getAttribute("pLesChevaux");
        Client leClient = (Client)request.getAttribute("pLeClient");
        %>
        
        <h2>LISTE DES CHEVAUX DU CLIENT <%out.println(leClient.getPrenom()+" "+leClient.getNom());%></h2>
        <% out.println("Adresse : "+leClient.getRue()+","+leClient.getVille()); %>
        
        <table  class="table table-bordered table-striped table-condensed">
            <thead>
                <tr>             
                    <th>id</th>
                    <th>nom</th>
                    <th>Sexe</th>
                    <th>date de naissance</th>
                    <th>race</th>  
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesChevaux.size();i++)
                    {
                        
                        Cheval unCheval = lesChevaux.get(i);
                        out.println("<tr><td>");
                        out.println(i+1);
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println("<a href='../../EquidaWebPeaky/ServletClient/infosCheval?codeCheval="+unCheval.getId()+"'>"+unCheval.getNom()+"</a>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unCheval.getSexe());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unCheval.getDateNaissance());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unCheval.getTypeCheval().getLibelle());
                        out.println("</td>");
                              
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
