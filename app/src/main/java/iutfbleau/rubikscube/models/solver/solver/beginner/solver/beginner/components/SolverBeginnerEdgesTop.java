package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import java.util.ArrayList;
import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.solver.SolverComponent;

public class SolverBeginnerEdgesTop extends SolverComponent
{
	public SolverBeginnerEdgesTop() 
	{
		super();
	}
	
	private boolean checkEdge(Cube cube, int face)
	{
		int color = cube.getSquare(face).getColor();
		if (cube.getSquare(face).getColor(0,1) == color)
		{
			return true;
		}
		return false;
	}
	
	private ArrayList<RotationEvent> solveEdgesClockwise()
	{
		ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.UP,0));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		return newRotations;
	}
	
	private ArrayList<RotationEvent> solveEdgesCounterClockwise()
	{
		ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.UP,0));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		return newRotations;
	}

	@Override
	public ArrayList<RotationEvent> solve(Cube cube) 
	{
		while(!this.checkEdge(cube, Cube.LEFT) || !this.checkEdge(cube, Cube.FRONT) || !this.checkEdge(cube, Cube.RIGHT) || !this.checkEdge(cube, Cube.RIGHT))
		{
			int edgesSolved = 0;
			
			if(this.checkEdge(cube, Cube.LEFT))
			{
				edgesSolved++;
			}
			if(this.checkEdge(cube, Cube.FRONT))
			{
				edgesSolved++;
			}
			if(this.checkEdge(cube, Cube.RIGHT))
			{
				edgesSolved++;
			}
			if(this.checkEdge(cube, Cube.BACK))
			{
				edgesSolved++;
			}
			
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			
			if(edgesSolved == 0)
			{	
				newRotations.addAll(this.solveEdgesCounterClockwise());	
			}
			
			else if(edgesSolved == 1)
			{
				if(this.checkEdge(cube, Cube.LEFT))
				{
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					newRotations.add(new RotationEvent(Rotation.LEFT,1));
					newRotations.add(new RotationEvent(Rotation.LEFT,2));
				}
				else if(this.checkEdge(cube, Cube.FRONT))
				{
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					newRotations.add(new RotationEvent(Rotation.LEFT,1));
					newRotations.add(new RotationEvent(Rotation.LEFT,2));
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					newRotations.add(new RotationEvent(Rotation.LEFT,1));
					newRotations.add(new RotationEvent(Rotation.LEFT,2));
				} 
				else if(this.checkEdge(cube, Cube.RIGHT))
				{
					newRotations.add(new RotationEvent(Rotation.RIGHT,0));
					newRotations.add(new RotationEvent(Rotation.RIGHT,1));
					newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				}
				for(int i = 0; i < newRotations.size(); i++)
				{
					RotationEvent rotation = newRotations.get(i);
					cube.rotate(rotation.getDirection(),rotation.getIndex());
				}
				this.rotations.addAll(newRotations);
				newRotations = new ArrayList<RotationEvent>();
				
				int colorLeft = cube.getSquare(Cube.LEFT).getColor();
				int colorFront = cube.getSquare(Cube.FRONT).getColor();
				int colorRight = cube.getSquare(Cube.RIGHT).getColor();
				if ((cube.getSquare(Cube.LEFT).getColor(0,1) == colorRight)
					&& (cube.getSquare(Cube.FRONT).getColor(0,1) == colorLeft)
					&& (cube.getSquare(Cube.RIGHT).getColor(0,1) == colorFront))
				{
					newRotations.addAll(this.solveEdgesClockwise());
				}
				
				else if ((cube.getSquare(Cube.LEFT).getColor(0,1) == colorFront)
						&& (cube.getSquare(Cube.FRONT).getColor(0,1) == colorRight)
						&& (cube.getSquare(Cube.RIGHT).getColor(0,1) == colorLeft))
				{
					newRotations.addAll(this.solveEdgesCounterClockwise());
				}
			}
			
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
