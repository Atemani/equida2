<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Thomas
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page import="formulaires.ClientForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Ajouter un entraineur</title>
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
            ClientForm form = (ClientForm)request.getAttribute("form");
        %>
        
        <h1>Inscrire un entraineur : </h1>
            
        <form style="width: 800px;margin-left: 10px" action="ajouterEntraineur" method="POST">
        <div style="margin-left:40%;" class="container">
            <div class="row">
                <div class="col">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Nom :</label>
                    <input required type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez un nom" name="nom">
                  </div>
                  <div class="form-group">
                    <label required for="exampleInputEmail1">Prénom :</label>
                    <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un prénom" name="prenom">
                  </div>
                  <div class="form-group">
                    <label required for="exampleInputEmail1">Adresse :</label>
                    <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez une rue" name="adresse">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Code postal :</label>
                    <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un code postal" name="copos">
                  </div>  
                </div>
                <div class="col">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Ville :</label>
                    <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez une ville" name="ville">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Téléphone :</label>
                    <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un téléphone" name="tel">
                  </div> 
                  <div class="form-group">
                    <label for="exampleInputEmail1">Nombre de victoires :</label>
                    <input required type="number" class="form-control" id="exampleInputEmail1" placeholder="nombre de victoires de l'entraineur" name="nbVictoires">
                  </div>
                  <center><button type="submit" class="btn btn-primary">Inscrire l'entraineur</button></center>
                </div>
            </div>
        </div>
    </form>
</body>

<%@ include file="/vues/inc/footer.jsp" %>
</html>
