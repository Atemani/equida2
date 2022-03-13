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
import modele.Cheval;
import modele.Client;
import modele.Lot;
import modele.Vendeur;
import modele.Vente;

/**
 *
 * @author bastu
 */
public class LotDAO {
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;    
    
    public static Lot getUnLot(Connection connection, int codeLot){      
        Lot unLot = new Lot();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM lot, vendeur WHERE lot_ven = ven_cli_id AND lot_id = ?");
            requete.setInt(1, codeLot);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unLot.setLot_id(rs.getInt("lot_id"));
                unLot.setLot_prixDepart(rs.getInt("lot_prixDepart"));
                
                Vendeur unVendeur = new Vendeur();
                Client unClient = ClientDAO.getUnClient(connection, rs.getInt("ven_cli_id"));
                unVendeur.setUnClient(unClient);
                
                Cheval unCheval = ChevalDAO.getUnCheval(connection, rs.getInt("lot_idCheval"));
                unLot.setUnCheval(unCheval);
                
                Vente uneVente = VenteDAO.getUneVente(connection, rs.getInt("lot_vente"));
                unLot.setUneVente(uneVente);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLot;    
    }
}
