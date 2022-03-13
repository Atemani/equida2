<%-- 
    Document   : connexion.jsp
    Created on : 24 sept. 2019, 10:43:51
    Author     : bastu
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Map"%>
<%@page import="formulaires.ConnexionForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/png" href="../img/horse.png" />
        <title>Page de connexion</title>
         <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="../css/connexion.css">
    </head>
    <body>
        <div class="container">
            
            <div class="card">
                <center>
                    <img src="../img/horse.png"/>
                    <div class="card-header">
                        <h3>Connectez vous pour continuer</h3><p><a href="../index.jsp">Retourner à la page d'acceuil</a></p>
                        <%--
                        //Client client=(Client)request.getAttribute("client");
                        --%>
                    </div>
                    
                    <div class="card-body">
                        <form action="connexion" method="POST">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    <span class="erreur-formulaire">${pTabErreurs['login']}</span>
                                </div>
                                <input type="text" class="form-control" name="login" placeholder="Nom d'utilisateur">
                            </div>
                            
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    <span class="erreur-formulaire">${erreurs['mdp']}</span>
                                </div>
                                <input type="password" class="form-control" name="mdp" placeholder="Mot de passe">
                                <p class="mdpOublie">Mot de passe oublié ?</p><br>
                            </div>

                            <div class="form-group">
                                <input type="submit" value="Se Connecter" class="btn right login_btn"/><br>
                                <a href='../../EquidaWebPeaky/ServletClient/ajouterClient'>Vous n'avez pas de compte ? Inscrivez vous !</a>
                            </div>
                        </form>

                        <%--
                        String verif = (String)request.getAttribute("pVerifLogin");  
                        if(verif == null || verif == "OK"){
                            out.println("");
                        }else{
                            out.println("<p style='color:red;'>Identifiant / Mot de passe incorrect, veuillez réessayer...</p>");
                        }
                        --%>
                    </div>
                    <p class="copyright">® 2019 Copyrights - Equida</p>
                </center>
            </div>
            <div class="img-card">
                <!--IMAGE CHEVAL A COTE DU FORMULAIRE-->
            </div>
        </div>
    </body>
</html>
