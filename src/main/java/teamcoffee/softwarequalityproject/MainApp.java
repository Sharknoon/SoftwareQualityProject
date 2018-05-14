package teamcoffee.softwarequalityproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Dies ist die Hauptklasse für den Start der JavaFX Applikation
 * @author Josua Frank
 */
public class MainApp extends Application {

    /**
     * Setzt die CSS-Klassen, lädt die Oberfläche aus der FXML-Datei, setzt den
     * Titel und das Icon und zeigt die Oberfläche an
     *
     * @param stage Wird automatisch übergeben
     * @throws Exception Falls z.B. die FXML-Datei nicht gefunden wird
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Kontaktsplitter");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
