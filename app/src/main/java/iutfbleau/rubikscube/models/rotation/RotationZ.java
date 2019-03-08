package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.Cube;

public class RotationZ implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case CLOCKWISE :        new RotationFace().rotate(cube.getFace(Cube.FRONT), Rotation.CLOCKWISE);        break;
	            case COUNTERCLOCKWISE : new RotationFace().rotate(cube.getFace(Cube.FRONT), Rotation.COUNTERCLOCKWISE); break;
	            default: break;
			}
        }
		else if (index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case CLOCKWISE :       new RotationFace().rotate(cube.getFace(Cube.BACK), Rotation.COUNTERCLOCKWISE);  break;
	            case COUNTERCLOCKWISE: new RotationFace().rotate(cube.getFace(Cube.BACK), Rotation.CLOCKWISE);         break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case CLOCKWISE :
            	int[] top = cube.getFace(Cube.TOP).getRow(cube.getSize() - index - 1);
            	cube.getFace(Cube.TOP).setRowReverse(cube.getSize() - index - 1, cube.getFace(Cube.LEFT).getCol(cube.getSize() - index - 1));
            	cube.getFace(Cube.LEFT).setCol(cube.getSize() - index - 1, cube.getFace(Cube.DOWN).getRow(index));
            	cube.getFace(Cube.DOWN).setRowReverse(index, cube.getFace(Cube.RIGHT).getCol(index));
            	cube.getFace(Cube.RIGHT).setCol(index, top);
            break;
            case COUNTERCLOCKWISE :
            	int[] tp = cube.getFace(Cube.TOP).getRow(cube.getSize() - index - 1);
            	cube.getFace(Cube.TOP).setRow(cube.getSize() - index - 1, cube.getFace(Cube.RIGHT).getCol(index));
            	cube.getFace(Cube.RIGHT).setColReverse(index, cube.getFace(Cube.DOWN).getRow(index));
            	cube.getFace(Cube.DOWN).setRow(index, cube.getFace(Cube.LEFT).getCol(cube.getSize() - index - 1));
            	cube.getFace(Cube.LEFT).setColReverse(cube.getSize() - index - 1, tp);
			break;
            default: break;
		}
	}
}
