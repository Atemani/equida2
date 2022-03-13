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
public class Course {
    private int id;
    private String libelle;
    private Lieu lieu;
    private String date;
    private ArrayList<Participer> listedesParticipation;
    private int archive;

    public Course() {
    }

    public Course(int id, String libelle, Lieu lieu, String date) {
        this.id = id;
        this.libelle = libelle;
        this.lieu = lieu;
        this.date = date;
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

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
            
    public void setlistedesParticipation(ArrayList<Participer> listedesParticipation) {
        this.listedesParticipation = listedesParticipation;
    }
    
    public void addParticipation(Participer uneParticipation){
        if(this.listedesParticipation == null){
            this.listedesParticipation = new ArrayList<Participer>();
        }else{
            this.listedesParticipation.add(uneParticipation);
        }
    }

    public ArrayList<Participer> getListedesParticipation() {
        return listedesParticipation;
    }

    public void setListedesParticipation(ArrayList<Participer> listedesParticipation) {
        this.listedesParticipation = listedesParticipation;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
    
    
}
