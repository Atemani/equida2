<%-- 
    Document   : ajouterTypeCheval
    Created on : 1 oct. 2019, 11:49:27
    Author     : bastu
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Course"%>
<%@page import="modele.Cheval"%>
<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>  
    <title>Equida - Ajouter un type de cheval</title>
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

        <center><h1>Ajouter un type de cheval</h1><br>
        <form action="ajouterTypeCheval" method="POST">
            <div class="form-group" style="width: 400px;">
                <label for="exampleInputEmail1">Nom :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
              <input required type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez un nom" name="nom">
            </div>
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputPassword1">Description :</label><span class="erreur-formulaire"> ${pTabErreurs['desc']}</span>
              <input required type="text" class="form-control" id="exampleInputPassword1" placeholder="Entrez une description" name="description">
            </div>
            <button type="submit" class="btn btn-primary">Ajouter ce type</button>
        </form></center>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
