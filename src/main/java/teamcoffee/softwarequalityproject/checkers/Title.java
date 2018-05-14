package teamcoffee.softwarequalityproject.checkers;

import teamcoffee.softwarequalityproject.db.DB;

/**
 * Überprüft Eingaben auf Titel
 * @author Josua Frank
 */
public class Title {

    private static String title;
    
    /**
     * Überprüfung des String auf einen möglichen Titel (Dok., usw.)
     * @param string Der mögliche Titel
     * @return true, if this is a title, false otherwise
     */
    public static boolean isTitle(String string) {
        title = string.trim().toLowerCase();
        return DB
                .getTitles()
                .getTitles()
                .stream()
                .anyMatch(s -> s.toLowerCase().contains(title));
    }

}
