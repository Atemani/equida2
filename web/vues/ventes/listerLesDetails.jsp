<%-- 
    Document   : Lister les courriels par vente 
    Created on : 16/09/2019, 15:40
    Author     : Thomas
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Courriel"%>
<%@page import="modele.PieceJointe"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des details</title>
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
    <h2>Détails du courriel :</h2><br>
         <%
        Courriel leCourriel = (Courriel)request.getAttribute("pLeCourriel");
        ArrayList<PieceJointe> lesPiecesJointes = (ArrayList)request.getAttribute("pLesPiecesJointes");
        %>
        


                <tr>
                    <form>
                        <div class="form-row">
                          <div class="form-group col-md-4 offset-md-2">
                            <label for="inputEmail4">Objet</label>
                            <input type="email" class="form-control" id="inputEmail4" disabled value="<%out.println(leCourriel.getObjetMessage());%>">
                          </div>
                            
                          <div class="form-group col-md-4">
                            <label for="inputEmail4">Date d'envoie</label>
                            <input type="email" class="form-control" id="inputEmail4" disabled value="<%out.println(leCourriel.getDateEnvoiMessage());%>">
                          </div>
                            
                        </div>
                        
                          <div class="form-row">
                        <div class="form-group col-md-8 offset-md-2">
                            <label for="inputEmail4">Message</label>
                            <input type="email" class="form-control" id="inputEmail4" disabled value="<%out.println(leCourriel.getCorpMessage());%>">
                          </div>
                          </div>
                          
                          <div class="form-row">
                          <div class="form-group col-md-6 offset-md-2">
                            <label for="inputEmail4">Pièces jointes</label>

                            <div class="card col-md-2">
                                
                            <% if(lesPiecesJointes.size() != 0){
                            for(int i = 0; i < lesPiecesJointes.size();i++)
                            { 
                            PieceJointe unePieceJointe = lesPiecesJointes.get(i);
                            %>
    
                                  <% out.println("<img height=200px width=300px src='"+unePieceJointe.getChemin()+"'/><br>"); %>
       
                            <%}                   
                        }else{
                            out.println("Aucune pièce jointe associé...");
                        } %>
                        
                        </div>
                          </div>
                        
                          </div>                  
                        </div>
                      </form>  
                </tr>

    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
