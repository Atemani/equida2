<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Liste des ventes</title>
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

        <h1>LISTE DES VENTES</h1>
         <%
        ArrayList<Vente> lesVentes = (ArrayList)request.getAttribute("pLesVentes");
        %>
        <table  class="table table-bordered" style="width: 1400px">  
            <thead class="thead-dark">
                <tr>
                    
                <%  if(connecte){ 
                        if(role.equals("ADMIN") || role.equals("EMPLOYE")){ %>   
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Date début</th>
                            <th>Lieu</th>
                            <th>Catégorie</th>
                            <th>Clients</th>
                            <th>Mails</th>
                            <th>Lots</th>
                            <th>Modifier</th>
                            <th>Supprimer</th>
                        <% }else{ %>
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Date début</th>
                            <th>Lieu</th>
                            <th>Catégorie</th>  
                            <th>Lots</th>
                        <% }
                    }else{ %>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Date début</th>
                        <th>Lieu</th>
                        <th>Catégorie</th>
                    <% } %>

                <br>
                <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesVentes.size();i++)
                    {
                        Vente uneVente = lesVentes.get(i);
                        if(connecte){
                            
                        if(role.equals("ADMIN") || role.equals("EMPLOYE")){                  
                        out.println("<tr><th>");
                        out.println(uneVente.getId());
                        out.println("</th>");

                        out.println("<td>");
                        out.println(uneVente.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getDateDebutVente());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneVente.getUnLieu().getVille());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getUneCategVente().getLibelle());
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesClients?codeCat="+ uneVente.getUneCategVente().getCode()+ "'>");
                        out.println("Clients interessés");
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesCourrielsParVente?codeVente="+uneVente.getId()+"'>");
                        out.println("Mails envoyés");
                        out.println("</td>");
                        
                        out.println("<td><a href ='../ServletVentes/listerLesLotsParVente?codeVente="+uneVente.getId()+"'>");
                        out.println("Lots de cette vente");
                        out.println("</td>");
                        
                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletVentes/modifierVente?codeVente="+uneVente.getId()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");
                        
                        out.println("<td style='width:160px;'>");
                        out.println("<a class='btn btn-danger' href='../../EquidaWebPeaky/ServletVentes/supprimerVente?codeVente="+uneVente.getId()+"' role='button'><i class='far fa-trash-alt'></i>Supprimer</a>");
                        out.println("</td></tr>");
                        }else{
                        out.println("<tr><th>");
                        out.println(i+1);
                        out.println("</th>");

                        out.println("<td>");
                        out.println(uneVente.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getDateDebutVente());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneVente.getUnLieu().getVille());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getUneCategVente().getLibelle());
                        out.println("</td>");  
                          
                        out.println("<td><a href ='../ServletVentes/listerLesLotsParVente?codeVente="+uneVente.getId()+"'>");
                        out.println("Lots de cette vente");
                        out.println("</td>");
                        }
                        
                    }else{
                        out.println("<tr><th>");
                        out.println(i+1);
                        out.println("</th>");

                        out.println("<td>");
                        out.println(uneVente.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getDateDebutVente());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneVente.getUnLieu().getVille());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(uneVente.getUneCategVente().getLibelle());
                        out.println("</td>");
                    }
                    }%>
                </tr>
            </tbody>
        </table>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
