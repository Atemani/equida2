/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
import modele.TypeCheval;

/**
 *
 * @author bastu
 */
public class ChevalForm {
    
    private String resultat;
    private static final String CHAMPS_NOM = "nom";
    private static final String CHAMPS_SEXE = "sexe";
    private static final String CHAMPS_DATE = "date";
    private static final String CHAMPS_RACE = "race";
    private static final String CHAMPS_SIRE = "sire";
    private static final String CHAMPS_PERE = "pere";
    private static final String CHAMPS_MERE = "mere";
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
        throw new Exception( "Le nom du cheval doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie nom
    private void validationSexe( String sexe ) throws Exception {
        if ( sexe != null && sexe.length() < 1 ) {
        throw new Exception( "Le sexe doit être précisé à l'aide des lettres M ou F." );
        }
    }
    
    private void validationDateNaissance( String dateNaissance ) throws Exception {
        if ( dateNaissance != null && dateNaissance.length() < 10 ) {
        throw new Exception( "La date de naissance doit contenir au moins 10 caractères." );
        }
    }
    
    private void validationRace( String race ) throws Exception {
        if ( race != null && race.length() < 1 ) {
        throw new Exception( "Veuillez choisir une race de cheval." );
        }
    }
    
    private void validationSire( String sire ) throws Exception {
        if ( sire != null && sire.length() < 5 ) {
        throw new Exception( "Le SIRE du cheval doit contenir au moins 5 caractères." );
        }
    }
    
    private void validationPere( String idPere ) throws Exception {
        if ( idPere != null && idPere.length() < 1 ) {
        throw new Exception( "Veuillez choisir le père du cheval que vous souhaitez ajouter." );
        }
    }
    
    private void validationMere( String idMere ) throws Exception {
        if ( idMere != null && idMere.length() < 1 ) {
        throw new Exception( "Veuillez choisir la mère du cheval que vous souhaitez ajouter." );
        }
    }
    
    private void validationOptionSuppression( String optionSuppression ) throws Exception {
        if ( optionSuppression == null) {
        throw new Exception( "Il faut sélectionner une des deux options...");
        }
    }

    private void validationTypeGestion( String typeGestion ) throws Exception {
        if ( typeGestion == null) {
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
    
    public Cheval ajouterCheval( HttpServletRequest request ) {
      
        Cheval unCheval  = new Cheval();
         
        String nom = getDataForm( request, "nom" );
        String sexe = getDataForm( request, "sexe");
        String dateNaissance = getDataForm( request, "dateNaissance");
        String race = getDataForm( request, "race");
        String sire = getDataForm( request, "sire");
        String pereCheval = getDataForm( request, "chevalPere");
        String mereCheval = getDataForm( request, "chevalMere");
        
              
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
        try {
             validationSexe( sexe );
        } catch ( Exception e ) {
            setErreur( CHAMPS_SEXE, e.getMessage() );
        }
        
        try {
             validationDateNaissance( dateNaissance );
        } catch ( Exception e ) {
            setErreur( CHAMPS_DATE, e.getMessage() );
        }
        
        try {
             validationRace( race );
        } catch ( Exception e ) {
            setErreur( CHAMPS_RACE, e.getMessage() );
        }
        
        try {
             validationSire( sire );
        } catch ( Exception e ) {
            setErreur( CHAMPS_SIRE, e.getMessage() );
        }
        
        try {
             validationPere( pereCheval );
        } catch ( Exception e ) {
            setErreur( CHAMPS_PERE, e.getMessage() );
        }
        
        try {
             validationMere( mereCheval );
        } catch ( Exception e ) {
            setErreur( CHAMPS_MERE, e.getMessage() );
        }
        
        TypeCheval unTypeCheval = new TypeCheval();
        unTypeCheval.setId(Integer.parseInt(race));
        
        Cheval chevalPere = new Cheval();
        chevalPere.setId(Integer.parseInt(pereCheval));
        
        Cheval chevalMere = new Cheval();
        chevalMere.setId(Integer.parseInt(mereCheval));
        
        unCheval.setNom(nom);
        unCheval.setSexe(sexe);
        unCheval.setDateNaissance(dateNaissance);
        unCheval.setTypeCheval(unTypeCheval);
        unCheval.setSire(sire);
        unCheval.setMere(chevalMere);
        unCheval.setPere(chevalPere);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM CHEVAL OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM CHEVAL PAS OK");
        }
               
       
        return unCheval ;
    }
    
    public Cheval modifierCheval( HttpServletRequest request ) {
      System.out.println("COUCOU LES PTITS LOUPS");
        Cheval unCheval  = new Cheval();
        int idCheval = Integer.parseInt(getDataForm(request, "idCheval"));
        String nom = getDataForm( request, "nom" );
        String sexe = getDataForm( request, "sexe");
        String dateNaissance = getDataForm( request, "dateNaissance");
        String race = getDataForm( request, "race");
        String sire = getDataForm( request, "sire");
        String pereCheval = getDataForm( request, "chevalPere");
        String mereCheval = getDataForm( request, "chevalMere");
        
              
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
        try {
             validationSexe( sexe );
        } catch ( Exception e ) {
            setErreur( CHAMPS_SEXE, e.getMessage() );
        }
        
        try {
             validationDateNaissance( dateNaissance );
        } catch ( Exception e ) {
            setErreur( CHAMPS_DATE, e.getMessage() );
        }
        
        try {
             validationRace( race );
        } catch ( Exception e ) {
            setErreur( CHAMPS_RACE, e.getMessage() );
        }
        
        try {
             validationSire( sire );
        } catch ( Exception e ) {
            setErreur( CHAMPS_SIRE, e.getMessage() );
        }
        
        try {
             validationPere( pereCheval );
        } catch ( Exception e ) {
            setErreur( CHAMPS_PERE, e.getMessage() );
        }
        
        try {
             validationMere( mereCheval );
        } catch ( Exception e ) {
            setErreur( CHAMPS_MERE, e.getMessage() );
        }
        
        TypeCheval unTypeCheval = new TypeCheval();
        unTypeCheval.setId(Integer.parseInt(race));
        
        Cheval chevalPere = new Cheval();
        chevalPere.setId(Integer.parseInt(pereCheval));
        
        Cheval chevalMere = new Cheval();
        chevalMere.setId(Integer.parseInt(mereCheval));
        
        unCheval.setId(idCheval);
        unCheval.setNom(nom);
        unCheval.setSexe(sexe);
        unCheval.setDateNaissance(dateNaissance);
        unCheval.setTypeCheval(unTypeCheval);
        unCheval.setSire(sire);
        unCheval.setMere(chevalMere);
        unCheval.setPere(chevalPere);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM CHEVAL OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM CHEVAL PAS OK");
        }
               
       
        return unCheval ;
    }
    
    public Cheval supprimerCheval( HttpServletRequest request) {
      
        Cheval unCheval  = new Cheval();
        int codeCheval = Integer.parseInt(getDataForm(request, "idCheval"));
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
        unCheval.setId(codeCheval);
               
       
        return unCheval ;
    }
    
    public Cheval gererCheval( HttpServletRequest request) {
      
        Cheval unCheval  = new Cheval();
        int codeCheval = Integer.parseInt(getDataForm(request, "idCheval"));
        String typeGestion = getDataForm( request, "typeGestion");
        System.out.println("TYPE GESTION :"+typeGestion);
        
        try {
             validationTypeGestion( typeGestion );
        } catch ( Exception e ) {
            System.out.println("OUPS GESTION");
            setErreur( "typeGestion", e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la gestion.";
        } else {
            resultat = "Échec de la gestion.";
        }
        
        System.out.println("RESULTAT :"+resultat);
        unCheval.setId(codeCheval);
               
       
        return unCheval ;
    }
    
}
