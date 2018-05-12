/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamcoffee.softwarequalityproject.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Eine Klasse statt ein enum, damit es dynamisch erweitert werden kann
 * (Lernfunktion)
 *
 * @author frank
 */
public class Titles {

    private final Set<String> titles = new HashSet<>();

    public Set<String> getTitles() {
        return titles;
    }

}
