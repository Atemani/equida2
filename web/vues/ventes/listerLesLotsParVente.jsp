<%-- 
    Document   : listerLesLots
    Created on : 24 sept. 2019, 11:33:17
    Author     : slam
--%>
<%@page import="modele.Vente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
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
</head>

<%@ include file="/vues/inc/header.jsp" %>
        <%
            ArrayList<Lot> lesLots = (ArrayList)request.getAttribute("pLesLots");
            Vente unevente = (Vente)request.getAttribute("pUneVente");
            
            out.println("<h4>Les Lots ci-dessous appartiennent à la vente "+unevente.getId()+" qui a eu lieu le"+unevente.getDateDebutVente()+" à "+unevente.getUnLieu().getVille()+""+"</h4>");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les Lots par Ventes</title>
    </head>
    <body>
                <table  class="table table-bordered table-striped table-condensed">  

        <h2>Liste des lots pour la vente <% out.println(unevente.getNom()); %></h2>
        
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>N°Lot</th>
                    <th>Sexe</th>
                    <th>Type</th>
                    <th>Nom</th>
                    <th>Père</th>
                    <th>Mère</th>
                    <th>Vendeur</th>
                    <th>Prix départ</th>

            <br>
            <br>
                </tr>
            </thead>
<tbody>
                <tr>
                    <%
                        
                    for(int i = 0; i < lesLots.size();i++)
                    {
                        
                        Lot unLot = lesLots.get(i);
                        
                        out.println("<tr><td>");
                        out.println(i+1);
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unLot.getUnCheval().getSexe());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unLot.getUnCheval().getTypeCheval().getLibelle());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unLot.getUnCheval().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unLot.getUnCheval().getPere().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unLot.getUnCheval().getMere().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unLot.getUnVendeur().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unLot.getLot_prixDepart());
                        out.println("</td>");
                              
                        out.println("<td><a href ='../ServletVentes/listerLesEncheresParLot?codeLot="+unLot.getLot_id()+"'>");
                        out.println("Encheres de ce lot");
                        out.println("</td></tr>"); 
                    }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
