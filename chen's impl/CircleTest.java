package edu.cmu.chen;

import java.util.List;

public class CircleTest {
    public static void main(String args[]){
        Ball circle = new Ball(3);
        circle.setPosition(3, 3);
        int[][] board ={{0,0,0,1,0,1,0},
                        {0,0,1,1,1,0,0},
                        {0,1,1,1,1,1,0},
                        {1,0,1,1,1,1,1},
                        {0,1,1,1,1,1,0},
                        {0,0,1,0,1,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0}
        };
        List<Point> points = circle.getCollide(board);
        List<Point> inRange = circle.getInRange(board, 5);
        points.get(0);
    }
}