import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import WorkWhithFile.JSONSerializer;
import by.belstu.university.decanat.Decanat;
import by.belstu.university.student.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


public class Main {
    static {
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Start prog");
            Decanat decanat = new Decanat("Shiman");
            Decanat.Decan decan = decanat.new Decan();

            decan.addNewStudents(new RegularStudent("John", 17, 1, Specialization.COMPUTER_SCIENCE));
            decan.addNewStudents(new RegularStudent("Alice", 18, 1, Specialization.MATHEMATICS));
            decan.addNewStudents(new DistanceStudent("Bob", 17, 1, Specialization.PHYSICS));

            decan.addNewStudents(new RegularStudent("John", 20, 2, Specialization.COMPUTER_SCIENCE));
            decan.addNewStudents(new RegularStudent("Alice", 19, 2, Specialization.MATHEMATICS));
            decan.addNewStudents(new DistanceStudent("Bob", 18, 2, Specialization.PHYSICS));

            decan.addNewStudents(new RegularStudent("John", 20, 3, Specialization.COMPUTER_SCIENCE));
            decan.addNewStudents(new RegularStudent("Alice", 21, 3, Specialization.MATHEMATICS));
            decan.addNewStudents(new DistanceStudent("Bob", 22, 3, Specialization.PHYSICS));

            LOGGER.info("\nPerforming various sorted");
            try {
                decan.countStudentsByCourse();
                decan.countStudentsByFormEducation();
                decan.SortStudentByName();
            } catch (Exception ex) {
                LOGGER.error("ERROR" + ex.getMessage());
            }

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }
}