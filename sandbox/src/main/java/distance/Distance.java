package distance;

import java.util.Scanner;

public class Distance {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        System.out.println("Введите координату x точки A:");
        double xa = in.nextDouble();

        System.out.println("Введите координату y точки A:");
        double ya = in.nextDouble();

        System.out.println("Введите координату x точки B:");
        double xb = in.nextDouble();

        System.out.println("Введите координату y точки B:");
        double yb = in.nextDouble();

        Point a = new Point(xa, ya);
        Point b = new Point(xb, yb);

        System.out.println("Расстояние между точками составляет: " + a.calcDistance(b));

    }

}
