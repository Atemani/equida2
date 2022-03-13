/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.VenteDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.CategVente;
import modele.Pays;
import modele.Vente;

/**
 *
 * @author Zakina
 */
public class PaysDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
     public static ArrayList<Pays>  getLesPays(Connection connection){      
        ArrayList<Pays> lesPays = new  ArrayList<Pays>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from pays where pays.archive=0");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Pays unPays = new Pays();
                unPays.setCode(rs.getString("code"));
                unPays.setNom(rs.getString("nom"));
                lesPays.add(unPays);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesPays ;    
    } 
     
     public static Pays  getUnPays(Connection connection, String codePays){   
         
        Pays unPays = new Pays();
        
        try
        {
            //preparation de la requete     
            System.out.println("payes recup dans DAO" + codePays);
            
            requete=connection.prepareStatement("select * from pays WHERE code = ?");
            requete.setString(1, codePays);
            
            System.out.println("requete" + requete);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if(rs.next() ) {
                
                unPays.setCode(rs.getString("code"));
                unPays.setNom(rs.getString("nom"));
            }
            
            System.out.println("DAO PAYS" + unPays.getNom());
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unPays ;    
    } 
     public static Pays ajouterPays(Connection connection, Pays unPays) {
		try {
			requete = connection.prepareStatement("INSERT INTO pays (code, nom) VALUES (?, ?)");
			requete.setString(1, unPays.getCode());
			requete.setString(2, unPays.getNom());

			
			requete.executeUpdate();
		
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		
		}
	
		return unPays;
	}
    
     public static Pays modifierPays(Connection connection, Pays unPays){      
         
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE pays SET nom=? WHERE code=?");
            requete.setString(1, unPays.getNom());
            requete.setString(2, unPays.getCode());
            System.out.println("PAYS MODIFIE CODE "+unPays.getCode()+" ET NOM "+unPays.getNom() + "requete" + requete);

            /* Exécution de la requête */
            requete.executeUpdate();
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unPays ;    
    }
      public static Pays archiverPays(Connection connection, Pays unPays){      
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE pays SET archive = 1 WHERE code=?");
            requete.setString(1, unPays.getCode());

           /* Exécution de la requête */
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unPays ;    
    }
    
    public static Pays supprimerPays(Connection connection, Pays unPays){      
        try
        {
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("DELETE FROM pays WHERE pays.code= ?");
            requete.setString(1, unPays.getCode());
            requete.executeUpdate();
          
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unPays ;
}
    
}