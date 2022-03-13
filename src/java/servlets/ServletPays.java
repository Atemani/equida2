/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.PaysDAO;
import formulaires.PaysForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Pays;

/**
 *
 * @author slam
 */
public class ServletPays extends HttpServlet {
    Connection connection ;
    private String codePays;
      
	/**
	 *
	 */
	@Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ServletPays</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ServletPays at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	String url = request.getRequestURI();
        
          System.out.println("SERVLET PAYS");
          
           if(url.equals("/EquidaWebPeaky/ServletPays/listerLesPays"))
        {  
             System.out.println("listerLesPays");
            
             ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
             request.setAttribute("pLesPays", lesPays);
             getServletContext().getRequestDispatcher("/vues/pays/listerLesPays.jsp").forward(request, response);
        }
            if(url.equals("/EquidaWebPeaky/ServletPays/paysAjouter"))
        {  
            
            ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
            request.setAttribute("pLesPays", lesPays);

			
            getServletContext().getRequestDispatcher("/vues/pays/paysAjouter.jsp").forward(request, response);
        }
           
           if(url.equals("/EquidaWebPeaky/ServletPays/modifierPays"))
        {  
             System.out.println("modifierPays");
                
               String codePays = (String)request.getParameter("CodePays"); 
               System.out.println("COUCOU PAYS "+codePays);
            
             Pays unPays = PaysDAO.getUnPays(connection, codePays);
             System.out.println("PAYS" + unPays.getNom());
             
             request.setAttribute("pPays", unPays);
             getServletContext().getRequestDispatcher("/vues/pays/modifierPays.jsp").forward(request, response);
        }
            if(url.equals("/EquidaWebPeaky/ServletPays/supprimerPays"))
        { 
            
            System.out.println("DANS SUPPRIMER UN Pays");
            codePays = request.getParameter("CodePays");
            
            Pays lePays = PaysDAO.getUnPays(connection, codePays);
            request.setAttribute("pPays", lePays);
            this.getServletContext().getRequestDispatcher("/vues/pays/supprimerPays.jsp" ).forward( request, response );
            
        }
        }
        
        
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             String url = request.getRequestURI();
         /* Préparation de l'objet formulaire */
        
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        
        if(url.equals("/EquidaWebPeaky/ServletPays/paysAjouter")){ 
            PaysForm form = new PaysForm();

            Pays unPays = form.ajouterPays(request);


            request.setAttribute("form", form);
            request.setAttribute("pPays", unPays);

            if(form.getErreurs().isEmpty()){
                PaysDAO.ajouterPays(connection, unPays);
                response.sendRedirect("../ServletPays/listerLesPays");
            }
            else{
                request.setAttribute("pTabErreurs", form.erreurs);
                getServletContext().getRequestDispatcher("/vues/pays/paysAjouter.jsp").forward(request, response);
                response.sendRedirect("../ServletPays/paysAjouter");
            }
        }   
         if(url.equals("/EquidaWebPeaky/ServletPays/modifierPays")){
            PaysForm form = new PaysForm();        
            Pays unPays = form.modifierPays(request);
            
            if (form.getErreurs().isEmpty()){
                System.out.println("COUCOU ON MODIFIE UN PAYS");
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               unPays = PaysDAO.modifierPays(connection, unPays);
               response.sendRedirect("/EquidaWebPeaky/ServletPays/listerLesPays");
            }else{
               String codePays = unPays.getCode();
               Pays lePays = PaysDAO.getUnPays(connection, codePays);
               request.setAttribute("pPays", lePays);
               request.setAttribute("pTabErreurs", form.erreurs);
               getServletContext().getRequestDispatcher("/vues/pays/modifierPays.jsp").forward(request, response);
           }   
        }
         if(url.equals("/EquidaWebPeaky/ServletPays/supprimerPays")){
            PaysForm form = new PaysForm(); 
            Pays unPays = form.supprimerPays(request, codePays);
            
            if (form.getErreurs().isEmpty()){
                System.out.println("COUCOU ON SUPPRIME UN pays");
                String optionSuppression = request.getParameter("typeSuppression");
                if("archiver".equals(optionSuppression)){
                    PaysDAO.archiverPays(connection, unPays);                    
                }else{
                    PaysDAO.supprimerPays(connection, unPays);  
                }
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               //ClientDAO.modifierClient(connection, unClient);
               response.sendRedirect("/EquidaWebPeaky/ServletPays/listerLesPays");
            }else{
               response.sendRedirect("../ServletPays/supprimerPays?CodePays="+unPays.getCode());
           } 
        }
	}

        @Override
	public String getServletInfo() {
		return "Short description";
	}
}
