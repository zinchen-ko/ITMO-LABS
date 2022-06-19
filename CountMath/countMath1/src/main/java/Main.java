package main.java;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Solver solver = new Solver();
        output(solver.solve(input()));

    }

    private static void output(Response response) {
        System.out.print("\n\nОтветы: ");
        System.out.println(Arrays.toString(response.getAnswer()));
        System.out.println("Количество итераций = " + response.getIterationCount());
        System.out.print("Вектор погрешностей: ");
        System.out.println(response.getErrRates().toString());
    }

    private static Matrix input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ввод данных из консоли - 0, из файла - 1");
        try {
            int userRequest = Integer.parseInt(scanner.nextLine().toLowerCase().trim());
            if (userRequest == 0) {
                return consoleInput();
            } else if (userRequest == 1) {
                return fileInput();
            } else {
                System.out.println("Была введена неправильная цифра!");
                System.exit(1);
                return null;
            }
        } catch (NumberFormatException e) {
            System.err.println("Была введена неправильная цифра!");
            System.exit(1);
            return null;
        }
    }

    private static Matrix consoleInput() {
        Scanner scanner = new Scanner(System.in);
        Matrix request = new Matrix();
        try {
            System.out.println("Введите размерность матрицы!");
            int arrSize = scanner.nextInt();
            double[][] srcMatrix = new double[arrSize][arrSize + 1];
            System.out.println("Введите точность:");
            request.setErrRate(scanner.nextDouble());
            System.out.println("Введите коэффициенты матрицы и свободные члены через проблелы и с разделение строк:");
            for (int i = 0; i < srcMatrix.length; i++) {
                for (int j = 0; j < srcMatrix[0].length; j++) {
                    srcMatrix[i][j] = scanner.nextDouble();
                }
            }
            request.setSrcMatrix(srcMatrix);
            return request;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Неправильный ввод!");
            System.exit(1);
            return request;
        }
    }

    private static Matrix fileInput() {
        Matrix request = new Matrix();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите путь к файлу:");
            scanner = new Scanner(new File(scanner.nextLine()));
            int arrSize = scanner.nextInt();
            double[][] srcMatrix = new double[arrSize][arrSize + 1];
            request.setErrRate(scanner.nextDouble());
            for (int i = 0; i < srcMatrix.length; i++) {
                for (int j = 0; j < srcMatrix[0].length; j++) {
                    srcMatrix[i][j] = scanner.nextDouble();
                }
            }
            request.setSrcMatrix(srcMatrix);
            return request;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Неправильный ввод!");
            System.exit(1);
            return request;
        }
    }
}
