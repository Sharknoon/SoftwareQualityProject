package teamcoffee.softwarequalityproject.logic;

import teamcoffee.softwarequalityproject.checkers.Salutation;
import teamcoffee.softwarequalityproject.checkers.Title;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Salutations;
import teamcoffee.softwarequalityproject.models.Contact;
import teamcoffee.softwarequalityproject.models.Result;

/**
 *
 * @author Josua Frank
 */
public class Parser {

    public static Result parse(String parse) {

        String split[] = parse.split(" ");

        int zaehler = 0;
        Salutations salutation = Salutations.NOT_SPECIFIED;
        String title = "";
        String firstName = "";
        String lastName = "";
        Genders gender = Genders.NOT_SPECIFIED;

        while (zaehler != split.length) {

            String splitPart = split[zaehler];

            // Überprüfung auf Anrede
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
            } else {//Vorname noch nicht gefunden
                if (firstName.isEmpty()) {
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

        Result result = new Result();
        // Leeres Feld = Falsch
        if (salutation == Salutations.NOT_SPECIFIED && title.isEmpty() && firstName.isEmpty() && lastName.isEmpty()) {
            String error = "Feld ist leer";
            result.addError(error);
        }
        // Weder anrede noch Vorname gefunden 
        if (salutation == Salutations.NOT_SPECIFIED && firstName.isEmpty()) {

            String error = "Keine Anrede gefunden";
            result.addError(error);

            error = "Keinen Vorname gefunden";
            result.addError(error);
        }
        //Kein Nachname angegeben
        if (lastName.isEmpty()) {
            String error = "Keinen Nachname angegeben";
            result.addError(error);
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
        Contact contact = new Contact(parse, salutation, title, gender, firstName, lastName);
        result.setContact(contact);
        return result;
    }

}
