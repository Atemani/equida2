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
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.Courriel;
import modele.Lieu;
import modele.Lot;
import modele.PieceJointe;
import modele.Pays;
import modele.TypeCheval;
import modele.Vente;

/**
 *
 * @author Zakina
 * 22/06/2017
 * Classe faisant la liaison entre la table Vente et la classe Vente
 */
public class CourrierDAO {

    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
    /* Pour chaque vente, on récupère aussi sa catégorie.
    /* La liste des vente est stockée dans une ArrayList
    */
   
   
    
    public static ArrayList<Courriel>  getLesCourrielsParVente(Connection connection, int codeVente){      
        ArrayList<Courriel> lesCourriels = new  ArrayList<Courriel>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM courriel WHERE cou_vente = "+codeVente);          
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                Courriel unCourriel = new Courriel();
                unCourriel.setId(rs.getInt("cou_id"));
                unCourriel.setDateEnvoiMessage(rs.getString("cou_dateEnvoiMessage"));
                unCourriel.setObjetMessage(rs.getString("cou_objetMessage"));
                unCourriel.setCorpMessage(rs.getString("cou_corpMessage"));
                lesCourriels.add(unCourriel);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesCourriels ; 
    }
   
    public static ArrayList<PieceJointe>  getLesPiecesJointesParCourriel(Connection connection, int codeCourriel){      
        ArrayList<PieceJointe> lesPiecesJointes = new  ArrayList<PieceJointe>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM piecesjointes, courriel, piecemail WHERE pie_id = pm_idPieceJointe AND cou_id = pm_idCourriel AND cou_id = ?");
            requete.setInt(1, codeCourriel);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                PieceJointe unePieceJointe = new PieceJointe();
                unePieceJointe.setId(rs.getInt("pie_id"));
                unePieceJointe.setChemin(rs.getString("pie_chemin"));
                unePieceJointe.setDescription(rs.getString("pie_desc"));
                lesPiecesJointes.add(unePieceJointe);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesPiecesJointes ; 
    }
    
    
    public static Courriel getDetailsParCourriel(Connection connection, int codeCourriel){      
        Courriel unCourriel = new  Courriel();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM courriel WHERE cou_id = ?");
            requete.setInt(1, codeCourriel);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if ( rs.next() ) {
                unCourriel.setId(rs.getInt("cou_id"));
                unCourriel.setDateEnvoiMessage(rs.getString("cou_dateEnvoiMessage"));
                unCourriel.setObjetMessage(rs.getString("cou_objetMessage"));
                unCourriel.setCorpMessage(rs.getString("cou_corpMessage"));
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return unCourriel ; 
    }
    
  
}
