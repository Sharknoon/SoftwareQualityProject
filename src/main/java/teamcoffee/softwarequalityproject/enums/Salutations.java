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
    X("Person", -1);

    private final String name;
    private final int language;

    private Salutations(String name, int language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public int getLanguage() {
        return language;
    }

    public Genders getGender(){
        switch(this){
            case HERR:
            case MR:
                return Genders.MALE;
            case FRAU:
            case MS:
                return Genders.FEMALE;
            case NOT_SPECIFIED:
                return Genders.NOT_SPECIFIED;
            case X:
                return Genders.X;
        }
        return Genders.NOT_SPECIFIED;
    }
    
    @Override
    public String toString() {
        return getName();
    }

    public Salutations changeGender(Genders newGender) {
        if (newGender == null) {
            return NOT_SPECIFIED;
        }
        switch (language) {
            case 0://English
                switch (newGender) {
                    case FEMALE:
                        return MS;
                    case MALE:
                        return MR;
                    case NOT_SPECIFIED:
                        return NOT_SPECIFIED;
                    case X:
                        return X;
                    default:
                        return NOT_SPECIFIED;
                }
            case -1://No lanugage, German is default
            case 1://German
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

}
