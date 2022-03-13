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
import modele.Participer;
import modele.TypeCheval;

/**
 *
 * @author bastu
 */
public class ParticiperDAO {
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;

    public static Participer ajouterUneParticipation(Connection connection, Participer uneParticipation){
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO participer (par_che_id, par_crs_id, par_place)" +
                    "VALUES (?,?,?)");
            requete.setInt(1, uneParticipation.getUnCheval().getId());
            requete.setInt(2, uneParticipation.getUneCourse().getId());
            requete.setInt(3, uneParticipation.getPlace());

           /* Exécution de la requête */
            requete.executeUpdate();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneParticipation ;
    }    
}
