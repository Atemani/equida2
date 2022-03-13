<%-- 
    Document   : header.jsp
    Created on : 30 sept. 2019, 13:49:50
    Author     : bastu
--%>

<%@page import="servlets.ServletConnexion"%>
<%@page import="modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <body>
        <%
            boolean connecte;
            Client unClient;
            String role;
            String url = request.getRequestURI();
            if((session.getAttribute("EtatConnexion")) !=null && (session.getAttribute("EtatConnexion")).equals(true)){
                connecte = (boolean)session.getAttribute("EtatConnexion");   
                unClient = (Client)session.getAttribute("leClient");
                role = (String)session.getAttribute("RoleUser"); 
            }else{
                connecte = false;
                unClient = null;
                role = null;
            }                

        %>
        <nav id="test" style="line-height: 40px;box-shadow: 5px 5px 30px;" class="navbar navbar-expand-lg navbar-dark">
            <% if(url.equals("/EquidaWebPeaky/") || url.equals("/EquidaWebPeaky/index.jsp") ){ %>
                    <img style="width:32px;height:32px; margin-right: 10px" src="img/horse.png"/>  
            <% }else{ %>
                    <img style="width:32px;height:32px; margin-right: 10px" src="../img/horse.png"/>  
            <% } %>
            
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav">
                  <%
                    if(connecte){
                        if (role.equals("ADMIN")){
                            
                  %>
                       <a id="navbarTest" class="nav-item nav-link active" href="../../EquidaWebPeaky/index.jsp">Accueil <span class="sr-only">(current)</span></a>
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Ventes
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/listerLesVentes">Liste des ventes</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/ajouterVente">Ajouter une vente</a>                              
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/listerLesCategories">Lister les catégories de vente</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/ajouterCateg">Ajouter une catégorie de vente</a>
                            </div>
                        </li>   
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Clients
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletClient/listerLesClients">Lister les clients</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletClient/ajouterClient">Ajouter un client</a>
                            </div>
                        </li>  
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Chevaux
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCheval/listerLesChevaux">Lister les chevaux</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCheval/ajouterCheval">Ajouter un cheval</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCheval/listerLesTypesChevaux">Lister les types de chevaux</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCheval/ajouterTypeCheval">Ajouter un type de cheval</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Courses
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCourse/listerLesCourses">Lister les courses</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCourse/ajouterCourse">Ajouter une course</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Entraineurs
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletEntraineur/listeEntraineurs">Lister les entraineurs</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletEntraineur/ajouterEntraineur">Ajouter un entraineur</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Pays
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletPays/listerLesPays">Lister les pays</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletPays/paysAjouter">Ajouter un pays</a>
                            </div>
                        </li> 
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Lieux
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletLieu/listerLesLieux">Lister les lieux</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletLieu/ajouterLieu">Ajouter un lieu</a>
                            </div>
                        </li>  
                        <a id="navbarTest" class="nav-item nav-link" href="../../EquidaWebPeaky/ServletClient/infosClient">Mes infos</a>
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletConnexion/connexion">Se déconnecter</a>
                        
                        <% if(url.equals("/EquidaWebPeaky/") || url.equals("/EquidaWebPeaky/index.jsp") ){ %>
                            <form style="margin-left : 10px;" class="form-inline my-2 my-lg-0" method="POST" action="ServletRecherche/resultatRecherche">
                              <input class="form-control mr-sm-2" type="search" name="nomRecherche" placeholder="Entre un nom ..." aria-label="Search">
                              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Rechercher</button>
                            </form>
                        <% }else{ %>
                            <form style="margin-left : 10px;" class="form-inline my-2 my-lg-0" method="POST" action="../ServletRecherche/resultatRecherche">
                              <input class="form-control mr-sm-2" type="search" name="nomRecherche" placeholder="Search" aria-label="Search">
                              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                            </form>
                        <% } %>
                  <%
                      }else if(role.equals("EMPLOYE")){ %>
                        <a class="nav-item nav-link active" href="../../EquidaWebPeaky/index.jsp">Accueil <span class="sr-only">(current)</span></a>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Ventes
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/listerLesVentes">Liste des ventes</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/ajouterVente">Ajouter une vente</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletVentes/listerLesCategories">Lister les catégories de vente</a>
                            </div>
                        </li> 
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Clients
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletClient/listerLesClients">Lister les clients</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletClient/ajouterClient">Ajouter un client</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Courses
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCourse/listerLesCourses">Lister les courses</a>
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCourse/ajouterCourse">Ajouter une course</a>
                            </div>
                        </li> 
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletClient/infosClient">Mes informations</a>
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletConnexion/connexion">Se déconnecter</a>

                  <%  }else{ %>
                        <a class="nav-item nav-link active" href="../../EquidaWebPeaky/index.jsp">Accueil<span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletVentes/listerLesVentes">Ventes</a>
                        <li class="nav-item dropdown">
                            <a id="navbarTest" class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Courses
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="../../EquidaWebPeaky/ServletCourse/listerLesCourses">Lister les courses</a>
                            </div>
                        </li> 
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletClient/infosClient">Mes informations</a>
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletConnexion/connexion">Se déconnecter</a>
                  <%  }
                   }else{ %>
                        <a class="nav-item nav-link active" href="../../EquidaWebPeaky/index.jsp">Accueil <span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletVentes/listerLesVentes">Ventes</a>
                        <a class="nav-item nav-link" href="../../EquidaWebPeaky/ServletConnexion/connexion">Connexion</a>
                  <%}
                  %>
               
              </div>
            </div>
        </nav>
            <% if(connecte){ %>
                <div class="container-img">
                    <div class="container-inner">
                        <h3>EQUIDA</h3>
                        <p>Bienvenue <% out.println(unClient.getPrenom()+" "+unClient.getNom());%></p>
                        <!--<a class="bouton-header" href="../../EquidaWebPeaky/ServletConnexion/connexion">Se déconnecter</a>-->
                    </div>
                </div>                      
            <% }else{ %>
                <div class="container-img">
                    <div class="container-inner">
                        <h3>EQUIDA</h3>
                        <a class="bouton-header" href="../../EquidaWebPeaky/ServletConnexion/connexion">Se connecter</a>
                    </div>
                </div>  
            <% } %>

        
