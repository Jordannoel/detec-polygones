package com.epsi.guez.detecpolygones;

import com.epsi.guez.detecpolygones.base.Faits;
import com.epsi.guez.detecpolygones.base.Regles;
import com.epsi.guez.detecpolygones.model.Polygone;
import com.epsi.guez.detecpolygones.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MoteurInferences {

    private final static String REDIRECT = "redirect:";

    private Regles regles;
    private Faits faits;
    private FormValidator formValidator;

    @Autowired
    public MoteurInferences(Regles regles, Faits faits, FormValidator formValidator) {
        this.regles = regles;
        this.faits = faits;
        this.formValidator = formValidator;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToIndex() {
        return REDIRECT + "/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String afficherIndex(ModelMap modelMap) {
        modelMap.addAttribute("polygone", new Polygone());
        return "/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
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

            Polygone p = new Polygone(nbCotesInt, nbCotesMemeTailleInt, nbAnglesDroitsInt, nbCotesParallelesInt);
            String description = faits.getFait(p);
            if (!description.equals("")) {
                p.setMessage(description);
            } else {
                regles.setPolygone(p);
                p = regles.getTypePolygone();
                faits.ajouterFait(p);
            }
            model.addAttribute("polygone", p);
        } catch (Exception e) {
            model.addAttribute("polygone", new Polygone());
            model.addAttribute("erreur", e.getMessage());
        }
        return "/index";
    }
}
