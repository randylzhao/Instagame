import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;



public class ImageUtil {

	/**
	 * @param args
	 */
	
	public static boolean in_bounds (int[][] image, int x, int y)
	{
		return x < image.length && y < image[0].length && x >=0 && y >= 0;
	}
	
	public static void check1(int[][] image, int x, int y, int rot)
	{
		if (!in_bounds(image, x+2, y+2)) return;
		//if (true) return;
		
		boolean makeThin = true;
		
		if (rot == 0)
		{
			if (image[x+0][y+0] != 0) makeThin = false;
			if (image[x+1][y+0] != 0) makeThin = false;
			if (image[x+2][y+0] != 0) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+0][y+2] != 1) makeThin = false;
			if (image[x+1][y+2] != 1) makeThin = false;
			if (image[x+2][y+2] != 1) makeThin = false;
		}
		else if (rot == 1)
		{
			if (image[x+0][y+0] != 1) makeThin = false;
			if (image[x+1][y+0] != 1) makeThin = false;
			if (image[x+2][y+0] != 1) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+0][y+2] != 0) makeThin = false;
			if (image[x+1][y+2] != 0) makeThin = false;
			if (image[x+2][y+2] != 0) makeThin = false;
		}
		else if (rot == 2)
		{
			if (image[x+0][y+0] != 1) makeThin = false;
			if (image[x+0][y+1] != 1) makeThin = false;
			if (image[x+0][y+2] != 1) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+2][y+0] != 0) makeThin = false;
			if (image[x+2][y+1] != 0) makeThin = false;
			if (image[x+2][y+2] != 0) makeThin = false;
		}
		else
		{
			if (image[x+0][y+0] != 0) makeThin = false;
			if (image[x+0][y+1] != 0) makeThin = false;
			if (image[x+0][y+2] != 0) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+2][y+0] != 1) makeThin = false;
			if (image[x+2][y+1] != 1) makeThin = false;
			if (image[x+2][y+2] != 1) makeThin = false;
		}
		
		if (makeThin) image[x+1][y+1] = 0;
	}
	
	
	public static void check2(int[][] image, int x, int y, int rot)
	{
		if (!in_bounds(image, x+2, y+2)) return;
		
		boolean makeThin = true;
		
		if (rot == 0)
		{
			if (image[x+1][y+0] != 0) makeThin = false;
			if (image[x+2][y+0] != 0) makeThin = false;
			if (image[x+2][y+1] != 0) makeThin = false;
			if (image[x+0][y+1] != 1) makeThin = false;
			if (image[x+1][y+2] != 1) makeThin = false;
			if (image[x+1][y+2] != 1) makeThin = false;
		}
		else if (rot == 1)
		{
			if (image[x+1][y+0] != 1) makeThin = false;
			if (image[x+0][y+1] != 0) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+2][y+1] != 1) makeThin = false;
			if (image[x+0][y+2] != 0) makeThin = false;
			if (image[x+1][y+2] != 0) makeThin = false;
		}
		else if (rot == 2)
		{
			if (image[x+0][y+0] != 0) makeThin = false;
			if (image[x+1][y+0] != 0) makeThin = false;
			if (image[x+0][y+1] != 0) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+1][y+2] != 1) makeThin = false;
			if (image[x+2][y+1] != 1) makeThin = false;
		}
		else
		{
			if (image[x+1][y+0] != 1) makeThin = false;
			if (image[x+0][y+1] != 1) makeThin = false;
			if (image[x+1][y+1] != 1) makeThin = false;
			if (image[x+2][y+1] != 0) makeThin = false;
			if (image[x+1][y+2] != 0) makeThin = false;
			if (image[x+2][y+2] != 0) makeThin = false;
		}
		
		if (makeThin)
		{
			image[x+1][y+1] = 0;
			//int a = x+1;
			//int b = y+1;
			//System.out.println(a + " " + b);
		}
	}
	
	public static void thin_lines (int[][] image)
	{
		for(int i = 0; i < image.length; i++)
		{
			for(int j = 0; j < image[0].length; j++)
			{
				for(int r = 0; r < 4; r++)
				{
					check1(image,i,j,r);
					check2(image,i,j,r);
				}
			}
		}
	}
	
	public static void print_array (int [][] A)
	{
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A[0].length; j++)
			{
				if (A[i][j] == 1) System.out.print("+");
				else System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	
	public static void remove_noise (int [][] image, int minsize)
	{
		int [][] visited = new int[image.length][image[0].length];
		for(int i = 0; i < image.length; i++)
		{
			for(int j = 0; j < image[0].length; j++)
			{
				DFS(image,i,j,0,minsize, visited);
			}
		}
	}
	
	public static int DFS (int [][] image, int r, int c, int count, int minsize, int[][] visited)
	{
		if (count >= 25)
		{
			System.out.println("asdf");
		}
		if (count > minsize)
		{
			return count;
		}
		if (visited[r][c] == 1)
		{
			return count;
		}
		else
		{
			visited [r][c] = 1;
		}
		
		if (image[r][c] == 0)
		{
			return count;
		}
		
		count++;
			
		boolean keep = count >= minsize;	
		
		if (in_bounds(image,r-1,c-1))
		{
			count = DFS(image,r-1,c-1,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r-1,c-0))
		{
			count = DFS(image,r-1,c-0,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r-1,c+1))
		{
			count = DFS(image,r-1,c+1,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r-0,c-1))
		{
			count = DFS(image,r-0,c-1,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r-0,c+1))
		{
			count = DFS(image,r-0,c+1,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r+1,c-1))
		{
			count = DFS(image,r+1,c-1,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r+1,c-0))
		{
			count = DFS(image,r+1,c+0,count,minsize,visited);
			keep = count >= minsize; 
		}
		if (in_bounds(image,r+1,c+1))
		{
			count = DFS(image,r+1,c+1,count,minsize, visited);
			keep = count >= minsize; 
		}

		
		if(!keep) image[r][c] = 0;
		visited[r][c] = 0;
		
		return count;
	}
	
	
	public static int[] getLine(int i)
	{
		switch(i)
		{
		case 0 : return new int[] {0,1,1,23,12};
		case 1 : return new int[] {1,23,12,1,1};
		case 2 : return new int[] {2,22,10,3,11};
		case 3 : return new int[] {3,22,1,1,12};
		case 4 : return new int[] {4,1,12,22,1};
		default : return new int[] {5,10,5,12,7};
		}
		
	}
	
	/* Nodes are marked as 1, vertices are marked as 0 */
	public static void get_path (int [][] image, int radius, int[] line)
	{
		
		switch(line[0])
		{
		case 0 :
		case 1 :
			clear(image, 1,1, radius);
			clear(image, 1,2, radius);
			clear(image, 1,3, radius);
			clear(image, 2,3, radius);
			clear(image, 2,4, radius);
			clear(image, 2,5, radius);
			clear(image, 2,6, radius);
			clear(image, 2,7, radius);
			clear(image, 2,8, radius);
			clear(image, 3,8, radius);
			clear(image, 4,8, radius);
			clear(image, 5,8, radius);
			clear(image, 6,8, radius);
			clear(image, 7,8, radius);
			clear(image, 8,8, radius);
			clear(image, 8,7, radius);
			clear(image, 8,6, radius);
			clear(image, 8,5, radius);
			clear(image, 7,5, radius);
			clear(image, 7,4, radius);
			clear(image, 8,4, radius);
			clear(image, 9,4, radius);
			clear(image, 10,4, radius);
			clear(image, 11,4, radius);
			clear(image, 12,4, radius);
			clear(image, 13,4, radius);
			clear(image, 14,4, radius);
			clear(image, 15,4, radius);
			clear(image, 16,4, radius);
			clear(image, 17,4, radius);
			clear(image, 18,4, radius);
			clear(image, 18,5, radius);
			clear(image, 18,6, radius);
			clear(image, 18,7, radius);
			clear(image, 18,8, radius);
			clear(image, 18,9, radius);
			clear(image, 18,10, radius);
			clear(image, 17,10, radius);
			clear(image, 16,10, radius);
			clear(image, 15,10, radius);
			clear(image, 14,10, radius);
			clear(image, 15,10, radius);
			clear(image, 15,11, radius);
			clear(image, 15,12, radius);
			clear(image, 16,12, radius);
			clear(image, 17,12, radius);
			clear(image, 18,12, radius);
			clear(image, 19,12, radius);
			clear(image, 20,12, radius);
			clear(image, 21,12, radius);
			clear(image, 22,12, radius);
			clear(image, 23,12, radius);
			break;
		case 2: //22,10,3,11
			clear(image, 22,10, radius);
			clear(image, 21,10, radius);
			clear(image, 20,10, radius);
			clear(image, 19,10, radius);
			clear(image, 18,10, radius);
			clear(image, 17,10, radius);
			clear(image, 16,10, radius);
			clear(image, 15,10, radius);
			clear(image, 14,10, radius);
			clear(image, 12,10, radius);
			clear(image, 11,10, radius);
			clear(image, 11,9, radius);
			clear(image, 11,8, radius);
			clear(image, 11,7, radius);
			clear(image, 11,6, radius);
			clear(image, 12,5, radius);
			clear(image, 13,5, radius);
			clear(image, 14,5, radius);
			clear(image, 15,5, radius);
			clear(image, 15,6, radius);
			clear(image, 15,7, radius);
			clear(image, 15,8, radius);
			clear(image, 15,9, radius);
			clear(image, 15,10, radius);
			clear(image, 15,11, radius);
			clear(image, 15,12, radius);
			clear(image, 14,12, radius);
			clear(image, 13,12, radius);
			clear(image, 12,12, radius);
			clear(image, 11,12, radius);
			clear(image, 11,11, radius);
			clear(image, 10,11, radius);
			clear(image, 9,11, radius);
			clear(image, 8,11, radius);
			clear(image, 7,11, radius);
			clear(image, 6,11, radius);
			clear(image, 5,11, radius);
			clear(image, 4,11, radius);
			clear(image, 3,11, radius);
			break;
		case 3: //22,1,1,12
		case 4:
			clear(image, 22,1, radius);
			clear(image, 22,2, radius);
			clear(image, 22,3, radius);
			clear(image, 22,4, radius);
			clear(image, 22,5, radius);
			clear(image, 22,6, radius);
			clear(image, 21,6, radius);
			clear(image, 20,6, radius);
			clear(image, 19,6, radius);
			clear(image, 18,6, radius);
			clear(image, 17,6, radius);
			clear(image, 16,6, radius);
			clear(image, 15,6, radius);
			clear(image, 14,6, radius);
			clear(image, 13,6, radius);
			clear(image, 13,5, radius);
			clear(image, 13,4, radius);
			clear(image, 13,3, radius);
			clear(image, 12,3, radius);
			clear(image, 11,3, radius);
			clear(image, 10,3, radius);
			clear(image, 9,3, radius);
			clear(image, 9,4, radius);
			clear(image, 9,5, radius);
			clear(image, 9,6, radius);
			clear(image, 9,7, radius);
			clear(image, 9,8, radius);
			clear(image, 8,8, radius);
			clear(image, 7,8, radius);
			clear(image, 6,8, radius);
			clear(image, 5,8, radius);
			clear(image, 4,8, radius);
			clear(image, 3,8, radius);
			clear(image, 2,8, radius);
			clear(image, 1,8, radius);
			clear(image, 1,9, radius);
			clear(image, 1,10, radius);
			clear(image, 1,11, radius);
			clear(image, 1,12, radius);
			break;
		default: //10,5,12,7
			clear(image, 10,5,radius);
			clear(image, 11,5,radius);
			clear(image, 12,5,radius);
			clear(image, 13,5,radius);
			clear(image, 14,5,radius);
			clear(image, 15,5,radius);
			clear(image, 16,5,radius);
			clear(image, 17,5,radius);
			clear(image, 17,6,radius);
			clear(image, 17,7,radius);
			clear(image, 17,8,radius);
			clear(image, 17,9,radius);
			clear(image, 17,10,radius);
			clear(image, 16,11,radius);
			clear(image, 15,11,radius);
			clear(image, 14,11,radius);
			clear(image, 13,11,radius);
			clear(image, 12,11,radius);
			clear(image, 11,11,radius);
			clear(image, 10,11,radius);
			clear(image, 8,11,radius);
			clear(image, 7,11,radius);
			clear(image, 6,11,radius);
			clear(image, 5,11,radius);
			clear(image, 4,11,radius);
			clear(image, 4,10,radius);
			clear(image, 4,9,radius);
			clear(image, 4,8,radius);
			clear(image, 4,7,radius);
			clear(image, 4,6,radius);
			clear(image, 4,5,radius);
			clear(image, 4,4,radius);
			clear(image, 5,4,radius);
			clear(image, 6,4,radius);
			clear(image, 7,4,radius);
			clear(image, 8,4,radius);
			clear(image, 9,4,radius);
			clear(image, 9,5,radius);
			clear(image, 9,6,radius);
			clear(image, 9,7,radius);
			clear(image, 10,7,radius);
			clear(image, 11,7,radius);
			clear(image, 12,7,radius);
			break;
			
			
		}
		
		
	}
	
	public static void clear (int[][] image, int i, int j, int radius)
	{
		for(int a = 0; a < radius; a++)
		{
			for(int b = 0; b < radius + 4; b++)
			{
				if (in_bounds(image, i*radius+a, j*radius+b)) image[i*radius+a][j*radius+b] = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		int [][] image = new int[][]
		{{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0}
		,{0,0,1,1,0,0,0,0,0,0,0,1,1,1,0,0}
		,{0,0,1,1,0,0,0,0,0,0,0,1,1,1,0,0}
		,{1,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0}
		,{1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,0}
		,{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0}
		,{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0}
		,{0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0}
		,{0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0}};
		
		int [][] image2 = new int[][] {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,1,1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,0,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		
		int [][] image3 = new int[][] {
				{0,0,1,0},
				{0,1,1,0},
				{0,0,1,0},
				{0,0,0,0},
		};
		
		int [][] image4 = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
		};
		
		
		print_array(image4);
		remove_noise(image4, 4);
		
		System.out.println("asdf");
		
		print_array(image4);
		
		
		
		
		ParseFile P = new ParseFile();

		try {
			image4 = P.parseFile("C:\\Users\\Randy Zhao\\Dropbox\\DropBall\\src\\applestore.ary");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		print_array(image3);
		System.out.println();
		for (int i = 0; i < 30; i++)
			thin_lines(image4);
		
		remove_noise(image4, 20);
		
		int [] line = getLine(1);
		get_path(image4,32,line);

		print_array(image3);
		
		System.out.println("done");
		
        try {
            PrintStream output = new PrintStream(new File("C:\\Users\\Randy Zhao\\Dropbox\\DropBall\\src\\output.txt"));
            

            for(int i =0;i<image4.length;i++){
                String sc ="";
                for (int j=0;j<image4[i].length;j++){
                        if (image4[i][j] == 1) sc+=image4[i][j] + " ";
                        else sc+= " "  + " ";
                }
                output.println(sc);
            }
            output.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        
        
        
		
		
		
	}

}
