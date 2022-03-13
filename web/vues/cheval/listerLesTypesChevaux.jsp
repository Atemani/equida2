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
    <title>Equida - Liste des types de chevaux</title>
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
    <h1>Liste des types de chevaux inscrits</h1>
        <%
            ArrayList<TypeCheval> lesTypesChevaux = (ArrayList)request.getAttribute("pLesTypesChevaux");
        %>
        <table  class="table" style="width: 1200px">  
            <thead class="thead-dark">
                <tr>             
                    <th>id</th>
                    <th>Libelle</th>
                    <th>Description</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesTypesChevaux.size();i++)
                    {
                        
                        TypeCheval unTypeCheval = lesTypesChevaux.get(i);
                        out.println("<tr><td>");
                        out.println(unTypeCheval.getId());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unTypeCheval.getLibelle());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unTypeCheval.getDescription());
                        out.println("</td>");
                        
                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletCheval/modifierTypesCheval?codeTypeCheval="+unTypeCheval.getId()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");
                        
                        out.println("<td style='width:160px;'>");
                        out.println("<a class='btn btn-danger' href='../../EquidaWebPeaky/ServletCheval/supprimerTypesCheval?codeTypeCheval="+unTypeCheval.getId()+"' role='button'><i class='far fa-trash-alt'></i>Supprimer</a>");
                        out.println("</td></tr>");
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
