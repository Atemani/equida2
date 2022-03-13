<%-- 
    Document   : listerLesTypesChevaux
    Created on : 1 oct. 2019, 22:11:41
    Author     : bastu
--%>

<%@page import="modele.Entraineur"%>
<%@page import="modele.TypeCheval"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des entraineurs</title>
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
        <h1>Liste des entraineurs inscrits</h1>
        <%
            ArrayList<Entraineur> lesEntraineurs = (ArrayList)request.getAttribute("pLesEntraineurs");
        %>
        <table  class="table" style="width: 1200px">  
            <thead class="thead-dark">
                <tr>             
                    <th>id</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Rue</th>
                    <th>Code postal</th>
                    <th>Ville</th>
                    <th>Téléphone</th>
                    <th>Nombre de victoires</th>
                    <th>Chevaux entrainés</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesEntraineurs.size();i++)
                    {
                        
                        Entraineur unEntraineur = lesEntraineurs.get(i);
                        out.println("<tr><td>");
                        out.println(unEntraineur.getId());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unEntraineur.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unEntraineur.getPrenom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unEntraineur.getAdresse());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unEntraineur.getCopos());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unEntraineur.getVille());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unEntraineur.getTelephone());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unEntraineur.getNbVictoires());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println("<a href='../../EquidaWebPeaky/ServletEntraineur/listeChevauxEntraines?codeEntraineur="+unEntraineur.getId()+"'>Voir les chevaux entrainés</a>");
                        out.println("</td>");
                                
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
