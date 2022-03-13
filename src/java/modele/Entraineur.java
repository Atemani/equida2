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
public class Entraineur {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String copos;
    private String ville;
    private String telephone;
    private int nbChevaux;
    private int nbVictoires;
    private ArrayList<Cheval> listeChevauxEntraines;

    public Entraineur() {
    }

    public Entraineur(int id, String nom, String prenom, String adresse, String copos, String ville, String telephone, int nbChevaux, int nbVictoires) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.copos = copos;
        this.ville = ville;
        this.telephone = telephone;
        this.nbChevaux = nbChevaux;
        this.nbVictoires = nbVictoires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCopos() {
        return copos;
    }

    public void setCopos(String copos) {
        this.copos = copos;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNbChevaux() {
        return nbChevaux;
    }

    public void setNbChevaux(int nbChevaux) {
        this.nbChevaux = nbChevaux;
    }

    public int getNbVictoires() {
        return nbVictoires;
    }

    public void setNbVictoires(int nbVictoires) {
        this.nbVictoires = nbVictoires;
    }

    public ArrayList<Cheval> getListeChevauxEntraines() {
        return listeChevauxEntraines;
    }

    public void setListeChevauxEntraines(ArrayList<Cheval> listeChevauxEntraines) {
        this.listeChevauxEntraines = listeChevauxEntraines;
    }
    
    public void addUnCheval(Cheval unCheval){
        if (listeChevauxEntraines == null){
            listeChevauxEntraines = new ArrayList<Cheval>();
        }
        listeChevauxEntraines.add(unCheval);
    }
    
}
