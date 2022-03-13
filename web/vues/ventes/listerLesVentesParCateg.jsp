<%-- 
    Document   : listerLesVentesParCateg
    Created on : 10 sept. 2019, 18:41:48
    Author     : bastu
--%>
<%@page import="modele.Client"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des ventes par catégorie</title>
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
  CategVente uneCateg = (CategVente)request.getAttribute("pLaCateg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des ventes pour la catégorie [NOM CATEG]</title>
    </head>
    <body>
        <h1>Liste des ventes pour la catégorie "<% out.println(uneCateg.getLibelle()); %>"</h1>
         <%
        ArrayList<Vente> lesVentes = (ArrayList)request.getAttribute("pLesVentes");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>nom</th>
                    <th>date début</th>
                    <th></th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesVentes.size();i++)
                    {
                        
                        Vente uneVente = lesVentes.get(i);
                        out.println("<tr><td>");
                        out.println(i+1);
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println(uneVente.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getDateDebutVente());
                        out.println("</td>");
                      
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
