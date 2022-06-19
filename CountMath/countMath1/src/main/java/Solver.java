package main.java;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    public Response solve(Matrix request) {
        double[][] srcMatrix = request.getSrcMatrix();
        double errRate = request.getErrRate();

        if (!verification(srcMatrix)) {
            return null;
        }

        double[][] indexMatrix = new double[srcMatrix.length][srcMatrix.length];
        double[] d = new double[srcMatrix.length];
        for (int i = 0; i < srcMatrix.length; i++) {
            System.arraycopy(srcMatrix[i], 0, indexMatrix[i], 0, srcMatrix.length);
            d[i] = srcMatrix[i][srcMatrix.length];
        }

        //здесь матрица индексов превращается в "C"
        for (int i = 0; i < indexMatrix.length; i++) {
            for (int j = 0; j < indexMatrix.length; j++) {
                if (i != j) {
                    indexMatrix[i][j] /= -indexMatrix[i][i];
                }
            }
            d[i] /= indexMatrix[i][i];
            indexMatrix[i][i] = 0;
        }

        double max = Double.MIN_VALUE;
        for (double[] matrix : indexMatrix) {
            double rowSum = 0;
            for (int j = 0; j < indexMatrix.length; j++) {
                rowSum += Math.abs(matrix[j]);
            }
            max = Math.max(max, rowSum);
        }
        if (max >= 1) {
            System.out.println("Условие сходимости не выполнено!");
            return null;
        } else {
            System.out.println("Условие сходимости выполнено!");
        }

        int iterationCounter = 0;
        List<Double> errRates = new ArrayList<>();
        double[] answer = d.clone();
        double maxErrRate;
        do {
            maxErrRate = 0;
            double[] newAnswer = new double[answer.length];
            for (int i = 0; i < answer.length; i++) {
                for (int j = 0; j < answer.length; j++) {
                    newAnswer[i] += indexMatrix[i][j] * answer[j];
                }
                newAnswer[i] += d[i];
                maxErrRate = Math.max(maxErrRate, Math.abs(newAnswer[i] - answer[i]));
            }
            answer = newAnswer;
            errRates.add(maxErrRate);
            iterationCounter++;
        } while (maxErrRate > errRate);
        return new Response(answer, errRates, iterationCounter);
    }

    private boolean verification(double[][] indexMatrix) {
        if (predominanceOfMainDiagonal(indexMatrix)) {
            System.out.println("Диагональное преобладание достигнуто!");
            return true;
        } else {
            int count = factorial(indexMatrix.length);
            int max = indexMatrix.length - 1;
            int shift = max;
            double[] t;
            while (count > 0) {
                t = indexMatrix[shift];
                indexMatrix[shift] = indexMatrix[shift - 1];
                indexMatrix[shift - 1] = t;
                if (predominanceOfMainDiagonal(indexMatrix)) {
                    System.out.println("Диагональное преобладание было достигнуто перестановкой уравнений!");
                    return true;
                }
                count--;
                if (shift < 2) {
                    shift = max;
                } else {
                    shift--;
                }
            }
        }
        System.out.println("Невозможно достигнуть диагонального преобладания!");
        return false;
    }

    private int factorial(int a) {
        int fact = 1;
        while (a > 0) {
            fact *= a;
            a--;
        }
        return fact;
    }

    private boolean predominanceOfMainDiagonal(double[][] srcMatrix) {
        boolean isStrict = false;
        for (int i = 0; i < srcMatrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < srcMatrix.length; j++) {
                if (j != i) {
                    sum += Math.abs(srcMatrix[i][j]);
                }
            }
            if (Math.abs(srcMatrix[i][i]) >= sum) {
                if (Math.abs(srcMatrix[i][i]) > sum) {
                    isStrict = true;
                }
            } else {
                return false;
            }
        }
        return isStrict;
    }
}
