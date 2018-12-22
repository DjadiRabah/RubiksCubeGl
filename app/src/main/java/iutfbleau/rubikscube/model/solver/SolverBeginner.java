package iutfbleau.rubikscube.model.solver;

import iutfbleau.rubikscube.model.cube.Cube;
import iutfbleau.rubikscube.model.cube.face.Face;
import iutfbleau.rubikscube.model.cube.piece.Piece;
import iutfbleau.rubikscube.model.rotation.Rotation;

public class SolverBeginner implements Solver
{
	protected int[][] edges;
	protected int[][] corners;
	
	public SolverBeginner()
	{
		this.edges = new int[24][5];
		this.corners = new int[8][12];
	}
	private int getSquareColor(Face face)
	{
		int[][] colors = face.getColors();
		return colors[(colors.length - 1) / 2][(colors.length - 1) / 2];
	}
	protected int getSquareColor(Face face, int row, int col)
	{
		int[][] colors = face.getColors();
		return colors[row][col];
	}
	protected int[] setEdgeTop(Cube cube, int square)
	{
		int[] edge = new int[5];
		edge[0] = this.getSquareColor(cube.getSquare(square), 0, 1);
		
		if(square == Cube.LEFT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.TOP), 1, 0);
		else if(square == Cube.FRONT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.TOP), 2, 1);
		else if(square == Cube.RIGHT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.TOP), 1, 2);
		else if(square == Cube.BACK)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.TOP), 0, 1);
		else if(square == Cube.TOP)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.BACK), 0, 1);
		else if((square == Cube.DOWN))
			edge[1] = this.getSquareColor(cube.getSquare(Cube.FRONT), 2, 1);
		
		edge[2] = square;
		edge[3] = 0;
		edge[4] = 1;

		return edge;
	}
	
	protected int[] setEdgeDown(Cube cube, int square)
	{
		int[] edge = new int[5];
		edge[0] = this.getSquareColor(cube.getSquare(square), 2, 1);
		
		if(square == Cube.LEFT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.DOWN), 1, 0);
		else if(square == Cube.FRONT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.DOWN), 0, 1);
		else if(square == Cube.RIGHT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.DOWN), 1, 2);
		else if(square == Cube.BACK)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.DOWN), 2, 1);
		else if(square == Cube.TOP)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.FRONT), 0, 1);
		else if((square == Cube.DOWN))
			edge[1] = this.getSquareColor(cube.getSquare(Cube.BACK), 2, 1);
		
		edge[2] = square;
		edge[3] = 2;
		edge[4] = 1;

		return edge;
	}
	
	protected int[] setEdgeLeft(Cube cube, int square)
	{
		int[] edge = new int[5];
		edge[0] = this.getSquareColor(cube.getSquare(square), 1, 0);
		
		if(square == Cube.LEFT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.BACK), 1, 2);
		else if(square == Cube.FRONT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.LEFT), 1, 2);
		else if(square == Cube.RIGHT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.FRONT), 1, 2);
		else if(square == Cube.BACK)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.RIGHT), 1, 2);
		else if(square == Cube.TOP)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.LEFT), 0, 1);
		else if((square == Cube.DOWN))
			edge[1] = this.getSquareColor(cube.getSquare(Cube.LEFT), 2, 1);
		
		edge[2] = square;
		edge[3] = 1;
		edge[4] = 0;

		return edge;
	}
	
	protected int[] setEdgeRight(Cube cube, int square)
	{
		int[] edge = new int[5];
		edge[0] = this.getSquareColor(cube.getSquare(square), 1, 2);
		
		if(square == Cube.LEFT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.FRONT), 1, 0);
		else if(square == Cube.FRONT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.RIGHT), 1, 0);
		else if(square == Cube.RIGHT)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.BACK), 1, 0);
		else if(square == Cube.BACK)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.LEFT), 1, 0);
		else if(square == Cube.TOP)
			edge[1] = this.getSquareColor(cube.getSquare(Cube.RIGHT), 0, 1);
		else if((square == Cube.DOWN))
			edge[1] = this.getSquareColor(cube.getSquare(Cube.RIGHT), 2, 1);
		
		edge[2] = square;
		edge[3] = 1;
		edge[4] = 2;

		return edge;
	}
	protected void setEdges(Cube cube)
	{
		int currentEdge = 0;
		for(int currentSquare = Cube.TOP; currentSquare <= Cube.DOWN; currentSquare++)
		{
			this.edges[currentEdge] = this.setEdgeTop(cube, currentSquare);
			currentEdge++;
			this.edges[currentEdge] = this.setEdgeLeft(cube, currentSquare);
			currentEdge++;
			this.edges[currentEdge] = this.setEdgeRight(cube, currentSquare);
			currentEdge++;
			this.edges[currentEdge] = this.setEdgeDown(cube, currentSquare);
			currentEdge++;
		}
	}
	
	private void setWhiteTop(Cube cube)
	{
		if(this.getSquareColor(cube.getSquare(Cube.TOP)) != Piece.WHITE)
		{
			if(this.getSquareColor(cube.getSquare(Cube.LEFT)) == Piece.WHITE)
				cube.rotate(Rotation.CLOCKWISE);
			else if(this.getSquareColor(cube.getSquare(Cube.FRONT)) == Piece.WHITE)
				cube.rotate(Rotation.UP);
			else if(this.getSquareColor(cube.getSquare(Cube.RIGHT)) == Piece.WHITE)
				cube.rotate(Rotation.COUNTERCLOCKWISE);
			else if(this.getSquareColor(cube.getSquare(Cube.BACK)) == Piece.WHITE)
				cube.rotate(Rotation.DOWN);
			else if(this.getSquareColor(cube.getSquare(Cube.DOWN)) == Piece.WHITE)
			{
				cube.rotate(Rotation.UP);
				cube.rotate(Rotation.UP);
			}
		}	
	}
	
	private int getEdgeColor(int square, int row, int col)
	{
		int color = 0;
		for (int[] edge : this.edges) {
			if ((edge[2] == square) && (edge[3] == row) && (edge[4] == col)) {
				return edge[0];
			}
		}
		return color;
	}
	private int[] getEdgePosition(int colorA, int colorB)
	{
		int[] position = new int[2];
		for (int[] edge : this.edges) {
			if ((edge[0] == colorA) && (edge[1] == colorB)) {
				position[0] = edge[2];
			}
			if ((edge[0] == colorB) && (edge[1] == colorA)) {
				position[1] = edge[2];
			}
		}
		return position;
	}
	
	private void setWhiteCross(Cube cube)
	{
		for(int i = 0; i < 4; i++)
		{
			this.setEdges(cube);
			int colorTop = this.getSquareColor(cube.getSquare(Cube.TOP));
			int colorFront = this.getSquareColor(cube.getSquare(Cube.FRONT));
			if((this.getEdgeColor(Cube.TOP, 2, 1) != colorTop) || ((this.getEdgeColor(Cube.FRONT, 0, 1) != colorFront)))
			{
				int[] edgePosition = this.getEdgePosition(colorTop, colorFront);
				
				if (edgePosition[0] == Cube.TOP)
				{
					if(edgePosition[1] == Cube.LEFT)
					{
						cube.rotate(Rotation.DOWN, 0);
						cube.rotate(Rotation.DOWN, 0);
						cube.rotate(Rotation.RIGHT, 2);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						cube.rotate(Rotation.DOWN, 2);
						cube.rotate(Rotation.DOWN, 2);
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
					}
					else if(edgePosition[1] == Cube.BACK)
					{
						cube.rotate(Rotation.CLOCKWISE, 2);
						cube.rotate(Rotation.CLOCKWISE, 2);
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
					}
				}
				else if (edgePosition[0] == Cube.LEFT)
				{
					if (edgePosition[1] == Cube.TOP)
					{
						cube.rotate(Rotation.DOWN, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
					}
					else if (edgePosition[1] == Cube.DOWN)
					{
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);
					}
					else if (edgePosition[1] == Cube.BACK)
					{
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.RIGHT, 2);
						cube.rotate(Rotation.DOWN, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.RIGHT);
						cube.rotate(Rotation.DOWN, 2);
						cube.rotate(Rotation.LEFT, 0);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.RIGHT, 0);
						cube.rotate(Rotation.LEFT);	
					}
					else if (edgePosition[1] == Cube.FRONT)
					{
						cube.rotate(Rotation.CLOCKWISE, 0);		
					}
				}
				else if(edgePosition[0] == Cube.FRONT)
				{
					if(edgePosition[1] == Cube.LEFT)
					{
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);	
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);	
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						cube.rotate(Rotation.CLOCKWISE, 0);	
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);
					}
					else if(edgePosition[1] == Cube.DOWN)
					{
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);	
					}	
					else if (edgePosition[1] == Cube.TOP)
					{
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);
					}
				}
				else if (edgePosition[0] == Cube.RIGHT)
				{
					if(edgePosition[1] == Cube.TOP)
					{
						cube.rotate(Rotation.DOWN, 2);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
					}
					else if(edgePosition[1] == Cube.DOWN)
					{
						cube.rotate(Rotation.UP, 2);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 2);
					}
					else if(edgePosition[1] == Cube.BACK)
					{
						cube.rotate(Rotation.UP, 2);
						cube.rotate(Rotation.UP, 2);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 2);
						cube.rotate(Rotation.DOWN, 2);
					}
					else if(edgePosition[1] == Cube.FRONT)
					{
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
					}
				}
				else if (edgePosition[0] == Cube.BACK)
				{
					if(edgePosition[1] == Cube.TOP)
					{
						cube.rotate(Rotation.CLOCKWISE, 2);
						cube.rotate(Rotation.CLOCKWISE, 2);
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.UP, 2);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 2);
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						cube.rotate(Rotation.CLOCKWISE, 2);
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 2);
						cube.rotate(Rotation.UP, 2);
						cube.rotate(Rotation.COUNTERCLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 2);
					}
					else if(edgePosition[1] == Cube.LEFT)
					{
						cube.rotate(Rotation.COUNTERCLOCKWISE, 2);
						cube.rotate(Rotation.RIGHT, 2);
						cube.rotate(Rotation.CLOCKWISE, 2);
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);
					}
					else if(edgePosition[1] == Cube.DOWN)
					{
						cube.rotate(Rotation.RIGHT, 2);
						cube.rotate(Rotation.UP, 0);
						cube.rotate(Rotation.CLOCKWISE, 0);
						cube.rotate(Rotation.DOWN, 0);
					}
				}
				else if (edgePosition[0] == Cube.DOWN)
				{
					if(edgePosition[1] == Cube.LEFT)
					{
						cube.rotate(Rotation.RIGHT, 2);
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						cube.rotate(Rotation.LEFT, 2);
					}
					else if(edgePosition[1] == Cube.BACK)
					{
						cube.rotate(Rotation.LEFT, 2);
						cube.rotate(Rotation.LEFT, 2);
					}
					cube.rotate(Rotation.CLOCKWISE, 0);
					cube.rotate(Rotation.CLOCKWISE, 0);
				}
			}
			cube.rotate(Rotation.RIGHT);
		}
	}
	
	private void setCorners(Cube cube)
	{
		this.corners[0][0] = this.getSquareColor(cube.getSquare(Cube.FRONT), 0, 0);
		this.corners[0][1] = this.getSquareColor(cube.getSquare(Cube.TOP), 2, 0);
		this.corners[0][2] = this.getSquareColor(cube.getSquare(Cube.LEFT), 0, 2);
		
		this.corners[1][0] = this.getSquareColor(cube.getSquare(Cube.FRONT), 0, 2);
		this.corners[1][1] = this.getSquareColor(cube.getSquare(Cube.TOP), 2, 2);
		this.corners[1][2] = this.getSquareColor(cube.getSquare(Cube.LEFT), 0, 0);
		
	}
	
	private void setWhiteFace(Cube cube)
	{
		/*if((this.getSquareColor(cube.getSquare(Cube.FRONT), 0, 2) == Piece.WHITE) && (this.getSquareColor(cube.getSquare(Cube.TOP), 2, 2) == this.getSquareColor(cube.getSquare(Cube.RIGHT))))
		{
			for(int i = 0; i < 4; i++)
			{
				cube.rotate(Rotation.DOWN, 2);
				cube.rotate(Rotation.LEFT, 2);
				cube.rotate(Rotation.UP, 2);
				cube.rotate(Rotation.RIGHT, 2);
			}
		}*/
		
	}
	
	@Override
	public void solve(Cube cube) 
	{
		this.setWhiteTop(cube);
		this.setWhiteCross(cube);
		this.setWhiteFace(cube);
	}
}
