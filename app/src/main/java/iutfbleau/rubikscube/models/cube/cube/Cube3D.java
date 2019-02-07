package iutfbleau.rubikscube.models.cube.cube;

import android.util.Log;

import iutfbleau.rubikscube.models.cube.face.Face3D;
import iutfbleau.rubikscube.models.cube.piece.Piece;
import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.models.rotation.RotationX3D;

public class Cube3D extends Cube
{
    private double tetaX;
    private double tetaY;
    private double tetaZ;

    public Cube3D(int size)
    {
        super(size);

        this.tetaX = 0.0;
        this.tetaY = 0.0;
        this.tetaZ = 0.0;

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
        this.tetaX = this.tetaX + teta;
        if(this.tetaX >= Math.PI / 2.0)
        {
            this.rotate(Rotation.DOWN);
            this.tetaX = 0.0;
        }
        else if(this.tetaX <= -Math.PI / 2.0)
        {
            this.rotate(Rotation.UP);
            this.tetaX = 0.0;
        }
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateX(teta);
        }
    }

    public void rotateY(double teta)
    {

        this.tetaY = this.tetaY + teta;
        if(this.tetaY >= Math.PI / 2.0)
        {
            this.rotate(Rotation.RIGHT);
            this.tetaY = 0.0;
        }
        else if(this.tetaY <= -Math.PI / 2.0)
        {
            this.rotate(Rotation.LEFT);
            this.tetaY = 0.0;
        }
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateY(teta);
        }
    }

    public void rotate(int direction, int index)
    {
        super.rotate(direction,index);
        new RotationX3D().rotate(this,direction,index);
    }

    public void rotateZ(double teta)
    {
        this.tetaZ = this.tetaZ + teta;
        if(this.tetaZ >= Math.PI / 2.0)
        {
            this.rotate(Rotation.CLOCKWISE);
            this.tetaZ = 0.0;
        }
        else if(this.tetaZ <= -Math.PI / 2.0)
        {
            this.rotate(Rotation.COUNTERCLOCKWISE);
            this.tetaZ = 0.0;
        }
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateZ(teta);
        }
    }
}
