package teamcoffee.softwarequalityproject.enums;

/**
 *
 * @author Josua Frank
 */
public enum LetterSalutations {
    FRAU_HERR("Sehr geehrte/r", 1),
    FRAU("Sehr geehrte Frau", 1),
    HERR("Sehr geehrter Herr", 1),
    MS_MR("Dear", 0),
    MS("Dear Ms", 0),
    MR("Dear Mr", 0);

    private final String name;
    private final int language;

    private LetterSalutations(String name, int language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public int getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return getName();
    }

}
