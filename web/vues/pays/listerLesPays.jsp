<%-- 
    Document   : listerLesPays
    Created on : 8 oct. 2019, 11:26:51
    Author     : slam
--%>
<%@page import="modele.Pays"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
    <title>Equida - Liste les pays</title>
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
        <%@ include file="/vues/inc/header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <%
            ArrayList<Pays> lesPays = (ArrayList)request.getAttribute("pLesPays");                      
        %>
        <center><title>Liste des Pays</title>
    </head>
   <body>
       <h3>Liste des Pays</h3>
       
                <a class='btn btn-primary' role='button' href='../../EquidaWebPeaky/ServletPays/paysAjouter'><i class='far fa-edit'></i>Ajouter un Pays</a>
            <table style="width: 700px;" class="table table-bordered table-striped table-condensed">  
            <thead class="thead-dark">
                <tr>                                
                    <th>Code Pays</th>
                    <th>Libelle</th>
                    <th>Modifier</th>  
                    <th>Supprimer</th>
            <br>
            <br>
                </tr>
            </thead>
             <tbody>
                <%
                        
                    for(int i = 0; i < lesPays.size();i++)
                    {
                         Pays unPays = lesPays.get(i);
                        
                        out.println("<tr><td>");
                        out.println(unPays.getCode());
                        out.println("</td>");
                        
                         out.println("<td>");
                        out.println(unPays.getNom());
                        out.println("</td>");
                        
                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletPays/modifierPays?CodePays="+unPays.getCode()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");
                     
                          
                        out.println("<td style='width:160px;'>");
                        out.println("<a class='btn btn-danger' href='../../EquidaWebPeaky/ServletPays/supprimerPays?CodePays="+unPays.getCode()+"' role='button'><i class='far fa-trash-alt'></i>Supprimer</a>");
                        out.println("</td></tr>");
                       
                     
                    }
                    %>
            </tbody>
        </table></center>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
