<%-- 
    Document   : categsAjouter
    Created on : 15 oct. 2019, 09:24:17
    Author     : Quentin
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.VentesForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Ajouter une catégorie de vente</title>
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
    <body>
    <center><h2>NOUVELLE CATEGORIE DE VENTE :</h2></center><br><br>
        
         <%
                //Client client=(Client)request.getAttribute("client");
                VentesForm form = (VentesForm)request.getAttribute("form");
                
            %>
            
        <form action="ajouterCateg" method="POST">            
                <div class="container">
                  <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="exampleFormControlInput1">Code de la catégorie : <I>(5 lettres max)</I></label><span class="erreur-formulaire"> ${pTabErreurs['code']}</span>
                          <input required type="text" name="code" size="10" maxlength="5" class="form-control" id="exampleFormControlInput1" placeholder="Entre le code de la catégorie">
                        </div></br>

                        <div class="form-group">
                          <label for="exampleFormControlInput1">Nom de la catégorie :</label><span class="erreur-formulaire"> ${pTabErreurs['nomCateg']}</span>
                          <input required type="text" name="libelle" size="30" class="form-control" id="exampleFormControlInput1" placeholder="Entrez le nom de la catégorie">
                        </div><br>
                        <br><button type="submit" name="valider" class="btn btn-primary">Valider</button>
                      </div> </br>
                    </div>
                  </div>
                </div> 
        </form>  
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
