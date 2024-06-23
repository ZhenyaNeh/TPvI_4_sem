package Tests;

import by.belstu.university.decanat.Decanat;
import by.belstu.university.student.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.reporters.AbstractXmlReporter;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
    private Decanat decanat;
    private Decanat.Decan decan;
    private DistanceStudent distanceStudent;
    private RegularStudent regularStudent;
    private final long CountOfStudents = 3;

    @BeforeTest
    public void Init(){
        decanat = new Decanat("Shiman");
        decan = decanat.new Decan();

        distanceStudent = new DistanceStudent("Bob", 17, 1, Specialization.PHYSICS);
        regularStudent = new RegularStudent("John", 17, 1, Specialization.COMPUTER_SCIENCE);

        decan.addNewStudents(distanceStudent);
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(regularStudent);
    }

    @AfterTest
    public void Clear(){
        decan = null;
        decanat = null;
    }

    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("Тест метода");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("Тест метода окончен");
    }

    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("Запуск тестов");
    }

    @AfterSuite
    public void AfterSuite() {
        System.out.println("Тесты окончены");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("Тест класса");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("Тест класса окончен");
    }

    @Test
    public void TestCountStudents(){
        Assert.assertEquals(CountOfStudents, decan.countStudents());
    }

    @Test
    public void TestSort1(){
        decanat = new Decanat("Shiman");
        decan = decanat.new Decan();
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(distanceStudent);
        Assert.assertEquals(CountOfStudents, decanat.countStud());
        decan.countStudentsByCourse();
        decan = null;
        decanat = null;
    }

    @Test (timeOut = 1000)
    public void TestSort2(){
        decanat = new Decanat("Shiman");
        decan = decanat.new Decan();
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(distanceStudent);
        Assert.assertEquals(CountOfStudents, decanat.countStud());
        decan.SortStudentByName();
        decan = null;
        decanat = null;
    }

    @Test
    public void TestSort3(){
        decanat = new Decanat("Shiman");
        decan = decanat.new Decan();
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(regularStudent);
        decan.addNewStudents(regularStudent);
        Assert.assertEquals(CountOfStudents, decanat.countStud());
        decan.countStudentsByFormEducation();
        decan = null;
        decanat = null;
    }

    @DataProvider(name = "testData")
    public Object[][] createData(){
        Decanat decanat1 = new Decanat();
        Decanat decanat2 = new Decanat();
        Decanat decanat3 = new Decanat();

        List<Students> list1 = new ArrayList<>();
        List<Students> list2 = new ArrayList<>();
        List<Students> list3 = new ArrayList<>();

        list1.add(regularStudent);
        list1.add(distanceStudent);
        list2.add(regularStudent);
        list2.add(regularStudent);
        list2.add(regularStudent);
        list3.add(distanceStudent);
        list3.add(distanceStudent);

        decanat1.setStudents(list1);
        decanat2.setStudents(list2);
        decanat3.setStudents(list3);

        return new Object[][] {
                {decanat1, 2 },
                {decanat2, 3 },
                {decanat3, 2 }
        };
    }

    @Test(dataProvider = "testData")
    public void testCount(Decanat decanat, int num) {
        Assert.assertEquals(decanat.countStud(), num);
    }
}
