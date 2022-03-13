/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Pays;

/**
 *
 * @author slam
 */
public class PaysForm {
    
    private String resultat;
    private static final String CHAMPS_CODE = "code";
    private static final String CHAMPS_NOM = "nom";
    public Map<String, String> erreurs = new HashMap<String, String>();
    
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
    private void validationCode( String code ) throws Exception {
        if ( code != null && code.length() < 3 ) {
        throw new Exception( "Le code d'un pays doit contenir 3 caractères." );
        }
    }
    
    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
        throw new Exception( "Le nom d'un pays doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationOptionSuppression( String optionSuppression ) throws Exception {
        if ( optionSuppression == null) {
        throw new Exception( "Il faut sélectionner une des deux options...");
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
     
      public Pays ajouterPays( HttpServletRequest request ) {
      
        Pays unPays  = new Pays();
         
        String code = getDataForm( request, "code" );
        String nom = getDataForm( request, "nom");
         
         try {
             validationCode( code );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CODE, e.getMessage() );
        }
     
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
                if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
        
        
        unPays.setCode(code);
        unPays.setNom(nom);
                                   
        
        return unPays;

      }
      
     public Pays modifierPays( HttpServletRequest request ) {
      
        Pays unPays  = new Pays(); 
        String idPays = getDataForm( request, "idPays" );
        String code = getDataForm( request, "code" );
        String nom = getDataForm( request, "nom");
        
        try {
             validationCode( code );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CODE, e.getMessage() );
        }
     
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
                if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
                
        unPays.setCode(idPays);
        unPays.setNom(nom);        
        
        
        return unPays;
        
     }
      public Pays supprimerPays( HttpServletRequest request, String code) {
      
        Pays unPays  = new Pays();
        String typeSuppression = getDataForm( request, "typeSuppression");
        System.out.println("TYPE SUPPRESSION :"+typeSuppression);
        
        try {
             validationOptionSuppression( typeSuppression );
        } catch ( Exception e ) {
            setErreur( "typeSuppression", e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la suppresion.";
        } else {
            resultat = "Échec de la suppression.";
        }
        
        System.out.println("RESULTAT :"+resultat);
        unPays.setCode(code);
               
       
        return unPays ;
    }
    
}
