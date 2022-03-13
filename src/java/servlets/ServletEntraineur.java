/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.EntraineurDAO;
import formulaires.EntraineurForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Cheval;
import modele.Entraineur;

/**
 *
 * @author theo
 */
public class ServletEntraineur extends HttpServlet {

    Connection connection ;
      
    /**
    /*
    */
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
            out.println("<title>Servlet ServletEntraineur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletEntraineur at " + request.getContextPath() + "</h1>");
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
        
        if(url.equals("/EquidaWebPeaky/ServletEntraineur/ajouterEntraineur")){
            this.getServletContext().getRequestDispatcher("/vues/entraineur/ajouterEntraineur.jsp" ).forward( request, response ); 
        }
        
        if(url.equals("/EquidaWebPeaky/ServletEntraineur/consulterEntraineur")){
            this.getServletContext().getRequestDispatcher("/vues/entraineur/consulterEntraineur.jsp" ).forward( request, response ); 
        }
  
        if(url.equals("/EquidaWebPeaky/ServletEntraineur/listeEntraineurs")){
            ArrayList<Entraineur> lesEntraineurs = EntraineurDAO.getLesEntraineurs(connection);
            request.setAttribute("pLesEntraineurs", lesEntraineurs);
            this.getServletContext().getRequestDispatcher("/vues/entraineur/listeEntraineurs.jsp" ).forward( request, response ); 
        }
        
        if(url.equals("/EquidaWebPeaky/ServletEntraineur/listeChevauxEntraines")){
            int idEntraineur = Integer.parseInt((String)request.getParameter("codeEntraineur"));
            ArrayList<Cheval> lesChevaux = EntraineurDAO.getLesChevauxParEntraineur(connection, idEntraineur);
            Entraineur unEntraineur = EntraineurDAO.getUnEntraineur(connection, idEntraineur);
            request.setAttribute("pLesChevaux", lesChevaux);
            request.setAttribute("pEntraineur", unEntraineur);
            this.getServletContext().getRequestDispatcher("/vues/entraineur/listeChevauxEntraines.jsp" ).forward( request, response ); 
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
        
        if(url.equals("/EquidaWebPeaky/ServletEntraineur/ajouterEntraineur")){
            EntraineurForm form = new EntraineurForm();        
            Entraineur unEntraineur = form.ajouterEntraineur(request);
            
            if(form.getErreurs().isEmpty()){
                unEntraineur = EntraineurDAO.ajouterEntraineur(connection, unEntraineur);
                request.setAttribute("pEntraineur", unEntraineur);
                this.getServletContext().getRequestDispatcher("/vues/entraineur/consulterEntraineur.jsp" ).forward( request, response );                
            }else{
                response.sendRedirect("../../EquidaWebPeaky/ServletEntraineur/ajouterEntraineur");
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
