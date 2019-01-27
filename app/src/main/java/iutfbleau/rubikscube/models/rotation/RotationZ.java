package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.cube.piece.Piece;

public class RotationZ implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case CLOCKWISE :        new RotationSquare().rotate(cube.getSquare(Cube.FRONT), CLOCKWISE);        break;
	            case COUNTERCLOCKWISE : new RotationSquare().rotate(cube.getSquare(Cube.FRONT), COUNTERCLOCKWISE); break;
	            default: break;
			}
        }
		else if (index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case CLOCKWISE :       new RotationSquare().rotate(cube.getSquare(Cube.BACK), COUNTERCLOCKWISE);  break;
	            case COUNTERCLOCKWISE: new RotationSquare().rotate(cube.getSquare(Cube.BACK), CLOCKWISE);         break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case CLOCKWISE :
            	Piece[] top = cube.getSquare(Cube.TOP).getRow(cube.getSize() - index - 1);
            	cube.getSquare(Cube.TOP).setRowReverse(cube.getSize() - index - 1, cube.getSquare(Cube.LEFT).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.LEFT).setCol(cube.getSize() - index - 1, cube.getSquare(Cube.DOWN).getRow(index));
            	cube.getSquare(Cube.DOWN).setRowReverse(index, cube.getSquare(Cube.RIGHT).getCol(index));
            	cube.getSquare(Cube.RIGHT).setCol(index, top);
            break;
            case COUNTERCLOCKWISE :
            	Piece[] tp = cube.getSquare(Cube.TOP).getRow(cube.getSize() - index - 1);
            	cube.getSquare(Cube.TOP).setRow(cube.getSize() - index - 1, cube.getSquare(Cube.RIGHT).getCol(index));
            	cube.getSquare(Cube.RIGHT).setColReverse(index, cube.getSquare(Cube.DOWN).getRow(index));
            	cube.getSquare(Cube.DOWN).setRow(index, cube.getSquare(Cube.LEFT).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.LEFT).setColReverse(cube.getSize() - index - 1, tp);
			break;
            default: break;
		}
	}
}
