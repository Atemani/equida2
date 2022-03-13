<%-- 
    Document   : Acceuil
    Created on : 28 sept. 2019, 11:00:05
    Author     : Thomas
--%>
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
                      ArrayList<Cheval> lesChevaux = (ArrayList)request.getAttribute("pLesChevaux");
                      ArrayList<Enchere> lesEncheres = (ArrayList)request.getAttribute("pLesEncheres");
                  %>
        <br><br>
        <div class="container">
            <div class="row">
              <div class="col">
                  <h4>Informations générales : </h4><br>
                <label style="font-weight: bold">Nom : </label> <label> <% out.println(unClient.getNom());%></label><br>
                <label style="font-weight: bold">Prénom : </label> <label> <% out.println(unClient.getPrenom());%></label><br>
                <label style="font-weight: bold">Rue : </label> <label> <% out.println(unClient.getRue());%></label><br>
                <label style="font-weight: bold">Ville : </label> <label> <% out.println(unClient.getVille());%></label><br>
                <label style="font-weight: bold">Code postal : </label> <label> <% out.println(unClient.getCopos());%></label><br>
                <label style="font-weight: bold">Email : </label> <label>
                    <% 
                    if(unClient.getMail() == null){
                        out.println("Aucun email renseigné ...");
                    }else{
                    out.println(unClient.getMail());                
                    }
                    %>
                </label><br>
              </div>
              <div class="col">
                  <h4>Vos chevaux : </h4><br>
                  
                  <%
                    if(lesChevaux.size() != 0){
                        for (int i=0; i<lesChevaux.size();i++){
                            Cheval unCheval = lesChevaux.get(i);%>                        

                            <div class="col-sm-6">
                              <% if(unCheval.getActive() == 2){ %>
                              <div class="card bg-light mb-3">
                                <div class="card-body">
                                  <h5 class="card-title"><% out.println(unCheval.getNom());%></h5>
                                  <p class="card-text"><% out.println("Sexe : "+unCheval.getSexe());%><br>
                                                       <% out.println("SIRE : "+unCheval.getSire());%></p>
                                  <a href="../ServletClient/infosCheval?codeCheval=<% out.println(unCheval.getId());%>" class="btn btn-primary">Plus d'informations</a>
                                </div>
                              </div>                                  
                              <% }else{ %>
                              <div class="card text-white bg-warning mb-3">
                                <div class="card-body">
                                  <p>En attente de validation</p>
                                  <h5 class="card-title"><% out.println(unCheval.getNom());%></h5>
                                  <p class="card-text"><% out.println("Sexe : "+unCheval.getSexe());%><br>
                                                       <% out.println("SIRE : "+unCheval.getSire());%></p>
                                  <a href="../ServletClient/infosCheval?codeCheval=<% out.println(unCheval.getId());%>" class="btn btn-secondary">Plus d'informations</a>
                                </div>
                              </div>   
                              <% } %>

                            </div><br>  
                        <% }
                    out.println("<a href='../../EquidaWebPeaky/ServletCheval/ajouterCheval'>Ajouter un cheval ?</a>");
                    }else{
                        out.println("<a href='../../EquidaWebPeaky/ServletCheval/ajouterCheval'>Ajouter un cheval ?</a>");    
                    }
                  %>    
                  
              </div>
              <div class="col">
                  <h4>Vos enchères : </h4><br>
                  
                    <%
                    if(lesEncheres.size() != 0){
                        for (int i=0; i<lesEncheres.size();i++){
                            Enchere uneEnchere = lesEncheres.get(i);%>                        
                            <div class="card bg-light mb-3" style="max-width: 18rem;">
                              <div class="card-header"><% out.println("Enchère n°"+(i+1)+" : "+uneEnchere.getPrix());%></div>
                              <div class="card-body">
                                <h5 class="card-title"><% out.println("Lot concerné : "+uneEnchere.getUnLot().getUnCheval().getNom());%></h5>
                                <p class="card-text"><% out.println("Propriétaire : "+uneEnchere.getUnLot().getUnVendeur().getUnClient().getNom());%></p>
                                <a href="../ServletVentes/infosEnchere?codeLot=<% out.println(uneEnchere.getUnLot().getLot_id());%>" class="btn btn-primary">Plus d'informations</a>
                              </div>
                            </div><br>  
                        <% }
                    }else{
                        out.println("Vous n'avez participé à aucune vente...");    
                    }
                  %>
                  
              </div>
            </div>
        </div>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
