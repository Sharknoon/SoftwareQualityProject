package teamcoffee.softwarequalityproject;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamcoffee.softwarequalityproject.enums.Genders;
import teamcoffee.softwarequalityproject.enums.Salutations;
import teamcoffee.softwarequalityproject.logic.Parser;
import teamcoffee.softwarequalityproject.models.Contact;

/**
 *
 * @author Josua Frank
 */
public class KontaktparserTest {

    public KontaktparserTest() {
    }

    static List<String> testcases = new ArrayList<>();
    static List<Contact> testresults = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
        testcases.add("Frau Sandra Berger");
        testresults.add(new Contact("Frau Sandra Berger", Salutations.FRAU, null, Genders.FEMALE, "Sandra", "Berger"));
        testcases.add("Herr Dr. Sandro Gutmensch");
        testresults.add(new Contact("Herr Dr. Sandro Gutmensch", Salutations.HERR, "Dr.", Genders.MALE, "Sandro", "Gutmensch"));
        testcases.add("Professor Heinrich Freiherr vom Wald");
        testresults.add(new Contact("Professor Heinrich Freiherr vom Wald", Salutations.NOT_SPECIFIED, "Professor", Genders.NOT_SPECIFIED, "Heinrich", "Freiherr vom Wald"));
        testcases.add("Mrs. Doreen Faber");
        testresults.add(new Contact("Mrs. Doreen Faber", Salutations.MS, null, Genders.FEMALE, "Doreen", "Faber"));
        testcases.add("Mme. Charlotte Noir");
        testresults.add(new Contact("Mme. Charlotte Noir", Salutations.MADAME, null, Genders.FEMALE, "Charlotte", "Noir"));
        testcases.add("Estobar y Gonzales");
        testresults.add(new Contact("Estobar y Gonzales", Salutations.NOT_SPECIFIED, null, Genders.NOT_SPECIFIED, null, "Estobar y Gonzales"));
        testcases.add("Frau Prof. Dr. rer. Nat. Maria von Leuthäuser-Schnarrenberger");
        testresults.add(new Contact("Frau Prof. Dr. rer. Nat. Maria von Leuthäuser-Schnarrenberger", Salutations.FRAU, "Prof. Dr. rer. Nat.", Genders.FEMALE, "Maria", "von Leuthäuser-Schnarrenberger"));
        testcases.add("Herr Dipl. Ing. Max von Müller");
        testresults.add(new Contact("Herr Dipl. Ing. Max von Müller", Salutations.HERR, "Dipl. Ing.", Genders.MALE, "Max", "von Müller"));
        testcases.add("Dr. Russwurm, Winfried");
        testresults.add(new Contact("Dr. Russwurm, Winfried", Salutations.NOT_SPECIFIED, "Dr.", Genders.NOT_SPECIFIED, "Winfried", "Russwurm"));
        testcases.add("Herr Dr.-Ing. Dr. rer. Nat. Dr. h.c. mult. Paul Steffens");
        testresults.add(new Contact("Herr Dr.-Ing. Dr. rer. Nat. Dr. h.c. mult. Paul Steffens", Salutations.HERR, "Dr.-Ing. Dr. rer. Nat. Dr. h.c. mult.", Genders.MALE, "Paul", "Steffens"));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        int zaehler = 0;
        while (zaehler < testresults.size()) {
            assertEquals(testresults.get(zaehler), Parser.parse(testcases.get(zaehler)));
            zaehler++;
        }
    }

}
