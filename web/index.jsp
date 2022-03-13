<%-- 
    Document   : Acceuil
    Created on : 28 sept. 2019, 11:00:05
    Author     : Thomas
--%>

<%@page import="modele.Vente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.VenteDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.ChevalDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>  
    <title>Equida - Page d'acceuil</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="icon" type="image/png" href="img/horse.png" /> 
</head>

<%@ include file="/vues/inc/header.jsp" %>

    <%
   
    Connection connection=null;
    ServletContext servletContext=getServletContext();
    connection=(Connection)servletContext.getAttribute("connection");    
    
    int cptChevauxProposes = ChevalDAO.cptChevauxProposes(connection);
    ArrayList<Vente> listerLesVentes = VenteDAO.getLesVentes(connection);
    %>
        <center>
          <%
            if(connecte && (role.equals("EMPLOYE") || role.equals("ADMIN")) && cptChevauxProposes > 0){ %>
                <br><div style="width: 500px" class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Bonjour <% out.println(unClient.getPrenom()+" "+unClient.getNom()); %>, </strong> des nouveaux chevaux sont en attente de validation, cliquez <a href="../../EquidaWebPeaky/ServletCheval/listerLesChevauxProposes">içi</a> pour voir la liste !
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
                </div>     
                <br><h4>Les 5 prochaines ventes : </h4>
                <div  style="width: 1100px;margin-left: 20px" id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                  <ol class="carousel-indicators">
                    <% Vente vente1 = listerLesVentes.get(1); %>
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <% for(int i=0; i<listerLesVentes.size(); i++){ %>
                    <li data-target="#carouselExampleIndicators" data-slide-to="<% out.println(i);%>"></li>
                    <% } %>
                  </ol>
                  <div  style="border-radius: 15px;box-shadow: 1px 1px 5px;" class="carousel-inner">
                    <div class="carousel-item active">
                      <img src="img/automne.jpg" class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                          <h5><% out.println(vente1.getNom()); %></h5>
                          <p>Date début : <% out.println(vente1.getDateDebutVente());%></p>
                        </div>
                    </div>
                    <% for(int i = 0; i<listerLesVentes.size(); i++){
                        Vente uneVente = listerLesVentes.get(i);
                    %>
                    <div class="carousel-item">
                      <img src="img/ete.jpg" class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                          <h5><% out.println(uneVente.getNom()); %></h5>
                          <p>Date début : <% out.println(uneVente.getDateDebutVente());%></p>
                        </div>
                    </div>
                    <% } %>
                  </div>
                  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
              </center>
           <% }else{ %>
          <br><h4>Les 5 prochaines ventes : </h4>
          <div  style="width: 1100px;margin-left: 20px" id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <% Vente vente1 = listerLesVentes.get(1); %>
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <% for(int i=0; i<listerLesVentes.size(); i++){ %>
              <li data-target="#carouselExampleIndicators" data-slide-to="<% out.println(i);%>"></li>
              <% } %>
            </ol>
            <div  style="border-radius: 15px;box-shadow: 1px 1px 5px;" class="carousel-inner">
              <div class="carousel-item active">
                <img src="img/automne.jpg" class="d-block w-100" alt="...">
                  <div class="carousel-caption d-none d-md-block">
                    <h5><% out.println(vente1.getNom()); %></h5>
                    <p>Date début : <% out.println(vente1.getDateDebutVente());%></p>
                  </div>
              </div>
              <% for(int i = 0; i<listerLesVentes.size(); i++){ 
                  Vente uneVente = listerLesVentes.get(i);
              %>
              <div class="carousel-item">
                <img src="img/hiver.jpg" class="d-block w-100" alt="...">
                  <div class="carousel-caption d-none d-md-block">
                    <h5><% out.println(uneVente.getNom()); %></h5>
                    <p>Date début : <% out.println(uneVente.getDateDebutVente());%></p>
                  </div>
              </div>
              <% } %>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </center>
    <% } %>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>




