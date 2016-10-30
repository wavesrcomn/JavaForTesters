package distance;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double calcDistance(Point b){

        return Math.sqrt(Math.pow((b.x - x), 2) + Math.pow((b.y - y), 2));

    }

}
