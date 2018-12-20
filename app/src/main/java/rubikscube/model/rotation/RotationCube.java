package rubikscube.model.rotation;

import rubikscube.model.cube.Cube;

public class RotationCube implements Rotation
{	
	public void rotate(Cube cube, int direction)
	{
		for(int i = 0; i < cube.getSize(); i++)
		{
			if ((direction == RIGHT) || (direction == LEFT))
			{
				new RotationX().rotate(cube, direction, i);
			}
			else if ((direction == UP) || (direction == DOWN))
			{
				new RotationY().rotate(cube, direction, i);
			}
			else if ((direction == CLOCKWISE) || (direction == COUNTERCLOCKWISE))
			{
				new RotationZ().rotate(cube, direction, i);
			}
		}
	}
}
