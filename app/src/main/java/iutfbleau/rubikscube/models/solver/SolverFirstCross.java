package iutfbleau.rubikscube.models.solver;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.cube.face.Face;
import iutfbleau.rubikscube.models.rotation.Rotation;

public class SolverFirstCross extends SolverComponent
{
	protected int[][] edges;
	protected int[][] corners;
	
	public SolverFirstCross()
	{
		super();
		this.edges = new int[24][5];
		this.corners = new int[8][12];
	}
	private int getSquareColor(Face square)
	{
		int[][] colors = square.getColors();
		return colors[(colors.length - 1) / 2][(colors.length - 1) / 2];
	}
	protected int getSquareColor(Face square, int row, int col)
	{
		int[][] colors = square.getColors();
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
	
	private int getEdgeColor(int square, int row, int col)
	{
		int color = 0;
		for(int i = 0; i < this.edges.length; i++)
		{
			if((this.edges[i][2] == square) && (this.edges[i][3] == row) && (this.edges[i][4] == col))
			{
				return this.edges[i][0];
			}
		}
		return color;
	}
	private int[] getEdgePosition(int colorA, int colorB)
	{
		int[] position = new int[2];
		for(int i = 0; i < this.edges.length; i++)
		{
			if((this.edges[i][0] == colorA) && (this.edges[i][1] == colorB))
			{
				position[0] = this.edges[i][2];
			}
			if((this.edges[i][0] == colorB) && (this.edges[i][1] == colorA))
			{
				position[1] = this.edges[i][2];
			}
		}
		return position;
	}
	
	public ArrayList<RotationEvent> solve(Cube cube) 
	{
		this.setEdges(cube);
		for(int currentEdge = 0; currentEdge < 4; currentEdge++)
		{
			this.setEdges(cube);
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			int colorTop = this.getSquareColor(cube.getSquare(Cube.TOP));
			int colorFront = this.getSquareColor(cube.getSquare(Cube.FRONT));
			if((this.getEdgeColor(Cube.TOP, 2, 1) != colorTop) || ((this.getEdgeColor(Cube.FRONT, 0, 1) != colorFront)))
			{
				int[] edgePosition = this.getEdgePosition(colorTop, colorFront);
				
				if (edgePosition[0] == Cube.TOP)
				{
					if(edgePosition[1] == Cube.LEFT)
					{
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
						newRotations.add(new RotationEvent(Rotation.RIGHT, 2));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
					}
					else if(edgePosition[1] == Cube.BACK)
					{
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
					}
				}
				else if (edgePosition[0] == Cube.LEFT)
				{
					if (edgePosition[1] == Cube.TOP)
					{
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
					}
					else if (edgePosition[1] == Cube.DOWN)
					{
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
					}
					else if (edgePosition[1] == Cube.BACK)
					{
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.RIGHT, 2));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.RIGHT,0));
						newRotations.add(new RotationEvent(Rotation.RIGHT,1));
						newRotations.add(new RotationEvent(Rotation.RIGHT,2));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 0));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.RIGHT, 0));
						newRotations.add(new RotationEvent(Rotation.LEFT,0));
						newRotations.add(new RotationEvent(Rotation.LEFT,1));	
						newRotations.add(new RotationEvent(Rotation.LEFT,2));	
					}
					else if (edgePosition[1] == Cube.FRONT)
					{
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));		
					}
				}
				else if(edgePosition[0] == Cube.FRONT)
				{
					if(edgePosition[1] == Cube.LEFT)
					{
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));	
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));	
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));	
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
					}
					else if(edgePosition[1] == Cube.DOWN)
					{
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));	
					}	
					else if (edgePosition[1] == Cube.TOP)
					{
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
					}
				}
				else if (edgePosition[0] == Cube.RIGHT)
				{
					if(edgePosition[1] == Cube.TOP)
					{
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
					}
					else if(edgePosition[1] == Cube.DOWN)
					{
						newRotations.add(new RotationEvent(Rotation.UP, 2));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
					}
					else if(edgePosition[1] == Cube.BACK)
					{
						newRotations.add(new RotationEvent(Rotation.UP, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 2));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
					}
					else if(edgePosition[1] == Cube.FRONT)
					{
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
					}
				}
				else if (edgePosition[0] == Cube.BACK)
				{
					if(edgePosition[1] == Cube.TOP)
					{
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 2));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 2));
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 2));
					}
					else if(edgePosition[1] == Cube.LEFT)
					{
						newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.RIGHT, 2));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
					}
					else if(edgePosition[1] == Cube.DOWN)
					{
						newRotations.add(new RotationEvent(Rotation.RIGHT, 2));
						newRotations.add(new RotationEvent(Rotation.UP, 0));
						newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
						newRotations.add(new RotationEvent(Rotation.DOWN, 0));
					}
				}
				else if (edgePosition[0] == Cube.DOWN)
				{
					if(edgePosition[1] == Cube.LEFT)
					{
						newRotations.add(new RotationEvent(Rotation.RIGHT, 2));
					}
					else if(edgePosition[1] == Cube.RIGHT)
					{
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
					}
					else if(edgePosition[1] == Cube.BACK)
					{
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
						newRotations.add(new RotationEvent(Rotation.LEFT, 2));
					}
					newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
					newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
				}
				
			}
			newRotations.add(new RotationEvent(Rotation.RIGHT,0));
			newRotations.add(new RotationEvent(Rotation.RIGHT,1));
			newRotations.add(new RotationEvent(Rotation.RIGHT,2));
			
			for(int i = 0; i < newRotations.size(); i++)
			{
				RotationEvent rotation = newRotations.get(i);
				cube.rotate(rotation.getDirection(),rotation.getIndex());
			}
			this.rotations.addAll(newRotations);
		}
		
		return this.rotations;
	}
}
