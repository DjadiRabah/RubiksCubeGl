package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.solver.SolverComponent;

public class SolverCrossTop extends SolverComponent
{

	public SolverCrossTop() 
	{
		super();
	}
	
	private boolean checkCross(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(0,1) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,0) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,2) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(2,1) == colorTop))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkHorizontalLine(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(1,0) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,2) == colorTop))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkVerticalLine(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(0,1) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(2,1) == colorTop))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkLeftTopL(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(0,1) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,0) == colorTop))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkTopRightL(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(0,1) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,2) == colorTop))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkBotRightL(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(2,1) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,2) == colorTop))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkBotLeftL(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		if ((cube.getSquare(Cube.TOP).getColor(2,1) == colorTop)
			&& (cube.getSquare(Cube.TOP).getColor(1,0) == colorTop))
		{
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<RotationEvent> solve(Cube cube) 
	{
		while(!this.checkCross(cube))
		{
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			if(this.checkHorizontalLine(cube))
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
			}
			
			else if(this.checkVerticalLine(cube))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
			}
			
			else if(this.checkLeftTopL(cube))
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
			}
			
			else if(this.checkTopRightL(cube))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
			}
			
			else if(this.checkBotRightL(cube))
			{
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
			}
			
			else if(this.checkBotLeftL(cube))
			{
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
			}
			
			else
			{
				newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
				newRotations.add(new RotationEvent(Rotation.LEFT,0));
				newRotations.add(new RotationEvent(Rotation.UP,2));
				newRotations.add(new RotationEvent(Rotation.RIGHT,0));
				newRotations.add(new RotationEvent(Rotation.DOWN,2));
				newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
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
