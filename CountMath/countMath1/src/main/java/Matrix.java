package main.java;

public class Matrix {
    private double[][] srcMatrix;
    private double errRate;

    public Matrix() {
    }

    public Matrix(double[][] srcMatrix, double errRate) {
        this.srcMatrix = srcMatrix;
        this.errRate = errRate;
    }

    public double[][] getSrcMatrix() {
        return srcMatrix;
    }

    public void setSrcMatrix(double[][] srcMatrix) {
        this.srcMatrix = srcMatrix;
    }

    public double getErrRate() {
        return errRate;
    }

    public void setErrRate(double errRate) {
        this.errRate = errRate;
    }
}
