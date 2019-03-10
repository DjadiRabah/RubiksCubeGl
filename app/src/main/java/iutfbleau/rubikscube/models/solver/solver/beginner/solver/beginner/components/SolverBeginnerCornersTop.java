package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;

public class SolverBeginnerCornersTop extends SolverComponent
{
	public SolverBeginnerCornersTop() 
	{
		super();
	}
	
	private boolean checkLeftBack(Cube cube)
	{
		int colorLeft = cube.getFace(Cube.LEFT).getColor();
		int colorBack = cube.getFace(Cube.BACK).getColor();
		if ((cube.getFace(Cube.LEFT).getColor(0,0) == colorLeft)
			&& (cube.getFace(Cube.BACK).getColor(0,2) == colorBack))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkLeftFront(Cube cube)
	{
		int colorLeft = cube.getFace(Cube.LEFT).getColor();
		int colorFront = cube.getFace(Cube.FRONT).getColor();
		if ((cube.getFace(Cube.LEFT).getColor(0,2) == colorLeft)
			&& (cube.getFace(Cube.FRONT).getColor(0,0) == colorFront))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkRightBack(Cube cube)
	{
		int colorRight = cube.getFace(Cube.RIGHT).getColor();
		int colorBack = cube.getFace(Cube.BACK).getColor();
		if ((cube.getFace(Cube.RIGHT).getColor(0,2) == colorRight)
			&& (cube.getFace(Cube.BACK).getColor(0,0) == colorBack))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkRightFront(Cube cube)
	{
		int colorRight = cube.getFace(Cube.RIGHT).getColor();
		int colorFront = cube.getFace(Cube.FRONT).getColor();
		if ((cube.getFace(Cube.RIGHT).getColor(0,0) == colorRight)
			&& (cube.getFace(Cube.FRONT).getColor(0,2) == colorFront))
		{
			return true;
		}
		return false;
	}
	
	public ArrayList<RotationEvent> solveCorners()
	{
		ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.CLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,2));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,2));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,2));
		newRotations.add(new RotationEvent(Rotation.COUNTERCLOCKWISE,2));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.RIGHT,0));
		return newRotations;
	}

	@Override
	public ArrayList<RotationEvent> solve(Cube cube) 
	{	
		while (!this.checkLeftBack(cube) || !this.checkLeftFront(cube)
				|| !this.checkRightBack(cube) || !this.checkRightFront(cube))
		{
			int max = 0;
			int corners = 0;
			for(int i = 0; i < 4; i++)
			{
				if (this.checkLeftBack(cube))
				{
					corners++;
				}
				if (this.checkLeftFront(cube))
				{
					corners++;
				}
				if (this.checkRightBack(cube))
				{
					corners++;
				}
				if (this.checkRightFront(cube))
				{
					corners++;
				}
				if(corners > max)
					max = corners;
				cube.rotate(Rotation.LEFT, 0);
				corners = 0;
			}
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			while(corners != max)
			{
				corners = 0;
				if (this.checkLeftBack(cube))
				{
					corners++;
				}
				if (this.checkLeftFront(cube))
				{
					corners++;
				}
				if (this.checkRightBack(cube))
				{
					corners++;
				}
				if (this.checkRightFront(cube))
				{
					corners++;
				}
				if(corners != max)
				{
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					cube.rotate(Rotation.LEFT,0);
				}
			}
			
			this.rotations.addAll(newRotations);
			newRotations = new ArrayList<RotationEvent>();
			
			if(max == 2)
			{
				if(this.checkLeftBack(cube) && this.checkLeftFront(cube))
				{
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					newRotations.add(new RotationEvent(Rotation.LEFT,1));
					newRotations.add(new RotationEvent(Rotation.LEFT,2));
				}
				else if(this.checkRightBack(cube) && this.checkRightFront(cube))
				{
					newRotations.add(new RotationEvent(Rotation.RIGHT,0));
					newRotations.add(new RotationEvent(Rotation.RIGHT,1));
					newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				}
				else if(this.checkRightFront(cube) && this.checkLeftFront(cube))
				{
					newRotations.add(new RotationEvent(Rotation.RIGHT,0));
					newRotations.add(new RotationEvent(Rotation.RIGHT,1));
					newRotations.add(new RotationEvent(Rotation.RIGHT,2));
					newRotations.add(new RotationEvent(Rotation.RIGHT,0));
					newRotations.add(new RotationEvent(Rotation.RIGHT,1));
					newRotations.add(new RotationEvent(Rotation.RIGHT,2));
				}
				newRotations.addAll(this.solveCorners());
			}
			this.rotations.addAll(newRotations);
			for(int i = 0; i < newRotations.size(); i++)
			{
				RotationEvent rotation = newRotations.get(i);
				cube.rotate(rotation.getDirection(),rotation.getIndex());
			}
		}
		return this.rotations;
	}
}
