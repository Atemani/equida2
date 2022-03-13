/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author bastu
 */

public class ConnexionForm {
    
    private String resultat;
    private static final String CHAMPS_LOGIN = "login";
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
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
   
    
    /**
    * Valide le nom d'utilisateur saisi.
    */
    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    /**
    * Valide le mot de passe saisi.
    */
    private void validationMdp( String mdp ) throws Exception {
        if ( mdp != null && mdp.length() < 2 ) {
        throw new Exception( "Le mot de passe doit contenir plusieurs caractères." );
        }
    }
    
    public ArrayList<String> verifierChamps (HttpServletRequest request ){
        ArrayList<String> listeChamps = new ArrayList();
        
        String login = getDataForm( request, "login" );
        String mdp = getDataForm( request, "mdp");
        
        try{
            validationNom(login);
        }catch (Exception e){
            erreurs.put( CHAMPS_LOGIN, e.getMessage());
        }
        
        try{
            validationMdp(mdp);
        }catch (Exception e){
            erreurs.put( mdp, e.getMessage() );
        }
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            System.out.println("Succès de la vérification.");
            listeChamps.add(login);
            listeChamps.add(mdp);
            return listeChamps;
        } else {
            System.out.println("Échec de la vérification.");
            return listeChamps;
        }
    }
    
}
