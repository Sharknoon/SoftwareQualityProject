package teamcoffee.softwarequalityproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class FXMLController implements Initializable {
    
    @FXML
    private TextArea textAreaInput;
    
    @FXML
    private TableView tableViewResult;
    
    @FXML
    private void onButtonParseClicked(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
