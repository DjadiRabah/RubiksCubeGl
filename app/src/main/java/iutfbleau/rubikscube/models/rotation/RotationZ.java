package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.cube.Cube;

public class RotationZ implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case CLOCKWISE :        new RotationFace().rotate(cube.getSquare(Cube.FRONT), Rotation.CLOCKWISE);        break;
	            case COUNTERCLOCKWISE : new RotationFace().rotate(cube.getSquare(Cube.FRONT), Rotation.COUNTERCLOCKWISE); break;
	            default: break;
			}
        }
		else if (index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case CLOCKWISE :       new RotationFace().rotate(cube.getSquare(Cube.BACK), Rotation.COUNTERCLOCKWISE);  break;
	            case COUNTERCLOCKWISE: new RotationFace().rotate(cube.getSquare(Cube.BACK), Rotation.CLOCKWISE);         break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case CLOCKWISE :
            	int[] top = cube.getSquare(Cube.TOP).getRow(cube.getSize() - index - 1);
            	cube.getSquare(Cube.TOP).setRowReverse(cube.getSize() - index - 1, cube.getSquare(Cube.LEFT).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.LEFT).setCol(cube.getSize() - index - 1, cube.getSquare(Cube.DOWN).getRow(index));
            	cube.getSquare(Cube.DOWN).setRowReverse(index, cube.getSquare(Cube.RIGHT).getCol(index));
            	cube.getSquare(Cube.RIGHT).setCol(index, top);
            break;
            case COUNTERCLOCKWISE :
            	int[] tp = cube.getSquare(Cube.TOP).getRow(cube.getSize() - index - 1);
            	cube.getSquare(Cube.TOP).setRow(cube.getSize() - index - 1, cube.getSquare(Cube.RIGHT).getCol(index));
            	cube.getSquare(Cube.RIGHT).setColReverse(index, cube.getSquare(Cube.DOWN).getRow(index));
            	cube.getSquare(Cube.DOWN).setRow(index, cube.getSquare(Cube.LEFT).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.LEFT).setColReverse(cube.getSize() - index - 1, tp);
			break;
            default: break;
		}
	}
}
