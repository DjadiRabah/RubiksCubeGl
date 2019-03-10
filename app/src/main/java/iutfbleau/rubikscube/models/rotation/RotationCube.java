package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.Cube;

public class RotationCube implements Rotation
{	
	public void rotate(Cube cube, int direction)
	{
		for(int i = 0; i < cube.getSize(); i++)
		{
			if ((direction == Rotation.RIGHT) || (direction == Rotation.LEFT))
			{
				new RotationX().rotate(cube, direction, i);
			}
			else if ((direction == Rotation.UP) || (direction == Rotation.DOWN))
			{
				new RotationY().rotate(cube, direction, i);
			}
			else if ((direction == Rotation.CLOCKWISE) || (direction == Rotation.COUNTERCLOCKWISE))
			{
				new RotationZ().rotate(cube, direction, i);
			}
		}
	}
}
