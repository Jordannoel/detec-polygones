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
    private String urlImage;

    public Polygone() {
    }

    public Polygone(int nbCotes, int nbCotesMemeTaille, int nbAnglesDroits, int nbCotesParalleles) {
        this.nbCotes = nbCotes;
        this.nbCotesMemeTaille = nbCotesMemeTaille;
        this.nbAnglesDroits = nbAnglesDroits;
        this.nbCotesParalleles = nbCotesParalleles;
        this.message = "Forme inconnue.";
        this.urlImage = "/";
    }

    public boolean isTriangle() {
        return this.getNbCotes() == 3;
    }

    public boolean isQuadrillatere() {
        return this.getNbCotes() == 4;
    }

    public boolean isPentagone() {
        return this.getNbCotes() == 5;
    }

    public boolean isTriangleRectangle() {
        return this.isTriangle() && this.getNbAnglesDroits() == 1;
    }

    public boolean isTriangleIsocele() {
        return this.isTriangle() && this.getNbCotesMemeTaille() == 2;
    }

    public boolean isTriangleRectangleIsocele() {
        return this.isTriangle()
                && this.isTriangleIsocele()
                && this.isTriangleRectangle();
    }

    public boolean isTriangleEquilateral() {
        return this.isTriangle() && this.getNbCotesMemeTaille() == 3;
    }

    public boolean isTriangleQuelconque() {
        return this.isTriangle()
                && !this.isTriangleRectangle()
                && !this.isTriangleIsocele()
                && !this.isTriangleEquilateral();
    }
}
