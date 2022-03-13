<%-- 
    Document   : informationlesLots
    Created on : 24 sept. 2019, 11:51:51
    Author     : slam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="../img/horse.png" /> 
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Les données si dessous seront en out Print or de la table</h1>
                    <h3>N°Lot, Nom, Type, Sexe, N°SIRE</h3>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>Prix départ</th>
                    <th>Vendeur</th>
                    <th>Père</th>
                    <th>Mère</th>
                    <th>Résultats en course</th>
                                      
            <br>
            <br>
                </tr>
            </thead>
             <tbody>
                <tr>
                </tr>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
