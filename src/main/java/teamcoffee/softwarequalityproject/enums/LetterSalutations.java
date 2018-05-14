package teamcoffee.softwarequalityproject.enums;

import java.util.Arrays;
import teamcoffee.softwarequalityproject.models.Contact;

/**
 * Verwaltung der Briefanreden
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

    /**
     * Gibt den Anzeigenamen zurück
     *
     * @return Den Anzeigenamen
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Sprache dieser Briefanrede zurück
     *
     * @return Die Sprache
     */
    public Languages getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return getName();
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
     * Erstellung der Briefanrede Anhand von Spezifikationen im Eingabestring
     *
     * @param contact Der Kontakt dessen Briefanrede generiert werden soll
     * @return Die Briefanrede, bei fehlerhaften angaben die geschlechtsneutrale
     * Briefanrede auf deutsch
     */
    public static String generateLetterSalutation(Contact contact) {
        if (contact == null) {
            return FRAU_HERR.getName();
        }
        Languages lang = contact.getSalutation() != null
                && contact.getSalutation().getLanguage() != Languages.NOT_SPECIFIED
                ? contact.getSalutation().getLanguage()
                : Languages.GERMAN;
        Genders gen = contact.getGender() != null
                && contact.getGender() != Genders.NOT_SPECIFIED
                ? contact.getGender()
                : Genders.X;
        StringBuilder builder = new StringBuilder();
        builder
                .append(Arrays
                        .asList(values())
                        .stream()
                        .filter(ls -> ls.getGender() == gen && ls.getLanguage() == lang)
                        .map(LetterSalutations::getName)
                        .findAny()
                        .orElse(FRAU_HERR.getName())
                )
                .append(" ")
                .append(contact.getTitle() == null || (contact.getTitle() != null && contact.getTitle().isEmpty()) ? "" : contact.getTitle() + " ")
                .append(contact.getFirstname() == null || (contact.getFirstname() != null && contact.getFirstname().isEmpty()) ? "" : contact.getFirstname() + " ")
                .append(contact.getLastname() == null || (contact.getLastname() != null && contact.getLastname().isEmpty()) ? "" : contact.getLastname());
        return builder.toString();
    }

}
