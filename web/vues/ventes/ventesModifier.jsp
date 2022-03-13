<%-- 
    Document   : ventesModifier
    Created on : 30 sept. 2019, 09:33:17
    Author     : Quentin
--%>

<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.VentesForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Modifier une vente</title>
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
  if(connecte){
      if(role.equals("ADMIN") || (role.equals("EMPLOYE"))){
          
%>   
         <%
                Vente vente=(Vente)request.getAttribute("pLaVente");
                VentesForm form = (VentesForm)request.getAttribute("form");
                
            %>
            <h2>MODIFIER UNE VENTE</h2><br>
       <form style="width: 800px;margin-left: 10px" action="modifierVente" method="POST">
        
           <input type="hidden" name="idVente" value="<% out.println(vente.getId());%>"/>
        <div class="container">
            <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="nom">Nom :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                            <input required type="text" class="form-control" id="nom" placeholder="Entrez un nom" name="nom" value="<% out.println(vente.getNom()); %>">
                        </div>
                        <div class="form-group">
                            <label for="date">Date de début : <I>(Format AAAA-MM-JJ)</I></label><span class="erreur-formulaire"> ${pTabErreurs['dateDebut']}</span>
                            <input required type="text" class="form-control" id="date" placeholder="Entrez une date" name="date" value="<% out.println(vente.getDateDebutVente()); %>">
                        </div>
                        
                    </div>
                    <div class="col">
                         <div class="form-group">
                            <label for="lieu">Choisissez un lieu :</label><span class="erreur-formulaire"> ${pTabErreurs['lieu']}</span>
                            <select required class="form-control" name="lieu" id="lieu">
                    <%
                        ArrayList<Lieu> lesLieux = (ArrayList)request.getAttribute("pLesLieux");
                        for (int i=0; i<lesLieux.size();i++){
                            Lieu l = lesLieux.get(i);
                            if(l.getId() == (vente.getUnLieu().getId())){
                                out.println("<option selected value='" + l.getId()+"'>" + l.getVille()+"</option>" );
                            }else{
                                out.println("<option value='" + l.getId()+"'>" + l.getVille()+"</option>" );
                            }
                        }
                    %>
                            </select>
                
                        </div>
              
              
                
                
              <%--  <label for="categVente">Categorie Vente : </label>
                <select name="categVente" size="5" multiple>
                <%
                        ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        for (int i=0; i<lesCategVente.size();i++){
                            CategVente cv = lesCategVente.get(i);
                            out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>"); 
                           
                        }
                    %>
                </select></br>
                
                
                
                 Cases à cocher permettant de choisir les différentes catégories de vente auxquelles le client souhaite s'inscrire --%>
                        <div>
                            <label for="categVente"><strong>CATEGORIE DE VENTE : </strong></label></br>
              
              <%
                       ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        for (int i=0; i<lesCategVente.size();i++){
                            CategVente cv = lesCategVente.get(i);
                            if (cv.getCode().equals(vente.getUneCategVente().getCode())){
                            out.println("<input type='radio' id='cb" + i + "' name='categ' value='" + cv.getCode() + "' checked='checked'>"); 
                            out.println("<label for='cb" + i + "'>" + cv.getLibelle()); 
                            out.println("</label></br>");
                            }else{
                            out.println("<input type='radio' id='cb" + i + "' name='categ' value='" + cv.getCode() + "'>"); 
                            out.println("<label for='cb" + i + "'>" + cv.getLibelle()); 
                            out.println("</label></br>");
                             
                            }
                        }
                    %>
                        </div> </br>
                    
                 
                
                 <input type="submit" name="valider" id="valider" value="Valider"/>
                </div>
            </div>
        </div>
            </form>
        
    </body>
      <%
            }else{
                out.println("<h4>Vous n'avez pas les droits pour accéder à cette page. Contactez l'administrateur pour plus d'informations.");
            }
        }else{
           out.println("<h4>Vous n'êtes pas autorisé à aller sur cette page. Cliquez <a href='../../../../EquidaWebPeaky/ServletConnexion/connexion'>içi</a> pour vous connecter !");
        }
    %>
    <%@ include file="/vues/inc/footer.jsp" %>
</html>
