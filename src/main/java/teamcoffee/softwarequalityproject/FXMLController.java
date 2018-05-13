package teamcoffee.softwarequalityproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    private TextField textFieldInput;

    @FXML
    private Button buttonParse;

    @FXML
    private TextFlow textFlowError;

    @FXML
    private Button buttonSave;

    @FXML
    private ChoiceBox<Salutations> choiceBoxSalutation;

    @FXML
    private TextField textFieldLetterSalutation;
    @FXML
    private TextField textFieldTitle;
    @FXML
    private ChoiceBox<Genders> choiceBoxGender;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;

    private Contact currentContact;
    private final BooleanProperty MISSING_LASTNAME = new SimpleBooleanProperty();
    private final BooleanProperty MISSING_FIRSTNAME = new SimpleBooleanProperty();
    private final BooleanProperty MISSING_GENDER = new SimpleBooleanProperty();

    @FXML
    private void onButtonParseClicked(ActionEvent event) {
        String input = textFieldInput.getText();
        textFieldInput.clear();
        Result result = Parser.parse(input);
        bindContact(result.getContact());

        List<String> errors = result.getErrors();
        setErrorText(errors.stream().collect(Collectors.joining(", ")));
    }

    @FXML
    private void onButtonSaveClicked(ActionEvent event) {
        DB.saveContact(currentContact);
        reset();
    }

    @FXML
    private void onButtonContactsClicked(ActionEvent event) {
        DB.openContactFolder();
    }

    private final ObjectProperty<Genders> selectedGender = new SimpleObjectProperty<>(Genders.NOT_SPECIFIED);
    private final ObjectProperty<Languages> selectedLanugage = new SimpleObjectProperty<>(Languages.NOT_SPECIFIED);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindContact(new Contact());
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
    }

    private void reset() {
        textFieldInput.clear();
        choiceBoxSalutation.getSelectionModel().select(Salutations.NOT_SPECIFIED);
        textFieldTitle.clear();
        textFieldFirstName.clear();
        textFieldLastName.clear();
    }

    private void setErrorText(String text) {
        Text errorText = new Text(text);
        errorText.setFill(Color.RED);
        textFlowError.getChildren().clear();
        textFlowError.getChildren().addAll(errorText);
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
    }
}
