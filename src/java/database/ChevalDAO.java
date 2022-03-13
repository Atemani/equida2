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
import modele.Cheval;
import modele.Client;
import modele.Course;
import modele.Lieu;
import modele.Participer;
import modele.TypeCheval;


/**
 *
 * @author bastu
 */
public class ChevalDAO {
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    
    private static int genererId(Connection connection){
        int j=0;

        try{
            requete=connection.prepareStatement("SELECT MAX(che_id) from cheval");
            rs=requete.executeQuery();

            if(rs.next()){
                j=rs.getInt("MAX(che_id)")+1;
                System.out.println("MAX ID = "+j);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return j;
    }
    
    public static Cheval getUnCheval(Connection connection, int codeCheval){
        Cheval unCheval = new Cheval();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval,typecheval, client WHERE che_race = typ_id AND che_client = client.id AND che_id=?");
            requete.setInt(1, codeCheval);
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  

                Client unClient = new Client();
                unClient.setId(rs.getInt("client.id"));
                unClient.setNom(rs.getString("client.nom"));
                unClient.setPrenom(rs.getString("client.prenom"));
                
                unCheval.setId(rs.getInt("che_id"));
                unCheval.setNom(rs.getString("che_nom"));
                System.out.println("NOM CHEVAL DAO : "+unCheval.getNom());
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setDateNaissance(rs.getString("che_datenaissance"));
                unCheval.setSire(rs.getString("che_siret"));
                
                Cheval chevalPere = new Cheval();
                chevalPere.setId(rs.getInt("che_id_pere"));
                
                Cheval chevalMere = new Cheval();
                chevalMere.setId(rs.getInt("che_id_mere"));
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
                
                unCheval.setTypeCheval(unTypeCheval);
                unCheval.setMere(chevalMere);
                unCheval.setPere(chevalPere);
                unCheval.setClient(unClient);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return unCheval;
    }
    
    public static Cheval ajouterCheval(Connection connection, Cheval unCheval, int codeClientConnecte){
        int idGenere = genererId(connection);
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO cheval (che_id, che_nom, che_sexe, che_dateNaissance, che_race, che_siret, che_id_pere, che_id_mere, che_client, che_active)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,0)");
            requete.setInt(1, idGenere);
            requete.setString(2, unCheval.getNom());
            requete.setString(3, unCheval.getSexe());
            requete.setString(4, unCheval.getDateNaissance());
            requete.setInt(5, unCheval.getTypeCheval().getId());
            requete.setString(6, unCheval.getSire());
            requete.setInt(7, unCheval.getPere().getId());
            requete.setInt(8, unCheval.getMere().getId());
            requete.setInt(9, codeClientConnecte);

           /* Exécution de la requête */
            requete.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval;
    }
    
    public static Cheval modifierCheval(Connection connection, Cheval unCheval){
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE cheval SET che_nom = ?, che_sexe = ?, che_dateNaissance = ?, che_race = ?, che_siret = ?, che_id_pere = ?, che_id_mere = ? WHERE che_id = ?");

            requete.setString(1, unCheval.getNom());
            requete.setString(2, unCheval.getSexe());
            requete.setString(3, unCheval.getDateNaissance());
            requete.setInt(4, unCheval.getTypeCheval().getId());
            requete.setString(5, unCheval.getSire());
            requete.setInt(6, unCheval.getPere().getId());
            requete.setInt(7, unCheval.getMere().getId());
            requete.setInt(8, unCheval.getId());

           /* Exécution de la requête */
            requete.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval;
    }

    public static Cheval supprimerCheval(Connection connection, Cheval unCheval){      
        try
        {
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("DELETE FROM participer where par_che_id = ?");
            requete.setInt(1, unCheval.getId());
            requete.executeUpdate();
            
            requete=connection.prepareStatement("DELETE FROM lot where lot_idCheval = ?");
            requete.setInt(1, unCheval.getId());
            requete.executeUpdate();
            
            requete=connection.prepareStatement("DELETE FROM cheval where che_id = ?");
            requete.setInt(1, unCheval.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval ;    
    }
    
    public static Cheval archiverCheval(Connection connection, Cheval unCheval){      
        try
        {
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("UPDATE cheval set che_archive = 1 WHERE che_id = ?");
            requete.setInt(1, unCheval.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval ;    
    }
    
    public static int cptChevauxProposes(Connection connection){
        int i=0;

        try{
            requete=connection.prepareStatement("SELECT COUNT(che_id) from cheval WHERE che_active = 0");
            rs=requete.executeQuery();

            if(rs.next()){
                i=rs.getInt("COUNT(che_id)");
                System.out.println("CPT CHEVAUX PROPOSES = "+i);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return i;
    }
    
    public static Cheval accepterCheval(Connection connection, Cheval unCheval){      
        try
        {
            //QUAND CHEVAL ACCEPTE, 2 = ACCEPTE
            //QUAND CHE_ACTIVE = 0, CHEVAL NON STATUé
            requete=connection.prepareStatement("UPDATE cheval set che_active = 2 WHERE che_id = ?");
            requete.setInt(1, unCheval.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval ;    
    }
    
    public static Cheval refuserCheval(Connection connection, Cheval unCheval){      
        try
        {
            //QUAND CHEVAL REFUSE, 1 = REFUSE
            //QUAND CHE_ACTIVE = 0, CHEVAL NON STATUé
            requete=connection.prepareStatement("UPDATE cheval set che_active = 1 WHERE che_id = ?");
            requete.setInt(1, unCheval.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval ;    
    }
    
    public static ArrayList<Cheval> getLesChevauxProposes(Connection connection){
        ArrayList<Cheval> listeChevaux = new ArrayList<Cheval>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval,typecheval, client WHERE che_race = typ_id AND che_client = client.id AND che_active = 0");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {   

                Client unClient = new Client();
                unClient.setId(rs.getInt("client.id"));
                unClient.setNom(rs.getString("client.nom"));
                unClient.setPrenom(rs.getString("client.prenom"));
                
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("che_id"));
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setDateNaissance(rs.getString("che_datenaissance"));
                unCheval.setSire(rs.getString("che_siret"));
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
                
                unCheval.setTypeCheval(unTypeCheval);
                unCheval.setArchive(rs.getInt("che_archive"));
                unCheval.setActive(rs.getInt("che_active"));
                unCheval.setClient(unClient);
                
                listeChevaux.add(unCheval);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return listeChevaux;
    }
    
    public static ArrayList<Participer>  getLesCoursesParCheval(Connection connection, int codeCheval){      
        ArrayList<Participer> listeParticipations = new  ArrayList<Participer>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval, course, lieu, participer WHERE che_id = par_che_id AND crs_id = par_crs_id AND crs_lieu = lie_id AND che_id = ?;");
            requete.setInt(1, codeCheval);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("che_id"));
                unCheval.setNom(rs.getString("che_nom"));
                
                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("lie_id"));
                unLieu.setVille(rs.getString("lie_ville"));
                unLieu.setNbBoxes(rs.getInt("lie_nbBoxes"));
                unLieu.setCommentaires(rs.getString("lie_commentaires"));
                
                Course uneCourse = new Course();
                uneCourse.setId(rs.getInt("crs_id"));
                uneCourse.setLibelle(rs.getString("crs_nom"));
                uneCourse.setDate(rs.getString("crs_date"));
                uneCourse.setLieu(unLieu);
                
                Participer uneParticipation = new Participer();
                uneParticipation.setUnCheval(unCheval);
                uneParticipation.setUneCourse(uneCourse);
                uneParticipation.setPlace(rs.getInt("par_place"));
                
                listeParticipations.add(uneParticipation);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return listeParticipations;    
    }    
    
    public static ArrayList<Cheval> getLesChevaux(Connection connection){
        ArrayList<Cheval> listeChevaux = new ArrayList<Cheval>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval,typecheval, client WHERE che_race = typ_id AND che_client = client.id AND che_active = 2");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {   

                Client unClient = new Client();
                unClient.setId(rs.getInt("client.id"));
                unClient.setNom(rs.getString("client.nom"));
                unClient.setPrenom(rs.getString("client.prenom"));
                
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("che_id"));
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setDateNaissance(rs.getString("che_datenaissance"));
                unCheval.setSire(rs.getString("che_siret"));  
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
                
                unCheval.setTypeCheval(unTypeCheval);
                unCheval.setArchive(rs.getInt("che_archive"));
                unCheval.setClient(unClient);
                
                listeChevaux.add(unCheval);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return listeChevaux;
    } 
    
}
