/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.CategVenteDAO;
import database.ClientDAO;
import database.CourrierDAO;
import database.EnchereDAO;
import database.LieuDAO;
import database.LotDAO;
import database.VenteDAO;
import formulaires.VentesForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Cheval;

import modele.Client;
import modele.Courriel;
import modele.Enchere;
import modele.Lot;
import modele.Lieu;
import modele.PieceJointe;
import modele.Vente;

/**
 *
 * @author Zakina
 * Classe Servlet permettant d'executer les fonctionnalités relatives aux ventes :
 * Fonctionnalités implémentées :
 *      lister les ventes
 *      lister les clients d'une vente passée en paramètre
 */
public class ServletVentes extends HttpServlet {
    
     Connection connection ;
      private int idVente;
        
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
        //response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletVentes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletVentes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
 
        
        String url = request.getRequestURI();
        
          System.out.println("SERVLET VENTE");
        // Récup et affichage par date décroissante de toutes les ventes   
          
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesVentes"))
        {  
            ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection);
            request.setAttribute("pLesVentes", lesVentes);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentes.jsp").forward(request, response);
        }
        


        if(url.equals("/EquidaWebPeaky/ServletVentes/ajouterVente"))
        {
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLesLieux", lesLieux);
			
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVente", lesCategVentes);
			
            getServletContext().getRequestDispatcher("/vues/ventes/ventesAjouter.jsp").forward(request, response);
        }
        
        if (url.equals ("/EquidaWebPeaky/ServletVentes/modifierVente"))
	{
            System.out.println("DANS MODIFIER UN CLIENT");
            
            idVente = Integer.parseInt((String)request.getParameter("codeVente")); 
            
            
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLesLieux", lesLieux);
            
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            Vente uneVente = VenteDAO.getUneVente(connection, idVente);
            request.setAttribute("pLaVente", uneVente);
            request.setAttribute("pLesCategVente", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/ventes/ventesModifier.jsp" ).forward( request, response );
        }
		
	if (url.equals ("/EquidaWebPeaky/ServletVentes/supprimerVente"))
	{
	System.out.println("DANS SUPPRIMER UNE VENTE");
            idVente = Integer.parseInt((String)request.getParameter("codeVente")); 
            Vente uneVente = VenteDAO.getUneVente(connection, idVente);
            request.setAttribute("pLaVente", uneVente);
            this.getServletContext().getRequestDispatcher("/vues/ventes/ventesSupprimer.jsp" ).forward( request, response );
		
	}
		
        // Récup et affichage des clients interessés par une certaine catégorie de ventes
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesClients"))
        {  
           System.out.println("DANS LISTER LES CLIENTS");
            String codeCat = (String)request.getParameter("codeCat");
           
            
            ArrayList<Client> lesClients = VenteDAO.getLesClients(connection, codeCat);
            request.setAttribute("pLesClients", lesClients);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesClients.jsp").forward(request, response);
        }
        
        //recup et affichage des categories
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesCategories"))
        {
            ArrayList<CategVente> lesCategories = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategories", lesCategories);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesCategories.jsp").forward(request, response);
        }
        
        
        if (url.equals("/EquidaWebPeaky/ServletVentes/ajouterCateg"))
        {
            getServletContext().getRequestDispatcher("/vues/ventes/categsAjouter.jsp").forward(request, response);
        }
        
        if (url.equals ("/EquidaWebPeaky/ServletVentes/modifierCateg"))
	{
            System.out.println("DANS MODIFIER UNE CATEG");
            
            String idCateg = (String)request.getParameter("codeCateg"); 
            
            CategVente uneCateg = CategVenteDAO.getUneCateg(connection, idCateg);
            request.setAttribute("pLaCateg", uneCateg);
          
            this.getServletContext().getRequestDispatcher("/vues/ventes/categsModifier.jsp" ).forward( request, response );
        }
        
        
        //recup et affichage des ventes associées a une categorie
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesVentesParCateg"))
        {  
           System.out.println("DANS LISTER LES VENTES PAR CATEGORIE");
            String codeCat = (String)request.getParameter("codeCat");
           
            
            ArrayList<Vente> lesVentes = CategVenteDAO.getVentesParCateg(connection, codeCat);
            CategVente uneCateg = CategVenteDAO.getUneCateg(connection, codeCat);
            request.setAttribute("pLesVentes", lesVentes);
            request.setAttribute("pLaCateg", uneCateg);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentesParCateg.jsp").forward(request, response);
        }
        
         //recup et affichage des mails envoyés pour une vente
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesCourrielsParVente"))
        {
           System.out.println("DANS LISTER LES MAILS PAR VENTE");
            String codeVente = (String)request.getParameter("codeVente");
            int code;
            code = Integer.parseInt(codeVente);
           
            
            ArrayList<Courriel> lesCourriels = VenteDAO.getLesCourrielsParVente(connection, code);
            request.setAttribute("pLesCourriels", lesCourriels);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesCourrielsParVente.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesPiecesJointes"))
        {
            String codeCourriel = (String)request.getParameter("codeCourriel");
            int code;
            code=Integer.parseInt(codeCourriel);
            
            ArrayList<PieceJointe> lesPiecesJointes = VenteDAO.getLesPiecesJointesParCourriel(connection, code);
            request.setAttribute("pLesPiecesJointes", lesPiecesJointes);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesPiecesJointes.jsp").forward(request, response);
        }
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesChevaux"))
        {
            String codeClient = (String)request.getParameter("codeClient");
            int code;
            code=Integer.parseInt(codeClient);
            
            ArrayList<Cheval> lesChevaux = ClientDAO.getLesChevaux(connection, code);
            Client leClient = ClientDAO.getUnClient(connection, code);
            request.setAttribute("pLesChevaux", lesChevaux);
            request.setAttribute("pLeClient", leClient);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesChevaux.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesLotsParVente"))
        {
              System.out.println("listerLesLotsParVente");
            String codeVente = (String)request.getParameter("codeVente");
            int code;
            code=Integer.parseInt(codeVente);
            
            
            ArrayList<Lot> lesLots = VenteDAO.getLesLotsParVente(connection, code);

            Vente unevente = VenteDAO.getUneVente(connection, code);
            request.setAttribute("pLesLots", lesLots);
            request.setAttribute("pUneVente", unevente);

            getServletContext().getRequestDispatcher("/vues/ventes/listerLesLotsParVente.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesEncheresParLot"))
        {
            System.out.println("listerLesEncheresParLot");
            String codeLot = (String)request.getParameter("codeLot");
            int code;
            code=Integer.parseInt(codeLot);
            
            
            ArrayList<Enchere> lesEncheres = EnchereDAO.getLesEncheresParLot(connection, code);
            Lot unLot = VenteDAO.getUnLot(connection, code);
            request.setAttribute("pLesEncheres", lesEncheres);
            request.setAttribute("pUnLot", unLot);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesEncheresParLot.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletVentes/infosEnchere"))
        {
            System.out.println("lister les details d'une enchère");
            int codeLot = Integer.parseInt((String)request.getParameter("codeLot"));
            ArrayList<Enchere> lesEncheres = EnchereDAO.getLesEncheresParLot(connection, codeLot);
            Lot unLot = LotDAO.getUnLot(connection, codeLot);
            request.setAttribute("pLesEncheres", lesEncheres);
            request.setAttribute("pLeLot", unLot);
            getServletContext().getRequestDispatcher("/vues/ventes/infosEnchere.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletVentes/listerLesDetails"))
        {
            System.out.println("lister les details d'un courrier");
            String codeCourriel = (String)request.getParameter("codeCourriel");
            int code;
            code=Integer.parseInt(codeCourriel);
            
            
            ArrayList<PieceJointe> lesPiecesJointes = CourrierDAO.getLesPiecesJointesParCourriel(connection, code);
            Courriel unCourriel = CourrierDAO.getDetailsParCourriel(connection, code);
            request.setAttribute("pLesPiecesJointes", lesPiecesJointes);
            request.setAttribute("pLeCourriel", unCourriel);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesDetails.jsp").forward(request, response);
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
		
        if(url.equals("/EquidaWebPeaky/ServletVentes/ajouterVente")){ 
            VentesForm form = new VentesForm();

            Vente uneVente = form.ajouterVente(request);


            request.setAttribute("form", form);
            request.setAttribute("pLaVente", uneVente);

            if(form.getErreurs().isEmpty()){
                VenteDAO.ajouterVente(connection, uneVente);
                response.sendRedirect("../ServletVentes/listerLesVentes");
            }else{
                ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                request.setAttribute("pLesLieux", lesLieux);
                ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                request.setAttribute("pLesCategVente", lesCategVentes);
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/ventes/ventesAjouter.jsp").forward(request, response);
            }
        }
                
        if (url.equals("/EquidaWebPeaky/ServletVentes/ajouterCateg")){
            VentesForm form = new VentesForm();
                    
            CategVente uneCateg = form.ajouterCateg(request);
            System.out.println("CODE CATEG FORM "+uneCateg.getCode());
            request.setAttribute("form", form);
            request.setAttribute("pLaCateg", uneCateg);

            if(form.getErreurs().isEmpty()){
                CategVenteDAO.ajouterCateg(connection, uneCateg);
                response.sendRedirect("../ServletVentes/listerLesCategories");
            }else{
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/ventes/categsAjouter.jsp").forward(request, response);
            }
        }
		 
        if(url.equals("/EquidaWebPeaky/ServletVentes/modifierVente"))
        {
            VentesForm form = new VentesForm();
            Vente uneVente = form.modifierVente(request);   
            if (form.getErreurs().isEmpty()){
                System.out.println("COUCOU ON MODIFIE UN CLIENT");
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client
                System.out.println("VENTE ID "+uneVente.getId());
                uneVente = VenteDAO.modifierVente(connection, uneVente);
                response.sendRedirect("/EquidaWebPeaky/ServletVentes/listerLesVentes");
            }else{
                ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                request.setAttribute("pLesLieux", lesLieux);
                ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                request.setAttribute("pLesCategVente", lesCategVentes);
                Vente laVente = VenteDAO.getUneVente(connection, uneVente.getId());
                request.setAttribute("pLaVente", laVente);
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/ventes/ventesModifier.jsp").forward(request, response);
            }
		 
        }
		 
        if(url.equals("/EquidaWebPeaky/ServletVentes/modifierCateg"))
        {
            VentesForm form = new VentesForm();
            CategVente uneCateg = form.modifierCateg(request);   
            if (form.getErreurs().isEmpty()){
                System.out.println("COUCOU ON MODIFIE UNE CATEG");
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client
                uneCateg = CategVenteDAO.modifierCateg(connection, uneCateg);
                response.sendRedirect("/EquidaWebPeaky/ServletVentes/listerLesCategories");
            }else{
                CategVente laCateg = CategVenteDAO.getUneCateg(connection, uneCateg.getCode());
                request.setAttribute("pLaCateg", laCateg);
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/ventes/categsModifier.jsp").forward(request, response);
            }	 
        }

	if(url.equals("/EquidaWebPeaky/ServletVentes/supprimerVente")){
            System.out.println("COUCOU ON SUPPRIME UNE VENTE");
            VentesForm form = new VentesForm();
            Vente uneVente = form.supprimerVente(request, idVente);   
            if (form.getErreurs().isEmpty()){
                System.out.println("Formulaire suppression OK");
                String optionSuppression = request.getParameter("typeSuppression");
                if("archiver".equals(optionSuppression)){
                    VenteDAO.archiverVente(connection, uneVente);
                    response.sendRedirect("/EquidaWebPeaky/ServletVentes/listerLesVentes");                    
                }else{
                    VenteDAO.supprimerVente(connection, uneVente);  
                    response.sendRedirect("/EquidaWebPeaky/ServletVentes/listerLesVentes");
                }
            }else{
                response.sendRedirect("../ServletVentes/supprimerVente?codeVente="+uneVente.getId());
            }
        }
    }
}
