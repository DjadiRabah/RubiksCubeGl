package iutfbleau.rubikscube.models.solver.solver.beginner.solver.beginner.components;

import java.util.ArrayList;

import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.solver.SolverComponent;
import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.solver.SolverComponent;

public class SolverFaceTop extends SolverComponent
{
	public SolverFaceTop() 
	{
		super();
	}
	
	private int getNumberCorner(Cube cube)
	{
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		int corner = 0;
		
		if (cube.getSquare(Cube.TOP).getColor(0,0) == colorTop)
		{
			corner++;
		}
		if (cube.getSquare(Cube.TOP).getColor(0,2) == colorTop)
		{
			corner++;
		}
		if (cube.getSquare(Cube.TOP).getColor(2,0) == colorTop)
		{
			corner++;
		}
		if (cube.getSquare(Cube.TOP).getColor(2,2) == colorTop)
		{
			corner++;
		}
		
		return corner;
	}
	
	private ArrayList<RotationEvent> solveCorners()
	{
		ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.UP,2));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.LEFT,0));
		newRotations.add(new RotationEvent(Rotation.DOWN,2));
		return newRotations;
		
	}

	@Override
	public ArrayList<RotationEvent> solve(Cube cube) 
	{
		int corners = this.getNumberCorner(cube);
		int colorTop = cube.getSquare(Cube.TOP).getColor();
		
		while(corners != 4)
		{
			ArrayList<RotationEvent> newRotations = new ArrayList<RotationEvent>();
			if(corners == 0)
			{
				while(cube.getSquare(Cube.LEFT).getColor(0,2) != colorTop)
				{
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					cube.rotate(Rotation.LEFT,0);
				}
			}
			
			else if(corners == 1)
			{
				while(cube.getSquare(Cube.TOP).getColor(2,0) != colorTop)
				{
					newRotations.add(new RotationEvent(Rotation.LEFT,0));
					cube.rotate(Rotation.LEFT,0);
				}
			}
			
			this.rotations.addAll(newRotations);
			newRotations = new ArrayList<RotationEvent>();
			
			newRotations.addAll(this.solveCorners());
			
			for(int i = 0; i < newRotations.size(); i++)
			{
				RotationEvent rotation = newRotations.get(i);
				cube.rotate(rotation.getDirection(),rotation.getIndex());
			}
			this.rotations.addAll(newRotations);

			corners = this.getNumberCorner(cube);
		}
		return this.rotations;
	}
}
