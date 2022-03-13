/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Course;
import modele.Participer;
import modele.Acheteur;
import modele.Cheval;
import modele.Course;
import modele.Enchere;
import modele.Lieu;
import modele.Lot;
import modele.TypeCheval;
/**
 *
 * @author slam
 */
public class CourseDAO {
	
	Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
	

    private static int genererId(Connection connection){
        int j=0;

        try{
            requete=connection.prepareStatement("SELECT MAX(crs_id) from course");
            rs=requete.executeQuery();

            if(rs.next()){
                j=rs.getInt("MAX(crs_id)")+1;
                System.out.println("MAX ID = "+j);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return j;
    }
    
    public static ArrayList<Course>  getLesCourses(Connection connection){
        ArrayList<Course> lesCourse = new  ArrayList<Course>();

        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM course, lieu where lie_id = crs_lieu AND crs_archive = 0 ORDER BY crs_date DESC");
            //executer la requete
            rs=requete.executeQuery();
            

            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                 
               Course uneCourse = new Course();
               uneCourse.setId(rs.getInt("crs_id"));
               uneCourse.setLibelle(rs.getString("crs_nom"));
               uneCourse.setDate(rs.getString("crs_date"));
               
               Lieu unLieu = new Lieu();
               unLieu.setId(rs.getInt("lie_id"));
               unLieu.setVille(rs.getString("lie_ville"));
               unLieu.setNbBoxes(rs.getInt("lie_nbBoxes"));
               unLieu.setCommentaires(rs.getString("lie_commentaires"));
               
               uneCourse.setLieu(unLieu);
               uneCourse.setArchive(rs.getInt("crs_archive"));
               
               lesCourse.add(uneCourse);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesCourse ; 
    }
        
    public static Course ajouterUneCourse(Connection connection, Course uneCourse){
        int idGenereCourse = genererId(connection);
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("INSERT INTO course (crs_id, crs_nom, crs_lieu, crs_date) VALUES(?,?,?,?)");
            requete.setInt(1, idGenereCourse);
            requete.setString(2, uneCourse.getLibelle());
            requete.setInt(3, uneCourse.getLieu().getId());
            requete.setString(4, uneCourse.getDate());
            requete.executeUpdate();
            
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return uneCourse; 
    }    
    
    public static Course modifierUneCourse(Connection connection, Course uneCourse){
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE course SET crs_nom = ?, crs_lieu = ?, crs_date = ? WHERE crs_id = ?");
            requete.setString(1, uneCourse.getLibelle());
            requete.setInt(2, uneCourse.getLieu().getId());
            requete.setString(3, uneCourse.getDate());
            requete.setInt(4, uneCourse.getId());
            requete.executeUpdate();
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return uneCourse; 
    }

    public static Course supprimerCourse(Connection connection, Course uneCourse){      
        try
        {
            requete=connection.prepareStatement("DELETE FROM participer where par_crs_id = ?");
            requete.setInt(1, uneCourse.getId());
            requete.executeUpdate();           
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("DELETE FROM course where crs_id = ?");
            requete.setInt(1, uneCourse.getId());
            requete.executeUpdate();
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneCourse ;    
    }
    
    public static Course archiverCourse(Connection connection, Course uneCourse){      
        try
        {
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("UPDATE course set crs_archive = 1 WHERE crs_id = ?");
            requete.setInt(1, uneCourse.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneCourse ;    
    }
    
    public static ArrayList<Participer>  getLesParticipations(Connection connection, int idcourse){
              ArrayList<Participer> lesParticipation = new  ArrayList<Participer>();   
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM course, lieu, participer, cheval WHERE par_crs_id = crs_id AND crs_lieu = lie_id AND par_che_id = che_id AND crs_id = ? ORDER BY par_place");
            requete.setInt(1, idcourse);
            //executer la requete
            rs=requete.executeQuery();
            

            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                    
                Participer uneParticipation = new Participer();         
               
                uneParticipation.setPlace(rs.getInt("par_place"));
                
                Cheval unCheval = new Cheval();
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setId(rs.getInt("che_id"));
                
               Lieu unLieu = new Lieu();
               unLieu.setId(rs.getInt("lie_id"));
               unLieu.setVille(rs.getString("lie_ville"));
               unLieu.setNbBoxes(rs.getInt("lie_nbBoxes"));
               unLieu.setCommentaires(rs.getString("lie_commentaires"));
                
                Course uneCourse = new Course();
                uneCourse.setId(rs.getInt("crs_id"));
                uneCourse.setLibelle(rs.getString("crs_nom"));
                uneCourse.setLieu(unLieu);
                uneCourse.setDate(rs.getString("crs_date"));
                
                uneParticipation.setUnCheval(unCheval);
                uneParticipation.setUneCourse(uneCourse);
               
                lesParticipation.add(uneParticipation);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesParticipation; 
    }
	
       public static Course getUneCourse(Connection connection, int codeCourse){      
        Course uneCourse = new Course();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM course, lieu WHERE crs_lieu = lie_id AND crs_id = ?");
            requete.setInt(1, codeCourse);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  

                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("lie_id"));
                unLieu.setVille(rs.getString("lie_ville"));
                unLieu.setNbBoxes(rs.getInt("lie_nbBoxes"));
                unLieu.setCommentaires(rs.getString("lie_commentaires"));
                
                uneCourse.setId(rs.getInt("crs_id"));
                uneCourse.setLibelle(rs.getString("crs_nom"));
                uneCourse.setLieu(unLieu);
                uneCourse.setDate(rs.getString("crs_date"));
                uneCourse.setArchive(rs.getInt("crs_archive"));
                 
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneCourse;    
    }
}
