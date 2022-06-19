package util;

import collection.*;
import dateBase.BaseManager;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class CollectionConstruct {

    private Map<Integer, StudyGroup> collection;
    private StudyGroup studyGroup;
    private Person person;
    private int id;
    private int checkPoint = 0;
    private int passage = 0;
    private String fileName;
    private ServerTransport transport;
    private BaseManager baseManager;

    public CollectionConstruct(Map<Integer, StudyGroup> collection, StudyGroup studyGroup, ServerTransport transport, BaseManager baseManager) {
        this.studyGroup = studyGroup;
        this.id = studyGroup.getId();
        this.collection = collection;
        this.transport=transport;
        this.baseManager=baseManager;
    }
    public CollectionConstruct(Map<Integer, StudyGroup> collection, StudyGroup studyGroup, ServerTransport transport) {
        this.studyGroup = studyGroup;
        this.id = studyGroup.getId();
        this.collection = collection;
        this.transport=transport;
    }
//
//    public CollectionConstruct(Map<Integer, StudyGroup> collection, String fileName) {
//        this.collection = collection;
//        studyGroup = new StudyGroup();
//        studyGroup.setCreationDate(new Date());
//        person = new Person();
//        this.fileName=fileName;
//    }

    public CollectionConstruct(Map<Integer, StudyGroup> collection) {
        this.collection=collection;
        studyGroup=new StudyGroup();
        studyGroup.setCreationDate("Apr 19, 2021 12:37:31 AM");
        person = new Person();
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

    public boolean checkName(int idk) throws SQLException {
        transport.send(new Answer("Пожалуйста, введите название мероприятия, поле не должно быть пустым."));
        checkPoint = 0;
        String name = transport.receive().getCommand();
        if (name.length() > 0 && studyGroup.setName(name) && name.replace(" ", "").length() > 0) {
            baseManager.updateBase(1,name,idk);
            return true;
        } else {
            transport.send(new Answer("Ошибка"));
            transport.send(new Answer("Не подходящее имя группы"));
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

    public boolean createCoordinates() {
        checkPoint = 0;
        Coordinates coordinates = new Coordinates();
        transport.send(new Answer("Введите этаж университета"));

        try {
            float x = Float.parseFloat(transport.receive().getCommand());
            transport.send(new Answer("Введите номер кабента"));
            int y = Integer.parseInt(transport.receive().getCommand());
            if (coordinates.setY(y) && coordinates.setX(x)) {
                studyGroup.setCoordinates(coordinates);
                return true;
            } else {
                transport.send(new Answer("Ошибка"));
                transport.send(new Answer("Числа не подходят"));
                return false;
            }
        } catch (NumberFormatException e) {
            transport.send(new Answer("Ошибка"));
            transport.send(new Answer("Вами было введено не число"));
            return false;
        }
    }

    public boolean creatCoordinates(int x, int y) {
        Coordinates coordinates = new Coordinates();
        try {
            if (coordinates.setY(x) && coordinates.setX(y)) {
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


    public boolean checkAverageMark(int id, long arg2) {
        transport.send(new Answer("Пожалуйста, введите средний балл группы "));
        try {
            if (studyGroup.setAverageMark(arg2)) {
                baseManager.updateBase(2,String.valueOf(arg2),id);
                return true;
            } else {
                transport.send(new Answer("Ошибка"));
                transport.send(new Answer("Число не подходит"));
                return false;
            }
        } catch (NumberFormatException | SQLException e) {
            transport.send(new Answer("Ошибка"));
            transport.send(new Answer("Вами было введено не число"));
            return false;
        }
    }

    public boolean checkAverageMark1(Long average, String idk1) {
        try {
            if (studyGroup.setAverageMark(average)) {
                int idk = Integer.parseInt(idk1);
                baseManager.updateBase(2,String.valueOf(average),idk);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | SQLException e) {
            return false;
        }
    }


    public boolean checkTransferredStudents(int id, int arg3) {
        transport.send(new Answer("Пожалуйста, введите количество иностранных студентов в группе "));
        try {
            if (studyGroup.setTransferredStudents(arg3)) {
                baseManager.updateBase(3,String.valueOf(arg3),id);
                return true;
            } else {
                transport.send(new Answer("Ошибка"));
                transport.send(new Answer("Не подходящее число"));
                return false;
            }
        } catch (NumberFormatException | SQLException e) {
            transport.send(new Answer("Ошибка"));
            transport.send(new Answer("Вами было введено не число"));
            return false;
        }
    }

    public boolean checkTransferredStudents1(int transferred,String idk1) {
        try {
            if (studyGroup.setTransferredStudents(transferred)) {
                int idk = Integer.parseInt(idk1);
                baseManager.updateBase(3,String.valueOf(transferred),idk);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | SQLException e) {
            return false;
        }
    }

    public boolean checkStudentsCount(int id, long arqument3 ) {
        try {
            if (studyGroup.setStudentsCount(arqument3)) {
                baseManager.updateBase(1,String.valueOf(arqument3),id);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | SQLException e) {
            return false;
        }
    }

    public boolean checkStudentsCount1(Long students, String idk1){
        try {
            if(studyGroup.setStudentsCount(students)){
                int idk = Integer.parseInt(idk1);
                baseManager.updateBase(1,String.valueOf(students),idk);
                return true;
            }else {
                return false;
            }
        } catch(NumberFormatException | SQLException e){
            return false;
        }
    }

    public boolean setSemester(){
        transport.send(new Answer("Выберите одно из предложенных годов обучения "));
        switch (transport.receive().getCommand().toLowerCase()){
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
                transport.send(new Answer("Ошибка"));
                transport.send(new Answer("Не верные данные"));
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

    public boolean checkPersonName(){
        System.out.println("Пожалуйста, введите имя старосты группы ");
        String groupAdmin =scanner.nextLine();
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

    public boolean cheakPersonInfo() {
        System.out.println("Пожалуйста, введите по очередно день рождения, вес, рост, и паспортныее данные старосты))) ");
        String birthday =scanner.nextLine();
        float height = Float.parseFloat(scanner.nextLine());
        int weight = Integer.parseInt(scanner.nextLine());
        String passportId =scanner.nextLine();
        if ((person.setBirthday(birthday))&&(person.setHeight(height))&&(person.setWeight(weight))&&(person.setPassportID(passportId))) {
            return true;
        } else {
            return false;
        }
    }



    public boolean cheakPersonInfo1(String adminName,String birthday,float height,int weight,String passportID) {
        if ((person.setName(adminName))&&(person.setBirthday(birthday))&&(person.setHeight(height))&&(person.setWeight(weight))&&(person.setPassportID(passportID))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean makeStudyGroup() {

        studyGroup.setPerson(person);
        collection.put(id,studyGroup);
        return true;
    }

    public boolean makeStudyGroup1() {

        studyGroup.setPerson(person);
        collection.put(id,studyGroup);
        return true;
    }
}
