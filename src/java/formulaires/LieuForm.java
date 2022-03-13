/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Lieu;

/**
 *
 * @author bastu
 */
public class LieuForm {
    
    private String resultat;
    private static final String CHAMPS_NOM = "nom";
    private static final String CHAMPS_NBBOXES = "nbBoxes";
    private static final String CHAMPS_COMMENTAIRE = "commentaire";
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
    private void validationVille( String ville ) throws Exception {
        if ( ville != null && ville.length() < 3 ) {
        throw new Exception( "Il faut entrer une ville !" );
        }
    }
    
    private void validationBoxes( int boxe ) throws Exception {
        if ( boxe == 0 ) {
        throw new Exception( "Il faut préciser le nombre de boxes disponibles !" );
        }
    }
    
    private void validationCommentaire( String commentaire ) throws Exception {
        if ( commentaire != null && commentaire.length() < 3 ) {
        throw new Exception( "Le commentaire doit contenir au moins 3 caractères" );
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
     
    public Lieu ajouterLieu( HttpServletRequest request ) {
      System.out.println("FORM LIEU");
        Lieu unLieu  = new Lieu();
         
        String ville = getDataForm( request, "ville" );
        int boxes = Integer.parseInt(getDataForm( request, "boxes"));
        System.out.println("BOXES : "+boxes);
        String commentaire = getDataForm( request, "commentaire");
         
         try {
             validationVille( ville );
        } catch ( Exception e ) {
            System.out.println("OUPS VILLE");
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
     
        try {
             validationBoxes( boxes );
        } catch ( Exception e ) {
            System.out.println("OUPS BOXE");
            setErreur( CHAMPS_NBBOXES, e.getMessage() );
        }
        
        try {
             validationCommentaire( commentaire );
        } catch ( Exception e ) {
            System.out.println("OUPS COMMENTAIRE");
            setErreur( CHAMPS_COMMENTAIRE, e.getMessage() );
        }        
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }     
        
        unLieu.setVille(ville);
        unLieu.setNbBoxes(boxes);
        unLieu.setCommentaires(commentaire);
                                   
        return unLieu;

    }
    
    public Lieu modifierLieu( HttpServletRequest request ) {
      System.out.println("FORM MODIFIER LIEU");
        Lieu unLieu  = new Lieu();
         
        int codeLieu = Integer.parseInt(getDataForm(request, "idLieu"));
        String ville = getDataForm( request, "ville" );
        System.out.println("NB BOXES : "+getDataForm( request, "boxes"));
        int boxes = Integer.parseInt(getDataForm( request, "boxes"));
        String commentaire = getDataForm( request, "commentaire");
         
         try {
             validationVille( ville );
        } catch ( Exception e ) {
            System.out.println("OUPS VILLE");
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
     
        try {
             validationBoxes( boxes );
        } catch ( Exception e ) {
            System.out.println("OUPS BOXE");
            setErreur( CHAMPS_NBBOXES, e.getMessage() );
        }
        
        try {
             validationCommentaire( commentaire );
        } catch ( Exception e ) {
            System.out.println("OUPS COMMENTAIRE");
            setErreur( CHAMPS_COMMENTAIRE, e.getMessage() );
        }        
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
        
        unLieu.setId(codeLieu);
        unLieu.setVille(ville);
        unLieu.setNbBoxes(boxes);
        unLieu.setCommentaires(commentaire);
                                   
        return unLieu;
    }
    public Lieu supprimerUnLieu( HttpServletRequest request) {
      
        Lieu unLieu  = new Lieu();
        int idLieu = Integer.parseInt(getDataForm(request, "idLieu"));
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
        unLieu.setId(idLieu);
               
       
        return unLieu ;
    }
}
