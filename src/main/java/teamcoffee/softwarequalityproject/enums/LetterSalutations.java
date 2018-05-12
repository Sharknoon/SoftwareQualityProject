package teamcoffee.softwarequalityproject.enums;

/**
 *
 * @author Josua Frank
 */
public enum LetterSalutations {
    NOT_SPECIFIED("Nicht angegeben", Languages.NOT_SPECIFIED, Genders.NOT_SPECIFIED),
    FRAU_HERR("Sehr geehrte/r", Languages.GERMAN, Genders.X),
    FRAU("Sehr geehrte Frau", Languages.GERMAN, Genders.FEMALE),
    HERR("Sehr geehrter Herr", Languages.GERMAN, Genders.MALE),
    MS_MR("Dear", Languages.ENGLISH, Genders.X),
    MS("Dear Ms", Languages.ENGLISH, Genders.FEMALE),
    MR("Dear Mr", Languages.ENGLISH, Genders.MALE),
    MADAME_MONSIEUR("Chère Madame, Cher Monsieur", Languages.FRENCH, Genders.X),
    MADAME("Chère Madame", Languages.FRENCH, Genders.FEMALE),
    MONSIEUR("Cher Monsieur", Languages.FRENCH, Genders.MALE);

    private final String name;
    private final Languages language;
    private final Genders gender;

    private LetterSalutations(String name, Languages language, Genders gender) {
        this.name = name;
        this.language = language;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Languages getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return getName();
    }

    public LetterSalutations changeGender(Genders newGender) {
        if (newGender == null) {
            return FRAU_HERR;
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
                    default:
                        return MS_MR;
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
                    default:
                        return MADAME_MONSIEUR;
                }
            case GERMAN:
            case NOT_SPECIFIED://No lanugage, German is default
                switch (newGender) {
                    case FEMALE:
                        return FRAU;
                    case MALE:
                        return HERR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                    default:
                        return FRAU_HERR;
                }
        }
        return FRAU_HERR;
    }

    public LetterSalutations changeLanguage(Languages newLanguage) {
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
                        return MS_MR;
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
                        return MADAME_MONSIEUR;
                    default:
                        return this;
                }
            case NOT_SPECIFIED:
            case GERMAN:
                switch (gender) {
                    case FEMALE:
                        return FRAU;
                    case MALE:
                        return HERR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return FRAU_HERR;
                    default:
                        return this;
                }
        }
        return this;
    }

    public Genders getGender() {
        return gender;
    }

}
