/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author bastu
 */
public class PieceJointe {
    private int Id;
    private String Chemin;
    private String Description;
    private ArrayList<Courriel> listeCourriel;

    public PieceJointe(int Id, String Chemin, String Description) {
        this.Id = Id;
        this.Chemin = Chemin;
        this.Description = Description;
    }

    public PieceJointe() {
    }

    public int getId() {
        return Id;
    }

    public String getChemin() {
        return Chemin;
    }

    public String getDescription() {
        return Description;
    }

    public ArrayList<Courriel> getListeCourriel() {
        return listeCourriel;
    }
    
    
    

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setChemin(String Chemin) {
        this.Chemin = Chemin;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setListeCourriel(ArrayList<Courriel> listeCourriel) {
        this.listeCourriel = listeCourriel;
    }
    
    public void addCourriel(Courriel unCourriel){
        
        if (this.listeCourriel == null){
            this.listeCourriel = new ArrayList<Courriel>();                  
        }
         listeCourriel.add(unCourriel);
    }
    
    
}
