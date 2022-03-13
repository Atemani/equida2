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
import modele.Pays;

/**
 *
 * @author bastu
 */
public class RechercheDAO {

    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    public static ArrayList<Client> rechercherClients(Connection connection, String nomClient){
        ArrayList<Client> lesClients = new ArrayList<Client>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM client WHERE archive = 0 AND nom LIKE '"+nomClient+"%' OR nom LIKE '%"+nomClient+"' OR nom LIKE '%"+nomClient+"%' ORDER BY id");
            System.out.println("REQUETE RECHERCHE : "+requete);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Client unClient = new Client();
                unClient.setId(rs.getInt("client.id"));
                unClient.setNom(rs.getString("client.nom"));
                unClient.setPrenom(rs.getString("client.prenom"));
                unClient.setRue(rs.getString("client.rue"));
                unClient.setCopos(rs.getString("client.copos"));
                unClient.setVille(rs.getString("client.ville"));
                unClient.setMail(rs.getString("client.mail"));
                unClient.setArchive(rs.getInt("client.archive"));
                //unPays.setNom(rs.getString(rs.getString("pays.nom")));
                lesClients.add(unClient);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }       
        
        return lesClients;
    }
    
    public static ArrayList<Cheval> rechercherChevaux(Connection connection, String nomCheval){
        ArrayList<Cheval> lesChevaux = new ArrayList<Cheval>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval WHERE che_nom LIKE '"+nomCheval+"%' OR che_nom LIKE '%"+nomCheval+"' OR che_nom LIKE '%"+nomCheval+"%'");
            
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
                
                unCheval.setArchive(rs.getInt("che_archive"));
                
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
    
}
