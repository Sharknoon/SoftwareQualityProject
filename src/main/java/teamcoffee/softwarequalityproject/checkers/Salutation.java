package teamcoffee.softwarequalityproject.checkers;

import java.util.EnumSet;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Salutations;

/**
 * Überprüft eingaben auf eine mögliche Anrede
 * @author Josua Frank
 */
public class Salutation {

    /**
     * Überprüfung des Strings auf eine mögliche Anrede
     *
     * @param string die mögliche Anrede
     * @return Gibt zurück ob dies eine Anrede ist
     */
    public static boolean isSalutation(String string) {
        return EnumSet
                .allOf(Salutations.class)
                .stream()
                .filter(s -> {
                    String searchString = string.replaceAll("[^a-zA-ZäöüÄÖÜ]*", "").toLowerCase();
                    return s.getName().toLowerCase().contains(searchString)
                            || s.name().toLowerCase().contains(searchString)
                            || s.getAlternativeNames().stream().map(String::toLowerCase).anyMatch(a -> a.contains(searchString));
                })
                .findAny()
                .isPresent();
    }

    /**
     * Bei gefundenen Anrede Zur Weiterverarbeitung des Anrede-Strings
     *
     * @param string die mögliche Anrede
     * @return Die gefundene Anrede oder NOT_SPECIFIED wenn nichts gefunden
     * wurde
     */
    public static Salutations getSalutation(String string) {
        return EnumSet
                .allOf(Salutations.class)
                .stream()
                .filter(s -> {
                    String searchString = string.replaceAll("[^a-zA-ZäöüÄÖÜ]*", "").toLowerCase();
                    return s.getName().toLowerCase().contains(searchString)
                            || s.name().toLowerCase().contains(searchString)
                            || s.getAlternativeNames().stream().map(String::toLowerCase).anyMatch(a -> a.contains(searchString));
                })
                .findFirst()
                .orElse(Salutations.NOT_SPECIFIED);
    }

    /**
     * Erhalten eines Geschlechts für die Weiterverarbeitung
     * @param string Das mögliche Geschlecht
     * @return Das Geschlecht oder NOT_SPECIFIED wenn kein Geschlecht gefunden wurde
     */
    public static Genders getGender(String string) {
        return getSalutation(string).getGender();
    }

}
