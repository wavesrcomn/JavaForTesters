package distance;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double calcDistance (){
        System.out.println(this.x + " " + x + " " + this.y + " " + y);
        return Math.sqrt(Math.pow((this.x - x), 2) * Math.pow((this.y - y), 2));

    }

}
