package modele;


import java.util.ArrayList;
import modele.Client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bastu
 */
public class Cheval {
    private int id;
    private String nom;
    private String sexe;
    private String dateNaissance;
    private Cheval pere;
    private Cheval mere;
    private TypeCheval typeCheval;
    private String sire;
    private Client client;
    private ArrayList<Lot> listeLots;
    private ArrayList<Participer> listeParticipation;
    private int archive;
    private int active;
    private Entraineur unEntraineur;
    
    
    public Cheval() {
    }

    public Cheval(int id, String nom, String sexe, String dateNaissance, String sire) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.sire = sire;
    }
    
    
    
    public Cheval(int id, String nom, String sexe, String dateNaissance, TypeCheval typeCheval, String sire, Client client) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.typeCheval = typeCheval;
        this.sire = sire;
        this.client = client;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Cheval getPere() {
        return pere;
    }

    public void setPere(Cheval pere) {
        this.pere = pere;
    }

    public Cheval getMere() {
        return mere;
    }

    public void setMere(Cheval mere) {
        this.mere = mere;
    }

    
    
    public TypeCheval getTypeCheval() {
        return typeCheval;
    }

    public void setTypeCheval(TypeCheval typeCheval) {
        this.typeCheval = typeCheval;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

     
    
    public void setlisteLots(ArrayList<Lot> listeLots) {
        this.listeLots = listeLots;
    }
    
    public void addLot(Lot unLot){
        if(this.listeLots == null){
            this.listeLots = new ArrayList<Lot>();
        }else{
            this.listeLots.add(unLot);
        }
    }
    
            public void setlisteParticipation(ArrayList<Participer> listeParticipation) {
        this.listeParticipation = listeParticipation;
    }
    
    public void addParticipation(Participer uneParticipation){
        if(this.listeParticipation == null){
            this.listeParticipation = new ArrayList<Participer>();
        }else{
            this.listeParticipation.add(uneParticipation);
        }
    }

    public ArrayList<Lot> getListeLots() {
        return listeLots;
    }

    public void setListeLots(ArrayList<Lot> listeLots) {
        this.listeLots = listeLots;
    }

    public ArrayList<Participer> getListeParticipation() {
        return listeParticipation;
    }

    public void setListeParticipation(ArrayList<Participer> listeParticipation) {
        this.listeParticipation = listeParticipation;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Entraineur getUnEntraineur() {
        return unEntraineur;
    }

    public void setUnEntraineur(Entraineur unEntraineur) {
        this.unEntraineur = unEntraineur;
    }
   
}
