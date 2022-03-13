<%-- 
    Document   : categsModifier
    Created on : 15 oct. 2019, 10:55:32
    Author     : Quentin
--%>


<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.VentesForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Modifier une catégorie de vente</title>
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
<%
  if(connecte){
      if(role.equals("ADMIN")){
          
%>   
         <%
                CategVente categ=(CategVente)request.getAttribute("pLaCateg");
                VentesForm form = (VentesForm)request.getAttribute("form");
                
            %>
            <h2>MODIFIER UNE CATEGORIE DE VENTE</h2><br>
       <form style="width: 800px;margin-left: 10px" action="modifierCateg" method="POST">
        
           <input type="hidden" name="idCateg" value="<% out.println(categ.getCode());%>"/>
           <h5>Code = <% out.println(categ.getCode()); %></h5>
        <div class="container">
            <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="libelle">Libelle :</label><span class="erreur-formulaire"> ${pTabErreurs['nomCateg']}</span>
                            <input required type="text" class="form-control" id="libelle" placeholder="Entrez un libelle" name="libelle" value="<% out.println(categ.getLibelle()); %>">
                        </div>
                        
                    </div>
                        </div></br>
         
                 <input type="submit" name="valider" id="valider" value="Valider"/>
                </div>
            </div>
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
