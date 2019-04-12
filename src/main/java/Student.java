import sun.security.acl.GroupImpl;

import java.io.IOException;
import java.util.ArrayList;

public class Student {
    int id;
    String fio;
    Group group;
    ArrayList<Integer> marks = new ArrayList<Integer>();
    ArrayList<Student> studentsList = new ArrayList<Student>();

    // Constructor:
    Student(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    void newStudent(int id, String fio) {
        studentsList.add(new Student(id, fio));
    }

    void setGroup(Group group) {
        this.group = group;
    }

    void addMark(int id, int mark) {        // Потом переделать
        int index = findStudent(id);
        if (index == -1) {
            return;
        }
        studentsList.get(index).marks.add(mark);
    }

    float getAverageMark(int id) {
        int index = findStudent(id);
        if (index == -1) {
            return 0;
        }

        int sum = 0;
        int counter = 0;
        for (int mark : studentsList.get(index).marks) {
            sum += mark;
            counter++;
        }
        return (float) (sum / counter);
    }

    int findStudent(int id) {
        try {
            for (Student student : studentsList) {
                if (student.id == id) {
                    return studentsList.indexOf(student);
                }
            }
        } catch (Exception ex) {
            System.out.println("Student not found!");
        } finally {
            return -1;
        }
    }

}

