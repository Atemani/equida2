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
public class Courriel {
    
    private int id;
    private String dateEnvoiMessage;
    private String objetMessage;
    private String corpMessage;
    private ArrayList<PieceJointe> listePiecesJointes;

    public Courriel(int id, String dateEnvoiMessage, String ObjetMessage, String CorpMessage) {
        this.id = id;
        this.dateEnvoiMessage = dateEnvoiMessage;
        this.objetMessage = ObjetMessage;
        this.corpMessage = CorpMessage;
    }

    public Courriel() {
    }

    public int getId() {
        return id;
    }

    public String getDateEnvoiMessage() {
        return dateEnvoiMessage;
    }

    public String getObjetMessage() {
        return objetMessage;
    }

    public String getCorpMessage() {
        return corpMessage;
    }

    public ArrayList<PieceJointe> getListePiecesJointes() {
        return listePiecesJointes;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setDateEnvoiMessage(String dateEnvoiMessage) {
        this.dateEnvoiMessage = dateEnvoiMessage;
    }

    public void setObjetMessage(String ObjetMessage) {
        this.objetMessage = ObjetMessage;
    }

    public void setCorpMessage(String CorpMessage) {
        this.corpMessage = CorpMessage;
    }

    public void setListePiecesJointes(ArrayList<PieceJointe> listePiecesJointes) {
        this.listePiecesJointes = listePiecesJointes;
    }
    
    public void addPieceJointe(PieceJointe unePieceJointe){
        
        if (this.listePiecesJointes == null){
            this.listePiecesJointes = new ArrayList<PieceJointe>();                  
        }
         listePiecesJointes.add(unePieceJointe);
    }
}
