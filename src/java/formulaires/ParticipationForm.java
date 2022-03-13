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
import modele.Course;
import modele.Participer;

/**
 *
 * @author bastu
 */
public class ParticipationForm {
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
        throw new Exception( "Le nom du cheval doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie nom
    private void validationNomCourse( String nomCourse ) throws Exception {
        if ( nomCourse != null && nomCourse.length() < 1 ) {
        throw new Exception( "Veuillez choisir une course." );
        }
    }
    
    //méthode de validation du champ de saisie nom
    private void validationPlace( String place ) throws Exception {
        if ( place != null && place.length() < 1 ) {
        throw new Exception( "Veuillez préciser la place obtenue par votre cheval." );
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
    
    public Participer ajouterUneParticipation( HttpServletRequest request ) {
        
        Participer uneParticipation = new Participer();
        
        String nom = getDataForm( request, "nom" );
        int idCheval = Integer.parseInt(getDataForm( request, "idCheval" ));
        System.out.println("ID CHEVAL :"+idCheval);
        System.out.println("NOM CHEVAL :"+nom);
        String course = getDataForm( request, "course");
        System.out.println("NOM COURSE :"+course);
        String place = getDataForm( request, "place");
        System.out.println("PLACE CHEVAL :"+place);
        
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            System.out.println("OUPS NOM");
            setErreur( "nom", e.getMessage() );
        }
        
        try {
             validationNomCourse(course);
        } catch ( Exception e ) {
            System.out.println("OUPS course");
            setErreur( "description", e.getMessage() );
        }
        
        try {
             validationPlace(place);
        } catch ( Exception e ) {
            System.out.println("OUPS place");
            setErreur( "description", e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
            System.out.println("FORM TYPECHEVAL OK");
        } else {
            resultat = "Échec de l'ajout.";
            System.out.println("FORM TYPECHEVAL PAS OK");
        }
        
        Course uneCourse = new Course();
        uneCourse.setId(Integer.parseInt(course));
        
        Cheval unCheval = new Cheval();
        unCheval.setId(idCheval);
        
        uneParticipation.setUneCourse(uneCourse);
        uneParticipation.setUnCheval(unCheval);
        uneParticipation.setPlace(Integer.parseInt(place));
       
        return uneParticipation ;
    }   
}
