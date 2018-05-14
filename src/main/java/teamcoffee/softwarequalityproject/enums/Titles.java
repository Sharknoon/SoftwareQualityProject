/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamcoffee.softwarequalityproject.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse in der die Titel gespeichert werden
 * 
 * Eine Klasse statt ein enum, damit es dynamisch erweitert werden kann
 * (Lernfunktion)
 *
 * @author frank
 */
public class Titles {

    private final Set<String> titles = new HashSet<>();
    
    public Titles() {
        //Vorgelernte Ausdruecke
        titles.add("Dr.");
        titles.add("Professor");
        titles.add("Prof.");
        titles.add("rer.");
        titles.add("Nat.");
        titles.add("Dipl.");
        titles.add("Ing.");
        titles.add("Dr.-Ing.");
        titles.add("h.c.");
        titles.add("mult.");
    }

    /**
     * Gibt die gespeicherten Titel zurück
     * @return Die gespeicherten Titel
     */
    public Set<String> getTitles() {
        return titles;
    }

}
