<%-- 
    Document   : listerLesCourses
    Created on : 7 oct. 2019, 13:40:07
    Author     : slam
--%>

<%@page import="modele.Course"%>
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
            ArrayList<Course> lesCourses = (ArrayList)request.getAttribute("pLesCourses");                      
        %>
        <title>Liste des Courses</title>
    </head>
    <body>
            <table  class="table" style="width: 1200px"> 
            <thead class="thead-dark">
                <tr>
                    <% if(role.equals("ADMIN")||role.equals("EMPLOYE")){%>
                    <th>Nom</th>
                    <th>Lieu</th>
                    <th>Date</th>
                    <th>Modifer</th>
                    <th>Supprimer</th>
                    <% }else{ %>
                    <th>Nom</th>
                    <th>Lieu</th>
                    <th>Date</th>                
                    <% } %>
                                      
            <br>
            <br>
            </tr>
            </thead>
            <tbody>
                <%
                if(role.equals("ADMIN")||role.equals("EMPLOYE")){
                    for(int i = 0; i < lesCourses.size();i++)
                    {
                   
                        Course uneCourse = lesCourses.get(i);                       
                        
                        out.println("<td><a href ='../ServletCourse/listerLesParticipationsparCourses?codeCourse="+uneCourse.getId()+"'</a>");  
                        out.println(uneCourse.getLibelle());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneCourse.getLieu().getVille());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneCourse.getDate());
                        out.println("</td>");

                        out.println("<td style='width:140px;'>");
                        out.println("<a class='btn btn-primary' href='../../EquidaWebPeaky/ServletCourse/modifierCourse?codeCourse="+uneCourse.getId()+"' role='button'><i class='far fa-edit'></i>Modifier</a>");
                        out.println("</td>");

                        out.println("<td style='width:160px;'>");
                        out.println("<a class='btn btn-danger' href='../../EquidaWebPeaky/ServletCourse/supprimerCourse?codeCourse="+uneCourse.getId()+"' role='button'><i class='far fa-trash-alt'></i>Supprimer</a>");
                        out.println("</td></tr>");                              
                        

                    }                      
                }else{
                    for(int i = 0; i < lesCourses.size();i++)
                    {
                   
                        Course uneCourse = lesCourses.get(i);                       
                        
                        out.println("<td><a href ='../ServletCourse/listerLesParticipationsparCourses?codeCourse="+uneCourse.getId()+"'</a>");  
                        out.println(uneCourse.getLibelle());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneCourse.getLieu().getVille());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneCourse.getDate());
                        out.println("</td></tr>");

                    }                       
                }     
                   
                %>
            </tbody>
        </table>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>