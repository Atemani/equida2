/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Course;
import modele.Lieu;

/**
 *
 * @author slam
 */
public class CoursesForm {
    
private String resultat;
    private static final String CHAMPS_NOM = "nom";
    private static final String CHAMPS_DATEDEBUT = "dateDebut";
    private static final String CHAMPS_LIEU = "lieu";
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
    private void validationDate( String desc ) throws Exception {
        if ( desc != null && desc.length() < 10 ) {
        throw new Exception( "La date doit contenir plus de 10 caractères." );
        }
    }
    
    private void validationLieu( int lieu ) throws Exception {
        if ( lieu==0) {
        throw new Exception( "Le lieu doit contenir au moins 3 caractères." );
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
    
    public Course ajouterCourse( HttpServletRequest request ) {
      
        Course uneCourse  = new Course();
         
        String nom = getDataForm( request, "nom" );
        System.out.println("NOM COURSE :"+nom);
        String date = getDataForm( request, "date");
        System.out.println("DATE COURSE :"+date);
        int lieu = Integer.parseInt(getDataForm( request, "lieu"));
        System.out.println("LIEU COURSE :"+lieu);
        
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            System.out.println("OUPS NOM");
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
        try {
             validationDate(date);
        } catch ( Exception e ) {
            System.out.println("OUPS date");
            setErreur( CHAMPS_DATEDEBUT, e.getMessage() );
        }

        try {
             validationLieu(lieu);
        } catch ( Exception e ) {
            System.out.println("OUPS lieu");
            setErreur( CHAMPS_LIEU, e.getMessage() );
        }
        
        Lieu unLieu = new Lieu();
        unLieu.setId(lieu);
        uneCourse.setLieu(unLieu);       
        uneCourse.setLibelle(nom);
        uneCourse.setDate(date);


        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM COURSE OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM COURSE PAS OK");
        }
        return uneCourse ;
    }
    
    public Course modifierCourse( HttpServletRequest request ) {
      
        Course uneCourse  = new Course();
        
        int idCourse = Integer.parseInt(getDataForm(request, "idCourse"));
        String nom = getDataForm( request, "nom" );
        System.out.println("NOM COURSE :"+nom);
        String date = getDataForm( request, "date");
        System.out.println("DATE COURSE :"+date);
        int lieu = Integer.parseInt(getDataForm( request, "lieu"));
        System.out.println("LIEU COURSE :"+lieu);
        
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            System.out.println("OUPS NOM");
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
        
        try {
             validationDate(date);
        } catch ( Exception e ) {
            System.out.println("OUPS date");
            setErreur( CHAMPS_DATEDEBUT, e.getMessage() );
        }

        try {
             validationLieu(lieu);
        } catch ( Exception e ) {
            System.out.println("OUPS lieu");
            setErreur( CHAMPS_LIEU, e.getMessage() );
        }

        Lieu unLieu = new Lieu();
        unLieu.setId(lieu);
        uneCourse.setLieu(unLieu);              
        uneCourse.setId(idCourse);
        uneCourse.setLibelle(nom);
        uneCourse.setDate(date);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM COURSE OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM COURSE PAS OK");
        }
        return uneCourse ;
    }
    
    public Course supprimerCourse( HttpServletRequest request) {
      
        Course uneCourse  = new Course();
        int idCourse = Integer.parseInt(getDataForm(request, "idCourse"));
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
        uneCourse.setId(idCourse);
               
       
        return uneCourse ;
    }
	
}
