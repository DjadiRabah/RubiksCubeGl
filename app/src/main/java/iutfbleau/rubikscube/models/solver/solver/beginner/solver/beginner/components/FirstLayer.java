package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.solver.SolverComponent;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;

public class FirstLayer extends SolverComponent
{
	public FirstLayer() 
	{
		super();
	}

	@Override
	public ArrayList<RotationEvent> solve(Cube cube) 
	{	
		int size = cube.getSize() - 1;
		
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		
		for(int currentCorner = 0; currentCorner < 4; currentCorner++)
		{
			int colorFront = cube.getSquare(Cube.FRONT).getColor();
			int colorRight = cube.getSquare(Cube.RIGHT).getColor();
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			if (((cube.getSquare(Cube.LEFT).getColor(0,0) == colorTop) && (cube.getSquare(Cube.TOP).getColor(0,0) == colorFront)
					&& (cube.getSquare(Cube.BACK).getColor(0,size) == colorRight))
				|| ((cube.getSquare(Cube.LEFT).getColor(0,0) == colorFront) && (cube.getSquare(Cube.TOP).getColor(0,0) == colorRight)
							&& (cube.getSquare(Cube.BACK).getColor(0,size) == colorTop)))
			{
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE, size));
			}
			
			else if (((cube.getSquare(Cube.RIGHT).getColor(0,size) == colorTop) && (cube.getSquare(Cube.TOP).getColor(0,size) == colorRight)
					&& (cube.getSquare(Cube.BACK).getColor(0,0) == colorFront))
				|| ((cube.getSquare(Cube.RIGHT).getColor(0,size) == colorRight) && (cube.getSquare(Cube.TOP).getColor(0,size) == colorFront)
						&& (cube.getSquare(Cube.BACK).getColor(0,0) == colorTop))
				|| ((cube.getSquare(Cube.RIGHT).getColor(0,size) == colorFront) && (cube.getSquare(Cube.TOP).getColor(0,size) == colorTop)
						&& (cube.getSquare(Cube.BACK).getColor(0,0) == colorRight))
					)
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, size));
			}
			
			else if (((cube.getSquare(Cube.TOP).getColor(size,0) == colorTop) && (cube.getSquare(Cube.LEFT).getColor(0,size) == colorFront)
					&& (cube.getSquare(Cube.FRONT).getColor(0,0) == colorRight))		
				|| ((cube.getSquare(Cube.TOP).getColor(size,0) == colorFront) && (cube.getSquare(Cube.LEFT).getColor(0,size) == colorRight)
						&& (cube.getSquare(Cube.FRONT).getColor(0,0) == colorTop))
				|| ((cube.getSquare(Cube.TOP).getColor(size,0) == colorRight) && (cube.getSquare(Cube.LEFT).getColor(0,size) == colorTop)
						&& (cube.getSquare(Cube.FRONT).getColor(0,0) == colorFront)))
			{
				newRotations.add(new RotationEvent(Rotation.DOWN, 0));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.UP, 0));
			}
			
			else if (((cube.getSquare(Cube.FRONT).getColor(size,0) == colorTop) && (cube.getSquare(Cube.LEFT).getColor(size,size) == colorFront)
					&& (cube.getSquare(Cube.DOWN).getColor(0,0) == colorRight))
				|| ((cube.getSquare(Cube.FRONT).getColor(size,0) == colorRight) && (cube.getSquare(Cube.LEFT).getColor(size,size) == colorTop)
						&& (cube.getSquare(Cube.DOWN).getColor(0,0) == colorFront))
				|| ((cube.getSquare(Cube.FRONT).getColor(size,0) == colorFront) && (cube.getSquare(Cube.LEFT).getColor(size,size) == colorRight)
						&& (cube.getSquare(Cube.DOWN).getColor(0,0) == colorTop)))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
			}
			else if ((cube.getSquare(Cube.RIGHT).getColor(0,0) == colorTop) && (cube.getSquare(Cube.TOP).getColor(size,size) == colorFront)
					&& (cube.getSquare(Cube.FRONT).getColor(0,size) == colorRight))
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
			}
			
			else if ((cube.getSquare(Cube.TOP).getColor(0,0) == colorTop) && (cube.getSquare(Cube.LEFT).getColor(0,0) == colorRight)
					&& (cube.getSquare(Cube.BACK).getColor(0,size) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.UP, 0));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.DOWN, 0));
			}
			
			else if ((cube.getSquare(Cube.LEFT).getColor(size,0) == colorTop) && (cube.getSquare(Cube.DOWN).getColor(size,0) == colorRight)
					&& (cube.getSquare(Cube.BACK).getColor(size,size) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
			}
			
			else if (((cube.getSquare(Cube.RIGHT).getColor(size,size) == colorTop) && (cube.getSquare(Cube.DOWN).getColor(size,size) == colorFront)
					&& (cube.getSquare(Cube.BACK).getColor(size,0) == colorRight))
				|| ((cube.getSquare(Cube.RIGHT).getColor(size,size) == colorRight) && (cube.getSquare(Cube.DOWN).getColor(size,size) == colorTop)
						&& (cube.getSquare(Cube.BACK).getColor(size,0) == colorFront))
				|| ((cube.getSquare(Cube.RIGHT).getColor(size,size) == colorFront) && (cube.getSquare(Cube.DOWN).getColor(size,size) == colorRight)
						&& (cube.getSquare(Cube.BACK).getColor(size,0) == colorTop)))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
			}
			
			else if (((cube.getSquare(Cube.BACK).getColor(size,size) == colorTop) && (cube.getSquare(Cube.DOWN).getColor(size,0) == colorFront)
					&& (cube.getSquare(Cube.LEFT).getColor(size,0) == colorRight))
				|| ((cube.getSquare(Cube.BACK).getColor(size,size) == colorRight) && (cube.getSquare(Cube.DOWN).getColor(size,0) == colorTop)
						&& (cube.getSquare(Cube.LEFT).getColor(size,0) == colorFront)))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
			}
			
			else if ((cube.getSquare(Cube.FRONT).getColor(0,size) == colorTop) && (cube.getSquare(Cube.TOP).getColor(size,size) == colorRight)
					&& (cube.getSquare(Cube.RIGHT).getColor(0,0) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.UP, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
			}
			
			for(int i = 0; i < newRotations.size(); i++)
			{
				RotationEvent rotation = newRotations.get(i);
				cube.rotate(rotation.getDirection(),rotation.getIndex());
			}
			
			this.rotations.addAll(newRotations);
			newRotations = new ArrayList<RotationEvent>();
			
			if ((cube.getSquare(Cube.FRONT).getColor(size,size) == colorFront) && (cube.getSquare(Cube.RIGHT).getColor(size,0) == colorTop)
					&& (cube.getSquare(Cube.DOWN).getColor(0,size) == colorRight))
			{
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.UP, size));
			}
			else if ((cube.getSquare(Cube.FRONT).getColor(size,size) == colorTop) && (cube.getSquare(Cube.RIGHT).getColor(size,0) == colorRight)
					&& (cube.getSquare(Cube.DOWN).getColor(0,size) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));	
				newRotations.add(new RotationEvent(Rotation.UP, size));
			}
			else if ((cube.getSquare(Cube.FRONT).getColor(size,size) == colorRight) && (cube.getSquare(Cube.RIGHT).getColor(size,0) == colorFront)
					&& (cube.getSquare(Cube.DOWN).getColor(0,size) == colorTop))
			{
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.UP, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));	
				newRotations.add(new RotationEvent(Rotation.UP, size));
			}
			
			for(int i = 0; i <= size; i++)
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,i));
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
