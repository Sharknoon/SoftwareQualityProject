package teamcoffee.softwarequalityproject.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Josua Frank
 */
public class Result {

    private Contact contact = null;
    private final List<String> errors = new ArrayList<>();

    public Result() {
    }
    
    public Result(Contact contact, String... errors) {
        this.contact = contact;
        this.errors.addAll(Arrays.asList(errors));
    }
    
    public void addError(String... errors){
        this.errors.addAll(Arrays.asList(errors));
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    
        
    public Contact getContact() {
        return contact;
    }

    public List<String> getErrors() {
        return errors;
    }

}
