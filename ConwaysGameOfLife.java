import java.io.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ConwaysGameOfLife
{
	public static void main(String[] sArgs)throws IOException
	{
		final int resHeight = 1920;
		final int resWidth = 1080;
		Board oB = new Board(1000,900);
		oB.randomize();
		GraphicsMain oGUI = new GraphicsMain(resHeight, resWidth, oB);
		while(true)
		{
			//try{Thread.sleep(500);}catch(InterruptedException e){}
			oB.act(1);
			oGUI.update();
		}
	}
}

class Board
{
	boolean[][] cells;
	boolean[][] nextState;
	int height;
	int width;
	public Board(int height, int width)
	{
		cells = new boolean[height][width];
		nextState= new boolean[height][width];
		this.height = height;
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
			for(int i = 0; i < height;i++)
			{
				for(int j = 0; j < width;j++)
				{
					if(cells[i][j])
					{
						if(getAliveNeighbors(i, j) < 2)nextState[i][j] = false;
						else if(getAliveNeighbors(i, j) > 3)nextState[i][j] = false;
					}
					else if(getAliveNeighbors(i, j) == 3)nextState[i][j] = true;	
				}
			}
			//cells = nextState;
			for(int i = 0; i < height;i++)
			{
				for(int j = 0; j < width;j++)
				{
					cells[i][j] = nextState[i][j];	
				}
			}
		}

	}

	public boolean currentState(int row, int col)
	{
		if(row < 0)return currentState(height + row, col);
		else if(row >= height)return currentState(row - height, col);
		if(col < 0)return currentState(row, width + col);
		else if(col >= width)return currentState(row, col - width);
		return cells[row][col];
	}

	public void randomize()
	{
		for(int i = 0; i < height;i++)
		{
			for(int j = 0 ; j < width;j++)
			{
				if(new Random().nextInt(100) <= 20)cells[i][j] = true; 
				else cells[i][j] = false;
			}
		}
	}
}

class GraphicsMain extends JFrame
{
	Board board;
	GraphicsPanel gPan;
	int resHeight;
	int resWidth;

	public GraphicsMain(int resHeight, int resWidth, Board board)
	{
		this.board = board;//problem?
		this.resHeight = resHeight;
		this.resWidth = resWidth;
		setTitle("Conway's Game of Life");
		setSize(resHeight, resWidth);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gPan = new GraphicsPanel();
		add(gPan);
		setBackground(Color.black);
		setVisible(true);
	}


	public void update()
	{
		gPan.draw();
	}

	class GraphicsPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			setBackground(Color.BLACK);
			int pixelSize = (resHeight * resWidth)/(board.height * board.width);
			int rectHeight = resHeight/board.height;
			int rectWidth = resWidth/board.height;
			//setBackground(Color.black);
			g.setColor(Color.white);
			for(int i = 0; i < board.height;i++)
			{
				for(int j = 0; j < board.width;j++)
				{
					//g.drawRect(rectWidth*i, rectHeight*j, rectWidth, rectHeight);
					if(board.currentState(i, j))g.fillRect(rectWidth*i, rectHeight*j, rectWidth, rectHeight);
				}
			}
		}

		public void draw()
		{
			//setBackground(Color.BLACK);
			repaint();
		}
	}
}
