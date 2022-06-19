package collection;

import java.io.Reader;
import java.io.Serializable;

public class Coordinates implements Serializable {
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
    public float getX() {
        return x;
    }

    public int getY() {
        return y;
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
