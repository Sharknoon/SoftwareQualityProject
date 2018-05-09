package teamcoffee.softwarequalityproject.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.LetterSalutations;
import teamcoffee.softwarequalityproject.enums.Salutations;

/**
 *
 * @author Josua Frank
 */
public class Contact {

    private final ObjectProperty<Salutations> salutation = new SimpleObjectProperty<>();
    private final ObjectProperty<LetterSalutations> letter_salutation = new SimpleObjectProperty<>();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Genders> gender = new SimpleObjectProperty<>();
    private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty lastname = new SimpleStringProperty();

    public Contact() {
    }

    public Contact(Salutations salutation, String title, Genders gender, String firstname, String lastname) {
        this.salutation.set(salutation);
        if (salutation != null) {
            switch (salutation) {
                case FRAU:
                    letter_salutation.set(LetterSalutations.FRAU);
                    break;
                case HERR:
                    letter_salutation.set(LetterSalutations.HERR);
                    break;
                case MR:
                    letter_salutation.set(LetterSalutations.MR);
                    break;
                case MS:
                    letter_salutation.set(LetterSalutations.MS);
                    break;
                case NOT_SPECIFIED:
                case X:
                default:
                    letter_salutation.set(LetterSalutations.FRAU_HERR);
                    break;
            }
        } else {
            letter_salutation.set(LetterSalutations.FRAU_HERR);
        }
        this.title.set(title);
        this.gender.set(gender);
        this.firstname.set(firstname);
        this.lastname.set(lastname);
    }

    public Salutations getSalutation() {
        return salutation.get();
    }

    public ObjectProperty<Salutations> salutationProperty() {
        return salutation;
    }

    public LetterSalutations getLetter_salutation() {
        return letter_salutation.get();
    }

    public ObjectProperty<LetterSalutations> letter_salutationProperty() {
        return letter_salutation;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public Genders getGender() {
        return gender.get();
    }

    public ObjectProperty<Genders> genderProperty() {
        return gender;
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

}
