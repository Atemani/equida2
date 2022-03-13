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
import modele.Vendeur;
import modele.Vente;

/**
 *
 * @author Zakina
 * 22/06/2017
 * Classe faisant la liaison entre la table Vente et la classe Vente
 */
public class VenteDAO {

    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
    /* Pour chaque vente, on récupère aussi sa catégorie.
    /* La liste des vente est stockée dans une ArrayList
    */
    public static ArrayList<Vente>  getLesVentes(Connection connection){      
        ArrayList<Vente> lesVentes = new  ArrayList<Vente>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM vente, categvente, lieu WHERE codeCategVente = categvente.code and ven_lieu = lie_id and archive = 0 order by dateDebut DESC");
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("id"));
                uneVente.setNom(rs.getString("nom"));
                uneVente.setDateDebutVente(rs.getString("dateDebut"));
				
				Lieu unLieu = new Lieu();
				unLieu.setId(rs.getInt("lie_id"));
				unLieu.setVille(rs.getString("lie_ville"));
                
                CategVente uneCateg = new CategVente();
                uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                uneCateg.setLibelle(rs.getString("libelle"));
				
				uneVente.setUnLieu(unLieu);
                
                uneVente.setUneCategVente(uneCateg);
                lesVentes.add(uneVente);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesVentes ;    
    } 
	
	
	public static Vente ajouterVente(Connection connection, Vente uneVente) {
		try {
			requete = connection.prepareStatement("INSERT INTO vente (nom, dateDebut, ven_lieu, codeCategVente)" + "VALUES (?, ?, ?, ?)");
			requete.setString(1, uneVente.getNom());
			requete.setString(2, uneVente.getDateDebutVente());
			requete.setInt(3, uneVente.getUnLieu().getId());
			requete.setString(4, uneVente.getUneCategVente().getCode());
			
			requete.executeUpdate();
		
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		
		}
	
		return uneVente;
	}
	
        public static Vente modifierVente(Connection connection, Vente uneVente) {

		try {
			
		requete = connection.prepareStatement("UPDATE vente SET nom=?, dateDebut=?, ven_lieu=?, codeCategVente=? WHERE id = ?");
		requete.setString(1, uneVente.getNom());
		requete.setString(2, uneVente.getDateDebutVente());
		requete.setInt(3, uneVente.getUnLieu().getId());
		requete.setString(4, uneVente.getUneCategVente().getCode());
		requete.setInt(5, uneVente.getId());
		requete.executeUpdate();
		
		}
	
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		return uneVente;
	 
	}	
	
		
	public static Vente archiverVente(Connection connection, Vente uneVente){      
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE vente SET archive = 1 WHERE id=?");
            requete.setInt(1, uneVente.getId());

           /* Exécution de la requête */
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneVente ;    
    }
    
    public static Vente supprimerVente(Connection connection, Vente uneVente){   
        ArrayList<Lot> listeLots = new ArrayList<Lot>();
        
        try
        {	
            //SUPPRESSION dans enchere liés aux lots supprimés.
            requete=connection.prepareStatement("SELECT * FROM lot WHERE lot_vente = ?");
            requete.setInt(1, uneVente.getId());
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Lot unLot = new Lot();
                unLot.setLot_id(rs.getInt("lot_id"));
                listeLots.add(unLot);
            }
 
            for (int i=0;i<listeLots.size();i++){
                Lot unLot = listeLots.get(i);
                PreparedStatement requete2=connection.prepareStatement("DELETE FROM enchere WHERE enc_idLot = ?");
                 requete2.setInt(1, unLot.getLot_id());
                 requete2.executeUpdate();
            }
            
            requete=connection.prepareStatement("DELETE FROM lot where lot_vente = ?");
            requete.setInt(1, uneVente.getId());
            requete.executeUpdate();
            
            //DELETE FROM enchere, lot WHERE enc_idLot = lot_id AND enc_idLot = (SELECT lot_id FROM lot WHERE lot_vente = 250220);
		
            //SUPPRESSION dans courriel
            requete=connection.prepareStatement("DELETE FROM courriel where cou_vente = ?");
            requete.setInt(1, uneVente.getId());
            requete.executeUpdate();
            
            requete=connection.prepareStatement("DELETE FROM vente where id = ?");
            requete.setInt(1, uneVente.getId());
            requete.executeUpdate(); 
           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
       
        return uneVente ;         

    }
	
		

    public static Vente getUneVente(Connection connection, int codeVente){      
        Vente unevente = new Vente();

        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM vente, categvente, lieu WHERE codeCategVente=code AND ven_lieu = lie_id  AND  id = ?");
            requete.setInt(1, codeVente);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unevente.setId(rs.getInt("id"));
                unevente.setNom(rs.getString("nom"));
                unevente.setDateDebutVente(rs.getString("dateDebut"));
                
                Lieu unLieu = new Lieu();
                unLieu.setVille(rs.getString("lie_ville"));
                
                CategVente uneCategVente = new CategVente();
                uneCategVente.setLibelle(rs.getString("libelle"));
                        
                unevente.setUnLieu(unLieu);
                unevente.setUneCategVente(uneCategVente);
                 
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unevente;    
    }
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister les clients interessés par la catégorie de la vente selectionnée (passée en paramètre de la méthode)
    /* Pour chaque client, on récupère aussi le nom de son pays
    /* La liste des clients est stockée dans une ArrayList
    */
    public static ArrayList<Client>  getLesClients(Connection connection, String codeCateg){      
        ArrayList<Client> lesClients = new  ArrayList<Client>();
        try
        {
            //preparation de la requete     
            //codeCateg="ETE";
            requete=connection.prepareStatement("SELECT c.*, p.nom as nomPays, cv.libelle FROM client c, pays p, clientcategvente cc, categvente cv where c.codePays=p.code and cc.codeClient=c.id and cv.code=cc.codeCategVente and codeCategVente= ?");
            requete.setString(1, codeCateg);
            //executer la requete
            rs=requete.executeQuery();
             
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                
                Client unClient = new Client();
                unClient.setId(rs.getInt("id"));
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setRue(rs.getString("rue"));
                unClient.setVille(rs.getString("ville"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setMail(rs.getString("mail"));
                if(unClient.getMail() == null){
                    unClient.setMail("Aucune adresse");
                }
                
                Pays p = new Pays();
                p.setCode(rs.getString("codePays"));
                p.setNom(rs.getString("nomPays"));
                
                unClient.setUnPays(p);
                /*CategVente uneCateg = new CategVente();
                uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                uneCateg.setLibelle(rs.getString("libelle"));*/
                
                lesClients.add(unClient);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesClients ;    
    } 
    
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
    
    public static ArrayList<Lot>  getLesLotsParVente(Connection connection, int codeVente){      
        ArrayList<Lot> lesLots = new  ArrayList<Lot>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM lot, cheval c, cheval cm, cheval cp,  vente, typecheval, vendeur, client WHERE lot_vente = vente.id AND lot_idcheval = c.che_id  AND c.che_race = typ_id AND lot_ven = ven_cli_id AND ven_cli_id = client.id AND c.che_id_pere = cp.che_id AND c.che_id_mere = cm.che_id AND vente.id = ?");
            requete.setInt(1, codeVente);
            //executer la requete
            rs=requete.executeQuery();
            

            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {
                
                Lot unLot = new Lot();
                
                unLot.setLot_id(rs.getInt("lot_id"));
                unLot.setLot_prixDepart(rs.getInt("lot_prixDepart"));
               
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("che_id"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setNom(rs.getString("che_nom"));
                
                Cheval unPere = new Cheval();
                unPere.setNom(rs.getString("cp.che_nom"));
                unCheval.setPere(unPere);
                
                Cheval unMere = new Cheval();
                unMere.setNom(rs.getString("cm.che_nom"));
                unCheval.setMere(unMere);
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unCheval.setTypeCheval(unTypeCheval);
                
              
                Vendeur unVendeur = new Vendeur();
                unVendeur.setNom(rs.getString("client.nom"));
                
                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("id"));
                
                unLot.setUnVendeur(unVendeur);
                unLot.setUnCheval(unPere);
                unLot.setUnCheval(unMere);
                unLot.setUnCheval(unCheval);
                unLot.setUneVente(uneVente);
                lesLots.add(unLot);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesLots ; 
    }
    
    public static Lot getUnLot(Connection connection, int codeLot){      
        Lot unLot = new Lot();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM lot, cheval, vente, typecheval, vendeur, client WHERE lot_vente = vente.id AND lot_idcheval = che_id AND che_race = typ_id AND lot_ven = ven_cli_id AND ven_cli_id = client.id AND lot_id = ?");
            requete.setInt(1, codeLot);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unLot.setLot_id(rs.getInt("lot_id"));
                
                
                Cheval unCheval = new Cheval();
                unCheval.setNom(rs.getString("che_nom"));
                unCheval.setSexe(rs.getString("che_sexe"));
                unCheval.setSire(rs.getString("che_siret"));
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unCheval.setTypeCheval(unTypeCheval);
                
                Vendeur unVendeur = new Vendeur();
                unVendeur.setNom(rs.getString("client.nom"));
                
                unLot.setUnVendeur(unVendeur);
                unLot.setUnCheval(unCheval);
                 
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
