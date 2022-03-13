<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Ajouter un Pays</title>
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
<center><h2>Ajouter un Pays</h2><br>
        
         <%
                PaysForm form = (PaysForm)request.getAttribute("form");
            %>
            
        <form style="width: 800px;margin-left: 10px" action="paysAjouter" method="POST">
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="exampleInputEmail1">Code :</label><span class="erreur-formulaire"> ${pTabErreurs['code']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez un code" name="code">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Nom :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un nom" name="nom">
              </div>
              
                </select>
              </div>
            </div>
          </div>
        </div>
        <center><button type="submit" class="btn btn-primary">Ajout d'un Pays</button></center>
       </form>
</body></center>
            <%@ include file="/vues/inc/footer.jsp" %>
</html>
