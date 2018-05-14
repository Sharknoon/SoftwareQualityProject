package teamcoffee.softwarequalityproject.checkers;

import java.util.EnumSet;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Salutations;

/**
 *
 * @author Josua Frank
 */
public class Salutation {
    
    
    // Überprüfung des Strings auf eine mögliche Anrede
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
    
    // Bei gefundenen Anrede 
    // Zur Weiterverarbeitung des Anrede-Strings
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
    
    // Erhalten eines Geschlechts-Strings für die Weiterferarbeitung
    public static Genders getGender(String string) {
        return getSalutation(string).getGender();
    }

}
