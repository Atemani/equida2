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
public class Lot {
    
    private int lot_id;
    private int lot_prixDepart;
    private Cheval unCheval;
    private Vente uneVente;
    private Vendeur unVendeur;
    private ArrayList<Enchere> listeEnchere;

    public Lot() {
    }

    public Lot(int lot_id, int lot_prixDepart) {
        this.lot_id = lot_id;
        this.lot_prixDepart = lot_prixDepart;
    }

    public int getLot_id() {
        return lot_id;
    }

    public void setLot_id(int lot_id) {
        this.lot_id = lot_id;
    }

    public int getLot_prixDepart() {
        return lot_prixDepart;
    }

    public void setLot_prixDepart(int lot_prixDepart) {
        this.lot_prixDepart = lot_prixDepart;
    }

    public Cheval getUnCheval() {
        return unCheval;
    }

    public void setUnCheval(Cheval unCheval) {
        this.unCheval = unCheval;
    }

    public Vente getUneVente() {
        return uneVente;
    }

    public void setUneVente(Vente uneVente) {
        this.uneVente = uneVente;
    }

    public Vendeur getUnVendeur() {
        return unVendeur;
    }

    public void setUnVendeur(Vendeur unVendeur) {
        this.unVendeur = unVendeur;
    }

    public ArrayList<Enchere> getListeEnchere() {
        return listeEnchere;
    }

    public void setListeEnchere(ArrayList<Enchere> listeEnchere) {
        this.listeEnchere = listeEnchere;
    }
    
    
       
     public void setlisteEnchere(ArrayList<Enchere> listeEnchere) {
        this.listeEnchere = listeEnchere;
    }
    
    public void addEnchere(Enchere uneEnchere){
        if(this.listeEnchere == null){
            this.listeEnchere = new ArrayList<Enchere>();
        }else{
            this.listeEnchere.add(uneEnchere);
        }
    }
    
    
    
    
}
