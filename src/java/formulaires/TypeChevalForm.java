/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.TypeCheval;

/**
 *
 * @author bastu
 */
public class TypeChevalForm {
    
    private String resultat;
    private static final String CHAMPS_NOM = "nom";
    private static final String CHAMPS_DESC = "desc";
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
    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie nom
    private void validationDesc( String desc ) throws Exception {
        if ( desc != null && desc.length() < 10 ) {
        throw new Exception( "La description doit contenir au moins 10 caractères." );
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
    
    public TypeCheval ajouterTypeCheval( HttpServletRequest request ) {
      
        TypeCheval unTypeCheval  = new TypeCheval();
         
        String nom = getDataForm( request, "nom" );
        System.out.println("NOM TYPE :"+nom);
        String description = getDataForm( request, "description");
        System.out.println("DESC TYPE :"+description);
              
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            System.out.println("OUPS NOM");
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
        try {
             validationDesc(description);
        } catch ( Exception e ) {
            System.out.println("OUPS DESC");
            setErreur( CHAMPS_DESC, e.getMessage() );
        }
        
        unTypeCheval.setLibelle(nom);
        unTypeCheval.setDescription(description);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM TYPECHEVAL OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM TYPECHEVAL PAS OK");
        }
               
       
        return unTypeCheval ;
    }

    public TypeCheval modifierTypeCheval( HttpServletRequest request ) {
      
        TypeCheval unTypeCheval  = new TypeCheval();
        int codeTypeCheval = Integer.parseInt(getDataForm(request, "idTypeCheval"));
        String nom = getDataForm( request, "nom" );
        System.out.println("NOM TYPE :"+nom);
        String description = getDataForm( request, "description");
        System.out.println("DESC TYPE :"+description);
              
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            System.out.println("OUPS NOM");
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
        try {
             validationDesc(description);
        } catch ( Exception e ) {
            System.out.println("OUPS DESC");
            setErreur( CHAMPS_DESC, e.getMessage() );
        }
        unTypeCheval.setId(codeTypeCheval);
        unTypeCheval.setLibelle(nom);
        unTypeCheval.setDescription(description);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM TYPECHEVAL OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM TYPECHEVAL PAS OK");
        }
               
       
        return unTypeCheval ;
    }
    
    public TypeCheval supprimerTypeCheval( HttpServletRequest request) {
      
        TypeCheval unTypeCheval  = new TypeCheval();
        int idTypeCheval = Integer.parseInt(getDataForm(request, "idTypeCheval"));
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
        unTypeCheval.setId(idTypeCheval);
               
       
        return unTypeCheval ;
    }
    
}
