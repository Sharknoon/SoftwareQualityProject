package teamcoffee.softwarequalityproject.db;

import com.google.gson.Gson;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.hildan.fxgson.FxGson;
import teamcoffee.softwarequalityproject.checkers.Title;
import teamcoffee.softwarequalityproject.enums.Titles;
import teamcoffee.softwarequalityproject.models.Contact;

/**
 * Diese Klasse verwaltet die Persistenz von Dateien
 *
 * @author Josua Frank
 */
public class DB {

    private static final Gson GSON = FxGson.fullBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get(System.getProperty("user.home") + "\\Kontaktparser\\");
    private static final String TITLES_FILENAME = "titles.json";

    /**
     * Speichert einen Kontakt mit dem dateinamen vorname_nachname.json
     * @param contact Der zu speicherne Kontakt
     * @return true wenn er erfolgreich gespeichert wurde, ansonsten false
     */
    public static boolean saveContact(Contact contact) {
        String json = GSON.toJson(contact);
        String filename = contact.getFirstname() + "_" + contact.getLastname();
        filename = filename.replace(" ", "_");

        try {
            if (Files.notExists(PATH)) {
                Files.createDirectories(PATH);
            }
            Path filePath = PATH.resolve(filename + ".json");
            int counter = 2;
            while (Files.exists(filePath)) {
                filePath = PATH.resolve(filename + " (" + counter + ").json");
                counter++;
            }
            Files.write(filePath, json.getBytes(StandardCharsets.UTF_8));

            if (!contact.getTitle().trim().isEmpty() && !Title.isTitle(contact.getTitle())) {
                Titles titles = getTitles();
                titles.getTitles().addAll(Arrays.asList(contact.getTitle().split(" ")));
                saveTitles(titles);
            }
            return true;
        } catch (UnsupportedOperationException | SecurityException | IOException ex) {
            System.err.println("Konnte Kontakt nicht schreiben " + ex);
        }
        return false;
    }

    /**
     * Gibt ein Titles objekt mit allen gepeicherten Titeln zurück
     * @return Das Titles objekt mit allen Titeln
     */
    public static Titles getTitles() {
        try {
            if (Files.notExists(PATH)) {
                Files.createDirectories(PATH);
            }
            Path filePath = PATH.resolve(TITLES_FILENAME);
            if (Files.notExists(filePath)) {
                saveTitles(new Titles());
            }
            return GSON.fromJson(Files.newBufferedReader(filePath, StandardCharsets.UTF_8), Titles.class);
        } catch (UnsupportedOperationException | SecurityException | IOException e) {
            System.err.println("Konnte Titel nicht lesen " + e);
        }
        return new Titles();
    }

    /**
     * Speichert die Titel
     * @param titles Die zu speichernden Titel
     * @return true wenn das Speichern erfolgreich war, ansonsten false
     */
    public static boolean saveTitles(Titles titles) {
        try {
            if (Files.notExists(PATH)) {
                Files.createDirectories(PATH);
            }
            Path filePath = PATH.resolve(TITLES_FILENAME);
            String json = GSON.toJson(titles);
            Files.write(filePath, json.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (UnsupportedOperationException | SecurityException | IOException e) {
            System.err.println("Konnte Titel nicht schreiben " + e);
        }
        return false;
    }

    /**
     * Öffnet den Ordner, in dem die Kontakte gespeichert sind
     */
    public static void openContactFolder() {
        try {
            if (Files.notExists(PATH)) {
                Files.createDirectories(PATH);
            }
            File file = PATH.toFile();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (UnsupportedOperationException | IOException | SecurityException | IllegalArgumentException | NullPointerException e) {
            System.err.println("Konnte Order " + PATH + " nicht öffnen.");
        }
    }

}
