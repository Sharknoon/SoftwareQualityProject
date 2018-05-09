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

    public LetterSalutations changeGender(Genders newGender) {
        if (newGender == null) {
            return FRAU_HERR;
        }
        switch (language) {
            case 0://English
                switch (newGender) {
                    case FEMALE:
                        return MS;
                    case MALE:
                        return MR;
                    case NOT_SPECIFIED:
                    case X:
                    default:
                        return MS_MR;
                }
            case -1://No lanugage, German is default
            case 1://German
                switch (newGender) {
                    case FEMALE:
                        return FRAU;
                    case MALE:
                        return HERR;
                    case NOT_SPECIFIED:
                    case X:
                    default:
                        return FRAU_HERR;
                }
        }
        return FRAU_HERR;
    }

    public Genders getGender() {
        switch (this) {
            case HERR:
            case MR:
                return Genders.MALE;
            case FRAU:
            case MS:
                return Genders.FEMALE;
            case FRAU_HERR:
            case MS_MR:
                return Genders.X;
        }
        return Genders.NOT_SPECIFIED;
    }

}
