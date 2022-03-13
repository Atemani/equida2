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
    <title>Equida - Ajouter un client</title>
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
  if(connecte && role.equals("CLIENT")){
    out.println("<h4>Vous avez déjà un compte. Cliquez <a href='../../../../EquidaWebPeaky/'>içi</a> pour aller à l'accueil !");
  }else{                 
%>
    <% if(role != null && role.equals("ADMIN")){ %>
        <center><h2>Ajouter un client :</h2><br>    
    <% }else{ %>
        <center><h2>Inscrivez vous içi !</h2><br>
    <% } %>

        
         <%
                //Client client=(Client)request.getAttribute("client");
                ClientForm form = (ClientForm)request.getAttribute("form");
            %>
            
        <form style="width: 800px;margin-left: 10px" action="ajouterClient" method="POST">
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="exampleInputEmail1">Nom :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez un nom" name="nom">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Prénom :</label><span class="erreur-formulaire"> ${pTabErreurs['prenom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un prénom" name="prenom">
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Mot de passe :</label><span class="erreur-formulaire"> ${pTabErreurs['mdp']}</span>
                <input required type="password" class="form-control" id="exampleInputEmail1" placeholder="Entrez un mot de passe" name="mdp">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Rue :</label><span class="erreur-formulaire"> ${pTabErreurs['rue']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez une rue" name="rue">
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Code postal :</label><span class="erreur-formulaire"> ${pTabErreurs['cp']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un code postal" name="copos">
              </div>  
            </div>
            <div class="col">
            <div class="form-group">
              <label for="exampleInputEmail1">Adresse mail</label><span class="erreur-formulaire"> ${pTabErreurs['mail']}</span>
              <input required type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
              <small id="emailHelp" class="form-text text-muted">Nous ne partagerons jamais votre adresse mail.</small>
            </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Ville :</label><span class="erreur-formulaire"> ${pTabErreurs['ville']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez une ville" name="ville">
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect1">Choisissez un pays :</label><span class="erreur-formulaire"> ${pTabErreurs['pays']}</span>
                <select required class="form-control" name="pays" id="exampleFormControlSelect1">
                    <%
                        ArrayList<Pays> lesPays = (ArrayList)request.getAttribute("pLesPays");
                        for (int i=0; i<lesPays.size();i++){
                            Pays p = lesPays.get(i);
                            out.println("<option value='" + p.getCode()+"'>" + p.getNom()+"</option>" );
                        }
                    %>
                </select>
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect2">Choisissez parmis les catégories :</label><span class="erreur-formulaire"> ${pTabErreurs['categ']}</span>
                <select required multiple size="4" class="form-control" name="categVente" id="exampleFormControlSelect2">
                <%
                        ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        for (int i=0; i<lesCategVente.size();i++){
                            CategVente cv = lesCategVente.get(i);
                            out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>"); 
                           
                        }
                    %>
                </select>
              </div>
            </div>
          </div>
        </div>
        <center><button type="submit" class="btn btn-primary">S'inscrire</button></center>
       </form>
</body></center>
    
    <%
    }
    %>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
