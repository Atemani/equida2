/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import modele.Cheval;
import modele.Lieu;
import modele.Courriel;
import modele.Entraineur;
import modele.PieceJointe;

/**
 *
 * @author Zakina
 */
public class ClientTest {
    
    public static void main(String[] args) {
        
        /*Client unClient = new Client(1, "dupont", "luc");
        unClient.setUnPays(new Pays("FRA", "FRANCE"));    
        System.out.println(unClient.toString());
        System.out.println(unClient.getNom() + "  " + unClient.getUnPays().getNom());*/
        
        /*Lieu unLieu = new Lieu();
        unLieu.setNbBoxes(36);
        unLieu.setVille("Caen");
        System.out.println("Le lieu a "+unLieu.getNbBoxes()+" boxes et il est situé dans la ville de "+unLieu.getVille());
        System.out.println(" ");
        
        Courriel unCourriel = new Courriel();
        unCourriel.setDateEnvoiMessage("09/09/2019");
        unCourriel.setObjetMessage("Un petit bonjour");
        unCourriel.setCorpMessage("Bonjour ! Ce mail n'a aucun intérêt a part vous spam. Cordialement, Equida.");
        System.out.println("Objet : "+unCourriel.getObjetMessage());
        System.out.println("Envoyé le "+unCourriel.getDateEnvoiMessage());
        System.out.println("Contenu du message : "+unCourriel.getCorpMessage());
        
        PieceJointe unePieceJointe = new PieceJointe();
        unePieceJointe.setDescription("Aucun interet.");
        unePieceJointe.setChemin("Le chemin du paradis.");
        PieceJointe unePieceJointe2 = new PieceJointe();
        unePieceJointe2.setDescription("Aucune description.");
        unePieceJointe2.setChemin("Le chemin du paradis.");
        unCourriel.addPieceJointe(unePieceJointe);
        unCourriel.addPieceJointe(unePieceJointe2);
        for(int i = 0; i<unCourriel.getListePiecesJointes().size(); i++){
            System.out.println("Description de la pièce jointe n°"+i+" : "+unCourriel.getListePiecesJointes().get(i).getDescription());
        }*/
        
        Entraineur unEntraineur = new Entraineur();
        unEntraineur.setNom("Leconte");
        unEntraineur.setPrenom("Thomas");
        
        System.out.println("Test getter / setter : "+unEntraineur.getNom());
        
        Cheval unCheval = new Cheval();
        unCheval.setNom("Petit tonnère");
        Cheval unCheval2 = new Cheval();
        unCheval2.setNom("AuGalo");
        
        unEntraineur.addUnCheval(unCheval);
        unEntraineur.addUnCheval(unCheval2);
        
        for(int i=0; i<unEntraineur.getListeChevauxEntraines().size(); i++){
            System.out.println("Cheval entrainé n°"+i+" :"+unEntraineur.getListeChevauxEntraines().get(i).getNom());
        }
        
        unCheval.setUnEntraineur(unEntraineur);
        System.out.println("Le cheval "+unCheval.getNom()+" est entrainé par "+unCheval.getUnEntraineur().getNom()+" "+unCheval.getUnEntraineur().getPrenom());
        
    }
    
}
