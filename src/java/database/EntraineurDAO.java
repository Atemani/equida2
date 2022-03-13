/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.ClientDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Cheval;
import modele.Client;
import modele.Entraineur;
import modele.TypeCheval;

/**
 *
 * @author bastu
 */
public class EntraineurDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static PreparedStatement requete2=null;
    static ResultSet rs=null;
    
    

    private static int genererId(Connection connection){
        int j=0;

        try{
            requete=connection.prepareStatement("SELECT MAX(ent_id) from entraineur");
            rs=requete.executeQuery();

            if(rs.next()){
                j=rs.getInt("MAX(ent_id)")+1;
                System.out.println("MAX ID = "+j);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return j;
    }
    // Méthode permettant d'insérer un client en base à partir de l'objet client passé en paramètre
    // Cette méthode renvoie l'objet client
    public static Entraineur ajouterEntraineur(Connection connection, Entraineur unEntraineur){      
        unEntraineur.setId(genererId(connection));
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO entraineur ( ent_id , ent_nom, ent_prenom, ent_adresse, ent_copos, ent_ville, ent_tel, ent_nbVictoires)"
                    +"VALUES (?,?,?,?,?,?,?,?)"); 
            requete.setInt(1, unEntraineur.getId());
            requete.setString(2, unEntraineur.getNom());
            requete.setString(3, unEntraineur.getPrenom());
            requete.setString(4, unEntraineur.getAdresse());
            requete.setString(5, unEntraineur.getCopos());
            requete.setString(6, unEntraineur.getVille());
            requete.setString(7, unEntraineur.getTelephone());
            requete.setInt(8, unEntraineur.getNbVictoires());

            System.out.println(requete);
           /* Exécution de la requête */
            requete.executeUpdate(); 
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unEntraineur ;    
    }
    
    public static ArrayList<Cheval> getLesChevauxParEntraineur(Connection connection, int idEntraineur){
       ArrayList<Cheval> lesChevaux = new ArrayList<Cheval>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval, typecheval WHERE Che_race = typ_id AND che_entraineur ="+idEntraineur+";");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("che_id"));
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setDateNaissance(rs.getString("che_datenaissance"));
                unCheval.setSire(rs.getString("che_siret"));
                unCheval.setActive(rs.getInt("che_active"));
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
                
                unCheval.setTypeCheval(unTypeCheval);
                //unCheval.setTypeCheval(rs.getString("typ_libelle"));
                lesChevaux.add(unCheval);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesChevaux;    
    }
    
    public static ArrayList<Entraineur> getLesEntraineurs(Connection connection){
       ArrayList<Entraineur> lesEntraineurs = new ArrayList<Entraineur>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM entraineur");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Entraineur unEntraineur = new Entraineur();
                unEntraineur.setId(rs.getInt("ent_id"));
                unEntraineur.setNom(rs.getString("ent_nom"));
                unEntraineur.setPrenom(rs.getString("ent_prenom"));
                unEntraineur.setAdresse(rs.getString("ent_adresse"));
                unEntraineur.setCopos(rs.getString("ent_copos"));
                unEntraineur.setVille(rs.getString("ent_ville"));
                unEntraineur.setTelephone(rs.getString("ent_tel"));
                unEntraineur.setNbVictoires(rs.getInt("ent_nbVictoires"));
                
                //unCheval.setTypeCheval(rs.getString("typ_libelle"));
                lesEntraineurs.add(unEntraineur);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesEntraineurs;    
    }
    
    public static Entraineur getUnEntraineur(Connection connection, int idEntraineur){
       Entraineur unEntraineur = new Entraineur();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM entraineur WHERE ent_id = ?");
            requete.setInt(1, idEntraineur);
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if ( rs.next() ) {  
                unEntraineur.setId(rs.getInt("ent_id"));
                unEntraineur.setNom(rs.getString("ent_nom"));
                unEntraineur.setPrenom(rs.getString("ent_prenom"));
                unEntraineur.setAdresse(rs.getString("ent_adresse"));
                unEntraineur.setCopos(rs.getString("ent_copos"));
                unEntraineur.setVille(rs.getString("ent_ville"));
                unEntraineur.setTelephone(rs.getString("ent_tel"));
                unEntraineur.setNbVictoires(rs.getInt("ent_nbVictoires"));
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unEntraineur;    
    }
    
}
