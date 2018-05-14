package teamcoffee.softwarequalityproject.logic;

import java.util.ArrayList;
import java.util.List;
import teamcoffee.softwarequalityproject.checkers.NobilityTitle;
import teamcoffee.softwarequalityproject.checkers.Salutation;
import teamcoffee.softwarequalityproject.checkers.Title;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Salutations;
import teamcoffee.softwarequalityproject.models.Contact;

/**
 * Der Parser parst die Eingabe zu einem Ergebnis
 *
 * @author Josua Frank
 */
public class Parser {

    /**
     * Parst einen Eingabestring zu einem Kontakt
     *
     * @param parse Der Eingabestring
     * @return Der geparste Kontakt
     */
    public static Contact parse(String parse) {

        String split[] = parse.split(" ");

        int zaehler = 0;
        Salutations salutation = Salutations.NOT_SPECIFIED;
        String title = "";
        String firstName = "";
        String lastName = "";
        Genders gender = Genders.NOT_SPECIFIED;
        List<String> nobilityTitles = new ArrayList<>();

        while (zaehler != split.length) {

            String splitPart = split[zaehler];

            // Überprüfung auf Adelstitel
            if (NobilityTitle.isNobilityTitle(splitPart)) {
                nobilityTitles.add(splitPart);
            } else // Überprüfung auf Anrede
            if (Salutation.isSalutation(splitPart) == true) {
                salutation = Salutation.getSalutation(splitPart);
                gender = Salutation.getGender(splitPart);
            } else // Überprüfung auf Titel
            if (Title.isTitle(splitPart) == true) {
                if (title.isEmpty()) {
                    title = splitPart;
                } else {
                    title = title + " " + splitPart;
                }
            } else // Nachname erkannt anhand von Komma
            if (splitPart.charAt(splitPart.length() - 1) == ',') {
                lastName = splitPart.substring(0, splitPart.length() - 1);
                if (zaehler + 1 < split.length) {
                    if (firstName.isEmpty()) {
                        firstName = split[zaehler + 1];
                    } else {
                        firstName = firstName + " " + split[zaehler + 1];
                    }
                    zaehler++;
                }
            } else//Spanischer Nachname
            if (splitPart.toLowerCase().equals("y") && zaehler + 1 < split.length && !firstName.isEmpty()) {
                lastName = firstName + " " + splitPart;
                firstName = "";
            } else {//Vorname noch nicht gefunden
                if (firstName.isEmpty() && zaehler < split.length - 1) {
                    firstName = splitPart;
                } else {
                    if (lastName.isEmpty()) {
                        lastName = splitPart;
                    } else {
                        lastName = lastName + " " + splitPart;
                    }
                }
            }
            zaehler++;
        }

        //Ueberpruefung auf Grossschreibung
        if (!firstName.isEmpty() && !Character.isUpperCase(firstName.charAt(0))) {
            String newFirstName = firstName.substring(0, 1).toUpperCase();
            if (firstName.length() > 1) {
                newFirstName = newFirstName + firstName.substring(1);
            }
            firstName = newFirstName;
        }
        String[] lastnames = lastName.split(" ");
        String lastLastname;
        if (lastnames.length > 0) {
            lastLastname = lastnames[lastnames.length - 1];
        } else {
            lastLastname = "";
        }
        if (!lastLastname.isEmpty() && !Character.isUpperCase(lastLastname.charAt(0))) {
            String newLastName = lastLastname.substring(0, 1).toUpperCase();
            if (lastLastname.length() > 1) {
                newLastName = newLastName + lastLastname.substring(1);
            }
            lastName = "";
            for (int i = 0; i < lastnames.length - 1; i++) {
                if (i > 0) {
                    lastName = lastName + " " + lastnames[i];
                } else {
                    lastName = lastnames[i];
                }
            }
            if (!lastName.isEmpty()) {
                lastName = lastName + " " + newLastName;
            } else {
                lastName = newLastName;
            }

        }
        return new Contact(parse, salutation, title, gender, firstName, lastName, nobilityTitles);
    }

}
