package teamcoffee.softwarequalityproject.enums;

/**
 *
 * @author Josua Frank
 */

// Erstellung von Geschlechts-Enums zur besseren Verarbeitung des Eingabestrings
// Zwie dessen Get bzw. Set Methoden
public enum Genders {
    NOT_SPECIFIED("Nicht angegeben"),
    MALE("Männlich"),
    FEMALE("Weiblich"),
    X("Andere");

    private final String name;

    private Genders(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
