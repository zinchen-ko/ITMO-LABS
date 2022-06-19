package collection;

public class Coordinates {
    private float x;
    private int y; //Поле не может быть null


    public Coordinates(float x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    @Override
    public String toString() {
        return "{" +
                "Этаж' " + x +
                ", Кабинет " + y +
                '}';
    }

    public boolean setX(float x) {
        this.x = x;
        return true;

    }

    public boolean setY(int y) {
        if (y>0) {
            this.y = y;
            return true;
        } else {
            return false;
        }
    }
}