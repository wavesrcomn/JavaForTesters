package distance;

import java.util.Scanner;

public class Distance {

    public static void main(String[] args){

        double xa;
        double ya;
        double xb;
        double yb;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите координату x точки A:");
        xa = in.nextDouble();

        System.out.println("Введите координату y точки A:");
        ya = in.nextDouble();

        System.out.println("Введите координату x точки B:");
        xb = in.nextDouble();

        System.out.println("Введите координату y точки B:");
        yb = in.nextDouble();

        PointA a = new PointA(xa, ya);
        PointB b = new PointB(xb, yb);
        System.out.println("Расстояние между точками составляет: " + calcDistance(a, b));

    }

    public static double calcDistance (PointA a, PointB b){
        return Math.sqrt(Math.pow((a.xa - b.xb), 2) * Math.pow((a.ya - b.yb), 2));

    }
}
