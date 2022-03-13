/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.ChevalDAO;
import database.CourseDAO;
import database.ParticiperDAO;
import database.TypeChevalDAO;
import formulaires.ChevalForm;
import formulaires.ParticipationForm;
import formulaires.TypeChevalForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Cheval;
import modele.Client;
import modele.Course;
import modele.Participer;
import modele.TypeCheval;

/**
 *
 * @author bastu
 */
@WebServlet(name = "ServletCheval", urlPatterns = {"/ServletCheval"})
public class ServletCheval extends HttpServlet {

    Connection connection ;
    
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCheval</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCheval at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String url = request.getRequestURI();
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/ajouterTypeCheval"))
        {
            this.getServletContext().getRequestDispatcher("/vues/cheval/ajouterTypeCheval.jsp" ).forward( request, response );
        }
        if(url.equals("/EquidaWebPeaky/ServletCheval/listerLesTypesChevaux"))
        {
            ArrayList<TypeCheval> listeTypeCheval = TypeChevalDAO.getLesTypesChevaux(connection);
            request.setAttribute("pLesTypesChevaux", listeTypeCheval);
            this.getServletContext().getRequestDispatcher("/vues/cheval/listerLesTypesChevaux.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/ajouterCheval"))
        {
            ArrayList<TypeCheval> listeTypeCheval = TypeChevalDAO.getLesTypesChevaux(connection);
            ArrayList<Cheval> lesChevaux = ChevalDAO.getLesChevaux(connection);
            request.setAttribute("pLesTypesChevaux", listeTypeCheval);
            request.setAttribute("pLesChevaux", lesChevaux); 
            this.getServletContext().getRequestDispatcher("/vues/cheval/ajouterCheval.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/modifierCheval"))
        {
            int codeCheval = Integer.parseInt((String)request.getParameter("codeCheval"));
            ArrayList<TypeCheval> listeTypeCheval = TypeChevalDAO.getLesTypesChevaux(connection);
            ArrayList<Cheval> lesChevaux = ChevalDAO.getLesChevaux(connection);
            Cheval leCheval = ChevalDAO.getUnCheval(connection, codeCheval);
            request.setAttribute("pLesTypesChevaux", listeTypeCheval);
            request.setAttribute("pLesChevaux", lesChevaux); 
            request.setAttribute("pLeCheval", leCheval);
            this.getServletContext().getRequestDispatcher("/vues/cheval/modifierCheval.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/supprimerCheval"))
        {
            System.out.println("DANS SUPPRIMER CHEVAL");
            int codeCheval = Integer.parseInt((String)request.getParameter("codeCheval"));
            Cheval leCheval = ChevalDAO.getUnCheval(connection, codeCheval);
            request.setAttribute("pLeCheval", leCheval);
            this.getServletContext().getRequestDispatcher("/vues/cheval/supprimerCheval.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/listerLesChevaux"))
        {
            ArrayList<Cheval> listeChevaux = ChevalDAO.getLesChevaux(connection);
            request.setAttribute("pLesChevaux", listeChevaux);            
            this.getServletContext().getRequestDispatcher("/vues/cheval/listerLesChevaux.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/listerLesChevauxProposes"))
        {
            System.out.println("DANS LISTER CHEVAUX PROPOSES");
            ArrayList<Cheval> listeChevaux = ChevalDAO.getLesChevauxProposes(connection);
            request.setAttribute("pLesChevaux", listeChevaux);            
            this.getServletContext().getRequestDispatcher("/vues/cheval/listerLesChevauxProposes.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/AccepterOuRefuserCheval"))
        {
            System.out.println("DANS ACCEPTER OU REFUSER CHEVAL");
            int codeCheval = Integer.parseInt((String)request.getParameter("codeCheval"));
            Cheval leCheval = ChevalDAO.getUnCheval(connection, codeCheval);
            request.setAttribute("pLeCheval", leCheval);
            this.getServletContext().getRequestDispatcher("/vues/cheval/AccepterOuRefuserCheval.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/ajouterUneParticipation"))
        {
            int codeCheval = Integer.parseInt((String)request.getParameter("codeCheval"));
            ArrayList<Course> listeCourses = CourseDAO.getLesCourses(connection);
            Cheval leCheval = ChevalDAO.getUnCheval(connection, codeCheval);
            request.setAttribute("pLesCourses", listeCourses);
            request.setAttribute("pLeCheval", leCheval); 
            this.getServletContext().getRequestDispatcher("/vues/cheval/ajouterUneParticipation.jsp" ).forward( request, response );
        }
        if(url.equals("/EquidaWebPeaky/ServletCheval/modifierTypesCheval"))
        {          
            System.out.println("DANS MODIFIER UN TYPECHEVAL");
            
            int idTypeCheval = Integer.parseInt((String)request.getParameter("codeTypeCheval")); 
            TypeCheval leTypeCheval = TypeChevalDAO.getUnTypeCheval(connection, idTypeCheval);
            request.setAttribute("pleTypeCheval", leTypeCheval);
            this.getServletContext().getRequestDispatcher("/vues/cheval/modifierTypesCheval.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/supprimerTypesCheval"))
        {  
             System.out.println("supprimer typecheval");
             int idTypeCheval = Integer.parseInt((String)request.getParameter("codeTypeCheval"));
             TypeCheval leTypeCheval = TypeChevalDAO.getUnTypeCheval(connection, idTypeCheval);
             request.setAttribute("pleTypeCheval", leTypeCheval);
             getServletContext().getRequestDispatcher("/vues/cheval/supprimerTypesCheval.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession();
        Client clientConnecte = (Client) session.getAttribute("leClient");
        String url = request.getRequestURI();
        
         /* Préparation de l'objet formulaire */
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/ajouterCheval"))
        {
           ChevalForm form = new ChevalForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Cheval unCheval = form.ajouterCheval(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeCheval", unCheval );

           if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                ChevalDAO.ajouterCheval(connection,unCheval,clientConnecte.getId());
                response.sendRedirect("../ServletClient/infosClient");
                //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
                System.out.println(form.erreurs);
                ArrayList<TypeCheval> listeTypeCheval = TypeChevalDAO.getLesTypesChevaux(connection);
                ArrayList<Cheval> lesChevaux = ChevalDAO.getLesChevaux(connection);
                request.setAttribute("pLesTypesChevaux", listeTypeCheval);
                request.setAttribute("pLesChevaux", lesChevaux);
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/cheval/ajouterCheval.jsp").forward(request, response);   
           }            
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/modifierCheval"))
        {
            
           ChevalForm form = new ChevalForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Cheval unCheval = form.modifierCheval(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeCheval", unCheval );

            if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                ChevalDAO.modifierCheval(connection,unCheval);
                response.sendRedirect("../ServletCheval/listerLesChevaux");
                //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
            }
            else{
                System.out.println(form.erreurs);
                Cheval leCheval = ChevalDAO.getUnCheval(connection, unCheval.getId());
                request.setAttribute("pLeCheval", leCheval);
                ArrayList<TypeCheval> listeTypeCheval = TypeChevalDAO.getLesTypesChevaux(connection);
                ArrayList<Cheval> lesChevaux = ChevalDAO.getLesChevaux(connection);
                request.setAttribute("pLesTypesChevaux", listeTypeCheval);
                request.setAttribute("pLesChevaux", lesChevaux);
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/cheval/modifierCheval.jsp").forward(request, response);   
           }            
        }

        if(url.equals("/EquidaWebPeaky/ServletCheval/supprimerCheval"))
        {
            
           ChevalForm form = new ChevalForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Cheval unCheval = form.supprimerCheval(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeCheval", unCheval );

           if (form.getErreurs().isEmpty()){
                String optionSuppression = request.getParameter("typeSuppression");
                if(optionSuppression.equals("archiver")){
                    ChevalDAO.archiverCheval(connection,unCheval);
                    response.sendRedirect("../ServletCheval/listerLesChevaux");                
                }else{
                    ChevalDAO.supprimerCheval(connection,unCheval);
                    response.sendRedirect("../ServletCheval/listerLesChevaux");                
                }
           }else{
               response.sendRedirect("../ServletCheval/supprimerCheval?codeCheval="+unCheval.getId());
           }           
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/ajouterUneParticipation"))
        {
           ParticipationForm form = new ParticipationForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Participer uneParticipation = form.ajouterUneParticipation(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLaParticipation", uneParticipation );

           if (form.getErreurs().isEmpty()){
            // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
            ParticiperDAO.ajouterUneParticipation(connection,uneParticipation);
            response.sendRedirect("../ServletClient/infosCheval?codeCheval="+uneParticipation.getUnCheval().getId());
            //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
               response.sendRedirect("../ServletCheval/ajouterUneParticipation?codeCheval="+uneParticipation.getUnCheval().getId());
           }
           
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/AccepterOuRefuserCheval"))
        {
           System.out.println("DANS POST - ACCEPTER OU REFUSER CHEVAL");
           ChevalForm form = new ChevalForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Cheval unCheval = form.gererCheval(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeCheval", unCheval );

           if (form.getErreurs().isEmpty()){
                String optionGestion = request.getParameter("typeGestion");
                if(optionGestion.equals("accepter")){
                    ChevalDAO.accepterCheval(connection,unCheval);
                    response.sendRedirect("../ServletCheval/listerLesChevauxProposes");                
                }else{
                    ChevalDAO.refuserCheval(connection,unCheval);
                    response.sendRedirect("../ServletCheval/listerLesChevauxProposes");                
                }
           }else{
               response.sendRedirect("../ServletCheval/AccepterOuRefuserCheval?codeCheval="+unCheval.getId());
           }
           
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/ajouterTypeCheval"))
        {
           TypeChevalForm form = new TypeChevalForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           TypeCheval unTypeCheval = form.ajouterTypeCheval(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeTypeCheval", unTypeCheval );

           if (form.getErreurs().isEmpty()){
            // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
            TypeChevalDAO.ajouterTypeCheval(connection,unTypeCheval);
            response.sendRedirect("../ServletCheval/listerLesTypesChevaux");
            //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/cheval/ajouterTypeCheval.jsp").forward(request, response);  
           }           
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/modifierTypesCheval")){
            TypeChevalForm form = new TypeChevalForm();
            TypeCheval unTypeCheval = form.modifierTypeCheval(request); 
           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeTypeCheval", unTypeCheval );

           if (form.getErreurs().isEmpty()){
           // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
           TypeChevalDAO.modifierTypeCheval(connection,unTypeCheval);
           response.sendRedirect("../ServletCheval/listerLesTypesChevaux");
           //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
               //ON RECUPERE LES INFOS ACTUELLES DU TYPE DE CHEVAL ET ON LES RENVOIE DANS LE FORMULAIRE
               int idTypeCheval = unTypeCheval.getId();
               TypeCheval leTypeCheval = TypeChevalDAO.getUnTypeCheval(connection, idTypeCheval);
               request.setAttribute("pleTypeCheval", leTypeCheval);
               request.setAttribute("pTabErreurs", form.erreurs);
               getServletContext().getRequestDispatcher("/vues/cheval/modifierTypesCheval.jsp").forward(request, response);
           }  
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCheval/supprimerTypesCheval"))
            {

                TypeChevalForm form = new TypeChevalForm();
                /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
                TypeCheval unTypeCheval = form.supprimerTypeCheval(request);

               /* Stockage du formulaire et de l'objet dans l'objet request */
               request.setAttribute( "form", form );
               request.setAttribute( "pLeTypeCheval", unTypeCheval );

               if (form.getErreurs().isEmpty()){
                    String optionSuppression = request.getParameter("typeSuppression");
                    if(optionSuppression.equals("supprimer")){
                        TypeChevalDAO.supprimerTypeCheval(connection,unTypeCheval);
                        response.sendRedirect("../ServletCheval/listerLesTypesChevaux");                
                    }
               }else{
                   response.sendRedirect("../ServletCheval/supprimerTypesCheval?codeTypeCheval="+unTypeCheval.getId());
               }           
            }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
