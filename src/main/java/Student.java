import sun.security.acl.GroupImpl;

import java.io.IOException;
import java.util.ArrayList;

public class Student {
    private int id;
    private String fio;
    private Group group;
    private ArrayList<Integer> marks = new ArrayList<Integer>();

    // Constructor:
    Student(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    static void newStudent(int id, String fio) {
        Deanery.studentsList.add(new Student(id, fio));
    }

    void setGroup(Group group) {
        this.group = group;
    }

    int getId(){
        return this.id;
    }

    String getFio(){
        return this.fio;
    }

    Group getGroup(){
        return this.group;
    }

    ArrayList<Integer> getMarks() {return this.marks;}

    static void addMark(int id, int mark) {
        int index = findStudent(id);
        if (index == -1) {
            return;
        }
        Deanery.studentsList.get(index).marks.add(mark);
    }

    void addMark(int mark){
        this.marks.add(mark);
    }

    static float getAverageMark(int id) {
        int index = findStudent(id);
        if (index == -1) {
            return 0;
        }

        float sum = 0;
        int counter = 0;
        for (int mark : Deanery.studentsList.get(index).marks) {
            sum += mark;
            counter++;
        }
        return (sum / (float)counter);
    }

    static float getAverageMark(Student student) {

        float sum = 0;
        int counter = 0;
        for (int mark : student.marks) {
            sum += mark;
            counter++;
        }
        return (sum / (float)counter);
    }

    static int findStudent(int id) {
        try {
            for (Student student : Deanery.studentsList) {
                if (student.getId() == id) {
                    return Deanery.studentsList.indexOf(student);
                }
            }
        } catch (Exception ex) {
            System.out.println("Student not found!");
        }
        return -1;
    }

}

