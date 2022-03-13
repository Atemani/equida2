<%-- 
    Document   : Lister les catégories 
    Created on : 10/09/2019, 10:40
    Author     : Thomas
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des catégories de vente</title>
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
        <h1>LISTE DES CATEGORIES</h1>
        <%
        ArrayList<CategVente> lesCategories = (ArrayList)request.getAttribute("pLesCategories");
        %>
        <table  class="table" style="width: 1200px">  
            <thead class="thead-dark">
                <tr>  
                    <%  if(connecte){ 
                        if(role.equals("ADMIN") || role.equals("EMPLOYE")){ %>   
                    <th>Id</th>
                    <th>Libelle</th>
                    <th>Liste des ventes</th>
                    <th>Modifier</th>
                    <% }else{ %>
                    <th>Id</th>
                    <th>Libelle</th>
                    <th>Liste des ventes</th>
                     <% }
                    }else{ %>
                    <th>Id</th>
                    <th>Libelle</th>
                    <th>Liste des ventes</th>
                    <% } %>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesCategories.size();i++)
                    {
                        
                    CategVente uneCategorie = lesCategories.get(i);
                        if(connecte){
                            
                        if(role.equals("ADMIN") || role.equals("EMPLOYE")){
                        
                        out.println("<tr><td>");
                        out.println(uneCategorie.getCode());
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println(uneCategorie.getLibelle());
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesVentesParCateg?codeCat="+ uneCategorie.getCode()+ "'>");
                        out.println("Lister les ventes associées");
                        out.println("</td>");
                        
                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletVentes/modifierCateg?codeCateg="+uneCategorie.getCode()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");
                         }else{
                              out.println("<tr><td>");
                        out.println(uneCategorie.getCode());
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println(uneCategorie.getLibelle());
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesVentesParCateg?codeCat="+ uneCategorie.getCode()+ "'>");
                        out.println("Lister les ventes associées");
                        out.println("</td>"); 
                    }
                        }else{
                            out.println("<tr><td>");
                        out.println(uneCategorie.getCode());
                        out.println("</a></td>");

                        out.println("<td>");
                        out.println(uneCategorie.getLibelle());
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesVentesParCateg?codeCat="+ uneCategorie.getCode()+ "'>");
                        out.println("Lister les ventes associées");
                        out.println("</td>"); 
                    }
                        }
                    %>
                </tr>
            </tbody>
        </table>        
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
