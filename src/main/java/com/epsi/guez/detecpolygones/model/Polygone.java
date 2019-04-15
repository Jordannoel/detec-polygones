package com.epsi.guez.detecpolygones.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Polygone {

    private int nbCotes;
    private int nbCotesMemeTaille;
    private int nbAnglesDroits;
    private int nbCotesParalleles;
    private String message;

    public Polygone() {
        this.message = "Forme inconnue.";
    }

    public Polygone(int nbCotes, int nbCotesMemeTaille, int nbAnglesDroits, int nbCotesParalleles) {
        this.nbCotes = nbCotes;
        this.nbCotesMemeTaille = nbCotesMemeTaille;
        this.nbAnglesDroits = nbAnglesDroits;
        this.nbCotesParalleles = nbCotesParalleles;
        this.message = "Forme inconnue.";
    }

    /**
     * Constructeur de polygone pour retourner un message (dto)
     * @param message
     */
    public Polygone(String message) {
        this.message = message;
    }
}
