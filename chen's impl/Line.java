package edu.cmu.chen;

public class Line implements Comparable<Line> {
    public Point startPoint;
    public Point endPoint;

    public Line(int startX, int startY, int endX, int endY) {
        startPoint = new Point(startX, startY);
        endPoint = new Point(endX, endY);
    }

    public Line(Point start, Point end) {
        startPoint = start.clonePoint();
        endPoint = end.clonePoint();
    }

    @Override
    public int compareTo(Line l) {
        if ((startPoint.compareTo(l.startPoint) == 0 && endPoint.compareTo(l.endPoint) == 0) ||
                (endPoint.compareTo(l.startPoint) == 0 && startPoint.compareTo(l.endPoint) == 0))
            return 0;
        return 1;
    }

    @Override
    public String toString() {
        return startPoint + "->" + endPoint;
    }

    public Line cloneLine() {
        Line newL = new Line(startPoint.clonePoint(), endPoint.clonePoint());
        return newL;
    }

}