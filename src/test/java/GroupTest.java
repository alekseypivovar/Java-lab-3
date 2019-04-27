import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void getTitle() {
        Group.addGroup("testTitle");
        assertEquals("testTitle", Deanery.groupList.get(0).getTitle());
    }

    @Test
    public void addStudent() {
        Group.addGroup("testGroup");
        Student.newStudent(123, "testName");
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(0));
        assertEquals("testName", Deanery.groupList.get(0).studentsInGroup.get(0).getFio());
    }

    @Test
    public void setHead() {
        Group.addGroup("testGroup");
        Student.newStudent(1, "I am the head");
        Student.newStudent(2, "I am not a head");
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(0));
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(1));
        Deanery.groupList.get(0).setHead(1);
        assertEquals(1, Deanery.groupList.get(0).getHead().getId());
    }

    @Test
    public void findStudent() {
        Group.addGroup("testGroup");
        Student.newStudent(123, "Petrov");
        Student.newStudent(124, "Sidorov");
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(0));
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(1));
        assertEquals(1, Deanery.groupList.get(0).findStudent(124));
        assertEquals(-1, Deanery.groupList.get(0).findStudent(999));

    }

    @Test
    public void findStudent1() {
        Group.addGroup("testGroup");
        Student.newStudent(123, "Petrov");
        Student.newStudent(124, "Sidorov");
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(0));
        Deanery.groupList.get(0).addStudent(Deanery.studentsList.get(1));
        assertEquals(1, Deanery.groupList.get(0).findStudent("Sidor"));
        assertEquals(-1, Deanery.groupList.get(0).findStudent("ksdfjsdhf"));
    }

}