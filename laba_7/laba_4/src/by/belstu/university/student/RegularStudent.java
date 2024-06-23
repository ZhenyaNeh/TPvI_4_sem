package by.belstu.university.student;

import org.apache.log4j.Logger;

public class RegularStudent extends Students {

    private static final Logger LOGGER = Logger.getLogger(RegularStudent.class);

    public RegularStudent () {
        super();
    }
    public RegularStudent(String name, int age, int course, Specialization specialization) {
        super(name, age, course,"Regular Learning", specialization);
        LOGGER.info("Added Regular Student: " + name);
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
