package edu.cmu.chen;

public class Point implements Comparable<Point> {
    /*
     * x is number of row in the image array; y is number of column in array.
     */
    public double x;
    public double y;

    public Point() {
        x = -1;
        y = -1;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Point clonePoint() {
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object pt) {
        if (!(pt instanceof Point)) {
            return false;
        }
        Point point = (Point) pt;
        if (point.x == this.x && point.y == this.y) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Point pt) {
        if (pt.x == x && pt.y == y)
            return 0;
        return (int) (x + y - ((Point) pt).x - ((Point) pt).y);
    }
}
