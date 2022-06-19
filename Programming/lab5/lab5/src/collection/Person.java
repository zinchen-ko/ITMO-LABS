package collection;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String birthday; //Поле не может быть null
    private float height; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 6, Поле может быть null

    public Person(String name, String birthday,float height,int weight,String passportID ){

        this.name=name;
        this.birthday=birthday;
        this.height=height;
        this.weight=weight;
        this.passportID=passportID;
    }

    public Person(){}

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }

    public String getName() { return name; }

    public boolean setName(String name) {
        if (name!=null){
         if (name.length() > 0)  {
             this.name=name;
             return true;
         }else {
             return false;
         }
        } else {
            return false;
        }
    }

    public String getBirthday() { return birthday; }

    public boolean setBirthday(String birthday) {

        if (birthday!=null) {
            this.birthday=birthday;

            return true;
        } else {
            return false;
        }
    }

    public float getHeight() { return height; }

    public boolean setHeight(float height) {
        if (height>0) {
            this.height=height;

            return true;
        } else {
            return false;
        }
    }

    public int getWeight() { return weight; }

    public boolean setWeight(int weight) {
        if (weight>0) {

            this.weight=weight;
            return true;
        } else {
            return false;
        }
    }

    public String getPassportID() { return passportID; }

    public boolean setPassportID(String passportID) {
        if ((passportID!=null)&&(passportID.length()>6)) {
            this.passportID=passportID;
            return true;
        }
        return false;
    }

}