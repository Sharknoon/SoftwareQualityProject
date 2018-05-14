package teamcoffee.softwarequalityproject.enums;

/**
 * Erstellung von Geschlechts-Enums zur besseren Verarbeitung des Eingabestrings
 * Zwie dessen Get bzw. Set Methoden
 *
 * @author Josua Frank
 */
public enum Genders {
    NOT_SPECIFIED("Nicht angegeben"),
    MALE("Männlich"),
    FEMALE("Weiblich"),
    X("Andere");

    private final String name;

    private Genders(String s) {
        this.name = s;
    }

    /**
     * Gibt den Anzeigenamen zurück
     * @return den Anzeigenamen
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
