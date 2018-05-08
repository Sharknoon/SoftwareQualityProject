package teamcoffee.softwarequalityproject.models;

/**
 *
 * @author Josua Frank
 */
public class Contact {

    public final String salutation;
    public final String letter_salutation;
    public final String title;
    public final String gender;
    public final String firstname;
    public final String lastname;

    public Contact(String salutation, String letter_salutation, String title, String gender, String firstname, String lastname) {
        this.salutation = salutation;
        this.letter_salutation = letter_salutation;
        this.title = title;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getLetter_salutation() {
        return letter_salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getGender() {
        return gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    
    
}
