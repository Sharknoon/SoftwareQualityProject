package teamcoffee.softwarequalityproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import teamcoffee.softwarequalityproject.db.DB;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.LetterSalutations;
import teamcoffee.softwarequalityproject.enums.Salutations;
import teamcoffee.softwarequalityproject.models.Contact;
import teamcoffee.softwarequalityproject.models.Result;
import teamcoffee.softwarequalityproject.parser.Parser;

public class FXMLController implements Initializable {

    @FXML
    private TextField textFieldInput;

    @FXML
    private Button buttonParse;

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
        Result result = Parser.parse(textFieldInput.getText());
        currentContact = result.getContact();
        choiceBoxSalutation.valueProperty().bindBidirectional(currentContact.salutationProperty());
        choiceBoxLetterSalutation.valueProperty().bindBidirectional(currentContact.letter_salutationProperty());
        textFieldTitle.textProperty().bindBidirectional(currentContact.titleProperty());
        choiceBoxGender.valueProperty().bindBidirectional(currentContact.genderProperty());
        textFieldFirstName.textProperty().bindBidirectional(currentContact.firstnameProperty());
        textFieldLastName.textProperty().bindBidirectional(currentContact.lastnameProperty());
    }

    @FXML
    private void onButtonSaveClicked(ActionEvent event) {
        DB.saveContact(currentContact);
    }

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
                                )
                                .not()
                );
        choiceBoxGender.getItems().setAll(Genders.values());
        choiceBoxGender.getSelectionModel().selectFirst();

        choiceBoxSalutation.getItems().setAll(Salutations.values());
        choiceBoxSalutation.getSelectionModel().selectFirst();

        choiceBoxLetterSalutation.getItems().setAll(LetterSalutations.values());
        choiceBoxLetterSalutation.getSelectionModel().selectFirst();

        choiceBoxGender.getSelectionModel()
                .selectedItemProperty().addListener((choiceBox, oldGender, newGender) -> {
                    Salutations sal = choiceBoxSalutation.getSelectionModel().getSelectedItem();
                    choiceBoxSalutation.getSelectionModel().select(
                            sal.changeGender(newGender)
                    );

                    LetterSalutations letsal = choiceBoxLetterSalutation.getSelectionModel().getSelectedItem();
                    choiceBoxLetterSalutation.getSelectionModel().select(
                            letsal.changeGender(newGender)
                    );
                });

        choiceBoxSalutation.getSelectionModel()
                .selectedItemProperty().addListener((choiceBox, oldSalutation, newSalutation) -> {
                    Genders newGender = newSalutation.getGender();
                    choiceBoxGender.getSelectionModel().select(newGender);

                    LetterSalutations letsal = choiceBoxLetterSalutation.getSelectionModel().getSelectedItem();
                    choiceBoxLetterSalutation.getSelectionModel().select(
                            letsal.changeGender(newGender)
                    );
                });

        choiceBoxLetterSalutation.getSelectionModel()
                .selectedItemProperty().addListener((choiceBox, oldLetterSalutation, newLetterSalutation) -> {
                    Genders newGender = newLetterSalutation.getGender();
                    choiceBoxGender.getSelectionModel().select(newGender);

                    Salutations sal = choiceBoxSalutation.getSelectionModel().getSelectedItem();
                    choiceBoxSalutation.getSelectionModel().select(
                            sal.changeGender(newGender)
                    );
                });
    }
}
