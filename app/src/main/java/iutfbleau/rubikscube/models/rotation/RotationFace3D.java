package iutfbleau.rubikscube.models.rotation;

import iutfbleau.rubikscube.models.cube.face.Face;
import iutfbleau.rubikscube.models.cube.face.Face3D;
import iutfbleau.rubikscube.models.cube.piece.Piece;

public class RotationFace3D extends RotationFace
{
    @Override
    public void rotate(Face face, int direction)
    {
        Face3D face3D = (Face3D)face;
        if(direction == CLOCKWISE)
        {
            face3D.rotateY(  Math.PI/2.0);
        }
        else if(direction == COUNTERCLOCKWISE)
        {
            face3D.rotateY(-Math.PI/2.0);
        }
    }
}
