<%-- 
    Document   : modifierPays
    Created on : 14 oct. 2019, 08:27:16
    Author     : slam
--%>

<%@page import="formulaires.PaysForm"%>
<%@page import="modele.Pays"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Modifier un Pays</title>
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
             
  if(connecte){
      if(role.equals("ADMIN")){
          
            Pays pays=(Pays)request.getAttribute("pPays");
            PaysForm form = (PaysForm)request.getAttribute("form");
          
         %>
         <h2>MODIFIER LE PAYS <% out.println(pays.getNom()); %> :</h2><br>
        
        <form style="width: 800px;margin-left: 10px" action="modifierPays" method="POST">
        <input type="hidden" name="idPays" value="<% out.println(pays.getCode());%>"/>
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="exampleInputEmail1">Code : <% out.println(pays.getCode()); %></label>
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Nom :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un nom" name="nom" value="<% out.println(pays.getNom()); %>">
              </div>            
              <button type="submit" class="btn btn-primary">Modifier ce pays</button>                 
        </div>
       </form>
    </body>
    
    <%
            }else{
                out.println("<h4>Vous n'avez pas les droits pour accéder à cette page. Contactez l'administrateur pour plus d'informations.");
            }
        }else{
           out.println("<h4>Vous n'êtes pas autorisé à aller sur cette page. Cliquez <a href='../../../../EquidaWebPeaky/ServletConnexion/connexion'>içi</a> pour vous connecter !");
        }
    %>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
