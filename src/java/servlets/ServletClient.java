/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.CategVenteDAO;
import database.ChevalDAO;
import database.ClientDAO;
import database.EnchereDAO;
import database.PaysDAO;
import database.Utilitaire;
import formulaires.ClientForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.Enchere;
import modele.Participer;
import modele.Pays;

/**
 *
 * @author Zakina
 * Servlet Client permettant d'excéuter les fonctionnalités relatives au clients
 * Fonctionnalités implémentées :
 *      ajouter un nouveau client
 */
public class ServletClient extends HttpServlet {
    
    Connection connection ;
    private int idClient;
      
        
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
            out.println("<title>Servlet ServletClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletClient at " + request.getContextPath() + "</h1>");
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
       
       HttpSession session = request.getSession();
       Client clientConnecte = (Client) session.getAttribute("leClient"); 
       String url = request.getRequestURI();
       
       if(url.equals("/EquidaWebPeaky/ServletClient/ajouterClient"))
        {          
            System.out.println("DANS AJOUTER UN CLIENT");
            ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
            request.setAttribute("pLesPays", lesPays);
            
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVente", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/client/clientAjouter.jsp" ).forward( request, response );
        }
       if(url.equals("/EquidaWebPeaky/ServletClient/modifierClient"))
        {          
            System.out.println("DANS MODIFIER UN CLIENT");
            
            idClient = Integer.parseInt((String)request.getParameter("codeClient")); 
            
            
            ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
            request.setAttribute("pLesPays", lesPays);
            
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            Client leClient = ClientDAO.getUnClient(connection, idClient);
            request.setAttribute("pLeClient", leClient);
            request.setAttribute("pLesCategVente", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/client/modifierClient.jsp" ).forward( request, response );
        }
       if(url.equals("/EquidaWebPeaky/ServletClient/supprimerClient"))
        { 
            
            System.out.println("DANS SUPPRIMER UN CLIENT");
            idClient = Integer.parseInt((String)request.getParameter("codeClient"));
            Client leClient = ClientDAO.getUnClient(connection, idClient);
            request.setAttribute("pLeClient", leClient);
            this.getServletContext().getRequestDispatcher("/vues/client/supprimerClient.jsp" ).forward( request, response );
            
        }
        if(url.equals("/EquidaWebPeaky/ServletClient/infosClient"))
        {      
            System.out.println("DANS LISTER INFOS D'UN CLIENT");
            ArrayList<Cheval> lesChevaux = ClientDAO.getLesChevaux(connection, clientConnecte.getId());
            ArrayList<Enchere> lesEncheres = EnchereDAO.getLesEncheresParClient(connection, clientConnecte.getId());
            request.setAttribute("pLesChevaux", lesChevaux);
            request.setAttribute("pLesEncheres", lesEncheres);
            this.getServletContext().getRequestDispatcher("/vues/client/infosClient.jsp" ).forward( request, response );
        }
        
        if(url.equals("/EquidaWebPeaky/ServletClient/infosCheval"))
        {                  
            System.out.println("DANS LISTER INFOS D'UN CHEVAL");
            String codeCheval = (String)request.getParameter("codeCheval"); 
            int code;
            code = Integer.parseInt(codeCheval);
            
            Cheval unCheval = ChevalDAO.getUnCheval(connection, code);
            ArrayList<Participer> listeParticipations = ChevalDAO.getLesCoursesParCheval(connection, code);
            request.setAttribute("pLesParticipations", listeParticipations);
            request.setAttribute("pLeCheval", unCheval);
            this.getServletContext().getRequestDispatcher("/vues/cheval/infosCheval.jsp" ).forward( request, response );
        }
        if(url.equals("/EquidaWebPeaky/ServletClient/listerLesClients"))
        {      
            System.out.println("DANS LISTER INFOS D'UN CLIENT(ADMIN)");
            ArrayList<Client> lesClients = ClientDAO.getLesClients(connection);
            request.setAttribute("pLesClients", lesClients);
            this.getServletContext().getRequestDispatcher("/vues/client/listerLesClients.jsp" ).forward( request, response );
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
               
        String url = request.getRequestURI();
         /* Préparation de l'objet formulaire */
        ClientForm form = new ClientForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */

        
        request.setAttribute( "form", form );
        //request.setAttribute( "pClient", unClient );
	
        if(url.equals("/EquidaWebPeaky/ServletClient/ajouterClient")){
            Client unClient = form.ajouterClient(request);
            if (form.getErreurs().isEmpty()){
                String mdp = request.getParameter("mdp");
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                ClientDAO.ajouterClient(connection, unClient, mdp);
                response.sendRedirect("../../EquidaWebPeaky/ServletConnexion/connexion");
            }
            else
            {
               ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
               request.setAttribute("pLesPays", lesPays);
               ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
               request.setAttribute("pLesCategVente", lesCategVentes);
               request.setAttribute("pTabErreurs", form.getErreurs());
               this.getServletContext().getRequestDispatcher("/vues/client/clientAjouter.jsp" ).forward( request, response );
            }            
        }

        
        if(url.equals("/EquidaWebPeaky/ServletClient/modifierClient")){
            Client unClient = form.modifierClient(request, idClient);   
            if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               ClientDAO.modifierClient(connection, unClient);
               response.sendRedirect("/EquidaWebPeaky/ServletClient/listerLesClients");
            }else{
               Client leClient = ClientDAO.getUnClient(connection, unClient.getId());
               request.setAttribute("pLeClient", leClient);
               ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
               request.setAttribute("pLesPays", lesPays);
               ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
               request.setAttribute("pLesCategVente", lesCategVentes);
               request.setAttribute("pTabErreurs", form.getErreurs());
               this.getServletContext().getRequestDispatcher("/vues/client/modifierClient.jsp" ).forward( request, response );
            }
        }
        
        if(url.equals("/EquidaWebPeaky/ServletClient/supprimerClient")){
            Client unClient = form.supprimerClient(request, idClient);   
            if (form.getErreurs().isEmpty()){
                System.out.println("COUCOU ON SUPPRIME UN CLIENT");
                String optionSuppression = request.getParameter("typeSuppression");
                if("archiver".equals(optionSuppression)){
                    ClientDAO.archiverClient(connection, unClient);                    
                }else{
                    ClientDAO.supprimerClient(connection, unClient);  
                }
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               //ClientDAO.modifierClient(connection, unClient);
               response.sendRedirect("/EquidaWebPeaky/ServletClient/listerLesClients");
            }else{
                response.sendRedirect("../ServletClient/supprimerClient?codeClient="+idClient);
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
 public void destroy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try
        {
            //fermeture
            System.out.println("Connexion fermée");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("Erreur lors de l’établissement de la connexion");
        }
        finally
        {
            //Utilitaire.fermerConnexion(rs);
            //Utilitaire.fermerConnexion(requete);
            Utilitaire.fermerConnexion(connection);
        }
    }
}
