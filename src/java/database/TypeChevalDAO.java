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
import modele.TypeCheval;

    /**
     *
     * @author bastu
     */
    public class TypeChevalDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    private static int genererId(Connection connection){
        int j=0;

        try{
            requete=connection.prepareStatement("SELECT MAX(typ_id) from typecheval");
            rs=requete.executeQuery();

            if(rs.next()){
                j=rs.getInt("MAX(typ_id)")+1;
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
    public static TypeCheval ajouterTypeCheval(Connection connection, TypeCheval unTypeCheval){
        int idGenere = genererId(connection);
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO typecheval (typ_id, typ_libelle, typ_desc)" +
                    "VALUES (?,?,?)");
            requete.setInt(1, idGenere);
            requete.setString(2, unTypeCheval.getLibelle());
            requete.setString(3, unTypeCheval.getDescription());

           /* Exécution de la requête */
            requete.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unTypeCheval ;
    }
    
    public static ArrayList<TypeCheval>  getLesTypesChevaux(Connection connection){      
        ArrayList<TypeCheval> listeTypeChevaux = new  ArrayList<TypeCheval>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM typecheval");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
                listeTypeChevaux.add(unTypeCheval);
                System.out.println("LIBELLE TYPE CHEVAL :"+unTypeCheval.getLibelle());
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return listeTypeChevaux;    
    }
    
    public static TypeCheval modifierTypeCheval(Connection connection, TypeCheval unTypeCheval){
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE typecheval SET typ_libelle=?, typ_desc=? where typ_id=?");
            requete.setString(1, unTypeCheval.getLibelle());
            requete.setString(2, unTypeCheval.getDescription());
            requete.setInt(3, unTypeCheval.getId());
            
            //executer la requete
            requete.executeUpdate();
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return unTypeCheval;
    }
    
    public static TypeCheval supprimerTypeCheval(Connection connection, TypeCheval unTypeCheval){      
        ArrayList<Cheval> lesChevaux = new ArrayList<Cheval>();
        try
        {
            
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval WHERE che_race = ?");
            requete.setInt(1, unTypeCheval.getId());
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("che_id"));
                lesChevaux.add(unCheval);
            }
            
            for (int i=0;i<lesChevaux.size();i++){
                Cheval unCheval = lesChevaux.get(i);
                ChevalDAO.supprimerCheval(connection, unCheval);
            }
            
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("DELETE FROM typecheval where typ_id = ?");
            requete.setInt(1, unTypeCheval.getId());
            requete.executeUpdate();
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unTypeCheval ;    
    }

    public static TypeCheval getUnTypeCheval(Connection connection, int codeTypeCheval){      
        TypeCheval unTypeCheval = new TypeCheval();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM typecheval WHERE typ_id = ?");
            requete.setInt(1, codeTypeCheval);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unTypeCheval;    
    }
}
