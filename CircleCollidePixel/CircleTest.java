package physics;

import java.awt.Point;
import java.util.List;

public class CircleTest {
	public static void main(String args[]){
		Circle circle = new Circle(3);
		circle.setPosition(3, 3);
		int[][] board ={{0,0,0,1,0,1,0,0},
						{0,0,1,1,1,0,0,0},
						{0,1,1,1,1,1,0,0},
						{1,1,1,1,1,1,1,0},
						{0,1,1,1,1,1,0,0},
						{0,0,1,1,1,0,0,0},
						{0,0,0,1,0,0,0,0},
						{0,0,0,0,0,0,0,0}
						};
		List<Point> points = circle.getCollide(board);
		List<Point> inRange = circle.getInRange(board, 5);
		}
}
