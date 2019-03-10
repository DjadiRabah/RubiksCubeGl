package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;

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
		
		int colorTop = cube.getFace(Cube.TOP).getColor();
		
		for(int currentCorner = 0; currentCorner < 4; currentCorner++)
		{
			int colorFront = cube.getFace(Cube.FRONT).getColor();
			int colorRight = cube.getFace(Cube.RIGHT).getColor();
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			if (((cube.getFace(Cube.LEFT).getColor(0,0) == colorTop) && (cube.getFace(Cube.TOP).getColor(0,0) == colorFront)
					&& (cube.getFace(Cube.BACK).getColor(0,size) == colorRight))
				|| ((cube.getFace(Cube.LEFT).getColor(0,0) == colorFront) && (cube.getFace(Cube.TOP).getColor(0,0) == colorRight)
							&& (cube.getFace(Cube.BACK).getColor(0,size) == colorTop)))
			{
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE, size));
			}
			
			else if (((cube.getFace(Cube.RIGHT).getColor(0,size) == colorTop) && (cube.getFace(Cube.TOP).getColor(0,size) == colorRight)
					&& (cube.getFace(Cube.BACK).getColor(0,0) == colorFront))
				|| ((cube.getFace(Cube.RIGHT).getColor(0,size) == colorRight) && (cube.getFace(Cube.TOP).getColor(0,size) == colorFront)
						&& (cube.getFace(Cube.BACK).getColor(0,0) == colorTop))
				|| ((cube.getFace(Cube.RIGHT).getColor(0,size) == colorFront) && (cube.getFace(Cube.TOP).getColor(0,size) == colorTop)
						&& (cube.getFace(Cube.BACK).getColor(0,0) == colorRight))
					)
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, size));
			}
			
			else if (((cube.getFace(Cube.TOP).getColor(size,0) == colorTop) && (cube.getFace(Cube.LEFT).getColor(0,size) == colorFront)
					&& (cube.getFace(Cube.FRONT).getColor(0,0) == colorRight))
				|| ((cube.getFace(Cube.TOP).getColor(size,0) == colorFront) && (cube.getFace(Cube.LEFT).getColor(0,size) == colorRight)
						&& (cube.getFace(Cube.FRONT).getColor(0,0) == colorTop))
				|| ((cube.getFace(Cube.TOP).getColor(size,0) == colorRight) && (cube.getFace(Cube.LEFT).getColor(0,size) == colorTop)
						&& (cube.getFace(Cube.FRONT).getColor(0,0) == colorFront)))
			{
				newRotations.add(new RotationEvent(Rotation.DOWN, 0));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.UP, 0));
			}
			
			else if (((cube.getFace(Cube.FRONT).getColor(size,0) == colorTop) && (cube.getFace(Cube.LEFT).getColor(size,size) == colorFront)
					&& (cube.getFace(Cube.DOWN).getColor(0,0) == colorRight))
				|| ((cube.getFace(Cube.FRONT).getColor(size,0) == colorRight) && (cube.getFace(Cube.LEFT).getColor(size,size) == colorTop)
						&& (cube.getFace(Cube.DOWN).getColor(0,0) == colorFront))
				|| ((cube.getFace(Cube.FRONT).getColor(size,0) == colorFront) && (cube.getFace(Cube.LEFT).getColor(size,size) == colorRight)
						&& (cube.getFace(Cube.DOWN).getColor(0,0) == colorTop)))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
			}
			else if ((cube.getFace(Cube.RIGHT).getColor(0,0) == colorTop) && (cube.getFace(Cube.TOP).getColor(size,size) == colorFront)
					&& (cube.getFace(Cube.FRONT).getColor(0,size) == colorRight))
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE, 0));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE, 0));
			}
			
			else if ((cube.getFace(Cube.TOP).getColor(0,0) == colorTop) && (cube.getFace(Cube.LEFT).getColor(0,0) == colorRight)
					&& (cube.getFace(Cube.BACK).getColor(0,size) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.UP, 0));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.DOWN, 0));
			}
			
			else if ((cube.getFace(Cube.LEFT).getColor(size,0) == colorTop) && (cube.getFace(Cube.DOWN).getColor(size,0) == colorRight)
					&& (cube.getFace(Cube.BACK).getColor(size,size) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));
			}
			
			else if (((cube.getFace(Cube.RIGHT).getColor(size,size) == colorTop) && (cube.getFace(Cube.DOWN).getColor(size,size) == colorFront)
					&& (cube.getFace(Cube.BACK).getColor(size,0) == colorRight))
				|| ((cube.getFace(Cube.RIGHT).getColor(size,size) == colorRight) && (cube.getFace(Cube.DOWN).getColor(size,size) == colorTop)
						&& (cube.getFace(Cube.BACK).getColor(size,0) == colorFront))
				|| ((cube.getFace(Cube.RIGHT).getColor(size,size) == colorFront) && (cube.getFace(Cube.DOWN).getColor(size,size) == colorRight)
						&& (cube.getFace(Cube.BACK).getColor(size,0) == colorTop)))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
			}
			
			else if (((cube.getFace(Cube.BACK).getColor(size,size) == colorTop) && (cube.getFace(Cube.DOWN).getColor(size,0) == colorFront)
					&& (cube.getFace(Cube.LEFT).getColor(size,0) == colorRight))
				|| ((cube.getFace(Cube.BACK).getColor(size,size) == colorRight) && (cube.getFace(Cube.DOWN).getColor(size,0) == colorTop)
						&& (cube.getFace(Cube.LEFT).getColor(size,0) == colorFront)))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
			}
			
			else if ((cube.getFace(Cube.FRONT).getColor(0,size) == colorTop) && (cube.getFace(Cube.TOP).getColor(size,size) == colorRight)
					&& (cube.getFace(Cube.RIGHT).getColor(0,0) == colorFront))
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
			
			if ((cube.getFace(Cube.FRONT).getColor(size,size) == colorFront) && (cube.getFace(Cube.RIGHT).getColor(size,0) == colorTop)
					&& (cube.getFace(Cube.DOWN).getColor(0,size) == colorRight))
			{
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.UP, size));
			}
			else if ((cube.getFace(Cube.FRONT).getColor(size,size) == colorTop) && (cube.getFace(Cube.RIGHT).getColor(size,0) == colorRight)
					&& (cube.getFace(Cube.DOWN).getColor(0,size) == colorFront))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT, size));
				newRotations.add(new RotationEvent(Rotation.DOWN, size));
				newRotations.add(new RotationEvent(Rotation.RIGHT, size));	
				newRotations.add(new RotationEvent(Rotation.UP, size));
			}
			else if ((cube.getFace(Cube.FRONT).getColor(size,size) == colorRight) && (cube.getFace(Cube.RIGHT).getColor(size,0) == colorFront)
					&& (cube.getFace(Cube.DOWN).getColor(0,size) == colorTop))
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
