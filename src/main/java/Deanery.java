import java.io.*;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*

Примерный перечень полей:

    students - массив студентов
    groups - массив групп

Обеспечить класс следующими методами:

    создание студентов на основе данных из файла
    создание групп на основе данных из файла
    добавление случайных оценок студентам
    накопление статистики по успеваемости студентов и групп
    перевод студентов из группы в группу
    отчисление студентов за неуспеваемость
    сохранение обновленных данных в файлах
    инициация выборов старост в группах
    вывод данных на консоль

*/
public class Deanery {
    static ArrayList<Student> studentsList = new ArrayList<Student>();
    static ArrayList<Group> groupList = new ArrayList<Group>();
    private static int idCounter = 1;

    static void addStudentsFromFile() {
        try {
            File f = new File("Names.json");
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);

            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("students");
            for (Object i : items) {
                Student.newStudent(Integer.parseInt(((JSONObject) i).get("id").toString()),(((JSONObject) i).get("fio").toString()));
            }
        } catch (
                FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void addGroupsFromFile() {
        try {
            File f = new File("Groups.json");
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);

            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("groups");
            for (Object i : items) {
                Group.addGroup((((JSONObject) i).get("groupname").toString()));
            }
        } catch (
                FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void setRandomMarks() {
        for (Student student : studentsList) {
            for (int i = 0; i < 20; i++) {
                student.addMark(randomInt(1, 5));
            }
        }
    }

    static int randomInt(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    static void changeGroup(int id, Group targetGroup) {

        int index = Student.findStudent(id);
        if (index != (-1)) {
            Student student = Deanery.studentsList.get(index);
            student.getGroup().deleteStudentFromGroup(student);
            targetGroup.addStudent(id);
            student.setGroup(targetGroup);
        }
    }

    static void sendDownStudent(Student student) {
        if (Student.getAverageMark(student) <= 2.50) {
            studentsList.remove(student);
            student.getGroup().deleteStudentFromGroup(student);
            System.out.println("Student " + student.getFio() + " was send down");
        } else {
            System.out.println("This student cannot be send down, because his average mark is " + Student.getAverageMark(student) + " (>=2.5");
        }
    }

    static void saveInfoToFile() {
        JSONArray studentsJSON = new JSONArray();
        for (Student student:studentsList){
            studentsJSON.add(student.getId());
            studentsJSON.add(student.getFio());
            studentsJSON.add(student.getGroup());
        }

        object.put("messages", messages);
        try (FileWriter writer = new FileWriter(FILENAME)){
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        }
        catch (IOException ex) {
            Logger.getLogger(JsonSimpleExample.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    static void voteForHeadinGroups() {          // 'Zaglushka'. First member of the each group is beginning to be a head of this group.
        for (Group group : groupList) {
            group.setHead(group.studentsInGroup.get(0).getId());
        }
    }

    static void printAllInfo() {
        for (Group group : groupList) {
            System.out.println(group.getTitle());
            for (Student student : group.studentsInGroup) {
                System.out.println(student.getId() + " - " + student.getFio());
            }
        }
    }
}
