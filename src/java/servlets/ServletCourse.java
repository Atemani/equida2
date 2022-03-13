/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import database.CourseDAO;
import database.LieuDAO;
import database.VenteDAO;
import formulaires.CoursesForm;
import modele.Course;
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
import modele.Participer;
import modele.Vente;

/**
 *
 * @author slam
 */
public class ServletCourse extends HttpServlet {

	
	
	
	
	Connection connection ;
      
	/**
	 *
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
			out.println("<title>Servlet ServletCourse</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ServletCourse at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	
	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	String url = request.getRequestURI();
        
          System.out.println("SERVLET COURSE");
	
	if(url.equals("/EquidaWebPeaky/ServletCourse/ajouterCourse"))
        {  
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLeslieux", lesLieux);
            getServletContext().getRequestDispatcher("/vues/course/ajouterCourse.jsp").forward(request, response);
        }
	
        if(url.equals("/EquidaWebPeaky/ServletCourse/listerLesCourses"))
        {  
             System.out.println("listerLesCourses");
            
             ArrayList<Course> lesCourses = CourseDAO.getLesCourses(connection);
             request.setAttribute("pLesCourses", lesCourses);
             getServletContext().getRequestDispatcher("/vues/course/listerLesCourses.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCourse/modifierCourse"))
        {  
             System.out.println("modifier course");
             ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
             request.setAttribute("pLeslieux", lesLieux);
             int idCourse = Integer.parseInt((String)request.getParameter("codeCourse"));
             Course laCourse = CourseDAO.getUneCourse(connection, idCourse);
             request.setAttribute("pLaCourse", laCourse);
             getServletContext().getRequestDispatcher("/vues/course/modifierCourse.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCourse/supprimerCourse"))
        {  
             System.out.println("supprimer course");
             int idCourse = Integer.parseInt((String)request.getParameter("codeCourse"));
             Course laCourse = CourseDAO.getUneCourse(connection, idCourse);
             request.setAttribute("pLaCourse", laCourse);
             getServletContext().getRequestDispatcher("/vues/course/supprimerCourse.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWebPeaky/ServletCourse/listerLesParticipationsparCourses"))
        {  
             System.out.println("listerLesParticipationsparCourses");
             int idCourse = Integer.parseInt((String)request.getParameter("codeCourse"));
            
             ArrayList<Participer> lesParticipation = CourseDAO.getLesParticipations(connection, idCourse);
             Course uneCourse = CourseDAO.getUneCourse(connection, idCourse);
             request.setAttribute("pLesParticipations", lesParticipation);
             request.setAttribute("pUneCourse", uneCourse);
             getServletContext().getRequestDispatcher("/vues/course/listerLesParticipationsparCourses.jsp").forward(request, response);}
	
	
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
            
            if(url.equals("/EquidaWebPeaky/ServletCourse/ajouterCourse"))
            {
                CoursesForm form = new CoursesForm();
                /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
                Course uneCourse = form.ajouterCourse(request);

               /* Stockage du formulaire et de l'objet dans l'objet request */
               request.setAttribute( "form", form );
               request.setAttribute( "pLaCourse", uneCourse );

               if (form.getErreurs().isEmpty()){
                    // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                    CourseDAO.ajouterUneCourse(connection,uneCourse);
                    response.sendRedirect("../ServletCourse/listerLesCourses");
                    //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
               }else{
                    ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                    request.setAttribute("pLeslieux", lesLieux);
                    request.setAttribute("pTabErreurs", form.erreurs);
                    getServletContext().getRequestDispatcher("/vues/course/ajouterCourse.jsp").forward(request, response);
               }                           
           }
            
            if(url.equals("/EquidaWebPeaky/ServletCourse/modifierCourse"))
            {
                CoursesForm form = new CoursesForm();
                /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
                Course uneCourse = form.modifierCourse(request);

               /* Stockage du formulaire et de l'objet dans l'objet request */
               request.setAttribute( "form", form );
               request.setAttribute( "pLaCourse", uneCourse );

               if (form.getErreurs().isEmpty()){
                    // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                    CourseDAO.modifierUneCourse(connection,uneCourse);
                    response.sendRedirect("../ServletCourse/listerLesCourses");
                    //this.getServletContext().getRequestDispatcher("/vues/clientConsulter.jsp" ).forward( request, response );
               }else{
                    int idCourse = uneCourse.getId();
                    Course laCourse = CourseDAO.getUneCourse(connection, idCourse);
                    request.setAttribute("pLaCourse", laCourse);
                    ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                    request.setAttribute("pLeslieux", lesLieux);
                    request.setAttribute("pTabErreurs", form.erreurs);
                    getServletContext().getRequestDispatcher("/vues/course/modifierCourse.jsp").forward(request, response);
               }                           
            }
            
            if(url.equals("/EquidaWebPeaky/ServletCourse/supprimerCourse"))
            {

                CoursesForm form = new CoursesForm();
                /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
                Course uneCourse = form.supprimerCourse(request);

               /* Stockage du formulaire et de l'objet dans l'objet request */
               request.setAttribute( "form", form );
               request.setAttribute( "pLaCourse", uneCourse );

               if (form.getErreurs().isEmpty()){
                    String optionSuppression = request.getParameter("typeSuppression");
                    if(optionSuppression.equals("archiver")){
                        CourseDAO.archiverCourse(connection,uneCourse);
                        response.sendRedirect("../ServletCourse/listerLesCourses");                
                    }else{
                        CourseDAO.supprimerCourse(connection,uneCourse);
                        response.sendRedirect("../ServletCourse/listerLesCourses");                
                    }
               }else{
                   response.sendRedirect("../ServletCourse/supprimerCourse?codeCourse="+uneCourse.getId());
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
