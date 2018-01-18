package com.example.simon.dycard.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Simon on 05/01/2017.
 */

public class Commande {

    private User user;
    private String forme;
    private Bitmap photo;
    private String format;
    private String texte;
    private List<Integer> destinataires;
    private double prix;
    private int formes;

    public Commande() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFormes() {
        return formes;
    }

    public void setFormes(int formes) {
        this.formes = formes;
    }
    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public List<Integer> getDestinataires() {
        return destinataires;
    }

    public void setDestinataires(List<Integer> destinataires) {
        this.destinataires = destinataires;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double commandePrix(){
        int nbDest;

        if(destinataires.size()<1)
            nbDest = 1;
        else
            nbDest = destinataires.size();

        return prix*nbDest;
    }
}
