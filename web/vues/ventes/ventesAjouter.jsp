<%-- 
    Document   : ventesAjouter
    Created on : 30 sept. 2019, 09:33:17
    Author     : Quentin
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.VentesForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Ajouter une vente</title>
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
    <center><h2>NOUVELLE VENTE :</h2></center><br><br>
        
         <%
                //Client client=(Client)request.getAttribute("client");
                VentesForm form = (VentesForm)request.getAttribute("form");
                
            %>
            
        <form action="ajouterVente" method="POST">            
                <div class="container">
                  <div class="row">
                    <div class="col">
                        <div class="form-group">
                          <label for="exampleFormControlInput1">Nom de la vente :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                          <input required type="text" name="nom" size="30" maxlength="30" class="form-control" id="exampleFormControlInput1" placeholder="Entre un nom de vente">
                        </div></br>

                        <div class="form-group">
                          <label for="exampleFormControlInput1">Date de début : <I>(Format AAAA-MM-JJ)</I></label><span class="erreur-formulaire"> ${pTabErreurs['dateDebut']}</span>
                          <input required type="text" name="dateDebut" size="10" class="form-control" id="exampleFormControlInput1" placeholder="Entrez une date de début">
                        </div><br>

                      <div class="form-group">
                        <label for="exampleFormControlSelect1">Lieu de la vente :</label><span class="erreur-formulaire"> ${pTabErreurs['lieu']}</span>
                        <select required name="lieu" class="form-control" id="exampleFormControlSelect1">
                                        <%
                                            ArrayList<Lieu> lesLieux = (ArrayList)request.getAttribute("pLesLieux");
                                                for (int i=0; i<lesLieux.size();i++){
                                                Lieu l = lesLieux.get(i);
                                                out.println("<option value='" + l.getId()+"'>" + l.getVille()+"</option>" );
                                            }
                                        %>
                        </select>
                      </div><br>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Catégorie de la vente :</label><span class="erreur-formulaire"> ${pTabErreurs['categ']}</span><br>
                       <%--  Cases à cocher permettant de choisir les différentes catégories de vente auxquelles le client souhaite s'inscrire --%>
                      <%
                               ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                                for (int i=0; i<lesCategVente.size();i++){ 
                                    CategVente cv = lesCategVente.get(i); %>

                                <div class="form-check form-check">
                                  <input class="form-check-input" name="categ" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="<% out.println(cv.getCode()); %>">
                                  <label class="form-check-label" for="inlineRadio1"><% out.println(cv.getLibelle()); %></label><br>
                                </div>

                               <% }
                            %>
                        <br><button type="submit" name="valider" class="btn btn-primary">Valider</button>
                      </div> </br>
                    </div>
                  </div>
                </div> 
        </form>  
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
