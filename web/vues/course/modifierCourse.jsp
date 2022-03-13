<%-- 
    Document   : ventesAjouter
    Created on : 30 sept. 2019, 09:33:17
    Author     : slam
--%>


<%@page import="modele.Lieu"%>
<%@page import="modele.Course"%>
<%@page import="java.util.ArrayList"%>

<%@page import="formulaires.CoursesForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Modifier une course</title>
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
    <body>
         <%
                //Client client=(Client)request.getAttribute("client");
                Course uneCourse = (Course)request.getAttribute("pLaCourse");
                ArrayList<Lieu> lesLieux = (ArrayList)request.getAttribute("pLeslieux");
                
            %>
    <%
      if(connecte){
          if(role.equals("ADMIN") || role.equals("EMPLOYE")){

    %>
        <h1>MODIFIER LA COURSE "<%out.println(uneCourse.getLibelle());%>" : </h1>
        
        <form action="modifierCourse" method="POST"> 
            <input type="hidden" name="idCourse" value="<% out.println(uneCourse.getId()); %>">
                <div class="container">
                  <div class="row">
                    <div class="col">
                        <div class="form-group">
                          <label for="exampleFormControlInput1">Nom</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                          <input required type="text" name="nom" size="30" maxlength="30" class="form-control" id="nom" size="10" value="<% out.println(uneCourse.getLibelle()); %>" maxlength="10" placeholder="Entre un nom de course">
                        </div></br>

                        <div class="form-group">
                            <label for="exampleFormControlInput1">Date de début <i>(FORMAT AAAA-MM-JJ)</i></label><span class="erreur-formulaire"> ${pTabErreurs['dateDebut']}</span>
                          <input required type="text" name="date" size="10" class="form-control" id="date" size="10" maxlength="10" value="<% out.println(uneCourse.getDate()); %>" placeholder="Entrez une date de début">
                        </div><br>
                        
                        <div class="form-group">
                            <label for="lieu"><strong>Lieu de la course :</strong></label><span class="erreur-formulaire"> ${pTabErreurs['lieu']}</span>
                              <select required class="form-control" name="lieu" id="exampleFormControlSelect1">
                                  <%
                                    for (int i=0; i<lesLieux.size();i++){
                                    Lieu unLieu = lesLieux.get(i);
                                      if(uneCourse.getLieu().getId() == unLieu.getId()){
                                        out.println("<option SELECTED value='" + unLieu.getId()+"'>" + unLieu.getVille()+"</option>" );                          
                                      }else{
                                        out.println("<option value='" + unLieu.getId()+"'>" + unLieu.getVille()+"</option>" );                          
                                      }
                                    }
                                  %>
                              </select>
                            </br> 
                        
                        <br><button type="submit" name="valider" class="btn btn-primary" id="valider" >Valider</button>

                    </div>
                  </div>
                </div>
        </form> 
        <%
                }else{
                    out.println("<h4>Vous n'avez pas les droits pour accéder à cette page. Contactez l'administrateur pour plus d'informations.");
                }
            }else{
               out.println("<h4>Vous n'êtes pas autorisé à aller sur cette page. Cliquez <a href='../../../../EquidaWebPeaky/ServletConnexion/connexion'>içi</a> pour vous connecter !");
            }
        %>
    </body>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
