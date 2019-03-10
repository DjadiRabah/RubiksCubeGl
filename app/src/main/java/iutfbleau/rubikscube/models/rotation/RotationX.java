package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.Cube;

public class RotationX implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case LEFT : new RotationFace().rotate(cube.getFace(Cube.TOP), Rotation.CLOCKWISE);         break;
	            case RIGHT: new RotationFace().rotate(cube.getFace(Cube.TOP), Rotation.COUNTERCLOCKWISE);  break;
	            default: break;
			}
        }
		else if(index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case LEFT :  new RotationFace().rotate(cube.getFace(Cube.DOWN), Rotation.COUNTERCLOCKWISE);       break;
	            case RIGHT:  new RotationFace().rotate(cube.getFace(Cube.DOWN), Rotation.CLOCKWISE); break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case LEFT :
            	int[] pieces = cube.getFace(Cube.LEFT).getRow(index);
				for(int i = 1; i < 4; i++)
				{
					cube.getFace(i).setRow(index, cube.getFace(i+1).getRow(index));
				}
				cube.getFace(4).setRow(index, pieces);
            break;
            case RIGHT:  
            	pieces = cube.getFace(Cube.BACK).getRow(index);
				for(int i = 4; i > 1; i--)
				{
					cube.getFace(i).setRow(index, cube.getFace(i-1).getRow(index));
				}
				cube.getFace(Cube.LEFT).setRow(index, pieces);
			break;
            default: break;
		}
	}
}
