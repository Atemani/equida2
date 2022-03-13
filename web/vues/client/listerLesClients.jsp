<%-- 
    Document   : listerLesTypesChevaux
    Created on : 1 oct. 2019, 22:11:41
    Author     : bastu
--%>

<%@page import="modele.TypeCheval"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des clients</title>
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
        <h1>Liste des clients inscrits</h1>
        <%
            if(connecte){
                if(role.equals("ADMIN") || role.equals("EMPLOYE")){
            ArrayList<Client> lesClients = (ArrayList)request.getAttribute("pLesClients");
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
                    <th>Email</th>
                    <th>Chevaux</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesClients.size();i++)
                    {
                        
                        Client leClient = lesClients.get(i);
                        out.println("<tr><td>");
                        out.println(leClient.getId());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(leClient.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(leClient.getPrenom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getRue());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getCopos());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getVille());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leClient.getMail());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println("<a href='../../EquidaWebPeaky/ServletVentes/listerLesChevaux?codeClient="+leClient.getId()+"'>Voir les chevaux associées</a>");
                        out.println("</td>");
                                
                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletClient/modifierClient?codeClient="+leClient.getId()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");
                        
                        out.println("<td style='width:160px;'>");
                        out.println("<a class='btn btn-danger' href='../../EquidaWebPeaky/ServletClient/supprimerClient?codeClient="+leClient.getId()+"' role='button'><i class='far fa-trash-alt'></i>Supprimer</a>");
                        out.println("</td></tr>");
                    }
                    %>
                </tr>
            </tbody>
        </table>
    <%
            }else{
                out.println("<h4>Vous n'avez pas les droits pour accéder à cette page. Contactez l'administrateur pour plus d'informations.");
            }
        }else{
           out.println("<h4>Vous n'êtes pas autorisé à aller sur cette page. Cliquez <a href='../../../../EquidaWebPeaky/ServletConnexion/connexion'>içi</a> pour vous connecter !");
        }
    %>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
