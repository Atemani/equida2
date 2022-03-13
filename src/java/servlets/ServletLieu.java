/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.LieuDAO;
import database.TypeChevalDAO;
import formulaires.LieuForm;
import formulaires.TypeChevalForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Lieu;
import modele.TypeCheval;

/**
 *
 * @author bastu
 */
public class ServletLieu extends HttpServlet {
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
            out.println("<title>Servlet ServletLieu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletLieu at " + request.getContextPath() + "</h1>");
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
        
        if(url.equals("/EquidaWebPeaky/ServletLieu/listerLesLieux"))
        {
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLeslieux", lesLieux);
            //System.out.println("LIEUX 1 "+lesLieux.get(1).getVille());
            this.getServletContext().getRequestDispatcher("/vues/lieu/listerLesLieux.jsp" ).forward( request, response );
        }
        if(url.equals("/EquidaWebPeaky/ServletLieu/ajouterLieu"))
        {
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLeslieux", lesLieux);
            this.getServletContext().getRequestDispatcher("/vues/lieu/ajouterLieu.jsp" ).forward( request, response );
        }
        if(url.equals("/EquidaWebPeaky/ServletLieu/modifierLieu"))
        {
            int codeLieu = Integer.parseInt((String)request.getParameter("codeLieu"));
            Lieu leLieu = LieuDAO.getUnLieu(connection, codeLieu);
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLeslieux", lesLieux);
            request.setAttribute("pLeLieu", leLieu);
            this.getServletContext().getRequestDispatcher("/vues/lieu/modifierLieu.jsp" ).forward( request, response );
        }
        if(url.equals("/EquidaWebPeaky/ServletLieu/supprimerLieu"))
        {
            int codeLieu = Integer.parseInt((String)request.getParameter("codeLieu"));
            Lieu leLieu = LieuDAO.getUnLieu(connection, codeLieu);            
            request.setAttribute("pLeLieu", leLieu);
            this.getServletContext().getRequestDispatcher("/vues/lieu/supprimerLieu.jsp" ).forward( request, response );
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
        String url = request.getRequestURI();

        if(url.equals("/EquidaWebPeaky/ServletLieu/ajouterLieu"))
        {
           LieuForm form = new LieuForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Lieu unLieu = form.ajouterLieu(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeLieu", unLieu );

           if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                LieuDAO.ajouterUnLieu(connection,unLieu);
                response.sendRedirect("../ServletLieu/listerLesLieux");
                //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
                int codeLieu = unLieu.getId();
                Lieu leLieu = LieuDAO.getUnLieu(connection, codeLieu);            
                request.setAttribute("pTabErreurs", form.erreurs);
                this.getServletContext().getRequestDispatcher("/vues/lieu/ajouterLieu.jsp" ).forward( request, response );
           }           
        }
        
        if(url.equals("/EquidaWebPeaky/ServletLieu/modifierLieu"))
        {
           LieuForm form = new LieuForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Lieu unLieu = form.modifierLieu(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeLieu", unLieu );

           if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                LieuDAO.modifierLieu(connection,unLieu);
                response.sendRedirect("../ServletLieu/listerLesLieux");
                //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
                ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                request.setAttribute("pLeslieux", lesLieux);
                int codeLieu = unLieu.getId();
                Lieu leLieu = LieuDAO.getUnLieu(connection, codeLieu);
                request.setAttribute("pLeLieu", leLieu);
                request.setAttribute("pTabErreurs", form.erreurs);
                this.getServletContext().getRequestDispatcher("/vues/lieu/modifierLieu.jsp" ).forward( request, response );
           }     
        }
        
        if(url.equals("/EquidaWebPeaky/ServletLieu/supprimerLieu"))
        {
           LieuForm form = new LieuForm();
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Lieu unLieu = form.supprimerUnLieu(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           request.setAttribute( "pLeLieu", unLieu );

           if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                LieuDAO.supprimerLieu(connection,unLieu);
                response.sendRedirect("../ServletLieu/listerLesLieux");
                //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
           }else{
               response.sendRedirect("../ServletLieu/supprimerLieu?codeLieu="+unLieu.getId());
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
