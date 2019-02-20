package iutfbleau.rubikscube.models.cube.cube;

import android.util.Log;

import iutfbleau.rubikscube.models.cube.face.Face;
import iutfbleau.rubikscube.models.cube.face.Face3D;
import iutfbleau.rubikscube.models.cube.piece.Piece;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.rotation.RotationX;
import iutfbleau.rubikscube.models.rotation.RotationX3D;
import iutfbleau.rubikscube.models.rotation.RotationY;
import iutfbleau.rubikscube.models.rotation.RotationZ;

public class Cube3D extends Cube
{
    public Cube3D(int size)
    {
        super(size);

        this.faces[0] = new Face3D(size, Piece.WHITE);
        ((Face3D)this.faces[0]).setPosition(-1.5f,1.5f,1.5f,1.5f,0.05f);
        ((Face3D)this.faces[0]).rotateX(Math.toRadians(-90.0));

        this.faces[1] = new Face3D(size, Piece.GREEN);
        ((Face3D)this.faces[1]).setPosition(-1.5f,1.5f,1.5f,1.5f,0.05f);
        ((Face3D)this.faces[1]).rotateY(Math.toRadians(-90.0));

        this.faces[2] = new Face3D(size, Piece.RED);
        ((Face3D)this.faces[2]).setPosition(-1.5f,1.5f,1.5f,1.5f,0.05f);

        this.faces[3] = new Face3D(size, Piece.BLUE);
        ((Face3D)this.faces[3]).setPosition(-1.5f,1.5f,1.5f,1.5f,0.05f);
        ((Face3D)this.faces[3]).rotateY(Math.toRadians(90.0));

        this.faces[4] = new Face3D(size, Piece.ORANGE);
        ((Face3D)this.faces[4]).setPosition(-1.5f,1.5f,1.5f,1.5f,0.05f);
        ((Face3D)this.faces[4]).rotateY(Math.toRadians(180.0));

        this.faces[5] = new Face3D(size, Piece.YELLOW);
        ((Face3D)this.faces[5]).setPosition(-1.5f,1.5f,1.5f,1.5f,0.05f);
        ((Face3D)this.faces[5]).rotateX(Math.toRadians(90.0));
    }

    public void rotateX(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateX(teta);
        }
    }

    public void rotateY(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateY(teta);
        }
    }

    @Override
    public Face3D getSquare(int position)
    {
        return (Face3D)this.faces[position];
    }

    public void rotate(int direction, int index)
    {
        super.rotate(direction,index);
        this.notifyObservers() ;
        if ((direction == Rotation.RIGHT) || (direction == Rotation.LEFT))
        {
            new RotationX3D().rotate(this, direction, index);
        }
    }

    public void rotateZ(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateZ(teta);
        }
    }
}
