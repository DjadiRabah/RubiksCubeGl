package iutfbleau.rubikscube.model.rotation;

import iutfbleau.rubikscube.model.cube.Cube;
import iutfbleau.rubikscube.model.cube.piece.Piece;
import rubikscube.model.cube.piece.*;

public class RotationY implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case UP :  new RotationSquare().rotate(cube.getSquare(Cube.LEFT), COUNTERCLOCKWISE); break;
	            case DOWN: new RotationSquare().rotate(cube.getSquare(Cube.LEFT), CLOCKWISE); break;
	            default: break;
			}
        }
		else if(index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case UP :  new RotationSquare().rotate(cube.getSquare(Cube.RIGHT), CLOCKWISE); break;
	            case DOWN: new RotationSquare().rotate(cube.getSquare(Cube.RIGHT), COUNTERCLOCKWISE); break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case UP :
            	Piece[] top = cube.getSquare(Cube.TOP).getCol(index);
            	cube.getSquare(Cube.TOP).setCol(index, cube.getSquare(Cube.FRONT).getCol(index));
            	cube.getSquare(Cube.FRONT).setCol(index, cube.getSquare(Cube.DOWN).getCol(index));
            	cube.getSquare(Cube.DOWN).setColReverse(index, cube.getSquare(Cube.BACK).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.BACK).setColReverse(cube.getSize() - index - 1, top);
            break;
            case DOWN:  
            	Piece[] front = cube.getSquare(Cube.FRONT).getCol(index);
            	cube.getSquare(Cube.FRONT).setCol(index, cube.getSquare(Cube.TOP).getCol(index));
            	cube.getSquare(Cube.TOP).setColReverse(index, cube.getSquare(Cube.BACK).getCol(cube.getSize() - index - 1));
            	cube.getSquare(Cube.BACK).setColReverse(cube.getSize() - index - 1, cube.getSquare(Cube.DOWN).getCol(index));
            	cube.getSquare(Cube.DOWN).setCol(index, front);
			break;
            default: break;
		}
	}
}
