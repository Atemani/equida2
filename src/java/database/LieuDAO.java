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
import modele.Lieu;
import modele.Vente;

/**
 *
 * @author Zakina
 */
public class LieuDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    private static int genererId(Connection connection){
        int j=0;

        try{
            requete=connection.prepareStatement("SELECT MAX(lie_id) from lieu");
            rs=requete.executeQuery();

            if(rs.next()){
                j=rs.getInt("MAX(lie_id)")+1;
                System.out.println("MAX ID = "+j);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return j;
    }
    
     public static ArrayList<Lieu>  getLesLieux(Connection connection){      
        ArrayList<Lieu> lesLieux = new  ArrayList<Lieu>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from lieu");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("lie_id"));
				unLieu.setVille(rs.getString("lie_ville"));
				unLieu.setNbBoxes(rs.getInt("lie_nbBoxes"));
				unLieu.setCommentaires(rs.getString("lie_commentaires"));
                lesLieux.add(unLieu);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesLieux ;    
    }
     
    public static Lieu getUnLieu(Connection connection, int codeLieu){      
        Lieu unLieu = new Lieu();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM lieu WHERE lie_id = ?");
            requete.setInt(1, codeLieu);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unLieu.setId(rs.getInt("lie_id"));
                unLieu.setVille(rs.getString("lie_ville"));
                unLieu.setNbBoxes(rs.getInt("lie_nbBoxes"));
                unLieu.setCommentaires(rs.getString("lie_commentaires"));
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieu;    
    }
     
    public static Lieu ajouterUnLieu(Connection connection, Lieu unLieu){
        int idGenere = genererId(connection);
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO lieu VALUES (?,?,?,?)");
            requete.setInt(1, idGenere);
            requete.setString(2, unLieu.getVille());
            requete.setInt(3, unLieu.getNbBoxes());
            requete.setString(4, unLieu.getCommentaires());

           /* Exécution de la requête */
            requete.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieu;
    }
    
    public static Lieu modifierLieu(Connection connection, Lieu unLieu){
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE lieu SET lie_ville = ?, lie_nbBoxes = ?, lie_commentaires = ? WHERE lie_id = ?");
            requete.setString(1, unLieu.getVille());
            requete.setInt(2, unLieu.getNbBoxes());
            requete.setString(3, unLieu.getCommentaires());
            requete.setInt(4, unLieu.getId());

           /* Exécution de la requête */
            requete.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieu;
    }
    
    public static Lieu supprimerLieu(Connection connection, Lieu unLieu){
        ArrayList<Vente> listeVentes = new ArrayList<Vente>();
        try
        {
            requete=connection.prepareStatement("DELETE FROM course where crs_lieu = ?");
            requete.setInt(1, unLieu.getId());
            requete.executeUpdate();
            
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM vente WHERE ven_lieu = ?");
            requete.setInt(1, unLieu.getId());
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("id"));
                listeVentes.add(uneVente);
            }
            
            for (int i=0;i<listeVentes.size();i++){
                Vente uneVente = listeVentes.get(i);
                VenteDAO.supprimerVente(connection, uneVente);
            }
            
            requete=connection.prepareStatement("DELETE FROM lieu where lie_id = ?");
            requete.setInt(1, unLieu.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieu ;    
    }
    
}
