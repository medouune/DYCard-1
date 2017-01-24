package com.example.simon.dycard.model;

/**
 * Created by Simon on 05/01/2017.
 */

public class User {

    private int id;
    private int nbDestinataire;

    public User() {
    }

    public User(int id, int nbDestinataire){
        this.id = id;
        this.nbDestinataire = nbDestinataire;
    }

    public int getNbDestinataire() {
        return nbDestinataire;
    }

    public void setNbDestinataire(int nbDestinataire) {
        this.nbDestinataire = nbDestinataire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
