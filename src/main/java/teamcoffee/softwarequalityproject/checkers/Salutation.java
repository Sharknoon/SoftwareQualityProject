package teamcoffee.softwarequalityproject.checkers;

import java.util.EnumSet;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Salutations;

/**
 *
 * @author Josua Frank
 */
public class Salutation {

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

    public static Genders getGender(String string) {
        return getSalutation(string).getGender();
    }

}
