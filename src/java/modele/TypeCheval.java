/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import modele.Cheval;

/**
 *
 * @author bastu
 */
public class TypeCheval {
    private int id;
    private String libelle;
    private String description;
    private ArrayList<Cheval> listeChevaux;

    public TypeCheval() {
    }

    public TypeCheval(int id, String libelle, String description, ArrayList<Cheval> lesChevaux) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.listeChevaux = lesChevaux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Cheval> getLesChevaux() {
        return listeChevaux;
    }

    public void setLesChevaux(ArrayList<Cheval> lesChevaux) {
        this.listeChevaux = lesChevaux;
    }
    
    public void addUnCheval(Cheval unCheval){
        
        if (this.listeChevaux == null){
            this.listeChevaux = new ArrayList<Cheval>();                  
        }
         listeChevaux.add(unCheval);
    }
    
}
