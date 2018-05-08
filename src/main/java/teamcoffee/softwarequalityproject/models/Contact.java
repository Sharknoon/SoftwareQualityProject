package teamcoffee.softwarequalityproject.models;

import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.LetterSalutations;
import teamcoffee.softwarequalityproject.enums.Salutations;

/**
 *
 * @author Josua Frank
 */
public class Contact {

    public final Salutations salutation;
    public final LetterSalutations letter_salutation;
    public final String title;
    public final Genders gender;
    public final String firstname;
    public final String lastname;

    public Contact(Salutations salutation, LetterSalutations letter_salutation, String title, Genders gender, String firstname, String lastname) {
        this.salutation = salutation;
        this.letter_salutation = letter_salutation;
        this.title = title;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Salutations getSalutation() {
        return salutation;
    }

    public LetterSalutations getLetter_salutation() {
        return letter_salutation;
    }

    public String getTitle() {
        return title;
    }

    public Genders getGender() {
        return gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    
    
}
