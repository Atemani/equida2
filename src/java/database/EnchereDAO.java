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
import modele.Acheteur;
import modele.Cheval;
import modele.Client;
import modele.Enchere;
import modele.Lot;
import modele.TypeCheval;
import modele.Vendeur;

/**
 *
 * @author slam
 */
public class EnchereDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    
    public static ArrayList<Enchere>  getLesEncheresParLot(Connection connection, int codeLot){
        System.out.println("CODE LOT:"+codeLot);
        ArrayList<Enchere> lesEnchere = new  ArrayList<Enchere>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM lot, cheval, typecheval, acheteur, vendeur, client, enchere WHERE lot_idCheval = che_id AND che_race = typ_id AND enc_acheteur = ach_cli_id AND lot_ven = ven_cli_id AND ach_cli_id = client.id AND enc_idLot = lot_id AND lot_id = ? ORDER BY enc_prix DESC");
            requete.setInt(1, codeLot);
            //executer la requete
            rs=requete.executeQuery();
            

            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                
                Enchere uneEnchere = new Enchere();   
                uneEnchere.setEnc_id(rs.getInt("enc_id"));
                
                Lot unLot = new Lot();
                unLot.setLot_id(rs.getInt("lot_id"));
                                
                                
                Cheval unCheval = new Cheval();
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setSire(rs.getString("che_siret"));
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unCheval.setTypeCheval(unTypeCheval);
                      
                Acheteur unAcheteur = new Acheteur();
                Client clientAcheteur = ClientDAO.getUnClient(connection, rs.getInt("enc_acheteur"));
                unAcheteur.setUnCient(clientAcheteur);
                
                Vendeur unVendeur = new Vendeur();
                Client clientVendeur = ClientDAO.getUnClient(connection, rs.getInt("ven_cli_id"));
                unVendeur.setUnClient(clientVendeur);
                                                    
                uneEnchere.setUnAcheteur(unAcheteur);
                uneEnchere.setUnLot(unLot);
                uneEnchere.setPrix(rs.getInt("enc_prix"));

                lesEnchere.add(uneEnchere);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesEnchere ; 
    }
    
    public static ArrayList<Enchere> getLesEncheresParClient (Connection connection, int idClient){
        ArrayList<Enchere> listeEncheres = new ArrayList<Enchere>();
        
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM enchere, lot, cheval, typecheval, acheteur, vendeur, client WHERE enc_idLot = lot_id AND lot_idCheval = che_id AND che_race = typ_id AND enc_acheteur = ach_cli_id AND lot_ven = ven_cli_id AND ven_cli_id = client.id AND enc_acheteur = ?");
            requete.setInt(1, idClient);
            //executer la requete
            rs=requete.executeQuery();
            

            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                
                Enchere uneEnchere = new Enchere();   
                uneEnchere.setEnc_id(rs.getInt("enc_id"));
                
                Lot unLot = new Lot();
                unLot.setLot_id(rs.getInt("lot_id"));
                                
                                
                Cheval unCheval = new Cheval();
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setSire(rs.getString("che_siret"));
                unLot.setUnCheval(unCheval);
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unCheval.setTypeCheval(unTypeCheval);
                      
                Acheteur unAcheteur = new Acheteur();
                unAcheteur.setNom(rs.getString("client.nom"));
                
                Vendeur unVendeur = new Vendeur();
                Client unClient = ClientDAO.getUnClient(connection, (rs.getInt("client.id")));
                unVendeur.setUnClient(unClient);
                unLot.setUnVendeur(unVendeur);
                                                    
                uneEnchere.setUnAcheteur(unAcheteur);
                uneEnchere.setUnLot(unLot);
                uneEnchere.setPrix(rs.getInt("enc_prix"));

                listeEncheres.add(uneEnchere);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }        
        
        return listeEncheres;
    }
    

    
}
