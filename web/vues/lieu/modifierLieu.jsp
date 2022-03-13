<%-- 
    Document   : ajouterTypeCheval
    Created on : 1 oct. 2019, 11:49:27
    Author     : bastu
--%>

<%@page import="modele.Lieu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Course"%>
<%@page import="modele.Cheval"%>
<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>  
    <title>Equida - modifier un lieu</title>
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
    ArrayList<Lieu> lesLieux = (ArrayList)request.getAttribute("pLeslieux");
    Lieu leLieu = (Lieu)request.getAttribute("pLeLieu");
%>

        <center><h1>Ajouter un lieu</h1><br>
        <form action="modifierLieu" method="POST">
            <input type="hidden" name="idLieu" value="<% out.println(leLieu.getId()); %>"/>
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputEmail1">Ville :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                <select required class="form-control" name="ville" id="exampleFormControlSelect1">
                    <%
                        for (int i=0; i<lesLieux.size();i++){
                            Lieu unLieu = lesLieux.get(i);
                            if((unLieu.getVille()).equals(leLieu.getVille())){
                                out.println("<option SELECTED value='" + unLieu.getVille()+"'>" + unLieu.getVille()+"</option>" );    
                            }else{
                                out.println("<option value='" + unLieu.getVille()+"'>" + unLieu.getVille()+"</option>" );    
                            }
                        }
                    %>
                </select>
            </div>
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputPassword1">Nombre de boxes :</label><span class="erreur-formulaire"> ${pTabErreurs['nbBoxes']}</span>
              <input required type="text" class="form-control" id="exampleInputPassword1" value="<% out.println(leLieu.getNbBoxes()); %>" placeholder="Précisez le nombre de boxes" name="boxes">
            </div>
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputPassword1">Commentaire :</label><span class="erreur-formulaire"> ${pTabErreurs['commentaire']}</span>
              <input required type="text" class="form-control" id="exampleInputPassword1" value="<% out.println(leLieu.getCommentaires()); %>" placeholder="Entrez un commentaire" name="commentaire">
            </div>
            <button type="submit" class="btn btn-primary">Modifier ce lieu</button>
        </form></center>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
