import java.io.File;
import java.io.IOException;
import java.util.*;

import by.belstu.university.decanat.Decanat;
import by.belstu.university.student.Students;
import by.belstu.university.decanat.Decanat;
import by.belstu.university.student.Specialization;
import by.belstu.university.student.RegularStudent;
import by.belstu.university.student.DistanceStudent;

import org.apache.log4j.LogManager;
import java.util.logging.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
//import org.apache.log4j.Logger;


public class Main {
    static {
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Starting program");

            List<Students> students = new ArrayList<>();

            students.add(new RegularStudent("John", 17,1, Specialization.COMPUTER_SCIENCE));
            students.add(new RegularStudent("Alice", 18,1, Specialization.MATHEMATICS));
            students.add(new DistanceStudent("Bob", 17,1, Specialization.PHYSICS));

            students.add(new RegularStudent("John", 20,2, Specialization.COMPUTER_SCIENCE));
            students.add(new RegularStudent("Alice", 19,2, Specialization.MATHEMATICS));
            students.add(new DistanceStudent("Bob", 18,2, Specialization.PHYSICS));

            students.add(new RegularStudent("John", 20,3, Specialization.COMPUTER_SCIENCE));
            students.add(new RegularStudent("Alice", 21,3, Specialization.MATHEMATICS));
            students.add(new DistanceStudent("Bob", 22,3, Specialization.PHYSICS));

            Decanat decanat = new Decanat();
            Decanat.Decan decan = decanat.new Decan(students);

            LOGGER.info("\nPerforming various sorted");
            try{
                decan.countStudentsByCourse();
                decan.countStudentsByFormEducation();
                decan.SortStudentByName();
            }
            catch(Exception ex){
                LOGGER.error("ERROR" + ex.getMessage());
            }


            LOGGER.info("End Programme");

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            LOGGER.error("An error occurred\n" + ex.getMessage());
        }
    }
}