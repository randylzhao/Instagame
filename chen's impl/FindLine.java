package edu.cmu.chen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/* Main problems:
 * 1. Extract edge points
 * 2. Connects points as a line; get starting point and endpoint
 * 3. Generate a complete path and connect it as a viable maze
 */

public class FindLine {

    public static void pl(Object o) {
        System.out.println(o);
    }

    public static void p(Object o) {
        System.out.print(o);
    }

    public static String toStr(int A[][]) {
        String s = "";
        int height = A.length;
        for (int row = 0; row < height; ++row) {
            s += Arrays.toString(A[row]) + "\n";
        }
        return s;
    }

    static TreeSet<Line> deepCopyLines(TreeSet<Line> lines) {
        TreeSet<Line> newLines = new TreeSet<Line>();
        Iterator<Line> iter = lines.iterator();

        while (iter.hasNext()) {
            Line ln = iter.next().cloneLine();
            newLines.add(ln);
        }

        return newLines;
    }

    public static void main(String[] args) throws Exception {
        (new FindLine()).findLine();
    }

    public void findLine() throws Exception {
        Scanner file = null;
        try { // in case the file's not there
            file = new Scanner(new File("./src/raw-data.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file\n");
        }

        int[][] table = new int[40][20];

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                table[i][j] = file.nextInt();
            }
        }

        getLines(table);
        pl(lines);
    }

    public static int countNeighbors(Point pt, int image[][]) {
        int linePointsCount = 0;

        for (int ii = -1; ii <= 1; ii++) {
            for (int jj = -1; jj <= 1; jj++) {
                int newX = (int) (pt.x + ii), newY = (int) (pt.y + jj);
                if (0 <= newX && newX <= image[0].length - 1 &&
                        0 <= newY && newY <= image.length - 1 &&
                        image[newY][newX] == 1 && (ii != 0 || jj != 0))
                    linePointsCount++;
            }
        }

        return linePointsCount;
    }

    /**
     * Assume the lines have one point in common. Return a new Line with the two points
     * that the lines don't share as endpoints.
     * @param l1
     * @param l2
     * @return
     */
    Line mergeConnectedLines(Line l1, Line l2) {
        Line newL = new Line(l1.startPoint.clonePoint(), new Point());
        if (l2.startPoint.equals(l1.startPoint)) {
            /* If l2's starting point == l1's starting point, replace */
            newL.startPoint = l2.endPoint.clonePoint();
            newL.endPoint = l1.endPoint.clonePoint();
        }
        else if (l2.startPoint.equals(l1.endPoint)) {
            /* If l2's starting point is l1's endpoint, replace */
            newL.endPoint = l2.endPoint.clonePoint();
        }
        else
            newL.endPoint = l2.startPoint.clonePoint();

        return newL;
    }

    TreeSet<Point> endPoints = new TreeSet<Point>();
    TreeSet<Line> lines = new TreeSet<Line>();

    public void getLines(int image[][]) throws Exception {
        /* Get dimensions of array */
        int height = image.length;
        int width = image[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                /* Use [i, j] as proposed starting point */
                if (image[i][j] == 1) {
                    Point furthest = new Point(j, i);
                    Point startPoint = furthest.clonePoint();
                    endPoints.add(startPoint);
                    /* Go through horizontal line to find an endpoint */
                    for (int deltaJ = 1; deltaJ <= width - 1 - j; deltaJ++) {
                        if (image[i][j+deltaJ] == 1) {
                            furthest.y = i;
                            furthest.x = j + deltaJ;

                            /* If this point is not an endpoint (kind of an "orphan" point),
                             * delete it
                             */
                            int numNeighbors = countNeighbors(furthest, image);
                            int newY = j + deltaJ + 1;
                            if ((numNeighbors == 1 && (newY > width - 1 || image[i][j+deltaJ+1] == 0)) ||
                                    (numNeighbors == 2 && newY <= width - 1 && image[i][j+deltaJ+1] == 1))
                                image[i][j+deltaJ] = 0;
                        }
                        else
                            break;
                    }

                    endPoints.add(furthest);
                    if (startPoint.compareTo(furthest) != 0)
                        lines.add(new Line(startPoint, furthest));

                    /* Go through vertical line in the same way */
                    furthest = new Point(j, i); /* Alloc a new Point */
                    for (int deltaI = 1; deltaI <= height - 1 - i; deltaI++) {
                        if (image[i+deltaI][j] == 1) {
                            furthest.y = i + deltaI;
                            furthest.x = j;

                            /* If this point is not an endpoint, delete it */
                            int numNeighbors = countNeighbors(furthest, image);
                            int newX = i + deltaI + 1;
                            if ((numNeighbors == 1 && (newX > height - 1 || image[newX][j] == 0)) ||
                                    (numNeighbors == 2 && newX <= height - 1 && image[newX][j] == 1))
                                image[i+deltaI][j] = 0;
                        }
                        else
                            break;
                    }

                    endPoints.add(furthest);
                    if (startPoint.compareTo(furthest) != 0)
                        lines.add(new Line(startPoint, furthest));

                    /* Go through diagonal line to bottom right */
                    furthest = new Point(j, i);
                    int smallerMaximum = height - 1 - i;
                    if (height - i > width - j)
                        smallerMaximum = width - 1 - j;
                    for (int delta = 1; delta <= smallerMaximum; delta++) {
                        if (image[i+delta][j+delta] == 1) {
                            furthest.y = i + delta;
                            furthest.x = j + delta;

                            /* If this point is not an endpoint, delete it */
                            int numNeighbors = countNeighbors(furthest, image);
                            int newX = i + delta + 1, newY = j + delta + 1;
                            if ((numNeighbors == 1 && (newX > height - 1 || newY > width - 1 || image[newX][newY] == 0)) ||
                                    (numNeighbors == 2 && newX <= height - 1 && newY <= width - 1 && image[newX][newY] == 1))
                                image[i+delta][j+delta] = 0;
                        }
                        else
                            break;
                    }

                    endPoints.add(furthest);
                    if (startPoint.compareTo(furthest) != 0)
                        lines.add(new Line(startPoint, furthest));
                }
            }
        }

        /* Do two rounds of merging lines */
        for (int i = 0; i <= 1; i++) {
            TreeSet<Line> removeList = new TreeSet<Line>(),
                          addList = new TreeSet<Line>();
            Iterator<Line> iterator1 = lines.iterator();
            while (iterator1.hasNext()) {
                Iterator<Line> iterator2 = lines.iterator();
                while (iterator2.hasNext()) {
                    Line first = iterator1.next(), second = iterator2.next();
                    if (first.startPoint.equals(second.startPoint) ||
                            first.endPoint.equals(second.startPoint) ||
                            first.startPoint.equals(second.endPoint) ||
                            first.startPoint.equals(second.startPoint)) {
                        /* If the two lines share a common endpoint */
                        /* Get the slopes of two lines */
                        /* slope = (y1 - y2) / (x2 - x1) */
                        double slopeFirst, slopeSecond;
                        if (first.startPoint.x - first.endPoint.x == 0)
                            slopeFirst = Double.POSITIVE_INFINITY;
                        else
                            slopeFirst = (first.startPoint.y - first.endPoint.y) / (first.startPoint.x - first.endPoint.x);

                        if (second.startPoint.x - second.endPoint.x == 0)
                            slopeSecond = Double.POSITIVE_INFINITY;
                        else
                            slopeSecond = (second.startPoint.y - second.endPoint.y)/ (second.startPoint.x - second.endPoint.x);

                        if (Math.abs(Math.atan(slopeFirst) - Math.atan(slopeSecond)) < Math.PI / 6) {
                            /* If angle is less than 30 degrees (pi / 6), merge them to the same line */
                            //lines.add(mergeConnectedLines(first, second));
                            //iterator1.remove();
                            //iterator2.remove();
                            removeList.add(first);
                            removeList.add(second);
                            addList.add(mergeConnectedLines(first, second));
                        }
                    }
                }
            }
        }
    }

}
