package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.Cube;

public class RotationY implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case UP :  new RotationFace().rotate(cube.getFace(Cube.LEFT), Rotation.COUNTERCLOCKWISE); break;
	            case DOWN: new RotationFace().rotate(cube.getFace(Cube.LEFT), Rotation.CLOCKWISE); break;
	            default: break;
			}
        }
		else if(index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case UP :  new RotationFace().rotate(cube.getFace(Cube.RIGHT), Rotation.CLOCKWISE); break;
	            case DOWN: new RotationFace().rotate(cube.getFace(Cube.RIGHT), Rotation.COUNTERCLOCKWISE); break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case UP :
            	int[] top = cube.getFace(Cube.TOP).getCol(index);
            	cube.getFace(Cube.TOP).setCol(index, cube.getFace(Cube.FRONT).getCol(index));
            	cube.getFace(Cube.FRONT).setCol(index, cube.getFace(Cube.DOWN).getCol(index));
            	cube.getFace(Cube.DOWN).setColReverse(index, cube.getFace(Cube.BACK).getCol(cube.getSize() - index - 1));
            	cube.getFace(Cube.BACK).setColReverse(cube.getSize() - index - 1, top);
            break;
            case DOWN:  
            	int[] front = cube.getFace(Cube.FRONT).getCol(index);
            	cube.getFace(Cube.FRONT).setCol(index, cube.getFace(Cube.TOP).getCol(index));
            	cube.getFace(Cube.TOP).setColReverse(index, cube.getFace(Cube.BACK).getCol(cube.getSize() - index - 1));
            	cube.getFace(Cube.BACK).setColReverse(cube.getSize() - index - 1, cube.getFace(Cube.DOWN).getCol(index));
            	cube.getFace(Cube.DOWN).setCol(index, front);
			break;
            default: break;
		}
	}
}
