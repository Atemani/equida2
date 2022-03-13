<%-- 
    Document   : listerLesEncheresParLot
    Created on : 24 sept. 2019, 11:54:18
    Author     : slam
--%>

<%@page import="modele.Lot"%>
<%@page import="modele.Enchere"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Equida - Liste des lots par vente</title>
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
            ArrayList<Enchere> lesEncheres = (ArrayList)request.getAttribute("pLesEncheres");                      
            Lot unLot = (Lot)request.getAttribute("pUnLot"); 

            out.println("<h4>Le Cheval nommé "+unLot.getUnCheval().getNom()+" qui est "+unLot.getUnCheval().getTypeCheval().getLibelle()+""+"</h4>");
            out.println("<h5>Son Numéro de Siret est "+unLot.getUnCheval().getSire()+""+"</h5>");
        %>
        <title>Liste des enchères pour le lot <% out.println(unLot.getLot_id()); %></title>
    </head>
    <body>
            <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>                                
                    <th>N°Enchere</th>
                    <th>Acheteur</th>
                    <th>Montant</th>
                                      
            <br>
            <br>
                </tr>
            </thead>
             <tbody>
                <%
                        
                    for(int i = 0; i < lesEncheres.size();i++)
                    {
                        
                        Enchere uneEnchere = lesEncheres.get(i);
                        
                        out.println("<tr><td>");
                        out.println(i+1);
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneEnchere.getUnAcheteur().getUnCient().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneEnchere.getPrix());
                        out.println("</td></tr>");
                     
                    }
                    %>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
