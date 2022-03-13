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
import modele.Lieu;
import modele.Vente;
import modele.CategVente;
import modele.Courriel;

/**
 *
 * @author Quentin
 */
public class VentesForm {
    
    private String resultat;
    
    //CHAMPS AJOUT/MODIF VENTE
    private static final String CHAMPS_NOM = "nom";
    private static final String CHAMPS_DATEDEBUT = "dateDebut";
    private static final String CHAMPS_LIEU = "lieu";
    private static final String CHAMPS_CATEG = "categ";
    
    //CHAMPS AJOUT/MODIF CATEG VENTE
    private static final String CHAMPS_CODE = "code";
    private static final String CHAMPS_NOMCATEG = "nomCateg";
    
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
			System.out.println("Nom pas assez long");
        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        
		}
			
		
    }
	
	private void validationDate( String dateDeb ) throws Exception {
        if ( dateDeb != null && dateDeb.length() != 10 ) {
            System.out.println("Date pas conforme");
            throw new Exception( "La date doit correspondre au format." );
		
        }
    }
	
	private void validationCateg( String categVente ) throws Exception {
        if ( categVente == null ) {
            System.out.println("Catégorie non sélectionnée");
            throw new Exception( "Vous devez selectionner une catégorie." );
        }
    }

	private void validationOptionSuppression( String optionSuppression ) throws Exception {
        if ( optionSuppression == null) {
        throw new Exception( "Il faut sélectionner une des deux options...");
        }
    }
        private void validationCode (String code) throws Exception {
        if ( code == null || code.length()< 3){
            throw new Exception("Le code doit comporter 3 caractères minimum...");
        }
    }
        private void validationLibelle (String libelle) throws Exception {
        if (libelle == null || libelle.length() < 3) {
            throw new Exception("Le nom de la catégorie doit comporter 3 caractères minimum...");
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
    
    
    public CategVente ajouterCateg( HttpServletRequest request) {
        CategVente uneCateg = new CategVente();
        
        String code = getDataForm(request, "code");
        String libelle = getDataForm(request, "libelle");

        try {
             validationCode( code );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CODE, e.getMessage() );
            System.out.println("OUPS CODE");
        }
        
        try {
             validationLibelle( libelle );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOMCATEG, e.getMessage() );
            System.out.println("OUPS LIBELLE");
        }
        
        uneCateg.setCode(code);
        uneCateg.setLibelle(libelle);
        
    return uneCateg;
    }
    
    public Vente ajouterVente( HttpServletRequest request ) {
        
        System.out.println("AJOUTER VENTE FORM");
      
        Vente uneVente  = new Vente();
        
        String nom = getDataForm( request, "nom" );
	String dateDeb = getDataForm(request, "dateDebut");
        String lieu = getDataForm( request, "lieu");
        String categVente = getDataForm(request, "categ");

        Lieu unLieu = new Lieu();
	int codeLieu = Integer.parseInt(lieu);
		
	unLieu.setId(codeLieu);		
	CategVente uneCategVente = new CategVente();
	uneCategVente.setCode(categVente);

        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
            System.out.println("OUPS NOM");
        }
	
        try {
             validationDate( dateDeb );
             
        } catch ( Exception e ) {
            setErreur( CHAMPS_DATEDEBUT, e.getMessage() );
            System.out.println("OUPS DATE");
        }
        
        try {
             validationCateg( categVente );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CATEG, e.getMessage() );
            System.out.println("OUPS CATEG");
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
	uneVente.setNom(nom);
        uneVente.setDateDebutVente(dateDeb);
        uneVente.setUnLieu(unLieu);
        uneVente.setUneCategVente(uneCategVente);
               
       
        return uneVente ;
    }
    
    public Vente modifierVente( HttpServletRequest request ) {
      
        Vente uneVente = new Vente();
        int idVente = Integer.parseInt(getDataForm(request, "idVente"));
        String nom = getDataForm( request, "nom" );
	String dateDeb = getDataForm(request, "date");
        String lieu = getDataForm( request, "lieu");
        String categVente = getDataForm(request, "categ");
        
	System.out.println("CODE LIEU "+lieu+"d");
	Lieu unLieu = new Lieu();
	int codeLieu = Integer.parseInt(lieu);
		
	unLieu.setId(codeLieu);		
	CategVente uneCategVente = new CategVente();
	uneCategVente.setCode(categVente);
       
         
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
     
        try {
             validationDate( dateDeb );
        } catch ( Exception e ) {
            setErreur( CHAMPS_DATEDEBUT, e.getMessage() );
        }
        
        try {
             validationCateg( categVente );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CATEG, e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
	uneVente.setId(idVente);
	uneVente.setNom(nom);
        uneVente.setDateDebutVente(dateDeb);
        uneVente.setUnLieu(unLieu);
        uneVente.setUneCategVente(uneCategVente);
               
       
        return uneVente ;
    }
	
    
    public CategVente modifierCateg( HttpServletRequest request ) {
      
        CategVente uneCateg = new CategVente();
        String idCateg = getDataForm(request, "idCateg");
        String libelle = getDataForm( request, "libelle" );
        
        try {
             validationLibelle( libelle );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOMCATEG, e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
	uneCateg.setCode(idCateg);
	uneCateg.setLibelle(libelle);

        return uneCateg ;
    }

    public Vente supprimerVente( HttpServletRequest request, int codeVente) {
      
        Vente uneVente  = new Vente();
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
        uneVente.setId(codeVente);
        
        return uneVente ;
    }
    
         
         
         
         

}
