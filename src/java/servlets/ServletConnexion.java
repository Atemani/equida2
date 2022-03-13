/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import database.ClientDAO;
import database.CompteDAO;
import formulaires.ConnexionForm;
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
import modele.Client;
import modele.Compte;

/**
 *
 * @author bastu
 */
@WebServlet(name = "ServletConnexion", urlPatterns = {"/ServletConnexion"})
public class ServletConnexion extends HttpServlet {
    
    Connection connection ;
      
        
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }
    
    //public static boolean EtatConnexion = false;
    //public static String RoleUser;
    //public static Client leClient;
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

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
            out.println("<title>Servlet ServletConnexion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletConnexion at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response); A QUOI SERT CE TRUC ? Faudrait demander au client !
        
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        session.setAttribute("leClient", null);
        
        if(session.getAttribute("EtatConnexion") == null){
            session.setAttribute("EtatConnexion", false);           
        }
        
        session.setAttribute("RoleUser", null);
        
        if(url.equals("/EquidaWebPeaky/ServletConnexion/connexion"))
        {
            if((session.getAttribute("EtatConnexion")).equals(true)){
                session.setAttribute("EtatConnexion", false);
                this.getServletContext().getRequestDispatcher("/vues/connexion.jsp" ).forward( request, response );  
            }else{
                this.getServletContext().getRequestDispatcher("/vues/connexion.jsp" ).forward( request, response );
            }

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
        
        ConnexionForm form = new ConnexionForm();
        
        ArrayList<String> listeChamps = form.verifierChamps(request);
        request.setAttribute( "form", form );
        if(form.getErreurs().isEmpty()){
            /* Transmission de la paire d'objets request/response à notre JSP */
            Compte unCompte = CompteDAO.verifLoginCompte(connection, listeChamps.get(0), listeChamps.get(1));
            
            if(unCompte.isConnexion()){
                session.setAttribute("leClient", ClientDAO.getUnClientParCompte(connection, unCompte.getId()));
                session.setAttribute("EtatConnexion", true);
                session.setAttribute("RoleUser", unCompte.getRole().getLibelle());

                System.out.println("ROLE USER :"+session.getAttribute("RoleUser"));
                response.sendRedirect("/EquidaWebPeaky");
                //this.getServletContext().getRequestDispatcher( "/vues/Acceuil.jsp" ).forward( request, response );           
            }else{
                request.setAttribute("pVerifLogin", "PAS OK");
                session.setAttribute("RoleUser", null);
                getServletContext().getRequestDispatcher("/vues/connexion.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("pTabErreurs", form.erreurs);
            getServletContext().getRequestDispatcher("/vues/connexion.jsp").forward(request, response);  
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet permettant la gestion de la connexion au site.";
    }// </editor-fold>
   
}
