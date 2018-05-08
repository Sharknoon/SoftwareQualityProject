package teamcoffee.softwarequalityproject.enums;

/**
 *
 * @author Josua Frank
 */
public enum Salutations {
    NOT_SPECIFIED("Nicht angegeben", -1),
    HERR("Herr", 1),
    FRAU("Frau", 1),
    MS("Ms", 0),
    MR("Mr", 0),
    X("X", -1);

    private final String name;
    private final int language;

    private Salutations(String name, int language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }
    
    public int getLanguage(){
        return language;
    }

    @Override
    public String toString() {
        return getName();
    }

}
