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
import modele.Vente;
/**
 *
 * @author Zakina
 */
public class CategVenteDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
     public static ArrayList<CategVente>  getLesCategVentes(Connection connection){      
        ArrayList<CategVente> lesCategVentes = new  ArrayList<CategVente>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from categvente");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                CategVente uneCategVente = new CategVente();
                uneCategVente.setCode(rs.getString("code"));
                uneCategVente.setLibelle(rs.getString("libelle"));
                lesCategVentes.add(uneCategVente);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesCategVentes ;    
    } 
    
    public static ArrayList<Vente>  getVentesParCateg(Connection connection, String codeCateg){      
        ArrayList<Vente> listeVenteParCateg = new  ArrayList<Vente>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM vente WHERE codeCategVente ='"+codeCateg+"'");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("id"));
                uneVente.setNom(rs.getString("nom"));
                uneVente.setDateDebutVente(rs.getString("dateDebut"));
                listeVenteParCateg.add(uneVente);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return listeVenteParCateg;    
    }
    
    public static CategVente getUneCateg(Connection connection, String codeCateg){
        CategVente uneCateg = new CategVente();
        
        try
        {
          requete=connection.prepareStatement("SELECT * FROM categvente WHERE code ='"+codeCateg+"'");
          rs=requete.executeQuery();
          if(rs.next()){
            uneCateg.setCode(rs.getString("code"));
            uneCateg.setLibelle(rs.getString("libelle"));
            //System.out.println("CODE CATEG : "+rs.getString("code")+" ET NOM CATEG :"+rs.getString("libelle"));             
          }else{
              uneCateg = null;
          }

          
        }catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneCateg;
    }
    
    
        public static CategVente ajouterCateg(Connection connection, CategVente uneCateg) {
		try {
                    
			requete = connection.prepareStatement("INSERT INTO categvente (code, libelle) VALUES (?, ?)");
			requete.setString(1, uneCateg.getCode());
			requete.setString(2, uneCateg.getLibelle());
			requete.executeUpdate();
		
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		
		}
	
		return uneCateg;
	}
        
        
        
         public static CategVente modifierCateg(Connection connection, CategVente uneCateg) {

		try {
			
		requete = connection.prepareStatement("UPDATE categvente SET libelle=? WHERE code = ?");
		requete.setString(1, uneCateg.getLibelle());
		requete.setString(2, uneCateg.getCode());
		requete.executeUpdate();
		
		}
	
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		return uneCateg;
	 
	}
}
