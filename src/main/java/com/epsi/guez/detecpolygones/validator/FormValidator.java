package com.epsi.guez.detecpolygones.validator;

import com.epsi.guez.detecpolygones.exceptions.MyException;
import org.springframework.stereotype.Component;

@Component
public class FormValidator {

    public void validate(String nbCotes, String nbCotesMemeTaille, String nbAnglesDroits, String nbCotesParalleles) throws MyException {
        if (nbCotes.equals("") || nbCotesMemeTaille.equals("") || nbAnglesDroits.equals("") || nbCotesParalleles.equals("")) {
            throw new MyException("Un des champs est vide, merci de le remplir.");
        }

        int nbCotesInt = Integer.parseInt(nbCotes);
        int nbCotesMemeTailleInt = Integer.parseInt(nbCotesMemeTaille);
        int nbAnglesDroitsInt = Integer.parseInt(nbAnglesDroits);
        int nbCotesParallelesInt = Integer.parseInt(nbCotesParalleles);

        if (nbCotesInt < nbCotesMemeTailleInt) {
            throw new MyException("Le nombre de côtés de même taille ne peut pas être supérieur au nombre de côtés.");
        }
        if (nbCotesInt < nbAnglesDroitsInt) {
            throw new MyException("Le nombre d'angles droits ne peut pas être supérieur au nombre de côtés.");
        }
        if (nbCotesInt < nbCotesParallelesInt) {
            throw new MyException("Le nombre de côtés parallèles ne peut pas être supérieur au nombre de côtés.");
        }
        if (nbCotesMemeTailleInt == 1) {
            throw new MyException("Une figure ne peut pas possèder un seul côté de même taille.");
        }
        if (nbCotesParallelesInt == 1) {
            throw new MyException("Une figure ne peut pas possèder un seul côté parallèle.");
        }
        switch (nbCotesInt) {
            case 3:
                if (nbAnglesDroitsInt > 1) {
                    throw new MyException("Un triangle ne peut pas avoir plus d'un angle droit.");
                }
                if (nbCotesParallelesInt > 0) {
                    throw new MyException("Un triangle ne peut pas contenir deux côtés parallèles.");
                }
                break;
            case 4:
                if (nbAnglesDroitsInt == 3) {
                    throw new MyException("Un quadrillatère ne peut pas avoir trois angles droits.");
                }
                if(nbAnglesDroitsInt == 2 && nbCotesMemeTailleInt == 3) {
                    throw new MyException("Un quadrillatère ne peut pas posséder deux angles droits et trois côtés de même taille simultanément.");
                }
                break;
            case 5:
                if (nbAnglesDroitsInt == 4) {
                    throw new MyException("Un pentagone ne peut pas avoir quatre angles droits.");
                }
                break;
            default:
                break;
        }
    }
}
