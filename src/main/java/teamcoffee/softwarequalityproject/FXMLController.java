package teamcoffee.softwarequalityproject;

import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.LetterSalutations;
import teamcoffee.softwarequalityproject.enums.Salutations;

public class FXMLController implements Initializable {

    @FXML
    private TextField textFieldInput;

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

    @FXML
    private void onButtonParseClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonSave
                .disableProperty()
                .bind(
                        textFieldLastName
                                .textProperty()
                                .isNotEmpty()
                                .and(
                                        choiceBoxLetterSalutation
                                                .getSelectionModel()
                                                .selectedIndexProperty()
                                                .isNotEqualTo(0)
                                )
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
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    int language = choiceBoxLetterSalutation
                    switch (newValue) {
                        case FEMALE:
                            
                            break;
                        case MALE:
                            
                            break;
                        case NOT_SPECIFIED:
                            
                            break;
                        case X:
                            
                            break;
                    }
                });
    }
}
