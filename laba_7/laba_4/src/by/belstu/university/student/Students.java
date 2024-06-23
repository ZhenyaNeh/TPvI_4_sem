package by.belstu.university.student;

public abstract class Students {
    private String name;
    private int age;
    private int course;
    private Specialization specialization;
    private String formEducation;

    public Students() {}

    public Students(String name, int age, int course, String formEducation, Specialization specialization){
        this.name = name;
        this.age = age;
        this.course = course;
        this.formEducation = formEducation;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getFormEducation() {
        return formEducation;
    }

    public void setFormEducation(String formEducation) {
        this.formEducation = formEducation;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                "\nAge: " + age +
                "\nCourse: " + course +
                "\nSpecialization: " + specialization +
                "\nForm of Education: " + formEducation;
    }
}
