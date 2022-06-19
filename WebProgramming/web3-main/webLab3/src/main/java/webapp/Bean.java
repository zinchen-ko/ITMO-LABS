package webapp;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "bean")
@ApplicationScoped
public class Bean implements Serializable {

    private double x;
    private double y;
    private double r;
    private List<Result> resultList;
    private BaseManager manager;
    private ArrayList<GraphicDot> graphicDots;
    private static final double HEIGHT_OF_AREA = 400;
    private static final double WIDTH_OF_AREA = 400;

    public Bean() {
    }

    public void addDot() throws SQLException, ClassNotFoundException {
        if (resultList == null) {
            resultList = new ArrayList<>();
        }
        if (manager == null) {
            manager = new BaseManager();
        }
        Result result;
        if (isHit(x, y, r)) {
            result = new Result(x, y, r, "Попал", (new SimpleDateFormat("HH:mm:ss")).format(new Date()));
        } else {
            result = new Result(x, y, r, "Не попал", (new SimpleDateFormat("HH:mm:ss")).format(new Date()));
        }
        resultList.add(result);
        manager.addUser(x, y, r, "Попал", (new SimpleDateFormat("HH:mm:ss")).format(new Date()));
    }

    private boolean isHit(double x, double y, double r){
        if (x>=0 && y>=0) {
            return y*2 < r - x;
        } else if(x<0 && y>0) {
            return (x <= r && y <= r/2);
        } else if(x<=0 && y<=0) {
            return false;
        } else if(x>=0 && y<=0) {
            return (Math.sqrt(x * x + y * y) <= r);
        } else {
            return false;
        }
    }

    private double countRealX(double x, double r){
        double pixelsInOneRadiusX = (WIDTH_OF_AREA + 0) / 2.5;
        return WIDTH_OF_AREA / 2 + pixelsInOneRadiusX * (x / r);
    }

    private double countRealY(double y, double r){
        double pixelsInOneRadiusY = (HEIGHT_OF_AREA + 0) / 2.5;
        return HEIGHT_OF_AREA / 2 - pixelsInOneRadiusY * (y / r);
    }

    private ArrayList<GraphicDot> getGraphicDotsFromResults(List<Result> results){

        ArrayList<GraphicDot> graphicDots = new ArrayList<>();

        for (Result result: results){
            double realX = countRealX(result.getX_value(), r);
            double realY = countRealY(result.getY_value(), r);
            String color = isHit(result.getX_value(), result.getY_value(), getR()) ? "green" : "red";
            graphicDots.add(new GraphicDot(realX, realY, color));
        }
        return graphicDots;
    }

    public void clear(){
        setResultList(new ArrayList<>());
        x = 0;
        y = 0;
        r = 1;
        manager.clear();
    }

    public List<Result> getResultList() {
        if (resultList == null) { resultList = new ArrayList<>(); }
        return resultList;
    }

    public void setResultList(ArrayList<Result> resultList) {
        this.resultList = resultList;
    }

    public ArrayList<GraphicDot> getGraphicDots() {
        graphicDots = getGraphicDotsFromResults(getResultList());
        return graphicDots;
    }

    public void setGraphicDots(ArrayList<GraphicDot> graphicDots) {
        this.graphicDots = graphicDots;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
