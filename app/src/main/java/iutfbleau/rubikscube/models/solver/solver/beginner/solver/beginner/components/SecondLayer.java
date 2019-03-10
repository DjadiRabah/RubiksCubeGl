package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.solver.SolverComponent;

public class SecondLayer extends SolverComponent
{
	public SecondLayer() 
	{
		super();
	}
	
	public ArrayList<RotationEvent> solveEdgeLeft()
	{
		ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		newRotations.add(new RotationEvent(Rotation.UP,0));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,0));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
		return newRotations;
	}
	
	public ArrayList<RotationEvent> solveEdgeRight()
	{
		ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		return newRotations;
	}

	@Override
	public ArrayList<RotationEvent> solve(Cube cube)
	{		
		for(int i = 0; i < cube.getSize(); i++)
		{
			this.rotations.add(new RotationEvent(Rotation.DOWN,i));
			this.rotations.add(new RotationEvent(Rotation.DOWN,i));
		}
		
		for(int i = 0; i < this.rotations.size(); i++)
		{
			RotationEvent rotation = this.rotations.get(i);
			cube.rotate(rotation.getDirection(),rotation.getIndex());
		}
		
		for(int currentEdge = 0; currentEdge < 4; currentEdge++)
		{
		
			int colorFront = cube.getSquare(Cube.FRONT).getColor();
			int colorLeft = cube.getSquare(Cube.LEFT).getColor();
			
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			
			if ((cube.getSquare(Cube.FRONT).getColor(0,1) == colorFront)
					&& (cube.getSquare(Cube.TOP).getColor(2,1) == colorLeft))
			{
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.LEFT).getColor(0,1) == colorFront)
					&& (cube.getSquare(Cube.TOP).getColor(1,0) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.LEFT).getColor(1,2) == colorFront)
					&& (cube.getSquare(Cube.FRONT).getColor(1,0) == colorLeft))
			{
				newRotations.addAll(this.solveEdgeLeft());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.LEFT).getColor(1,0) == colorFront)
					&& (cube.getSquare(Cube.BACK).getColor(1,2) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeLeft());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));		
			}
			
			else if ((cube.getSquare(Cube.TOP).getColor(0,1) == colorFront)
					&& (cube.getSquare(Cube.BACK).getColor(0,1) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
			}
			
			else if ((cube.getSquare(Cube.TOP).getColor(2,1) == colorFront)
					&& (cube.getSquare(Cube.FRONT).getColor(0,1) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
			}
			
			else if ((cube.getSquare(Cube.TOP).getColor(1,0) == colorFront)
					&& (cube.getSquare(Cube.LEFT).getColor(0,1) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
			}
			
			else if ((cube.getSquare(Cube.TOP).getColor(1,2) == colorFront)
					&& (cube.getSquare(Cube.RIGHT).getColor(0,1) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
			}
			
			else if ((cube.getSquare(Cube.RIGHT).getColor(1,2) == colorFront)
					&& (cube.getSquare(Cube.BACK).getColor(1,0) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
			}
			
			else if ((cube.getSquare(Cube.FRONT).getColor(1,2) == colorFront)
					&& (cube.getSquare(Cube.RIGHT).getColor(1,0) == colorLeft))
			{
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
			}
			
			else if ((cube.getSquare(Cube.BACK).getColor(1,0) == colorFront)
					&& (cube.getSquare(Cube.RIGHT).getColor(1,2) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.RIGHT).getColor(1,0) == colorFront)
					&& (cube.getSquare(Cube.FRONT).getColor(1,2) == colorLeft))
			{
				newRotations.addAll(this.solveEdgeRight());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.RIGHT).getColor(0,1) == colorFront)
					&& (cube.getSquare(Cube.TOP).getColor(1,2) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.BACK).getColor(0,1) == colorFront)
					&& (cube.getSquare(Cube.TOP).getColor(0,1) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			else if ((cube.getSquare(Cube.BACK).getColor(1,2) == colorFront)
					&& (cube.getSquare(Cube.LEFT).getColor(1,0) == colorLeft))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,1));
				newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				newRotations.addAll(this.solveEdgeLeft());
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
				newRotations.addAll(this.solveEdgeLeft());
			}
			
			if(currentEdge < 3)
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,1));
				newRotations.add(new RotationEvent(Rotation.LEFT,2));
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
