/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamcoffee.softwarequalityproject.checkers;

import teamcoffee.softwarequalityproject.db.DB;

/**
 *  Überprüft Eingaben auf Adelstitel
 * @author frank
 */
public class NobilityTitle {

    private static String nobilityTitle;

    /**
     * Überprüfung des String auf einen möglichen Adelstitel (Freiherr, usw.)
     *
     * @param string Der mögliche Adelstitel
     * @return wahr, wenn dies ein Adelstitel ist, ansonsten falsch
     */
    public static boolean isNobilityTitle(String string) {
        nobilityTitle = string.trim().toLowerCase();
        return DB
                .getNobilityTitles()
                .getNobilityTitles()
                .stream()
                .anyMatch(s -> nobilityTitle.contains(s.toLowerCase()));
    }
}
