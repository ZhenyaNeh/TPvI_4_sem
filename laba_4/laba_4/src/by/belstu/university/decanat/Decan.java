package by.belstu.university.decanat;

import by.belstu.university.student.Students;

import java.util.*;
import java.util.logging.*;
import org.apache.log4j.Logger;

public class Decan  implements IDecan {
    private static final Logger LOGGER = Logger.getLogger(Decan.class);

    private List<Students> students;
    private String decanName;

    public  Decan() {}

    public  Decan(String decan) {
        this.decanName = decan;
    }

    public  Decan(String decan, List<Students> students) {
        this.decanName = decan;
        this.students = students;
    }

    public Decan(List<Students> students) {
        this.students = students;
    }

    @Override
    public void countStudentsByCourse() {
        LOGGER.info("Counting students by course");

        Map<Integer, Integer> courseCounts = new HashMap<>();
        for (Students student : students) {
            courseCounts.put(student.getCourse(), courseCounts.getOrDefault(student.getCourse(), 0) + 1);
        }

        System.out.println("\nStudents count by course:");
        for (Map.Entry<Integer, Integer> entry : courseCounts.entrySet()) {
            System.out.println("Course " + entry.getKey() + ": " + entry.getValue() + " students");
        }
    }

    @Override
    public void countStudentsByFormEducation() {
        LOGGER.info("Counting students by Format Education");

        System.out.println("\nStudents on Distance Learning Education:");
        for(Students stud : students){
            if(stud.getFormEducation() == "Distance Learning"){
                System.out.println(stud.getName());
            }
        }

        System.out.println("\nStudents on Regular Education:");
        for(Students stud : students){
            if(stud.getFormEducation() == "Regular"){
                System.out.println(stud.getName());
            }
        }
    }
}
