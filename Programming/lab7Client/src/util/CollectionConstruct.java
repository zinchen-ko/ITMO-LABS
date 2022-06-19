package util;

import collection.*;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.io.CharArrayWriter;
import java.util.*;

public class CollectionConstruct {

    private Map<Integer, StudyGroup> collection;
    private StudyGroup studyGroup;
    private Person person;
    private int id;
    private int checkPoint = 0;
    private int passage = 0;
    private String fileName;
    private String login;

//    public CollectionConstruct(Map<Integer, StudyGroup> collection, StudyGroup studyGroup) {
//        this.studyGroup = studyGroup;
//        this.id = studyGroup.getId();
//        this.collection = collection;
//        studyGroup.setCreationDate(new Date());
//        person = new Person();
//    }

    public CollectionConstruct(Map<Integer, StudyGroup> collection, String login) {
        this.collection = collection;
        studyGroup = new StudyGroup();
        studyGroup.setCreationDate("Apr 19, 2021 12:37:31 AM");
        person = new Person();
        this.login=login;

    }

    Scanner scanner = new Scanner(System.in);

    /**
     * Class for collection creation methods
     */

    public boolean checkId(int id) {
        checkPoint = 0;
        if (checkPoint == 0 && studyGroup.setId(id)) {
            studyGroup.setId(id);
            this.id = id;
            return true;
        } else {
            System.out.println("Поле id должно быть уникальным и строго больше нуля. Введенное вами значение не подходит, пожалуйста введите корректные данные");
            passage = 1;
            return false;

        }
    }

    public boolean checkName() {
        System.out.println("Пожалуйста, введите название мероприятия, поле не должно быть пустым.");
        checkPoint = 0;
        String name = scanner.nextLine();
        if (name.length() > 0 && studyGroup.setName(name) && name.replace(" ", "").length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkName(String name) {
        if (name.length() > 0 && studyGroup.setName(name) && name.replace(" ", "").length() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean creatCoordinates() {
        checkPoint = 0;
        Coordinates coordinates = new Coordinates();
        System.out.println("Пожалуйста, поочередно введите место и ряд , номер места должен быть больше -951 и представлен в виде целого числа, ряд в виде числа с плавающей точкой");

        try {
            float x = Float.parseFloat(scanner.nextLine());
            int y = Integer.parseInt(scanner.nextLine());
            if (coordinates.setY(y) && coordinates.setX(x)) {
                studyGroup.setCoordinates(coordinates);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Вами было введено не число");
            return false;
        }
    }

    public boolean creatCoordinates(String x, String y) {
        Coordinates coordinates = new Coordinates();
        try {
            float q = Float.parseFloat(x);
            int z = Integer.parseInt(y);
            if (coordinates.setY(z) && coordinates.setX(q)) {
                studyGroup.setCoordinates(coordinates);
                return true;
            } else {

                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Было считано не число.Из за этого команда не может быть выполнена");
            return false;


        }
    }


    public boolean checkAverageMark() {
        System.out.println("Пожалуйста, введите средний балл группы ");
        try {
            long averageMark = Long.parseLong(scanner.nextLine());
            if (studyGroup.setAverageMark(averageMark)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;
        }
    }

    public boolean checkAverageMark1(String average) {
        try {
            long averageMark = Long.parseLong(average);
            if (studyGroup.setAverageMark(averageMark)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;
        }
    }


    public boolean checkTransferredStudents() {
        System.out.println("Пожалуйста, введите количество иностранных студентов в группе ");
        try {
            int transferredStudents = Integer.parseInt(scanner.nextLine());
            if (studyGroup.setTransferredStudents(transferredStudents)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;
        }
    }

    public boolean checkTransferredStudents1(String transferred) {
        try {
            int transferredStudents = Integer.parseInt(transferred);
            if (studyGroup.setTransferredStudents(transferredStudents)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;
        }
    }

    public boolean checkStudentsCount() {
        System.out.println("Пожалуйста, введите количество студентов в группе ");
        try {
            long studentsCount = Long.parseLong(scanner.nextLine());
            if (studyGroup.setStudentsCount(studentsCount)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;
        }
    }

    public boolean checkStudentsCount1(String students){
        try {
            long studentsCount = Long.parseLong(students);
            if(studyGroup.setStudentsCount(studentsCount)){
                return true;
            }else {
                return false;
            }
        } catch(NumberFormatException e){
            System.out.println("Введеные вами данные не похожи на число, которое требовалось ввести");
            return false;
        }
    }

    public boolean setSemester(){
        System.out.println("Выберите одно из предложенных годов обучения ");
        switch (scanner.nextLine().toLowerCase()){
            case "first":
                studyGroup.setSemesterEnum(Semester.FIRST);
                return true;

            case "second":
                studyGroup.setSemesterEnum(Semester.SECOND);
                return true;

            case "seventh":
                studyGroup.setSemesterEnum(Semester.SEVENTH);
                return true;

            default:
                System.out.println("Пожалуйста, выберите одно из предложенных значений");
                return false;
        }
    }

    public boolean setSemester(String semester) {
        switch (scanner.nextLine().toLowerCase()){
            case "first":
                studyGroup.setSemesterEnum(Semester.FIRST);
                return true;

            case "second":
                studyGroup.setSemesterEnum(Semester.SECOND);
                return true;

            case "seventh":
                studyGroup.setSemesterEnum(Semester.SEVENTH);
                return true;

            default:
                System.out.println("Пожалуйста, выберите одно из предложенных значений");
                return false;
        }
    }

    public void setOwner() {
        studyGroup.setOwnerLogin(login);
    }

    public boolean checkPersonName(){
        System.out.println("Пожалуйста, введите имя старосты группы ");
        String groupAdmin =scanner.nextLine();
        Person person = new Person();
        if(person.setName(groupAdmin)){
            return true;
        }else {
            System.out.println("Вы нчиего не ввели");
            return false;
        }
    }

    public boolean checkPersonName(String antonGod) {

        if(person.setName(antonGod)){
            return true;
        }else {
            System.out.println("Вы нчиего не ввели");
            return false;
        }
    }

    public Person person() {
        return new Person();
    }

    public boolean cheakPerson() {
        System.out.println("Пожалуйста, введите по очередно имя старосты, день рождения, вес, рост, и паспортныее данные старосты))) ");
        String name = scanner.nextLine();
        String birthday =scanner.nextLine();
        float height = Float.parseFloat(scanner.nextLine());
        int weight = Integer.parseInt(scanner.nextLine());
        String passportId =scanner.nextLine();
        if ((person.setName(name))&&(person.setBirthday(birthday))&&(person.setHeight(height))&&(person.setWeight(weight))&&(person.setPassportID(passportId))) {
            return true;
        } else {
            return false;
        }
    }




    public boolean cheakPersonInfo1(String birthday,String height,String weight,String passportID) {
        float height1 = Float.parseFloat(height);
        int weight1 = Integer.parseInt(weight);
        if ((person.setBirthday(birthday))&&(person.setHeight(height1))&&(person.setWeight(weight1))&&(person.setPassportID(passportID))) {
            return false;
        } else {
            return true;
        }
    }

    public StudyGroup makeStudyGroup() {
        studyGroup.setPerson(person);
        collection.put(id,studyGroup);
        return studyGroup ;
    }

}
