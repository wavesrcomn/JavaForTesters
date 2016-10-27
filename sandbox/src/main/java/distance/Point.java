package distance;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double calcDistance (){

        Point p = new Point(0, 0);
        return Math.sqrt(Math.pow((p.x - x), 2) + Math.pow((p.y - y), 2));

    }

}
