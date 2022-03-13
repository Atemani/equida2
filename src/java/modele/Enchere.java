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
public class Enchere {
    
    private int enc_id;
    private int prix;
    private Lot unLot;
    private Acheteur unAcheteur;
    

    public Enchere() {
    }

    public Enchere(int enc_id, int prix) {
        this.enc_id = enc_id;
        this.prix = prix;
    }

    public int getEnc_id() {
        return enc_id;
    }

    public void setEnc_id(int enc_id) {
        this.enc_id = enc_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Lot getUnLot() {
        return unLot;
    }

    public void setUnLot(Lot unLot) {
        this.unLot = unLot;
    }

    public Acheteur getUnAcheteur() {
        return unAcheteur;
    }

    public void setUnAcheteur(Acheteur unAcheteur) {
        this.unAcheteur = unAcheteur;
    }

    
       
}
