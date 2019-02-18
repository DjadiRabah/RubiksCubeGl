package iutfbleau.rubikscube.models.rotation;

import android.util.Log;

import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.models.cube.piece.Piece;
import iutfbleau.rubikscube.models.cube.piece.Piece3D;

public class RotationX3D extends RotationX
{
    public void rotate(Cube3D cube, int direction, int index)
    {
        /*
        if(index == 0)
        {
            switch (direction)
            {
                case LEFT : new RotationFace().rotate(cube.getSquare(Cube.TOP), CLOCKWISE);         break;
                case RIGHT: new RotationFace().rotate(cube.getSquare(Cube.TOP), COUNTERCLOCKWISE);  break;
                default: break;
            }
        }
        else if(index == cube.getSize() - 1)
        {
            switch (direction)
            {
                case LEFT :  new RotationFace().rotate(cube.getSquare(Cube.DOWN), COUNTERCLOCKWISE);       break;
                case RIGHT:  new RotationFace().rotate(cube.getSquare(Cube.DOWN), CLOCKWISE); break;
                default: break;
            }
        }*/
        switch (direction)
        {
            case LEFT :
                float[][] vertices = cube.getSquare(Cube.LEFT).getRowVertices(index);
                for(int i = 1; i < 4; i++)
                {
                    cube.getSquare(i).setRowVertices(index, cube.getSquare(i+1).getRowVertices(index));
                }
                cube.getSquare(4).setRowVertices(index, vertices);
                break;
            case RIGHT:
                vertices = cube.getSquare(Cube.BACK).getRowVertices(index);
                for(int i = 4; i > 1; i--)
                {
                    cube.getSquare(i).setRowVertices(index, cube.getSquare(i-1).getRowVertices(index));
                }
                cube.getSquare(Cube.LEFT).setRowVertices(index, vertices);
                break;
            default: break;
        }
    }
}
