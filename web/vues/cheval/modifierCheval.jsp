<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Thomas
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.TypeCheval"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page import="formulaires.ClientForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>  
    <title>Equida - Modifier un cheval</title>
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
%>
            <%
                //Client client=(Client)request.getAttribute("client");
                ArrayList<TypeCheval> lesTypesChevaux = (ArrayList)request.getAttribute("pLesTypesChevaux");
                ArrayList<Cheval> lesChevaux = (ArrayList)request.getAttribute("pLesChevaux");
                Cheval leCheval = (Cheval)request.getAttribute("pLeCheval");
            %>
<center><h2>Modifier le cheval <% out.println(leCheval.getNom()); %>: </h2><br>    
        <form style="width: 800px;margin-left: 10px" action="modifierCheval" method="POST">
        <input type="hidden" name="idCheval" value="<% out.println(leCheval.getId()); %>"/>
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="exampleInputEmail1">Nom</label><span class="erreur-formulaire"> ${pTabErreurs['nom']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="<% out.println(leCheval.getNom());%>" placeholder="Entrez un nom" name="nom">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Sexe (M ou F)</label><span class="erreur-formulaire"> ${pTabErreurs['sexe']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" value="<% out.println(leCheval.getSexe());%>" placeholder="Male ou Femelle" name="sexe">
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Date de naissance (AAAA-MM-JJ)</label><span class="erreur-formulaire"> ${pTabErreurs['date']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" value="<% out.println(leCheval.getDateNaissance());%>" placeholder="Entrez la date de naissance" name="dateNaissance">
              </div>
              <div class="form-group">
                <label required for="exampleInputEmail1">Race</label><span class="erreur-formulaire"> ${pTabErreurs['race']}</span>
                <select required class="form-control" name="race" id="exampleFormControlSelect1">
                    <%
                        for (int i=0; i<lesTypesChevaux.size();i++){
                            TypeCheval leTypeCheval = lesTypesChevaux.get(i);
                            if((leTypeCheval.getId()) == leCheval.getTypeCheval().getId()){
                            out.println("<option SELECTED value='" + leTypeCheval.getId()+"'>" + leTypeCheval.getLibelle()+"</option>" );    
                            }else{
                            out.println("<option value='" + leTypeCheval.getId()+"'>" + leTypeCheval.getLibelle()+"</option>" );                                
                            }

                        }
                    %>
                </select>
              </div>
              <div class="form-group">
                <label for="exampleInputEmail1">Sire</label><span class="erreur-formulaire"> ${pTabErreurs['sire']}</span>
                <input required type="text" class="form-control" id="exampleInputEmail1" value="<% out.println(leCheval.getSire());%>" placeholder="Entrez le SIRE du cheval" name="sire">
              </div>  
            </div>
            <div class="col">
              <div class="form-group">
                <label for="exampleFormControlSelect2">Choisissez le père du cheval</label><span class="erreur-formulaire"> ${pTabErreurs['pere']}</span>
                <select required class="form-control" name="chevalPere" id="exampleFormControlSelect2">
                <%
                        for (int i=0; i<lesChevaux.size();i++){
                            Cheval unCheval = lesChevaux.get(i);
                            if(unCheval.getId() == leCheval.getPere().getId()){
                                out.println("<option SELECTED value ='" + unCheval.getId() + "'>" + unCheval.getNom() + "</option>");                                
                            }else{
                                out.println("<option value ='" + unCheval.getId() + "'>" + unCheval.getNom() + "</option>");    
                            }
                        }
                    %>
                </select>
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect2">Choisissez la mère du cheval</label><span class="erreur-formulaire"> ${pTabErreurs['mere']}</span>
                <select required class="form-control" name="chevalMere" id="exampleFormControlSelect2">
                <%
                        for (int i=0; i<lesChevaux.size();i++){
                            Cheval unCheval = lesChevaux.get(i);
                            if(unCheval.getId() == leCheval.getMere().getId()){
                                out.println("<option SELECTED value ='" + unCheval.getId() + "'>" + unCheval.getNom() + "</option>");                                
                            }else{
                                out.println("<option value ='" + unCheval.getId() + "'>" + unCheval.getNom() + "</option>");    
                            }
                        }
                    %>
                </select>
              </div>
              <center><button type="submit" class="btn btn-primary">Modifier ce cheval</button></center>
            </div>
          </div>
        </div>
       </form>
</body></center>
    
    <%
    }else{
        out.println("<h4>Vous n'êtes pas connecté, cliquez <a href='../../EquidaWebPeaky/ServletConnexion/connexion'>içi</a> pour vous connecter !");
    }
    %>
<%@ include file="/vues/inc/footer.jsp" %>
</html>
