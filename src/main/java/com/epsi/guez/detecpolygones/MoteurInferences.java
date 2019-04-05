package com.epsi.guez.detecpolygones;

import com.epsi.guez.detectionpolygones.base.Faits;
import com.epsi.guez.detectionpolygones.base.Regles;
import com.epsi.guez.detectionpolygones.model.Polygone;
import com.epsi.guez.detectionpolygones.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MoteurInferences {

    private Regles regles;
    private Faits faits;
    private FormValidator formValidator;

    @Autowired
    public MoteurInferences(Regles regles, Faits faits) {
        this.regles = regles;
        this.faits = faits;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String afficherIndex(HttpServletRequest req, ModelMap model) {
        model.addAttribute("image", "/img/tri-isocele.jpg");
        return "/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String parseData(HttpServletRequest req, ModelMap model) {
        try {
            String nbCotes = req.getParameter("nbCotes");
            String nbCotesMemeTaille = req.getParameter("nbCotesMemeTaille");
            String nbAnglesDroits = req.getParameter("nbAnglesDroits");
            String nbCotesParalleles = req.getParameter("nbCotesParalleles");

            formValidator.validate(nbCotes, nbCotesMemeTaille, nbAnglesDroits, nbCotesParalleles);

            int nbCotesInt = Integer.parseInt(nbCotes);
            int nbCotesMemeTailleInt = Integer.parseInt(nbCotesMemeTaille);
            int nbAnglesDroitsInt = Integer.parseInt(nbAnglesDroits);
            int nbCotesParallelesInt = Integer.parseInt(nbCotesParalleles);

            regles.setPolygone(new Polygone(nbCotesInt, nbCotesMemeTailleInt, nbAnglesDroitsInt, nbCotesParallelesInt));

            String message = regles.getResultat(nbCotesInt, nbCotesMemeTailleInt, nbAnglesDroitsInt, nbCotesParallelesInt);
            model.addAttribute("message", message);
            model.addAttribute("image", "/img/tri-isocele.jpg");
        } catch (Exception e) {
            model.addAttribute("erreur", e.getMessage());
        }
        return "/index";
    }

    private String getResultatFromReglesEtFaits() {
        return "blablabla";
    }
}
