<%-- 
    Document   : listerLesParticipationsparCourses
    Created on : 7 oct. 2019, 15:44:46
    Author     : slam
--%>

<%@page import="modele.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Participer"%>
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
                  ArrayList<Participer> LesParticipations = (ArrayList)request.getAttribute("pLesParticipations"); 
                  Course uneCourse = (Course)request.getAttribute("pUneCourse");
                  
                  out.println("<h5>Les résultats de la course "+uneCourse.getLibelle()+" du "+uneCourse.getDate()+" à "+uneCourse.getLieu().getVille()+"</h5>");
        
        %>
        <title>Liste des Résultats pour la course</title>
     </head>
      <body>
            <table  style="width:800px;" class="table table-bordered table-striped table-condensed">  
            <thead class="thead-dark">
                <tr>                                
                    <th>Cheval</th>
                    <th>Place</th>
                    <th>Date</th>
                                      
            <br>
            <br>
                </tr>
            </thead>
     <tbody>
                <%
                        
                    for(int i = 0; i < LesParticipations.size();i++)
                    {
                        
                        Participer uneParticipation = LesParticipations.get(i);                       
                        
                        out.println("<td>");
                        out.println("<a href='../ServletClient/infosCheval?codeCheval="+uneParticipation.getUnCheval().getId()+"'>"+uneParticipation.getUnCheval().getNom()+"</a>");
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneParticipation.getPlace());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneParticipation.getUneCourse().getDate());
                        out.println("</td></tr>");
                     
                        
                    }
                    %>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>