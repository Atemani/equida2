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
public class Vendeur extends Client{
    
    private ArrayList<Lot> listeLot;
    private Client unClient;

    public Vendeur() {
        super();
    }   

    public Vendeur(ArrayList<Lot> listeLot, int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.listeLot = listeLot;
    }

    public Client getUnClient() {
        return unClient;
    }

    public void setUnClient(Client unClient) {
        this.unClient = unClient;
    }

    public ArrayList<Lot> getListeLot() {
        return listeLot;
    }

    public void setListeLot(ArrayList<Lot> listeLot) {
        this.listeLot = listeLot;
    }
    
    
    
    
    
}
