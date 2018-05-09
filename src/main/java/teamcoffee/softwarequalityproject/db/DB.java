package teamcoffee.softwarequalityproject.db;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.hildan.fxgson.FxGson;
import teamcoffee.softwarequalityproject.models.Contact;

/**
 *
 * @author Josua Frank
 */
public class DB {

    private static final Gson GSON = FxGson.fullBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get(System.getProperty("user.home") + "\\Kontaktparser\\");

    public static void saveContact(Contact contact) {
        String json = GSON.toJson(contact);
        String filename = contact.getFirstname() + "_" + contact.getLastname();

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
        } catch (UnsupportedOperationException | SecurityException | IOException ex) {
            System.err.println("Konnte Kontakt nicht schreiben");
        }
    }

}
