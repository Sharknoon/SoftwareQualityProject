package teamcoffee.softwarequalityproject.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.LetterSalutations;
import teamcoffee.softwarequalityproject.enums.Salutations;

/**
 * Das bean zur Speicherung der Kontaktdaten
 *
 * @author Josua Frank
 */
public class Contact {

    private final StringProperty input = new SimpleStringProperty();
    private final ObjectProperty<Salutations> salutation = new SimpleObjectProperty<>();
    private final StringProperty letter_salutation = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Genders> gender = new SimpleObjectProperty<>();
    private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty lastname = new SimpleStringProperty();
    private final transient List<String> nobilityTitles = new ArrayList<>();

    public Contact() {
        this(null, null, null, null, null, null, null);
    }

    public Contact(String input, Salutations salutation, String title, Genders gender, String firstname, String lastname, List<String> nobilityTitles) {
        this.input.set(input == null ? "" : input);
        this.salutation.set(salutation == null ? Salutations.NOT_SPECIFIED : salutation);
        this.title.set(title == null ? "" : title);
        this.gender.set(gender == null ? Genders.NOT_SPECIFIED : gender);
        this.firstname.set(firstname == null ? "" : firstname);
        this.lastname.set(lastname == null ? "" : lastname);
        this.nobilityTitles.addAll(nobilityTitles == null ? new ArrayList<>() : nobilityTitles);
        this.letter_salutation.set(LetterSalutations.generateLetterSalutation(this));

        bindLetterSalutationGenerator();
    }

    /**
     * Setzt die Briefanrede bei Änderung der Anrede, des Titels, des
     * Geschlechts, des Vornamens oder des Nachnamens neu
     */
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

    /**
     * Gibt die Anrede zurück
     *
     * @return Die Anrede
     */
    public Salutations getSalutation() {
        return salutation.get();
    }

    public ObjectProperty<Salutations> salutationProperty() {
        return salutation;
    }

    /**
     * Gibt die Briefanrede zurück
     *
     * @return Die Briefanrede
     */
    public String getLetter_salutation() {
        return letter_salutation.get();
    }

    public StringProperty letter_salutationProperty() {
        return letter_salutation;
    }

    /**
     * Gibt die Titel zurück
     *
     * @return Die Titel
     */
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Gibt das Geschlecht zurück
     *
     * @return Das Geschlecht
     */
    public Genders getGender() {
        return gender.get();
    }

    public ObjectProperty<Genders> genderProperty() {
        return gender;
    }

    /**
     * Gibt den Vornamen zurück
     *
     * @return Den Vornamen
     */
    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    /**
     * Gibt den Nachnamen zurück
     *
     * @return Den Nachnamen
     */
    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    /**
     * Gibt die Liste der Adelstitel zurück
     * @return 
     */
    public List<String> getNobilityTitles() {
        return nobilityTitles;
    }
    
//    public ListProperty<String> nobilityTitlesProperty(){
//        return nobilityTitles;
//    }

    @Override
    public String toString() {
        return "Contact{" + "input=" + input.get() + ", salutation=" + salutation.get() + ", letter_salutation=" + letter_salutation.get() + ", title=" + title.get() + ", gender=" + gender.get() + ", firstname=" + firstname.get() + ", lastname=" + lastname.get() + ", nobilityTitles=" + nobilityTitles + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.input);
        hash = 41 * hash + Objects.hashCode(this.salutation);
        hash = 41 * hash + Objects.hashCode(this.letter_salutation);
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.gender);
        hash = 41 * hash + Objects.hashCode(this.firstname);
        hash = 41 * hash + Objects.hashCode(this.lastname);
        hash = 41 * hash + Objects.hashCode(this.nobilityTitles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!this.input.get().equals(other.input.get())) {
            return false;
        }
        if (!this.salutation.get().equals(other.salutation.get())) {
            return false;
        }
        if (!this.letter_salutation.get().equals(other.letter_salutation.get())) {
            return false;
        }
        if (!this.title.get().equals(other.title.get())) {
            return false;
        }
        if (!this.gender.get().equals(other.gender.get())) {
            return false;
        }
        if (!this.firstname.get().equals(other.firstname.get())) {
            return false;
        }
        if (!this.lastname.get().equals(other.lastname.get())) {
            return false;
        }
        return this.nobilityTitles.equals(other.nobilityTitles);
    }

}
