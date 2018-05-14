package teamcoffee.softwarequalityproject.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Verwaltung der Anreden
 *
 * @author Josua Frank
 */
public enum Salutations {
    NOT_SPECIFIED("Nicht angegeben", Languages.NOT_SPECIFIED, Genders.NOT_SPECIFIED, "-"),
    FRAU("Frau", Languages.GERMAN, Genders.FEMALE),
    HERR("Herr", Languages.GERMAN, Genders.MALE),
    X("Person", Languages.GERMAN, Genders.X),
    MS("Ms", Languages.ENGLISH, Genders.FEMALE, "Mrs"),
    MR("Mr", Languages.ENGLISH, Genders.MALE),
    MX("Mx", Languages.ENGLISH, Genders.X),
    MADAME("Mme", Languages.FRENCH, Genders.FEMALE, "Mlle", "Mademoiselle"),
    MONSIEUR("M", Languages.FRENCH, Genders.MALE),
    PERSONE("Persone", Languages.FRENCH, Genders.X);

    private final String name;
    private final Languages language;
    private final Genders gender;
    private final List<String> alternativeNames;

    private Salutations(String name, Languages language, Genders gender, String... alternatives) {
        this.name = name;
        this.language = language;
        this.gender = gender;
        alternativeNames = Arrays.asList(alternatives);
    }

    /**
     * Gibt den Anzeigenamen zurück
     *
     * @return Den Anzeigenamen
     */
    public String getName() {
        return name;
    }

    /**
     * Gitb die Sprache zurück
     *
     * @return Die Sprache
     */
    public Languages getLanguage() {
        return language;
    }

    /**
     * Gibt das Geschlecht zurück
     *
     * @return Das Geschlecht
     */
    public Genders getGender() {
        return gender;
    }

    /**
     * Gibt sie alternativen Namen zu den Anreden, z.B. Mrs. zu Ms. zurück
     *
     * @return Die alternativen Namen
     */
    public List<String> getAlternativeNames() {
        return alternativeNames;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Ändert das Geschlecht basierend auf dem neuen Geschlecht und der Sprache
     *
     * @param newGender Das neue Geschlecht
     * @return Die neue Anrede mit der selben Sprache und dem neuen Geschlecht
     */
    public Salutations changeGender(Genders newGender) {
        if (newGender == null) {
            return NOT_SPECIFIED;
        }
        if (newGender == gender) {
            return this;
        }
        switch (language) {
            case ENGLISH:
                switch (newGender) {
                    case FEMALE:
                        return MS;
                    case MALE:
                        return MR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return MX;
                    default:
                        return NOT_SPECIFIED;
                }
            case FRENCH:
                switch (newGender) {
                    case FEMALE:
                        return MADAME;
                    case MALE:
                        return MONSIEUR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return PERSONE;
                    default:
                        return NOT_SPECIFIED;
                }
            case NOT_SPECIFIED://No lanugage, German is default
            case GERMAN:
                switch (newGender) {
                    case FEMALE:
                        return FRAU;
                    case MALE:
                        return HERR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return X;
                    default:
                        return NOT_SPECIFIED;
                }
        }
        return NOT_SPECIFIED;
    }

    /**
     * Ändert die Sprache basierend auf der neuen Sprache und dem Geschlecht
     *
     * @param newLanguage Die neue Sprache
     * @return Die neue Anrede mit dem selben Geschlecht und der neuen Sprache
     */
    public Salutations changeLanugage(Languages newLanguage) {
        if (newLanguage == null) {
            newLanguage = Languages.GERMAN;
        }
        if (newLanguage == language) {
            return this;
        }
        switch (newLanguage) {
            case ENGLISH:
                switch (gender) {
                    case FEMALE:
                        return MS;
                    case MALE:
                        return MR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return MX;
                    default:
                        return this;
                }
            case FRENCH:
                switch (gender) {
                    case FEMALE:
                        return MADAME;
                    case MALE:
                        return MONSIEUR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return PERSONE;
                    default:
                        return this;
                }
            case NOT_SPECIFIED://German is default
            case GERMAN:
                switch (gender) {
                    case FEMALE:
                        return FRAU;
                    case MALE:
                        return HERR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return X;
                    default:
                        return this;
                }
        }
        return NOT_SPECIFIED;
    }

}
