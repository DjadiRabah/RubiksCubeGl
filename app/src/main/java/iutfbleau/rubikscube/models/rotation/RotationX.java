package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.cube.piece.Piece;

public class RotationX implements RotationComplex
{	
	@Override
	public void rotate(Cube cube, int direction, int index)
	{
		if(index == 0)
		{
			switch (direction) 
			{
	            case LEFT : new RotationSquare().rotate(cube.getSquare(Cube.TOP), CLOCKWISE);         break;
	            case RIGHT: new RotationSquare().rotate(cube.getSquare(Cube.TOP), COUNTERCLOCKWISE);  break;
	            default: break;
			}
        }
		else if(index == cube.getSize() - 1)
		{
			switch (direction) 
			{
	            case LEFT :  new RotationSquare().rotate(cube.getSquare(Cube.DOWN), COUNTERCLOCKWISE);       break;
	            case RIGHT:  new RotationSquare().rotate(cube.getSquare(Cube.DOWN), CLOCKWISE); break;
	            default: break;
			}
		}
		switch (direction) 
		{
            case LEFT :
            	Piece[] pieces = cube.getSquare(Cube.LEFT).getRow(index);
				for(int i = 1; i < 4; i++)
				{
					cube.getSquare(i).setRow(index, cube.getSquare(i+1).getRow(index));
				}
				cube.getSquare(4).setRow(index, pieces);
            break;
            case RIGHT:  
            	pieces = cube.getSquare(Cube.BACK).getRow(index);
				for(int i = 4; i > 1; i--)
				{
					cube.getSquare(i).setRow(index, cube.getSquare(i-1).getRow(index));
				}
				cube.getSquare(Cube.LEFT).setRow(index, pieces);
			break;
            default: break;
		}
	}
}
