package teamcoffee.softwarequalityproject.enums;

import java.util.Arrays;
import java.util.List;

/**
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

    public String getName() {
        return name;
    }

    public Languages getLanguage() {
        return language;
    }

    public Genders getGender() {
        return gender;
    }

    public List<String> getAlternativeNames() {
        return alternativeNames;
    }

    @Override
    public String toString() {
        return getName();
    }

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
