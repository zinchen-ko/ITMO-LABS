package main.java;

import java.util.List;

public class Response {
    private double[] answer;
    private List<Double> errRates;
    private int iterationCount;

    public Response(double[] answer, List<Double> errRates, int iterationCount) {
        this.answer = answer;
        this.errRates = errRates;
        this.iterationCount = iterationCount;
    }

    public double[] getAnswer() {
        return answer;
    }

    public void setAnswer(double[] answer) {
        this.answer = answer;
    }

    public List<Double> getErrRates() {
        return errRates;
    }

    public void setErrRates(List<Double> errRates) {
        this.errRates = errRates;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }
}