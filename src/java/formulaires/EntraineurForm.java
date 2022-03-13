/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Client;
import modele.Entraineur;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class EntraineurForm {
    
    private String resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    
    //méthode de validation du champ de saisie nom
    private void validationNom( String nom ) throws Exception {
        
        if ( nom != null && nom.length() < 3 ) {
        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 3 ) {
        throw new Exception( "Le prénom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationAdresse( String rue ) throws Exception {
        if ( rue != null && rue.length() < 3 ) {
        throw new Exception( "Le nom de la rue doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationCopos( String copos ) throws Exception {
        if ( copos != null && copos.length() < 5 ) {
        throw new Exception( "Le code postal doit contenir au moins 5 caractères." );
        }
    }
    
    private void validationVille( String ville ) throws Exception {
        if ( ville != null && ville.length() < 5 ) {
        throw new Exception( "L'email doit contenir au moins 5 caractères.");
        }
    }
   
    private void validationTel( String tel ) throws Exception {
        if ( tel != null && tel.length() < 10 ) {
        throw new Exception( "Le téléphone doit contenir au moins 5 caractères.");
        }
    }
            
    private void validationNbVictoires( String nbVictoires ) throws Exception {
        if ( nbVictoires.length() == 0 ) {
        throw new Exception( "Veuillez préciser le nombre de victoires.");
        }
    }

    private void setErreur( String champ, String message ) {
    erreurs.put(champ, message );
    }    
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
    
    
    public Entraineur ajouterEntraineur( HttpServletRequest request ) {
      
        Entraineur unEntraineur  = new Entraineur();
         
        String nom = getDataForm( request, "nom" );
        String prenom = getDataForm( request, "prenom");
        String adresse = getDataForm( request, "adresse" );
        String copos = getDataForm( request, "copos");
        String ville = getDataForm( request, "ville" );
        String tel = getDataForm(request, "tel");
        String nbVictoires = getDataForm(request, "nbVictoires");
        
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( "nom", e.getMessage() );
            System.out.println("OUPS NOM");
        }
     
        try {
             validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( "prenom", e.getMessage() );
            System.out.println("OUPS PRENOM");
        }
        
        try {
             validationAdresse( adresse );
        } catch ( Exception e ) {
            setErreur( "adresse", e.getMessage() );
            System.out.println("OUPS ADRESSE");
        }
        try {
             validationVille( ville );
        } catch ( Exception e ) {
            setErreur( "ville", e.getMessage() );
            System.out.println("OUPS ville");
        }
        
        try {
             validationCopos( copos );
        } catch ( Exception e ) {
            setErreur( "copos", e.getMessage() );
            System.out.println("OUPS copos");
        }
        
        try {
             validationTel( tel );
        } catch ( Exception e ) {
            setErreur( "tel", e.getMessage() );
            System.out.println("OUPS tel");
        }
           
        try {
             validationNbVictoires( nbVictoires );
        } catch ( Exception e ) {
            setErreur( "nbVictoires", e.getMessage() );
            System.out.println("OUPS nbVictoires "+nbVictoires);
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
        unEntraineur.setNom(nom);      
        unEntraineur.setPrenom(prenom);
        unEntraineur.setAdresse(adresse);
        unEntraineur.setCopos(copos);
        unEntraineur.setVille(ville);
        unEntraineur.setTelephone(tel);
        unEntraineur.setNbVictoires(Integer.parseInt(nbVictoires));

        return unEntraineur ;
    }

}
