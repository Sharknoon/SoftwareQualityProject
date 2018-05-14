/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamcoffee.softwarequalityproject.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse in der die Adelstitel gespeichert werden
 * 
 * Eine Klasse statt ein enum, damit es dynamisch erweitert werden kann
 * (Lernfunktion)
 *
 * @author frank
 */
public class NobilityTitles {

    private final Set<String> nobilityTitles = new HashSet<>();
    
    public NobilityTitles() {
        //Vorgelernte Ausdruecke
        nobilityTitles.add("Kaiser");
        nobilityTitles.add("Kaiserin");
        nobilityTitles.add("König");
        nobilityTitles.add("Königin");
        nobilityTitles.add("Erzherzog");
        nobilityTitles.add("Erzherzogin");
        nobilityTitles.add("Großherzog");
        nobilityTitles.add("Großherzogin");
        nobilityTitles.add("Kurfürst");
        nobilityTitles.add("Kurfürstin");
        nobilityTitles.add("Herzog");
        nobilityTitles.add("Herzogin");
        nobilityTitles.add("Landgraf");
        nobilityTitles.add("Landgräfin");
        nobilityTitles.add("Pfalzgraf");
        nobilityTitles.add("Pfalzgräfin");
        nobilityTitles.add("Markgraf");
        nobilityTitles.add("Markgräfin");
        nobilityTitles.add("Fürst");
        nobilityTitles.add("Fürstin");
        nobilityTitles.add("Graf");
        nobilityTitles.add("Gräfin");
        nobilityTitles.add("Freiherr");
        nobilityTitles.add("Freifrau");
        nobilityTitles.add("Baron");
        nobilityTitles.add("Baronin");
        nobilityTitles.add("Ritter");
        nobilityTitles.add("Edler");
        nobilityTitles.add("Edle");
        nobilityTitles.add("Prinz");
        nobilityTitles.add("Prinzessin");
        nobilityTitles.add("Kronprinz");
        nobilityTitles.add("Kronprinzessin");
        nobilityTitles.add("Großfürst");
        nobilityTitles.add("Großfürstin");
        nobilityTitles.add("Erzherzog");
        nobilityTitles.add("Erzherzogin");
        nobilityTitles.add("Erbgroßherzog");
        nobilityTitles.add("Erbgroßherzogin");
        nobilityTitles.add("Kurprinz");
        nobilityTitles.add("Kurprinzessin");
        nobilityTitles.add("Erbgraf");
        nobilityTitles.add("Erbgräfin");
        nobilityTitles.add("Komtess");
        nobilityTitles.add("Freiin");
        nobilityTitles.add("Baronesse");
        nobilityTitles.add("Junker von");
        nobilityTitles.add("Junkfrau von");
        //Englisch
        nobilityTitles.add("Emperor");
        nobilityTitles.add("Empress ");
        nobilityTitles.add("King");
        nobilityTitles.add("Queen");
        nobilityTitles.add("Archduke");
        nobilityTitles.add("Archduchess");
        nobilityTitles.add("Grand Duke");
        nobilityTitles.add("Grand Duchess");
        nobilityTitles.add("Duke");
        nobilityTitles.add("Duchess");
        nobilityTitles.add("Elector");
        nobilityTitles.add("Electoral prince");
        nobilityTitles.add("Prince");
        nobilityTitles.add("Princess");
        nobilityTitles.add("Baroness");
        nobilityTitles.add("Marquess");
        nobilityTitles.add("Margrave");
        nobilityTitles.add("Marchioness");
        nobilityTitles.add("Margravine");
        nobilityTitles.add("Earl");
        nobilityTitles.add("Count");
        nobilityTitles.add("Countess");
        nobilityTitles.add("Palsgrave");
        nobilityTitles.add("Landgrave");
        nobilityTitles.add("Landgravine");
        nobilityTitles.add("Raugrave");
        nobilityTitles.add("Wildgrave");
        nobilityTitles.add("Knight");
        nobilityTitles.add("Nobleman");
        nobilityTitles.add("Sir");
        nobilityTitles.add("Dame");
        //Französisch
        nobilityTitles.add("Empereur");
        nobilityTitles.add("Imperatrice");
        nobilityTitles.add("Roi");
        nobilityTitles.add("Reine");
        nobilityTitles.add("Archiduc");
        nobilityTitles.add("Archiduchesse");
        nobilityTitles.add("Grand Duc");
        nobilityTitles.add("Grande Duchesse");
        nobilityTitles.add("Duc");
        nobilityTitles.add("Duchesse");
        nobilityTitles.add("Grand Prince");
        nobilityTitles.add("Grand Duc");
        nobilityTitles.add("Grande Duchesse");
        nobilityTitles.add("Prince électeur");
        nobilityTitles.add("Princesse");
        nobilityTitles.add("Margrave");
        nobilityTitles.add("Marquis");
        nobilityTitles.add("Marquise");
        nobilityTitles.add("Comte");
        nobilityTitles.add("Comtesse");
        nobilityTitles.add("Comte Palatin");
        nobilityTitles.add("Comtesse Palatine");
        nobilityTitles.add("Comte du Saint Empire");
        nobilityTitles.add("Comtesse du Saint Empire");
        nobilityTitles.add("Altgrave");
        nobilityTitles.add("Burgrave");
        nobilityTitles.add("Landgrave");
        nobilityTitles.add("Raugrave");
        nobilityTitles.add("Rhingrave");
        nobilityTitles.add("Waldgrave");
        nobilityTitles.add("Wildgrave");
        nobilityTitles.add("Baronne");
        nobilityTitles.add("Chevalier");
        nobilityTitles.add("Equité");
        nobilityTitles.add("écuyer");
        nobilityTitles.add("Sieur");
        nobilityTitles.add("Seigneur");
    }

    /**
     * Gibt die gespeicherten Adelstitel zurück
     * @return Die gespeicherten Titel
     */
    public Set<String> getNobilityTitles() {
        return nobilityTitles;
    }

}
