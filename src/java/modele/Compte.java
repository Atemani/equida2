/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author bastu
 */
public class Compte {
    private int id;
    private String identifiant;
    private String mdp;
    private Role role;
    private boolean connexion;

    public Compte() {
    }

    public Compte(int id, String identifiant, String mdp, Role role) {
        this.id = id;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isConnexion() {
        return connexion;
    }

    public void setConnexion(boolean connexion) {
        this.connexion = connexion;
    }
    
    
}
