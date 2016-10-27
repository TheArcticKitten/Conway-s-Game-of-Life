import java.io.*;
import java.awt.*;
import java.util.*;
public class ConwaysGameOfLife
{
	public static void main(String[] sArgs)throws IOException
	{
		
	}
}

class Board
{
	boolean[][] cells;
	boolean[][] nextState;
	int length;
	int width;
	public Board(int length, int width)
	{
		cells = new boolean[length][width];
		nextState= new boolean[length][width];
		this.length = length;
		this.width = width;
	}

	public int getAliveNeighbors(int row, int col)
	{
		int ret = 0;
		if(currentState(row+1, col-1))ret++;
		if(currentState(row+1, col))ret++;
		if(currentState(row+1, col+1))ret++;
		if(currentState(row, col-1))ret++;
		if(currentState(row, col+1))ret++;
		if(currentState(row-1, col-1))ret++;
		if(currentState(row-1, col))ret++;
		if(currentState(row-1, col+1))ret++;
		return ret;
	}

	public void act(int steps)
	{
		for(int z = 0; z < steps; z++)
		{
			for(int i = 0; i < length;i++)
			{
				for(int j = 0; j < width;j++)
				{
					if(cells[i][j])
					{
						if(getAliveNeighbors(i, j) < 2)cells[i][j] = false;
						else if(getAliveNeighbors(i, j) > 3)cells[i][j] = false;
					}
					else if(getAliveNeighbors(i, j) == 3)cells[i][j] = true;	
				}
			}
		}

	}

	public boolean currentState(int row, int col)
	{
		if(row < 0)return cells[length - row][col];
		else if(row > 0)currentState(row - length, col);
		if(col < 0)return cells[row][length - col];
		else if(col > 0)currentState(row, col - length);
		return cells[row][col];
	}

	public void randomize()
	{
		for(int i = 0; i < length;i++)
		{
			for(int j = 0 ; j < width;j++)
			{
				if(new Random().nextInt(100) <= 20)cells[i][j] = true; 
				else cells[i][j] = false;
			}
		}
	}
}

class BoardGraphics extends JFrame
{
	int pixel;
	int width
	public BoardGraphics()	
}