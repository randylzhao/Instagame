package hackathon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Walkable {

	public boolean pathExist(int[][] board, int startX, int startY, int endX, int endY){
		// dfs with a stack
		Point start = new Point(startX, startY);
		Point end = new Point(endX, endY);
		// ensure start point and end point is not on the wall
		if(board[startX][startY] == 1 || board[endX][endY]==1){
			return false;
		}
		
		Stack<Point> path = new Stack<Point>();
		path.add(start);
		// board[startX][startY] = 1;
		
		Point newpos = new Point();
		while(!path.empty()){
			newpos = path.peek();
			if(newpos.equals(end)){
				return true;
			}
			board[newpos.x][newpos.y] = 1;
			List<Point> neighbours = getNeighbour(board, newpos);
			if(neighbours.size()==0){
				// no neighbour, backtrack.
				// board[newpos.x][newpos.y] = 0;
				path.pop();
			}else{
				for(int i=0; i <neighbours.size();i++){
					path.add(neighbours.get(i));
				}
			}
		}
		return false;
	}
	
	private List<Point> getNeighbour(int[][] board, Point currPoint){
		List<Point> neighbour = new ArrayList<Point>();
		// get north
		if(currPoint.x-1 >= 0 && board[currPoint.x-1][currPoint.y]!=1){
			neighbour.add(new Point(currPoint.x-1, currPoint.y));
		} 
		
		// get south
		if(currPoint.x+1 < board.length && board[currPoint.x+1][currPoint.y]!=1){
			neighbour.add(new Point(currPoint.x+1, currPoint.y));
		}
		
		// get west
		if(currPoint.y -1 >=0 && board[currPoint.x][currPoint.y-1]!=1){
			neighbour.add(new Point(currPoint.x, currPoint.y-1));
		}
		// get east
		if(currPoint.y+1 < board[0].length && board[currPoint.x][currPoint.y+1]!=1){
			neighbour.add(new Point(currPoint.x, currPoint.y+1));
		}
		
		return neighbour;
	}
	
}
