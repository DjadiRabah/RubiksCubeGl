package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.cube.Cube;

public class RotationY implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case UP :  new RotationFace().rotate(cube.getSquare(Cube.LEFT), Rotation.COUNTERCLOCKWISE); break;
	            case DOWN: new RotationFace().rotate(cube.getSquare(Cube.LEFT), Rotation.CLOCKWISE); break;
	            default: break;
			}
        }
		else if(index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case UP :  new RotationFace().rotate(cube.getSquare(Cube.RIGHT), Rotation.CLOCKWISE); break;
	            case DOWN: new RotationFace().rotate(cube.getSquare(Cube.RIGHT), Rotation.COUNTERCLOCKWISE); break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case UP :
            	int[] top = cube.getSquare(Cube.TOP).getCol(index);
            	cube.getSquare(Cube.TOP).setCol(index, cube.getSquare(Cube.FRONT).getCol(index));
            	cube.getSquare(Cube.FRONT).setCol(index, cube.getSquare(Cube.DOWN).getCol(index));
            	cube.getSquare(Cube.DOWN).setColReverse(index, cube.getSquare(Cube.BACK).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.BACK).setColReverse(cube.getSize() - index - 1, top);
            break;
            case DOWN:  
            	int[] front = cube.getSquare(Cube.FRONT).getCol(index);
            	cube.getSquare(Cube.FRONT).setCol(index, cube.getSquare(Cube.TOP).getCol(index));
            	cube.getSquare(Cube.TOP).setColReverse(index, cube.getSquare(Cube.BACK).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.BACK).setColReverse(cube.getSize() - index - 1, cube.getSquare(Cube.DOWN).getCol(index));
            	cube.getSquare(Cube.DOWN).setCol(index, front);
			break;
            default: break;
		}
	}
}
