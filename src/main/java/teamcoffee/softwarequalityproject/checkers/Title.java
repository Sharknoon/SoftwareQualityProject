package teamcoffee.softwarequalityproject.checkers;

import teamcoffee.softwarequalityproject.db.DB;

/**
 * Überprüft Eingaben auf Titel
 * @author Josua Frank
 */
public class Title {

    private static String title;
    
    /**
     * Überprüfung des String auf einen möglichen Titel (Dr., usw.)
     * @param string Der mögliche Titel
     * @return wahr, wenn dies ein Titel ist, ansonsten falsch
     */
    public static boolean isTitle(String string) {
        title = string.trim().toLowerCase();
        return DB
                .getTitles()
                .getTitles()
                .stream()
                .anyMatch(s -> title.contains(s.toLowerCase()));
    }

}
