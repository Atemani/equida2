<%-- 
    Document   : listerLesTypesChevaux
    Created on : 1 oct. 2019, 22:11:41
    Author     : bastu
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.TypeCheval"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des chevaux</title>
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
        <h1>Liste des chevaux</h1>
        <%
            if(connecte){
                if(role.equals("ADMIN") || role.equals("EMPLOYE")){
                ArrayList<Cheval> lesChevaux = (ArrayList)request.getAttribute("pLesChevaux");
        %>
        <table  class="table" style="width: 1200px">  
            <thead class="thead-dark">
                <tr>             
                    <th>id</th>
                    <th>Nom</th>
                    <th>Sexe</th>
                    <th>Date de naissance</th>
                    <th>Siret</th>
                    <th>Race</th>
                    <th>Propriétaire</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesChevaux.size();i++)
                    {
                        
                        Cheval leCheval = lesChevaux.get(i);
                        out.println("<tr><td>");
                        out.println(leCheval.getId());
                        out.println("</td>");

                        out.println("<td>");
                        out.println("<a href='../ServletClient/infosCheval?codeCheval="+leCheval.getId()+"'>"+leCheval.getNom()+"</a>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println(leCheval.getSexe());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leCheval.getDateNaissance());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leCheval.getSire());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leCheval.getTypeCheval().getLibelle());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(leCheval.getClient().getNom()+" "+leCheval.getClient().getPrenom());
                        out.println("</td>");
                        
                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletCheval/modifierCheval?codeCheval="+leCheval.getId()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");
                        
                        out.println("<td style='width:160px;'>");
                        out.println("<a class='btn btn-danger' href='../../EquidaWebPeaky/ServletCheval/supprimerCheval?codeCheval="+leCheval.getId()+"' role='button'><i class='far fa-trash-alt'></i>Supprimer</a>");
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
