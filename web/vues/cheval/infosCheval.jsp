<%-- 
    Document   : infosCheval
    Created on : 1 oct. 2019, 09:36:18
    Author     : bastu
--%>
<%@page import="modele.Participer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Informations cheval</title>
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
            Cheval leCheval = (Cheval)request.getAttribute("pLeCheval");
            ArrayList<Participer> lesParticipations = (ArrayList)request.getAttribute("pLesParticipations");
        %>
            
<div class="container">
  <div class="row">
    <div class="col">
        <h2>Informations du cheval "<% out.println(leCheval.getNom());%>" :</h2><br>
          <div class="card" style="width: 25rem;margin-left: 10px">
            <ul class="list-group list-group-flush">
              <li class="list-group-item"><label style="font-weight: bold">Nom : </label> <label> <% out.println(leCheval.getNom());%></label></li>
              <li class="list-group-item"><label style="font-weight: bold">Sexe : </label> <label> <% out.println(leCheval.getSexe());%></label></li>
              <li class="list-group-item"><label style="font-weight: bold">Siret : </label> <label> <% out.println(leCheval.getSire());%></label></li>
              <li class="list-group-item"><label style="font-weight: bold">Date naissance : </label> <label> <% out.println(leCheval.getDateNaissance());%></label></li>
              <li class="list-group-item"><label style="font-weight: bold">Race : </label> <label> <% out.println(leCheval.getTypeCheval().getLibelle());%></label></li>
              <li class="list-group-item"><label style="font-weight: bold">Nom du propriétaire : </label> <% out.println(leCheval.getClient().getPrenom()+" "+leCheval.getClient().getNom());%> <label></li>
            </ul>
        </div>
    </div>
    <div class="col">
        <h2>Historique des courses :</h2><br>
           <%
                    if(lesParticipations.size()!=0){
                        for(int i = 0; i < lesParticipations.size();i++)
                        {
                            Participer uneParticipation = lesParticipations.get(i); %>

                            <div class="card" style="width: 18rem;">
                              <ul class="list-group list-group-flush">
                                <li class="list-group-item">Nom de la course : <% out.println(uneParticipation.getUneCourse().getLibelle()); %></li>
                                <li class="list-group-item">Classement : <% out.println(uneParticipation.getPlace()); %></li>
                              </ul>
                            </div><br>
                     <% }
                        if((leCheval.getClient().getId()) == unClient.getId()){
                            out.println("<a href='../../EquidaWebPeaky/ServletCheval/ajouterUneParticipation?codeCheval="+leCheval.getId()+"'>Enregistrer une participation ?</a>");
                        }
                    }else{
                        out.println("Aucune course enregistrée pour ce cheval ...<br>");
                        if((leCheval.getClient().getId()) == unClient.getId()){
                            out.println("<a href='../../EquidaWebPeaky/ServletCheval/ajouterUneParticipation?codeCheval="+leCheval.getId()+"'>Enregistrer une participation ?</a>");
                        }
                    }

          %>
    </div>
  </div>
</div>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
