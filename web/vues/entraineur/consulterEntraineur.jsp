<%-- 
    Document   : Acceuil
    Created on : 28 sept. 2019, 11:00:05
    Author     : Thomas
--%>
<%@page import="modele.Entraineur"%>
<%@page import="modele.Enchere"%>
<%@page import="modele.Cheval"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Informations client</title>
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
                      Entraineur unEntraineur = (Entraineur)request.getAttribute("pEntraineur");
                  %>
        <br><br>
        <div class="container">
            <div class="row">
              <div class="col">
                  <h4>Informations de l'entraineur enregistré : </h4><br>
                <label style="font-weight: bold">Nom : </label> <label> <% out.println(unEntraineur.getNom());%></label><br>
                <label style="font-weight: bold">Prénom : </label> <label> <% out.println(unEntraineur.getPrenom());%></label><br>
                <label style="font-weight: bold">Rue : </label> <label> <% out.println(unEntraineur.getAdresse());%></label><br>
                <label style="font-weight: bold">Ville : </label> <label> <% out.println(unEntraineur.getVille());%></label><br>
                <label style="font-weight: bold">Code postal : </label> <label> <% out.println(unEntraineur.getCopos());%></label><br>
                <label style="font-weight: bold">Téléphone : </label> <label> <% out.println(unEntraineur.getTelephone());%></label><br>
                <label style="font-weight: bold">Nombre de victoires : </label> <label> <% out.println(unEntraineur.getNbVictoires());%></label><br>
              </div>  
            </div>
        </div>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
