package teamcoffee.softwarequalityproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    private Label labelError;

    @FXML
    private Button buttonSave;

    @FXML
    private ChoiceBox<Salutations> choiceBoxSalutation;

    @FXML
    private ChoiceBox<LetterSalutations> choiceBoxLetterSalutation;
    @FXML
    private TextField textFieldTitle;
    @FXML
    private ChoiceBox<Genders> choiceBoxGender;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;

    private Contact currentContact;

    @FXML
    private void onButtonParseClicked(ActionEvent event) {
        String input = textFieldInput.getText();
        textFieldInput.clear();
        Result result = Parser.parse(input);
        currentContact = result.getContact();
        textFieldTitle.textProperty().bindBidirectional(currentContact.titleProperty());
        textFieldFirstName.textProperty().bindBidirectional(currentContact.firstnameProperty());
        textFieldLastName.textProperty().bindBidirectional(currentContact.lastnameProperty());

        choiceBoxGender.valueProperty().bindBidirectional(currentContact.genderProperty());
        choiceBoxLetterSalutation.valueProperty().bindBidirectional(currentContact.letter_salutationProperty());
        choiceBoxSalutation.valueProperty().bindBidirectional(currentContact.salutationProperty());

        List<String> errors = result.getErrors();
        labelError.setText(errors.stream().collect(Collectors.joining(", ")));
    }

    @FXML
    private void onButtonSaveClicked(ActionEvent event) {
        DB.saveContact(currentContact);
        reset();
    }

    private final ObjectProperty<Genders> selectedGender = new SimpleObjectProperty<>(Genders.NOT_SPECIFIED);
    private final ObjectProperty<Languages> selectedLanugage = new SimpleObjectProperty<>(Languages.NOT_SPECIFIED);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldInput.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.TAB)) {
                buttonParse.fire();
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
                                        choiceBoxLetterSalutation
                                                .getSelectionModel()
                                                .selectedIndexProperty()
                                                .isNotEqualTo(0)
                                )
                                .not()
                );
        choiceBoxGender.getItems().setAll(Genders.values());
        choiceBoxGender.getSelectionModel().selectFirst();

        choiceBoxSalutation.getItems().setAll(Salutations.values());
        choiceBoxSalutation.getSelectionModel().selectFirst();

        choiceBoxLetterSalutation.getItems().setAll(LetterSalutations.values());
        choiceBoxLetterSalutation.getSelectionModel().selectFirst();

        choiceBoxGender.valueProperty().bindBidirectional(selectedGender);

        choiceBoxSalutation.getSelectionModel()
                .selectedItemProperty().addListener((choiceBox, oldSalutation, newSalutation) -> {
                    System.out.println("new Gender based on salutation (" + newSalutation + "): " + newSalutation.getGender());
                    selectedGender.set(newSalutation.getGender());
                    System.out.println("  new Language based on salutation (" + newSalutation + "): " + newSalutation.getLanguage());
                    selectedLanugage.set(newSalutation.getLanguage());
                });

        choiceBoxLetterSalutation.getSelectionModel()
                .selectedItemProperty().addListener((choiceBox, oldLetterSalutation, newLetterSalutation) -> {
                    System.out.println("new Gender based on lettersalutation (" + newLetterSalutation + "): " + newLetterSalutation.getGender());
                    selectedGender.set(newLetterSalutation.getGender());
                    System.out.println("  new Language based on lettersalutation (" + newLetterSalutation + "): " + newLetterSalutation.getLanguage());
                    selectedLanugage.set(newLetterSalutation.getLanguage());
                });

        selectedGender.addListener((observable, oldGender, newGender) -> {
            Salutations sal = choiceBoxSalutation.getSelectionModel().getSelectedItem();
            choiceBoxSalutation.getSelectionModel().select(
                    sal.changeGender(newGender)
            );

            LetterSalutations letsal = choiceBoxLetterSalutation.getSelectionModel().getSelectedItem();
            choiceBoxLetterSalutation.getSelectionModel().select(
                    letsal.changeGender(newGender)
            );
        });

        selectedLanugage.addListener((observable, oldLanugage, newLanugage) -> {
            Salutations sal = choiceBoxSalutation.getSelectionModel().getSelectedItem();
            choiceBoxSalutation.getSelectionModel().select(
                    sal.changeLanugage(newLanugage)
            );

            LetterSalutations letsal = choiceBoxLetterSalutation.getSelectionModel().getSelectedItem();
            choiceBoxLetterSalutation.getSelectionModel().select(
                    letsal.changeLanguage(newLanugage)
            );
        });
    }

    private void reset() {
        textFieldInput.clear();
        choiceBoxSalutation.getSelectionModel().select(Salutations.NOT_SPECIFIED);
        textFieldTitle.clear();
        textFieldFirstName.clear();
        textFieldLastName.clear();
    }
}
