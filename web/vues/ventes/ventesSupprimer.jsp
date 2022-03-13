<%-- 
    Document   : ventesSupprimer
    Created on : 14 oct. 2019, 11:37:53
    Author     : Quentin
--%>

<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.VentesForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Supprimer une vente</title>
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
      if(role.equals("ADMIN") || role.equals("EMPLOYE")){
          
%>
         <%
            Vente vente=(Vente)request.getAttribute("pLaVente");
            VentesForm form = (VentesForm)request.getAttribute("form");
         %>
         <center><div style="width: 500px" class="alert alert-danger alert-dismissible fade show" role="alert">
            En supprimant définitivement cette vente, vous supprimez également tout les lots et enchères associées à celle-ci !
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
         </div></center>
         <center><br><h2>SUPPRIMER LA VENTE <% out.println(vente.getNom()); %> :</h2><br>
        
        <form action="supprimerVente" method="POST">
            <div class="form-check">
              <input class="form-check-input" type="radio" name="typeSuppression" id="exampleRadios1" value="archiver" checked>
              <label class="form-check-label" for="exampleRadios1">
                Archiver
              </label>
            </div>
            <div class="form-check">
              <input class="form-check-input" type="radio" name="typeSuppression" id="exampleRadios2" value="supprimer">
              <label class="form-check-label" for="exampleRadios2">
                Supprimer
              </label>
            </div>

            <br><button type="submit" class="btn btn-primary">Supprimer la vente</button>
        </form></center>
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
