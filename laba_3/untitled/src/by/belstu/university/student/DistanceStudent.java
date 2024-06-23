package by.belstu.university.student;

import by.belstu.university.decanat.Decanat;
import org.apache.log4j.Logger;

public class DistanceStudent extends Students {
    private static final Logger LOGGER = Logger.getLogger(DistanceStudent.class);

    public DistanceStudent() {
        super();
    }

    public DistanceStudent(String name, int age,int course, Specialization specialization) {
        super(name, age, course,specialization, "Distance Learning");
        LOGGER.info("Added Distance Student: " + name);
    }

    @Override
    public String toString(){
        return "Name: " + super.getName() +
                "\nAge: " + super.getAge() +
                "\nCourse: " + super.getCourse() +
                "\nSpecialization: " + super.getSpecialization() +
                "\nForm of Education: " + super.getFormEducation();
    }
}
