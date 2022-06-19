package webapp;


import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;


//@Entity
//@Table(name = "Result")
@ManagedBean
public class Result implements Serializable {

//    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Transient
//    private static final long serialVersionUID = 4L;
//    @Column(name="x_value")
    private double x_value;
//    @Column(name="y_value")
    private double y_value;
//    @Column(name="r_value")
    private double r_value;
//    @Column(name="result")
    private String result;
//    @Column(name="requestTime")
    private String requestTime;


    public Result() {
    }

    public Result(double xValue, double yValue, double rValue, String result, String requestTime) {
        this.x_value = xValue;
        this.y_value = yValue;
        this.r_value = rValue;
        this.result = result;
        this.requestTime = requestTime;
    }

    public double getX_value() {
        return x_value;
    }

    public void setX_value(double x_value) {
        this.x_value = x_value;
    }

    public double getY_value() {
        return y_value;
    }

    public void setY_value(double y_value) {
        this.y_value = y_value;
    }

    public double getR_value() {
        return r_value;
    }

    public void setR_value(double r_value) {
        this.r_value = r_value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }


}