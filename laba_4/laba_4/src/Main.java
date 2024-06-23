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
            LOGGER.info("Starting program");

            // =====================================================================================
            /*Произвести проверку валидности XML-документа с привлечением XSD*/

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

            String filename = "files/info.xml";
            String schemaName = "files/info.xsd";

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            try {
                Schema schema = factory.newSchema(new File(schemaName));
                Validator validator = schema.newValidator();
                Source source = new StreamSource(filename);
                validator.validate(source);
                LOGGER.info(filename + " IS VALID");
            } catch (SAXException e) {
                LOGGER.error(filename + " SAX ERROR " + e.getMessage());
            } catch (IOException e) {
                LOGGER.error(" IO ERROR " + e.getMessage());
            }

            // =====================================================================================
            /*4. Для разбора использовать на выбор один из: SAX (Simpl API for XML),
            DOM (Document Object Model), StAX (Streaming API foe XML) парсеры. Для сортировки объектов
            использовать интерфейс Comparator*/

            try {
                XMLReader reader = XMLReaderFactory.createXMLReader();
                ParserHandler parser = new ParserHandler();
                reader.setContentHandler(parser);
                reader.parse("files/info.xml");
                LOGGER.info(filename + " IS VALID PARSE");

            } catch (SAXException e) {
                LOGGER.error(filename + " SAX error " + e.getMessage());
            } catch (IOException e) {
                LOGGER.error(" io error " + e.getMessage());
            }

            // ==================================================================================================
            //5. Добавьте методы сериализация и десериализации в JSON файл
            //(используйте любую библиотеку)

            Student stud1 = new Student("John1", 17, 1, "Distanse Lerning", Specialization.COMPUTER_SCIENCE);
            Student stud2 = new Student("John2", 17, 1, "Distanse Lerning", Specialization.COMPUTER_SCIENCE);
            Student stud3 = new Student("John3", 17, 1, "Distanse Lerning", Specialization.COMPUTER_SCIENCE);
            Student stud4 = new Student("John4", 17, 1, "Distanse Lerning", Specialization.COMPUTER_SCIENCE);

            List<Student> studentsForSerialize = new ArrayList<>();
            studentsForSerialize.add(stud1);
            studentsForSerialize.add(stud2);
            studentsForSerialize.add(stud3);
            studentsForSerialize.add(stud4);

            boolean correctSerialize = JSONSerializer.SerializerToJSON(studentsForSerialize, "json/Serialize.json");

            if (correctSerialize) {
                List<Student> deserialiazeStud = JSONSerializer.Deserializer("json/Serialize.json");

                for (Student el : deserialiazeStud) {
                    System.out.println(el);
                }
            }

            // =====================================================================================
            //6. Добавьте в проект работу со StreamAPI для обработки данных в
            //функциональном стиле
            System.out.println("\n\nOutPut Filter Students were age > 20: \n");
            var result = decan.getStudents().stream().filter(x -> x.getAge() > 20).toArray();

            for (var el : result) {
                System.out.println(el);
            }

            LOGGER.info("\nPerforming various sorted");
            try {
//                decan.countStudentsByCourse();
//                decan.countStudentsByFormEducation();
//                decan.SortStudentByName();
            } catch (Exception ex) {
                LOGGER.error("ERROR" + ex.getMessage());
            }


            LOGGER.info("End Programme");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            LOGGER.error("An error occurred\n" + ex.getMessage());
        }
    }
}