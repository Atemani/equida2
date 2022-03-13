/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author slam
 */
public class Acheteur extends Client{
    
    private ArrayList<Enchere> listeEnchre;
    private Client unCient;

    public Acheteur() {
    }

    public Client getUnCient() {
        return unCient;
    }

    public void setUnCient(Client unCient) {
        this.unCient = unCient;
    }

    public Acheteur(ArrayList<Enchere> listeEnchre, int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.listeEnchre = listeEnchre;
    }

    public ArrayList<Enchere> getListeEnchre() {
        return listeEnchre;
    }

    public void setListeEnchre(ArrayList<Enchere> listeEnchre) {
        this.listeEnchre = listeEnchre;
    }
       
    
}
