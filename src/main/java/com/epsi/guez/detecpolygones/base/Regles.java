package com.epsi.guez.detecpolygones.base;

import com.epsi.guez.detectionpolygones.model.Polygone;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Regles {

    private Polygone polygone;

    @Autowired
    public Regles(Polygone polygone) {
        this.polygone = polygone;
    }

    public Polygone getResultat(int nbCotes, int nbCotesMemeTaille, int nbAnglesDroits, int nbCotesParalleles) {
        Polygone polygone = new Polygone(nbCotes, nbCotesMemeTaille, nbAnglesDroits, nbCotesParalleles);
        if (polygone.isTriangleRectangle()) {
            polygone.setMessage("Triangle rectangle");
        }
        if (polygone.isTriangleIsocele()) {
            polygone.setMessage("Triangle isocèle");
        }
        if (polygone.isTriangleEquilateral()) {
            polygone.setMessage("Triangle équilateral");
        }
        if (polygone.isTriangleRectangleIsocele()) {
            polygone.setMessage("Triangle rectangle isocèle");
        }
        if (polygone.isTriangleQuelconque()) {
            polygone.setMessage("Triangle quelconque");
        }
        return polygone;
    }

}
