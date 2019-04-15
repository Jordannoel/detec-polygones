package com.epsi.guez.detecpolygones.base;

import com.epsi.guez.detecpolygones.model.Polygone;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class Faits {

    private static final String CHEMIN_FICHIER = "faits.txt";

    public String getFait(Polygone p) throws IOException {
        String description = "";
        boolean trouve = false;
        BufferedReader reader = new BufferedReader(new FileReader(CHEMIN_FICHIER));
        String line = reader.readLine();
        while (line != null && !trouve) {
            String[] lineSplitted = line.split(";");
            if (lineSplitted[0].equals(String.valueOf(p.getNbCotes()))
                    && lineSplitted[1].equals(String.valueOf(p.getNbCotesMemeTaille()))
                    && lineSplitted[2].equals(String.valueOf(p.getNbAnglesDroits()))
                    && lineSplitted[3].equals(String.valueOf(p.getNbCotesParalleles()))) {
                description = lineSplitted[4];
                trouve = true;
            }
            line = reader.readLine();
        }
        return description;
    }

    public void ajouterFait(Polygone p) {
        String str = p.getNbCotes() + ";" + p.getNbCotesMemeTaille() + ";" + p.getNbAnglesDroits() + ";" + p.getNbCotesParalleles() + ";" + p.getMessage() + "\n";
        try {
            Files.write(Paths.get(CHEMIN_FICHIER), str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
