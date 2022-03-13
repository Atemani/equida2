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
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class ClientForm {
    
    private String resultat;
    private static final String CHAMPS_NOM = "nom";
    private static final String CHAMPS_PRENOM = "prenom";
    private static final String CHAMPS_MDP = "mdp";
    private static final String CHAMPS_RUE = "rue";
    private static final String CHAMPS_CP = "cp";
    private static final String CHAMPS_MAIL = "mail";
    private static final String CHAMPS_VILLE = "ville";
    private static final String CHAMPS_PAYS = "pays";
    private static final String CHAMPS_CATEG = "categ";
    
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
        if ( nom == null || nom.length() < 3 ) {
        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom == null || prenom.length() < 3 ) {
        throw new Exception( "Le prénom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationRue( String rue ) throws Exception {
        if ( rue != null && rue.length() < 3 ) {
        throw new Exception( "Le nom de la rue doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationCopos( String copos ) throws Exception {
        if ( copos != null && copos.length() < 5 ) {
        throw new Exception( "Le code postale doit contenir au moins 5 caractères." );
        }
    }
    
    private void validationMdp( String mdp ) throws Exception {
        if ( mdp != null && mdp.length() < 3 ) {
        throw new Exception( "Le mot de passe doit contenir au moins 3 caractères.");
        }
    }
    
    private void validationEmail( String email ) throws Exception {
        if ( email != null && email.length() < 5 ) {
        throw new Exception( "L'email doit contenir au moins 5 caractères.");
        }
    }
    
    private void validationVille( String ville ) throws Exception {
        if ( ville != null && ville.length() < 5 ) {
        throw new Exception( "Le nom de la ville doit contenir au moins 5 caractères.");
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
    
    
    public Client ajouterClient( HttpServletRequest request ) {
      
        Client unClient  = new Client();
         
        String nom = getDataForm( request, "nom" );
        String prenom = getDataForm( request, "prenom");
        String mdp = getDataForm( request, "mdp" );
        String rue = getDataForm( request, "rue" );
        String copos = getDataForm( request, "copos");
        String ville = getDataForm( request, "ville" );
        String email = getDataForm( request, "email" );
        String pays = getDataForm( request, "pays" );
        
        // Traitement de la liste à choix multiple
        //Pour chq catégorie selectionné, on instancie une nouvelle catégorie et on l'ajoute au client
        CategVente uneCategVente ;
        String[] categVente = request.getParameterValues("categVente");
        for (int i=0; i<categVente.length; i++){
            uneCategVente = new CategVente();
            uneCategVente.setCode(categVente[i]);
            unClient.addUneCategVente(uneCategVente);
        }

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
     
        try {
            validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_PRENOM, e.getMessage() );
        }
        
        try {
            validationMdp( mdp );
        } catch ( Exception e ) {
            setErreur( CHAMPS_MDP, e.getMessage() );
        }
        
        try {
            validationRue( rue );
        } catch ( Exception e ) {
            setErreur( CHAMPS_RUE, e.getMessage() );
        }
        try {
            validationVille( ville );
        } catch ( Exception e ) {
            setErreur( CHAMPS_VILLE, e.getMessage() );
        }
        
        try {
            validationCopos( copos );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CP, e.getMessage() );
        }
        
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMPS_MAIL, e.getMessage() );
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
        unClient.setNom(nom);      
        unClient.setPrenom(prenom);
        unClient.setRue(rue);
        unClient.setCopos(copos);
        unClient.setMail(email);
        unClient.setVille(ville);
        unClient.setUnPays(new Pays(pays));
        
        return unClient ;
    }
    
    public Client modifierClient( HttpServletRequest request, int codeClient ) {
      
        Client unClient  = new Client();
        
        
        String nom = getDataForm( request, "nom" );
        String prenom = getDataForm( request, "prenom");
        String rue = getDataForm( request, "rue" );
        String copos = getDataForm( request, "copos");
        String ville = getDataForm( request, "ville" );
        String email = getDataForm( request, "email" );
        String pays = getDataForm( request, "pays" );
        
        // Traitement de la liste à choix multiple
        //Pour chq catégorie selectionné, on instancie une nouvelle catégorie et on l'ajoute au client
        CategVente uneCategVente ;
        String[] categVente = request.getParameterValues("categVente");
        for (int i=0; i<categVente.length; i++){
            uneCategVente = new CategVente();
            uneCategVente.setCode(categVente[i]);
            unClient.addUneCategVente(uneCategVente);
        }
         
        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_NOM, e.getMessage() );
        }
     
        try {
            validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( CHAMPS_PRENOM, e.getMessage() );
        }
        
        try {
            validationRue( rue );
        } catch ( Exception e ) {
            setErreur( CHAMPS_RUE, e.getMessage() );
        }
        try {
            validationVille( ville );
        } catch ( Exception e ) {
            setErreur( CHAMPS_VILLE, e.getMessage() );
        }
        
        try {
            validationCopos( copos );
        } catch ( Exception e ) {
            setErreur( CHAMPS_CP, e.getMessage() );
        }
        
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMPS_MAIL, e.getMessage() );
        }
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
        unClient.setId(codeClient);
        unClient.setNom(nom);      
        unClient.setPrenom(prenom);
        unClient.setRue(rue);
        unClient.setCopos(copos);
        unClient.setMail(email);
        unClient.setVille(ville);
        unClient.setUnPays(new Pays(pays));

        return unClient ;
    }

    public Client supprimerClient( HttpServletRequest request, int codeClient) {
      
        Client unClient  = new Client();
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
        unClient.setId(codeClient);
               
       
        return unClient ;
    }

}
