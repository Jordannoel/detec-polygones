package com.epsi.guez.detecpolygones.validator;

import com.epsi.guez.detectionpolygones.exceptions.MyException;

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
    }
}
