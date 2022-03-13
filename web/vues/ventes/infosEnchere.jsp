<%-- 
    Document   : Acceuil
    Created on : 28 sept. 2019, 11:00:05
    Author     : Thomas
--%>
<%@page import="modele.Lot"%>
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
                      Lot unLot = (Lot)request.getAttribute("pLeLot");
                  %>
        <br><br>
        <div class="container">
            <div class="row">
              <div class="col">
                <h4>Informations générales : </h4><br>
                <label style="font-weight: bold">Prix de départ : </label> <label> <% out.println(unLot.getLot_prixDepart());%></label><br>
                <label style="font-weight: bold">Vente associée : </label> <label> <% out.println(unLot.getUneVente().getNom());%></label><br>
                <label style="font-weight: bold">Cheval vendu : </label> <a href="../ServletClient/infosCheval?codeCheval=<% out.println(unLot.getUnCheval().getId());%>"><label> <% out.println(unLot.getUnCheval().getNom());%></label></a><br>
                <label style="font-weight: bold">Propriétaire : </label> <label> <% out.println(unLot.getUnCheval().getClient().getPrenom()+" "+unLot.getUnCheval().getClient().getNom());%></label><br>           
              </div>
              <div class="col">
                <h4>Enchères de ce lot : </h4>
                <div class="card" style="width: 18rem;">
                  <ul class="list-group list-group-flush">
                    <% for (int i=0;i<lesEncheres.size();i++){
                        Enchere uneEnchere = lesEncheres.get(i); %>
                        <% if (unClient.getId() == uneEnchere.getUnAcheteur().getUnCient().getId()){ %>
                        <li style="background-color: rgba(46, 204, 113,0.5)" class="list-group-item"><% out.println("Acheteur : "+uneEnchere.getUnAcheteur().getUnCient().getNom()+"<br>Prix : <strong>"+uneEnchere.getPrix()+"€</strong>"); %></li>     
                        <% }else{ %>
                        <li class="list-group-item"><% out.println("Acheteur : "+uneEnchere.getUnAcheteur().getUnCient().getNom()+"<br>Prix : <strong>"+uneEnchere.getPrix()+"€</strong>"); %></li>     
                        <% } %>
                           
                    <% } %>
                  </ul>
                </div>                  
              </div>
            </div>
        </div>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
