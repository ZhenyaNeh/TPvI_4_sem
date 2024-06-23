package by.belstu.university.decanat;
import by.belstu.university.student.Students;

import java.util.*;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decanat {
    private static final Logger LOGGER = Logger.getLogger(by.belstu.university.decanat.Decanat.class);

    private String DecanName;

    public Decanat(){}

    public Decanat(String name){
        this.DecanName = name;
    }

    public class Decan implements IDecan {
        private List<Students> students = new ArrayList<Students>();

        public List<Students> getStudents() {
            return students;
        }

        public void setStudents(List<Students> students) {
            this.students = students;
        }

        public Decan () {}

        public Decan(List<Students> students) {
            this.students = students;
        }

        public void addNewStudents(Students stud){
            if(stud != null) {
                this.students.add(stud);
            }
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

        @Override
        public void SortStudentByName(){
            LOGGER.info("Sorted students by Name");

            List<Students> list = students;
            list.sort((x1,x2) -> x1.getName().compareTo(x2.getName()));

            System.out.println("\nSorted student by name:");
            for(Students stud : list){
                    System.out.println(stud.getName());
            }
        }
    }
}
