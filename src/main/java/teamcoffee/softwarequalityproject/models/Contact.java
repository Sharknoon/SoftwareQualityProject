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

    private final StringProperty input = new SimpleStringProperty("");
    private final ObjectProperty<Salutations> salutation = new SimpleObjectProperty<>();
    private final StringProperty letter_salutation = new SimpleStringProperty("");
    private final StringProperty title = new SimpleStringProperty("");
    private final ObjectProperty<Genders> gender = new SimpleObjectProperty<>();
    private final StringProperty firstname = new SimpleStringProperty("");
    private final StringProperty lastname = new SimpleStringProperty("");

    public Contact() {
        this(null, null, null, null, null, null);
    }

    public Contact(String input, Salutations salutation, String title, Genders gender, String firstname, String lastname) {
        this.input.set(input == null ? "" : input);
        this.salutation.set(salutation == null ? Salutations.NOT_SPECIFIED : salutation);
        this.title.set(title == null ? "" : title);
        this.gender.set(gender == null ? Genders.NOT_SPECIFIED : gender);
        this.firstname.set(firstname == null ? "" : firstname);
        this.lastname.set(lastname == null ? "" : lastname);
        this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));

        bindLetterSalutationGenerator();
    }

    private void bindLetterSalutationGenerator() {
        this.salutation.addListener((observable, oldValue, newValue) -> {
            this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));
        });
        this.title.addListener((observable, oldValue, newValue) -> {
            this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));
        });
        this.gender.addListener((observable, oldValue, newValue) -> {
            this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));
        });
        this.firstname.addListener((observable, oldValue, newValue) -> {
            this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));
        });
        this.lastname.addListener((observable, oldValue, newValue) -> {
            this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));
        });
    }

    public Salutations getSalutation() {
        return salutation.get();
    }

    public ObjectProperty<Salutations> salutationProperty() {
        return salutation;
    }

    public String getLetter_salutation() {
        return letter_salutation.get();
    }

    public StringProperty letter_salutationProperty() {
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
