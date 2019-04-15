package com.epsi.guez.detecpolygones.base;

import com.epsi.guez.detecpolygones.model.Polygone;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Regles {

    private Polygone polygone;

    @Autowired
    public Regles(Polygone polygone) {
        this.polygone = polygone;
    }

    public Polygone getTypePolygone() {
        if (isTriangle()) {
            polygone.setMessage("Triangle quelconque.");
            if (isTriangleRectangle()) {
                polygone.setMessage("Triangle rectangle.");
            }
            if (isTriangleIsocele()) {
                polygone.setMessage("Triangle isocèle.");
            }
            if (isTriangleEquilateral()) {
                polygone.setMessage("Triangle équilateral.");
            }
            if (isTriangleRectangleIsocele()) {
                polygone.setMessage("Triangle rectangle isocèle.");
            }
        }
        if (isQuadrillatere()) {
            polygone.setMessage("Quadrillatère quelconque.");
            if (isTrapeze()) {
                polygone.setMessage("Trapèze.");
            }
            if (isParallelogramme()) {
                polygone.setMessage("Parallélogramme.");
                if (isRectangle()) {
                    polygone.setMessage("Rectangle.");
                }
                if (isCarre()) {
                    polygone.setMessage("Carré.");
                }
                if (isLosange()) {
                    polygone.setMessage("Losange.");
                }
            }
        }

        if(isPentagone()) {
            polygone.setMessage("Pentagone quelconque.");
            if(isPentagramme()) {
                polygone.setMessage("Pentagramme.");
            }
        }

        return polygone;
    }

    //<editor-fold desc=" verifs triangle">
    private boolean isTriangle() {
        return polygone.getNbCotes() == 3;
    }

    private boolean isTriangleRectangle() {
        return polygone.getNbAnglesDroits() == 1;
    }

    private boolean isTriangleIsocele() {
        return polygone.getNbCotesMemeTaille() == 2
                && polygone.getNbAnglesDroits() == 0;
    }

    private boolean isTriangleRectangleIsocele() {
        return isTriangleIsocele()
                && isTriangleRectangle();
    }

    private boolean isTriangleEquilateral() {
        return polygone.getNbCotesMemeTaille() == 3;
    }
    //</editor-fold>

    //<editor-fold desc=" verifs quadrillatère">
    private boolean isQuadrillatere() {
        return polygone.getNbCotes() == 4;
    }

    private boolean isCarre() {
        return polygone.getNbAnglesDroits() == 4;
    }

    private boolean isRectangle() {
        return polygone.getNbAnglesDroits() == 4;
    }

    private boolean isLosange() {
        return polygone.getNbCotesMemeTaille() == 4;
    }

    private boolean isParallelogramme() {
        return polygone.getNbCotesMemeTaille() == 4
                && polygone.getNbCotesParalleles() == 4;
    }

    private boolean isTrapeze() {
        return polygone.getNbCotesParalleles() == 2;
    }
    //</editor-fold>

    //<editor-fold desc=" verifs pentagone">
    private boolean isPentagone() {
        return polygone.getNbCotes() == 5;
    }

    private boolean isPentagramme() {
        return polygone.getNbCotesMemeTaille() == 5;
    }
    //</editor-fold>


}
