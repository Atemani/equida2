<%-- 
    Document   : ajouterTypeCheval
    Created on : 1 oct. 2019, 11:49:27
    Author     : bastu
--%>

<%@page import="modele.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>
<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>  
    <title>Equida - Ajouter une participation</title>
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
            ArrayList<Course> lesCourses = (ArrayList)request.getAttribute("pLesCourses");
        %>
        <center><h2>Ajouter une participation :</h2><br>
        <form action="ajouterUneParticipation" method="POST">
            
            <input type="hidden" name="idCheval" value="<% out.println(leCheval.getId()); %>"/>
            
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputEmail1">Nom du cheval</label>
              <input required type="text" name="nom" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="<% out.println(leCheval.getNom()); %>" placeholder="Entrez un nom">
              <!--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
            </div>
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputPassword1">Choisissez une course</label>
                <select required class="form-control" name="course" id="exampleFormControlSelect1">
                    <%
                        for (int i=0; i<lesCourses.size();i++){
                            Course uneCourse = lesCourses.get(i);
                            out.println("<option value='" + uneCourse.getId()+"'>" + uneCourse.getLibelle()+"</option>" );
                        }
                    %>
                </select>
            </div>
            <div class="form-group" style="width: 400px;">
              <label for="exampleInputPassword1">Place</label>
              <input required type="text" class="form-control" id="exampleInputPassword1" placeholder="Entrez la place finale de votre cheval" name="place">
            </div>
            <button type="submit" class="btn btn-primary">Ajouter cette participation</button>
        </form></center>
    </body>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
