package teamcoffee.softwarequalityproject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import teamcoffee.softwarequalityproject.db.DB;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Languages;
import teamcoffee.softwarequalityproject.enums.LetterSalutations;
import teamcoffee.softwarequalityproject.enums.Salutations;
import teamcoffee.softwarequalityproject.models.Contact;
import teamcoffee.softwarequalityproject.models.Result;
import teamcoffee.softwarequalityproject.logic.Parser;

public class FXMLController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField textFieldInput;

    @FXML
    private JFXButton buttonParse;

    @FXML
    private TextFlow textFlowError;

    @FXML
    private JFXButton buttonSave;

    @FXML
    private JFXComboBox<Salutations> choiceBoxSalutation;

    @FXML
    private JFXTextField textFieldLetterSalutation;
    @FXML
    private JFXTextField textFieldTitle;
    @FXML
    private JFXComboBox<Genders> choiceBoxGender;
    @FXML
    private JFXTextField textFieldFirstName;
    @FXML
    private JFXTextField textFieldLastName;

    private Contact currentContact;
    private final BooleanProperty MISSING_LASTNAME = new SimpleBooleanProperty();
    private final BooleanProperty MISSING_FIRSTNAME = new SimpleBooleanProperty();
    private final BooleanProperty MISSING_SALUTATION = new SimpleBooleanProperty();

    @FXML
    private void onButtonParseClicked(ActionEvent event) {
        String input = textFieldInput.getText();
        textFieldInput.clear();
        Result result = Parser.parse(input);
        bindContact(result.getContact());
    }

    @FXML
    private void onButtonSaveClicked(ActionEvent event) {
        reset();
        boolean success = DB.saveContact(currentContact);
        JFXSnackbar bar = new JFXSnackbar(root);
        if (success) {
            bar.enqueue(new SnackbarEvent("Speichern erfolgreich", "success"));
        } else {
            bar.enqueue(new SnackbarEvent("Speichern fehlgeschlagen", "failure"));
        }

    }

    @FXML
    private void onButtonContactsClicked(ActionEvent event) {
        DB.openContactFolder();
    }

    private final ObjectProperty<Genders> selectedGender = new SimpleObjectProperty<>(Genders.NOT_SPECIFIED);
    private final ObjectProperty<Languages> selectedLanugage = new SimpleObjectProperty<>(Languages.NOT_SPECIFIED);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reset();
        textFieldInput.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.TAB)) {
                buttonParse.fire();
            }
        });
        textFieldInput.textProperty().addListener((textField, oldString, newString) -> {
            if (newString.length() > 100) {
                textFieldInput.setText(newString.substring(0, 100));
            }
        });
        textFieldLetterSalutation.textProperty().addListener((textField, oldString, newString) -> {
            if (newString.length() > 100) {
                textFieldLetterSalutation.setText(newString.substring(0, 100));
            }
        });
        textFieldTitle.textProperty().addListener((textField, oldString, newString) -> {
            if (newString.length() > 100) {
                textFieldTitle.setText(newString.substring(0, 100));
            }
        });
        textFieldFirstName.textProperty().addListener((textField, oldString, newString) -> {
            if (newString.length() > 100) {
                textFieldFirstName.setText(newString.substring(0, 100));
            }
        });
        textFieldLastName.textProperty().addListener((textField, oldString, newString) -> {
            if (newString.length() > 100) {
                textFieldLastName.setText(newString.substring(0, 100));
            }
        });
        buttonSave
                .disableProperty()
                .bind(
                        textFieldLastName
                                .textProperty()
                                .isNotEmpty()
                                .and(
                                        textFieldFirstName
                                                .textProperty()
                                                .isNotEmpty()
                                                .or(
                                                        choiceBoxSalutation
                                                                .getSelectionModel()
                                                                .selectedIndexProperty()
                                                                .isNotEqualTo(0)
                                                )
                                ).and(
                                        textFieldLetterSalutation
                                                .textProperty()
                                                .isNotEmpty()
                                )
                                .not()
                );
        choiceBoxGender.getItems().setAll(Genders.values());
        choiceBoxGender.getSelectionModel().selectFirst();

        choiceBoxSalutation.getItems().setAll(Salutations.values());
        choiceBoxSalutation.getSelectionModel().selectFirst();

        choiceBoxGender.valueProperty().bindBidirectional(selectedGender);

        choiceBoxSalutation.getSelectionModel()
                .selectedItemProperty().addListener((choiceBox, oldSalutation, newSalutation) -> {
                    selectedGender.set(newSalutation.getGender());
                    selectedLanugage.set(newSalutation.getLanguage());
                });

        selectedGender.addListener((observable, oldGender, newGender) -> {
            Salutations sal = choiceBoxSalutation.getSelectionModel().getSelectedItem();
            choiceBoxSalutation.getSelectionModel().select(
                    sal.changeGender(newGender)
            );

            textFieldLetterSalutation.setText(LetterSalutations.generateLetterSalutation(currentContact));
        });

        selectedLanugage.addListener((observable, oldLanugage, newLanugage) -> {
            Salutations sal = choiceBoxSalutation.getSelectionModel().getSelectedItem();
            choiceBoxSalutation.getSelectionModel().select(
                    sal.changeLanugage(newLanugage)
            );

            textFieldLetterSalutation.setText(LetterSalutations.generateLetterSalutation(currentContact));
        });

        initErrorText();
    }

    private void reset() {
        bindContact(new Contact());
    }

    private final Text textMissingFirstnameOrSalutation = new Text("Vorname oder Anrede benötigt   ");
    private final Text textMissingFirstname = new Text("Vorname dringend empfohlen   ");
    private final Text textMissingLastname = new Text("Nachname benötigt   ");
    private final Text textMissingSalutation = new Text("Anrede dringend empfohlen   ");

    private void initErrorText() {
        textMissingFirstnameOrSalutation.setFill(Color.RED);
        textMissingFirstname.setFill(Color.ORANGE);
        textMissingLastname.setFill(Color.RED);
        textMissingSalutation.setFill(Color.ORANGE);
        ObservableList<Text> errorTexts = FXCollections.observableArrayList();
        errorTexts.addAll(textMissingFirstnameOrSalutation, textMissingLastname, textMissingSalutation, textMissingFirstname);
        setErrorTexts(errorTexts);
        MISSING_FIRSTNAME.addListener((observable, oldValue, newValue) -> {
            setErrorTexts(errorTexts);
        });
        MISSING_LASTNAME.addListener((observable, oldValue, newValue) -> {
            setErrorTexts(errorTexts);
        });
        MISSING_SALUTATION.addListener((observable, oldValue, newValue) -> {
            setErrorTexts(errorTexts);
        });
    }

    private void setErrorTexts(ObservableList<Text> list) {
        textFlowError.getChildren().setAll(list
                .filtered(t -> {
                    if (t == textMissingFirstnameOrSalutation) {
                        return MISSING_FIRSTNAME.get() && MISSING_SALUTATION.get();
                    }
                    if (t == textMissingFirstname) {
                        if (MISSING_SALUTATION.get() && MISSING_FIRSTNAME.get()) {
                            return false;//uses missing firstnameorsalutation
                        }
                        return MISSING_FIRSTNAME.get();
                    }
                    if (t == textMissingLastname) {
                        return MISSING_LASTNAME.get();
                    }
                    if (t == textMissingSalutation) {
                        if (MISSING_SALUTATION.get() && MISSING_FIRSTNAME.get()) {
                            return false;//uses missing firstnameorsalutation
                        }
                        return MISSING_SALUTATION.get();
                    }
                    return true;
                }));
    }

    private void bindContact(Contact contact) {
        if (contact == null) {
            currentContact = new Contact();
        } else {
            currentContact = contact;
        }
        textFieldTitle.textProperty().bindBidirectional(currentContact.titleProperty());
        textFieldFirstName.textProperty().bindBidirectional(currentContact.firstnameProperty());
        textFieldLastName.textProperty().bindBidirectional(currentContact.lastnameProperty());
        textFieldLetterSalutation.textProperty().bindBidirectional(currentContact.letter_salutationProperty());

        choiceBoxGender.valueProperty().bindBidirectional(currentContact.genderProperty());
        choiceBoxSalutation.valueProperty().bindBidirectional(currentContact.salutationProperty());

        MISSING_FIRSTNAME.bind(currentContact.firstnameProperty().isEmpty());
        MISSING_LASTNAME.bind(currentContact.lastnameProperty().isEmpty());
        MISSING_SALUTATION.bind(currentContact.salutationProperty().isEqualTo(Salutations.NOT_SPECIFIED));
    }
}
