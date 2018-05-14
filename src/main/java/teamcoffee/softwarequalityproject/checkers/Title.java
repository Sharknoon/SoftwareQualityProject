package teamcoffee.softwarequalityproject.checkers;

import teamcoffee.softwarequalityproject.db.DB;

/**
 *
 * @author Josua Frank
 */
public class Title {

    private static String title;
    
    // Überprüfung des String auf einen möglichen Titel (Dok., usw.)
    public static boolean isTitle(String string) {
        title = string.trim().toLowerCase();
        return DB
                .getTitles()
                .getTitles()
                .stream()
                .anyMatch(s -> s.toLowerCase().contains(title));
    }

}
