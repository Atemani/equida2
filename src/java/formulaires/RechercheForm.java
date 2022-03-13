/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bastu
 */
public class RechercheForm {

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
        if ( nom == null ) {
        throw new Exception( "Veuillez préciser un nom dans votre recherche..." );
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
    
public String rechercher ( HttpServletRequest request ) {
         
        String nom = getDataForm( request, "nomRecherche" );
        System.out.println("NOM RECHERCHE :"+nom);
              
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            System.out.println("OUPS NOM");
            setErreur( "nom", e.getMessage() );
        }
        

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la recheche";
        } else {
            resultat = "Échec de la recherche.";
        }
               
       
        return nom ;
    }    
    
}
