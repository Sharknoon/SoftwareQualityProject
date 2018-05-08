package teamcoffee.softwarequalityproject.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import teamcoffee.softwarequalityproject.models.Contact;

/**
 *
 * @author Josua Frank
 */
public class DB {

    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    public static void saveContact(Contact contact) {
        String json = GSON.toJson(contact);
        String filename = contact.firstname
    }

}
