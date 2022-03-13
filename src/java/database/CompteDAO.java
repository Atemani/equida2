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
import modele.Compte;
import modele.Role;

/**
 *
 * @author bastu
 */
public class CompteDAO {
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    public static Compte verifLoginCompte(Connection connection, String identifiant, String mdp){
        System.out.println("IDENTIFIANT : "+identifiant+" ET MDP : "+mdp);
        Compte unCompte = new Compte();
        Role unRole = new Role();
        try
        {
            requete = connection.prepareStatement("SELECT COUNT(com_id) as cpt, compteconnexion.* FROM compteconnexion WHERE com_identifiant = ? AND com_mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, mdp);
            
            rs=requete.executeQuery();
            
            if(rs.next()){
                if(rs.getInt("cpt") == 1){
                    unCompte.setConnexion(true);
                    unCompte.setId(rs.getInt("com_id"));
                    unRole.setId(rs.getInt("com_role"));
                }else{
                    unCompte.setConnexion(false);
                }
            }
            //On hydrate l'objet métier Compte avec les résultats de la requête

        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        if(unCompte.isConnexion()){
            try
            {
                PreparedStatement requete2=connection.prepareStatement("SELECT * FROM role WHERE role_id=?");
                requete2.setInt(1, unRole.getId());

                rs=requete2.executeQuery();

                if(rs.next()){
                    unRole.setId(rs.getInt("role_id"));
                    unRole.setLibelle(rs.getString("role_libelle"));
                    unCompte.setRole(unRole);
                }else{
                    unRole = null;
                    unCompte.setRole(unRole);
                }
                //On hydrate l'objet métier Compte avec les résultats de la requête

            }   
            catch (SQLException e) 
            {
                e.printStackTrace();
                //out.println("Erreur lors de l’établissement de la connexion");
            }           
        }

        
        return unCompte;
    }
    
    public static Compte getUnCompte(Connection connection, int id){
        Compte unCompte = new Compte();
        try{
            requete = connection.prepareStatement("SELECT * FROM compteconnexion, role WHERE com_id = ?");
            requete.setInt(1, id);
            
            rs=requete.executeQuery();
            
            if(rs.next()){
                    unCompte.setId(rs.getInt("com_id"));
                    unCompte.setIdentifiant(rs.getString("com_identifiant"));
                    unCompte.setMdp(rs.getString("com_mdp"));
                    
            }else{
                unCompte = null;
            }
            //On hydrate l'objet métier Compte avec les résultats de la requête
        
        }catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }

        return unCompte;
    }
}
