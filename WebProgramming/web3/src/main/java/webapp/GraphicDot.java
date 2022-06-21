package webapp;


import java.io.Serializable;
import java.text.SimpleDateFormat;

public class GraphicDot implements Serializable {

    private double realX;
    private double realY;
    private String color;

    public GraphicDot(){

    }

    public GraphicDot(double realX, double realY, String color) {
        this.realX = realX;
        this.realY = realY;
        this.color = color;
    }

    @Override
    public String toString(){
        return "x: " + realX + " y: " + realY + " color: " + color;
    }

    public double getRealX() {
        return realX;
    }

    public void setRealX(double realX) {
        this.realX = realX;
    }

    public double getRealY() {
        return realY;
    }

    public void setRealY(double realY) {
        this.realY = realY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}