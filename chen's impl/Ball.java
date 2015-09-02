package edu.cmu.chen;

import java.util.ArrayList;
import java.util.List;

public class Ball {
    int radius;
    Point pos;

    /** 
     * Number of pixels per second
     */
    Velocity v;

    public Ball(int radius) {
        this.radius = radius;
    }

    public void setPosition(int x, int y) {
        pos = new Point(x, y);
    }   
        
    public void setVelocity(int vx, int vy){
        v = new Velocity(vx, vy);
    }
        
    // return two vectors
    // pixel between range and radius
    public List<Point> getInRange(int[][] board, int range){
        List<Point> result = new ArrayList<Point>();
        List<Point> inRangeList = new ArrayList<Point>();
        int numRow = board.length;
        int numCol = board[0].length;
        
        for(int i=-range; i <=range; i++){
            int difference = range - Math.abs(i);
            for(int j=-difference; j <= difference; j++){
                if(pos.x + i >=0 && pos.x + i < numCol && pos.y + j >= 0 && pos.y + j < numRow) {
                    if(board[(int) (pos.y+j)][(int) (pos.x+i)]==1){
                        inRangeList.add(new Point(pos.y+j, pos.x+i));
                    }
                }
            }
        }

        List<Point> collideList = getCollide(board);
        inRangeList.removeAll(collideList);
         
        return inRangeList;
    }
     
    // pixel inside radius
    public List<Point> getCollide(int [][] board){
        int numRow = board[0].length;
        int numCol = board.length;
        List<Point> result = new ArrayList<Point>();
        for(int i=-radius; i <= radius; i++){
            int difference = radius - Math.abs(i);
            for(int j= -difference; j <= difference;j++){
                /* Check is valid range or not */
                if(pos.x + i >= 0 && pos.x + i < numCol && pos.y + j >= 0 && pos.y + j < numRow) {
                    // check the board == 1 or not
                    if(board[(int) (pos.y+j)][(int) (pos.x+i)]==1){
                        result.add(new Point(pos.y+j, pos.x+i));
                    }
                }
            }
        }
        return result;
    }
}
