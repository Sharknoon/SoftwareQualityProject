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
                .filter(s -> s.getName().toLowerCase().contains(string.toLowerCase())
                || s.name().toLowerCase().contains(string.toLowerCase()))
                .findAny()
                .isPresent();
    }

    public static Salutations getSalutation(String string) {
        return EnumSet
                .allOf(Salutations.class)
                .stream()
                .filter(s -> s.getName().toLowerCase().contains(string.toLowerCase())
                || s.name().toLowerCase().contains(string.toLowerCase()))
                .findFirst()
                .orElse(Salutations.NOT_SPECIFIED);
    }

    public static Genders getGender(String string) {
        return getSalutation(string).getGender();
    }

}
