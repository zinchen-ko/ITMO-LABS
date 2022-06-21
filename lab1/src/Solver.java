import java.util.ArrayList;

public class Solver {

    public Solver(){}

    public void solve() {

        // Иннициализация первого массива
        ArrayList<Short> firstArray = new ArrayList<>();

        for (int i = 22; i >= 2; i--)
            if (i % 2 == 0)
                firstArray.add((short) i);

        // Иннициализация второго массива
        ArrayList<Double> secondArray = new ArrayList<>();
        double minValue = Double.MIN_VALUE;

        for (int i = 0; i < 13; i++)
            secondArray.add(Math.random() * ((10.0 + minValue) - (-13.0)) + (-13.0));
        

        // Иннициализация третьего массива
        double[][] thirdArray = new double[11][13];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                if (firstArray.get(i) == 22) {
                    thirdArray[i][j] = Math.pow(Math.E,
                            Math.pow((Math.tan(secondArray.get(j)-1)/3), 3));
                } else if (firstArray.get(i) <= 20 && firstArray.get(i) >= 6) {
                    thirdArray[i][j] = Math.tan(Math.pow(Math.E,Math.atan((secondArray.get(j)-1.5)/23)));
                } else
                    thirdArray[i][j] = Math.pow(Math.sin(Math.pow(Math.pow(Math.E,secondArray.get(j)),
                                Math.cos(secondArray.get(j)))),
                        Float.parseFloat(String.valueOf(2/3))/Math.pow(Float.parseFloat(String.valueOf(2/3))*Math.tan(Math.sin(secondArray.get(j))),
                                Math.asin(Math.pow((secondArray.get(j) - 1.5)/23, 2))));
            }
        }

        printArray(thirdArray);
    }

    public void printArray(double[][] array) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.printf("%.3f",array[i][j]);
                System.out.print("   ");
            }
            System.out.println();
        }
    }
}
