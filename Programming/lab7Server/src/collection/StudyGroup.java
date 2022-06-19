package collection;

import java.io.Serializable;
import java.util.Map;

/**
 * Хранит в себе данные группы
 */

public class StudyGroup implements Map.Entry<Integer, StudyGroup>, Comparable<StudyGroup>, Serializable {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private int transferredStudents; //Значение поля должно быть больше 0, Поле может быть null
    private long averageMark; //Значение поля должно быть больше 0, Поле может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
    String ownerLogin;

    public StudyGroup(int id, String name, Coordinates coordinates,String creationDate,long studentsCount,int transferredStudents,long averageMark,Semester semesterEnum,Person groupAdmin, String ownerLogin) {

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
        this.ownerLogin = ownerLogin;
    }
    public StudyGroup(){}

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", transferredStudents=" + transferredStudents +
                ", averageMark=" + averageMark +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    public int getId() {
        return id;
    }

    public boolean setId(Integer id) {
        if(id>0){
            this.id = id;
            return true;
        }else {
            return false;
        }

    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if(name!=null){
            this.name = name;
            return true;
        }else {
            return false;
        }

    }

    public void setNames(String name){
        this.name=name;
    }

    public Coordinates getCoordinates() { return coordinates; }

    public boolean setCoordinates(Coordinates coordinates) {
        if(coordinates!=null){
            this.coordinates = coordinates;
            return true;
        }else {
            return false;
        }
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

//    public java.util.Date getCreationDate() { return  creationDate; }
//
//    public boolean setCreationDate(java.util.Date creationDate) {
//        if(creationDate!=null){
//            this.creationDate = creationDate;
//            return true;
//        }else {
//            return false;
//        }
//    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate =creationDate;
    }

    public long getStudentsCount() { return studentsCount; }

    public boolean setStudentsCount(long studentsCount) {
        if(studentsCount>0){
            this.studentsCount = studentsCount;
            return true;
        }else {
            return false;
        }
    }

    public int getTransferredStudents(){ return transferredStudents; }

    public boolean setTransferredStudents(int transferredStudents) {
        if(transferredStudents>0){
            this.transferredStudents = transferredStudents;
            return true;
        }else {
            return false;
        }
    }

    public long getAverageMark(){ return averageMark; }

    public boolean setAverageMark(long averageMark){
        if(averageMark>0){
            this.averageMark = averageMark;
            return true;
        }else {
            return false;
        }
    }

    public Semester getSemesterEnum() { return semesterEnum; }

    public boolean setSemesterEnum(Semester semesterEnum) {
        if(semesterEnum!=null){
            this.semesterEnum=semesterEnum;
            return true;
        }else {
            return false;
        }
    }

    public Person getGroupAdmin(){ return groupAdmin; }

    public boolean setGroupAdmin(Person groupAdmin) {
        if(groupAdmin!=null){
            this.groupAdmin = groupAdmin;
            return true;
        }else {
            return false;
        }
    }

    public void setPerson(Person person) {
        this.groupAdmin = person;
    }


    @Override
    public Integer getKey() { return null; }

    @Override
    public StudyGroup getValue() { return null; }

    @Override
    public StudyGroup setValue(StudyGroup value) { return null; }

    @Override
    public int compareTo(StudyGroup studyGroup){
        return Long.compare(studyGroup.getStudentsCount(),this.getStudentsCount());
    }




// @Override
// public int compareTo(StudyGroup studyGroup) {
// if (studentsCount == studyGroup.getStudentsCount()) {
// return 0;
// }
// if (studentsCount < studyGroup.getStudentsCount()){
// return -1;
// }
// return 1;
// }
}
