package WorkWhithFile;

import by.belstu.university.student.RegularStudent;
import by.belstu.university.student.Student;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class JSONSerializer {
    private static final Logger LOGGER = Logger.getLogger(RegularStudent.class);

    public static Boolean SerializerToJSON(List<Student> stud, String path) {
        System.out.println("\nSerialization___________________________");
        try (FileWriter writer = new FileWriter(path, false)) {
            Gson gson = new Gson();
            String json = gson.toJson(stud);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            LOGGER.error(" io error " + e.getMessage());
            return false;
        }

        return true;
    }

    public static List<Student> Deserializer(String filePath) throws IOException {

        System.out.println("Deserialization_________________________");
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Student>>() {}.getType();

        String json = new String(Files.readAllBytes(Paths.get(filePath)));

        List<Student> stud = gson.fromJson(json, listType);

        return stud;
    }
}
