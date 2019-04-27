public class Main {
    public static void main(String args[]){
        Deanery dekanat = new Deanery();
    Deanery.addStudentsFromFile();
    Deanery.addGroupsFromFile();

    for (Student student:Deanery.studentsList){
        int groupNumber=Deanery.randomInt(0,2);
        Deanery.groupList.get(groupNumber).addStudent(student);
    }

    Deanery.setRandomMarks();
    Deanery.printAllInfo();

    Deanery.voteForHeadinGroups();

    for (int i=0;i<20;i++){
        Deanery.studentsList.get(0).addMark(1);
    }

    Deanery.sendDownStudent(Deanery.studentsList.get(0));
    Deanery.printAllInfo();

    Group.addGroup("NewTestGroup");
    Deanery.changeGroup(Deanery.studentsList.get(0),Deanery.groupList.get(3));
    Deanery.printAllInfo();

    System.out.println(Deanery.groupList.get(3).getAverageMark());

    Deanery.saveInfoToFile();


    }
}
