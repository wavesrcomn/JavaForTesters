package distance;

import java.util.Scanner;

public class Distance {

    public static void main(String[] args){

        double x;
        double y;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите координату x:");
        x = in.nextDouble();

        System.out.println("Введите координату y:");
        y = in.nextDouble();

        Point p = new Point(x, y);
        System.out.println("Расстояние до точки составляет: " + p.calcDistance());

    }

}
