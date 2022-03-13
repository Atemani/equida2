/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Zakina
 */
public class Vente {
    private int id;
    private String nom;
    private String dateDebutVente;
    private Lieu unLieu;
    private CategVente uneCategVente;
    private ArrayList<Courriel> listeCourriel;
    private ArrayList<Lot> listeLot;
	private int archive;

    public Vente() {
    }

    public Vente(int id, String nom, String dateDebutVente) {
        this.id = id;
        this.nom = nom;
        this.dateDebutVente = dateDebutVente;
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

    public String getDateDebutVente() {
        return dateDebutVente;
    }

    public ArrayList<Courriel> getListeCourriel() {
        return listeCourriel;
    }
    

    public void setDateDebutVente(String dateDebutVente) {
        this.dateDebutVente = dateDebutVente;
    }

    public CategVente getUneCategVente() {
        return uneCategVente;
    }

    public void setUneCategVente(CategVente uneCategVente) {
        this.uneCategVente = uneCategVente;
    }

    public Lieu getUnLieu() {
        return unLieu;
    }

    public void setUnLieu(Lieu unLieu) {
        this.unLieu = unLieu;
    }

    public void setListeCourriel(ArrayList<Courriel> listeCourriel) {
        this.listeCourriel = listeCourriel;
    }
    
    public void addCourriel(Courriel unCourriel){
        if(this.listeCourriel == null){
            this.listeCourriel = new ArrayList<Courriel>();
        }else{
            this.listeCourriel.add(unCourriel);
        }
    }
    
    public void setListeLot(ArrayList<Lot> listeLot) {
        this.listeLot = listeLot;
    }
    
    public void addLot(Lot unLot){
        if(this.listeLot == null){
            this.listeLot = new ArrayList<Lot>();
        }else{
            this.listeLot.add(unLot);
        }
    }

	public int getArchive() {
		return archive;
	}

	public void setArchive(int archive) {
		this.archive = archive;
	}
	
	
    
}
