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
import modele.Pays;
import modele.TypeCheval;

/**
 *
 * @author Zakina
 * 23/06/2017
 * Classe faisant la liaison entre la table client et la classe Client
 */
public class ClientDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static PreparedStatement requete2=null;
    static ResultSet rs=null;
    
    
    private static int genererId(Connection connection){
        int j=0;

        try{
            requete=connection.prepareStatement("SELECT MAX(com_id) from compteconnexion");
            rs=requete.executeQuery();

            if(rs.next()){
                j=rs.getInt("MAX(com_id)")+1;
                System.out.println("MAX ID = "+j);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return j;
    }
    // Méthode permettant d'insérer un client en base à partir de l'objet client passé en paramètre
    // Cette méthode renvoie l'objet client
    public static Client ajouterClient(Connection connection, Client unClient, String mdp){      
        int idGenereClient = -1;
        int idGenereCompte = genererId(connection);
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO client ( nom, prenom, rue, copos, ville, mail, codePays)\n" +
                    "VALUES (?,?,?,?,?,?,?)", requete.RETURN_GENERATED_KEYS );
            requete.setString(1, unClient.getNom());
            requete.setString(2, unClient.getPrenom());
            requete.setString(3, unClient.getRue());
            requete.setString(4, unClient.getCopos());
            requete.setString(5, unClient.getVille());
            requete.setString(6, unClient.getMail());
            requete.setString(7, unClient.getUnPays().getCode());

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client
            rs = requete.getGeneratedKeys();
            while ( rs.next() ) {
                idGenereClient = rs.getInt( 1 );
                unClient.setId(idGenereClient);
            }
            
            // ajout des enregistrement dans la table clientcategvente
            for (int i=0;i<unClient.getLesCategVentes().size();i++){
                PreparedStatement requete2=connection.prepareStatement("INSERT INTO clientcategvente (codeClient, codeCategVente )\n" +
                    "VALUES (?,?)");
                 requete2.setInt(1, unClient.getId());
                 requete2.setString(2, unClient.getLesCategVentes().get(i).getCode());
                 requete2.executeUpdate();
            }
            
            String identifiantClient = (unClient.getNom().substring(0,1)+unClient.getPrenom()+"0").toLowerCase();
            System.out.println("IDENTIFIANT GENERE :"+identifiantClient);
            System.out.println("MDP :"+mdp);
            
            int RoleParDefaut = 3; //3 = CLIENT
            
            requete2 = connection.prepareStatement("INSERT INTO compteconnexion(com_id, com_identifiant, com_mdp, com_role, com_cli)\n" +
                    "VALUES (?,?,?,?,?)",  requete2.RETURN_GENERATED_KEYS);
            
                 requete2.setInt(1, idGenereCompte);
                 requete2.setString(2, identifiantClient);
                 requete2.setString(3, mdp);
                 requete2.setInt(4, RoleParDefaut);
                 requete2.setInt(5,unClient.getId());
                 requete2.executeUpdate();
                 
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ;    
    }
    
    public static Client modifierClient(Connection connection, Client unClient){      
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE client SET nom=?, prenom=?, rue=?, copos=?, ville=?, mail=?, codepays=? WHERE id=?");
            requete.setString(1, unClient.getNom());
            requete.setString(2, unClient.getPrenom());
            requete.setString(3, unClient.getRue());
            requete.setString(4, unClient.getCopos());
            requete.setString(5, unClient.getVille());
            requete.setString(6, unClient.getMail());
            requete.setString(7, unClient.getUnPays().getCode());
            requete.setInt(8, unClient.getId());

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client
            //PreparedStatement requete2=connection.prepareStatement("");
            
            // ajout des enregistrement dans la table clientcategvente
            /*for (int i=0;i<unClient.getLesCategVentes().size();i++){
                PreparedStatement requete2=connection.prepareStatement("INSERT INTO clientcategvente (codeClient, codeCategVente )\n" +
                    "VALUES (?,?)");
                 requete2.setInt(1, unClient.getId());
                 requete2.setString(2, unClient.getLesCategVentes().get(i).getCode());
                 requete2.executeUpdate();
            }*/
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ;    
    }
    
    public static Client archiverClient(Connection connection, Client unClient){      
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("UPDATE client SET archive = 1 WHERE id=?");
            requete.setInt(1, unClient.getId());

           /* Exécution de la requête */
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ;    
    }
    
    public static Client supprimerClient(Connection connection, Client unClient){      
        try
        {
            //SUPPRESSION dans compteconnection
            requete=connection.prepareStatement("DELETE FROM compteconnexion where com_cli = ?");
            requete.setInt(1, unClient.getId());
            requete.executeUpdate();
            
            requete=connection.prepareStatement("DELETE FROM clientcategvente where codeClient = ?");
            requete.setInt(1, unClient.getId());
            requete.executeUpdate();
            
            requete=connection.prepareStatement("DELETE FROM client where id = ?");
            requete.setInt(1, unClient.getId());
            requete.executeUpdate();
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ;    
    }
    
    public static ArrayList<Cheval>  getLesChevaux(Connection connection, int codeClient){      
        ArrayList<Cheval> listeChevaux = new  ArrayList<Cheval>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM cheval, typecheval WHERE Che_race = typ_id AND che_client ="+codeClient+";");
            
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
                unCheval.setActive(rs.getInt("che_active"));
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("typ_id"));
                unTypeCheval.setLibelle(rs.getString("typ_libelle"));
                unTypeCheval.setDescription(rs.getString("typ_desc"));
                
                unCheval.setTypeCheval(unTypeCheval);
                //unCheval.setTypeCheval(rs.getString("typ_libelle"));
                listeChevaux.add(unCheval);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return listeChevaux;    
    }
    
    public static ArrayList<Client> getLesClients(Connection connection){      
        ArrayList<Client> lesClients = new  ArrayList<Client>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM client, pays WHERE codePays = pays.code and client.archive = 0 ORDER BY client.id");
            
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
                Pays unPays = new Pays();
                unPays.setCode(rs.getString("pays.code"));
                //unPays.setNom(rs.getString(rs.getString("pays.nom")));
                unClient.setUnPays(unPays);
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
    
        /**
         * Permet de retourner un client en fonction d'un ID.
         * @param connection
         * @param codeClient
         * @return 
         */
    public static Client getUnClientParCompte(Connection connection, int codeClient){      
        Client unClient = new Client();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM client, compteconnexion, pays WHERE client.id = com_cli AND client.codePays = pays.code AND com_id = "+codeClient+";");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unClient.setId(rs.getInt("id"));
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setRue(rs.getString("rue"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setVille(rs.getString("ville"));
                unClient.setMail(rs.getString("mail"));
                Pays unPays = new Pays();
                unPays.setCode(rs.getString("pays.code"));
                unPays.setNom(rs.getString("pays.nom"));
                unClient.setUnPays(unPays);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        try{
            PreparedStatement requete2=connection.prepareStatement("SELECT * FROM clientcategvente, categvente WHERE codecategvente = categvente.code AND codeclient=?");
            requete2.setInt(1, unClient.getId());

            rs=requete2.executeQuery();

            while(rs.next()){
                CategVente uneCategVente = new CategVente();
                uneCategVente.setCode("categvente.code");
                uneCategVente.setLibelle("categvente.libelle");
                unClient.addUneCategVente(uneCategVente);
            }
                //On hydrate l'objet métier Compte avec les résultats de la requête

        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return unClient;    
    }
    
    public static Client getUnClient(Connection connection, int codeClient){      
        Client unClient = new Client();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM client, pays WHERE client.codePays = pays.code AND client.id = "+codeClient+";");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            if( rs.next() ) {  
                
                unClient.setId(rs.getInt("id"));
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setRue(rs.getString("rue"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setVille(rs.getString("ville"));
                unClient.setMail(rs.getString("mail"));
                Pays unPays = new Pays();
                unPays.setCode(rs.getString("pays.code"));
                unPays.setNom(rs.getString("pays.nom"));
                unClient.setUnPays(unPays);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        try{
            PreparedStatement requete2=connection.prepareStatement("SELECT * FROM clientcategvente, categvente WHERE codecategvente = categvente.code AND codeclient=?");
            requete2.setInt(1, unClient.getId());

            rs=requete2.executeQuery();

            while(rs.next()){
                CategVente uneCategVente = new CategVente();
                uneCategVente.setCode(rs.getString("categvente.code"));
                uneCategVente.setLibelle(rs.getString("categvente.libelle"));
                unClient.addUneCategVente(uneCategVente);
            }
                //On hydrate l'objet métier Compte avec les résultats de la requête

        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return unClient;    
    }
    
}
