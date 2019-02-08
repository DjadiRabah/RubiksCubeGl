package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.cube.Cube;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.models.cube.face.Face3D;
import iutfbleau.rubikscube.models.cube.piece.Piece;

public class RotationX3D extends RotationX
{
    @Override
    public void rotate(Cube cube, int direction, int index)
    {
        Cube3D cube3D = (Cube3D)cube;
        if(index == 0)
        {
            switch (direction)
            {
                case LEFT : new RotationFace3D().rotate(cube.getSquare(Cube.TOP), CLOCKWISE);         break;
                case RIGHT: new RotationFace3D().rotate(cube.getSquare(Cube.TOP), COUNTERCLOCKWISE);  break;
                default: break;
            }
        }
        else if(index == cube.getSize() - 1)
        {
            switch (direction)
            {
                case LEFT :  new RotationFace3D().rotate(cube.getSquare(Cube.DOWN), COUNTERCLOCKWISE);       break;
                case RIGHT:  new RotationFace3D().rotate(cube.getSquare(Cube.DOWN), CLOCKWISE); break;
                default: break;
            }
        }
        switch (direction)
        {
            case LEFT :
                for(int i = Cube.LEFT; i <= Cube.BACK; i++)
                {
                    Face3D face3D =  ((Face3D)cube3D.getSquare(i));
                    for(int j = 0; j < face3D.getSize(); j++) {
                        face3D.rotateY(-Math.PI / 2.0, index, j);
                    }
                }
                break;
            case RIGHT:
                for(int i = Cube.LEFT; i <= Cube.BACK; i++)
                {
                    Face3D face3D =  ((Face3D)cube3D.getSquare(i));
                    for(int j = 0; j < face3D.getSize(); j++) {
                        face3D.rotateY(Math.PI / 2.0, index, j);
                    }
                }
            default: break;
        }
    }
}

