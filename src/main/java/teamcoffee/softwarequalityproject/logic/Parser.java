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
            String Error = "Feld ist leer";
            result.addError(Error);
        }
        // Weder anrede noch Vorname gefunden 
        if (salutation == Salutations.NOT_SPECIFIED && firstName.isEmpty()) {

            String Error = "Keine Anrede gefunden";
            result.addError(Error);

            Error = "Keinen Vorname gefunden";
            result.addError(Error);
        }
        //Kein Nachname angegeben
        if (lastName.isEmpty()) {
            String Error = "Keinen Nachname angegeben";
            result.addError(Error);
        }
        Contact contact = new Contact(parse, salutation, title, gender, firstName, lastName);
        result.setContact(contact);
        return result;
    }

}
