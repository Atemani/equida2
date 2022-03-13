<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Thomas
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page import="formulaires.ClientForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Modifier un client</title>
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
  if(connecte){
      if(role.equals("ADMIN") || role.equals("EMPLOYE")){
          
%>
         <%
                Client client=(Client)request.getAttribute("pLeClient");
                ClientForm form = (ClientForm)request.getAttribute("form");
         %>
        <h2>MODIFIER LE CLIENT <% out.println(client.getPrenom()+" "+client.getNom()); %> :</h2><br>
        
        <form style="width: 800px;margin-left: 10px" action="modifierClient" method="POST">
        
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="exampleInputEmail1">Nom :</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrez un nom" name="nom" value="<% out.println(client.getNom()); %>">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Prénom :</label><span class="erreur-formulaire"> ${pTabErreurs['prenom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un prénom" name="prenom" value="<% out.println(client.getPrenom()); %>">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Rue :</label><span class="erreur-formulaire"> ${pTabErreurs['rue']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez une rue" name="rue" value="<% out.println(client.getRue()); %>">
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Code postal :</label><span class="erreur-formulaire"> ${pTabErreurs['cp']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez un code postal" name="copos" value="<% out.println(client.getCopos()); %>">
              </div>
              <button type="submit" class="btn btn-primary">Modifier ce client</button>
            </div>
            <div class="col">
            <div class="form-group">
                <label for="exampleInputEmail1">Adresse mail</label><span class="erreur-formulaire"> ${pTabErreurs['mail']}</span>
                <input required type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" value="<% if(client.getMail()!=null)out.println(client.getMail());else out.println("Aucune adresse mail saisie"); %>">
                <small id="emailHelp" class="form-text text-muted">Nous ne partagerons jamais votre adresse mail.</small>
            </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Ville :</label><span class="erreur-formulaire"> ${pTabErreurs['ville']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" placeholder="Entrez une ville" name="ville" value="<% out.println(client.getVille()); %>">
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect1">Choisissez un pays :</label><span class="erreur-formulaire"> ${pTabErreurs['pays']}</span>
                <select required class="form-control" name="pays" id="exampleFormControlSelect1">
                    <%
                        ArrayList<Pays> lesPays = (ArrayList)request.getAttribute("pLesPays");
                        for (int i=0; i<lesPays.size();i++){
                            Pays p = lesPays.get(i);
                            if(p.getCode().equals(client.getUnPays().getCode())){
                                out.println("<option selected value='" + p.getCode()+"'>" + p.getNom()+"</option>" );
                            }else{
                                out.println("<option value='" + p.getCode()+"'>" + p.getNom()+"</option>" );
                            }
                        }
                    %>
                </select>
                
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect2">Choisissez parmis les catégories :</label><span class="erreur-formulaire"> ${pTabErreurs['categ']}</span>
                <select required multiple size="4" class="form-control" name="categVente" id="exampleFormControlSelect2">
                <%
                        ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        
                        ArrayList<CategVente> categValide = new ArrayList<CategVente>();
                        
                        if(client.getLesCategVentes() != null){
                            ArrayList<CategVente> clientCategVente = client.getLesCategVentes();
                            
                            if(clientCategVente.size() != lesCategVente.size()){
                                while(clientCategVente.size() != lesCategVente.size()){
                                   CategVente uneCategVente = new CategVente();
                                   client.addUneCategVente(uneCategVente);
                               }                           
                            }
                            for (int i=0; i<lesCategVente.size();i++){
                                CategVente cv = lesCategVente.get(i);
                                //System.out.println("CATEGVENTE CODE : "+cv.getCode()+" ET CLIENTCATEGVENTE CODE : "+clientCateg.getCode());
                                for(int j=0; j<clientCategVente.size(); j++){
                                    CategVente clientCateg = clientCategVente.get(j);
                                    if(cv.getCode().equals(clientCateg.getCode())){     
                                      out.println("<option SELECTED value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");   
                                      categValide.add(cv);
                                    }                             
                                }
                                out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");
                            }                            
                        }else{
                            for (int i=0; i<lesCategVente.size();i++){
                                CategVente cv = lesCategVente.get(i);
                                out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");
                            }      
                        }             
                    %>
                </select>
              </div>
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

        
                
                
                
                <%-- Cases à cocher permettant de choisir les différentes catégories de vente auxquelles le client souhaite s'inscrire 
                <label for="categVente">Categories de vente : </label></br>
                 <%
                        ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        for (int i=0; i<lesCategVente.size();i++){
                            CategVente cv = lesCategVente.get(i);
                            out.println("<input type='checkbox' id='cb" + i + "' name='" + cv.getCode() + "'>"); 
                            out.println(cv.getLibelle()); 
                            out.println("</label></br>");
                        }
                    %>
                    </br>
                    --%>
