package iutfbleau.rubikscube.models.cube.cube;

import iutfbleau.rubikscube.models.cube.face.Face3D;
import iutfbleau.rubikscube.models.cube.piece.Piece;
import iutfbleau.rubikscube.models.rotation.Rotation;

public class Cube3D extends Cube
{
    public Cube3D(int size)
    {
        super(size);

        float alpha = 1.5f;
        float offset = 0.05f;

        this.faces[0] = new Face3D(size, Piece.WHITE);
        ((Face3D)this.faces[0]).setPosition(-alpha,alpha,alpha,alpha,offset);
        ((Face3D)this.faces[0]).rotateX(Math.toRadians(-90.0));

        this.faces[1] = new Face3D(size, Piece.GREEN);
        ((Face3D)this.faces[1]).setPosition(-alpha,alpha,alpha,alpha,offset);
        ((Face3D)this.faces[1]).rotateY(Math.toRadians(-90.0));

        this.faces[2] = new Face3D(size, Piece.RED);
        ((Face3D)this.faces[2]).setPosition(-alpha,alpha,alpha,alpha,offset);

        this.faces[3] = new Face3D(size, Piece.BLUE);
        ((Face3D)this.faces[3]).setPosition(-alpha,alpha,alpha,alpha,offset);
        ((Face3D)this.faces[3]).rotateY(Math.toRadians(90.0));

        this.faces[4] = new Face3D(size, Piece.ORANGE);
        ((Face3D)this.faces[4]).setPosition(-alpha,alpha,alpha,alpha,offset);
        ((Face3D)this.faces[4]).rotateY(Math.toRadians(180.0));

        this.faces[5] = new Face3D(size, Piece.YELLOW);
        ((Face3D)this.faces[5]).setPosition(-alpha,alpha,alpha,alpha,offset);
        ((Face3D)this.faces[5]).rotateX(Math.toRadians(90.0));
    }

    public void disableColors()
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            this.faces[i].disableColors();
        }
    }

    public void setFace(int face, int[][] colors)
    {
        this.faces[face].setFace(colors);
    }

    public int[][] getFace(int face){
        return this.faces[face].getColors();
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

    public void rotateZ(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            ((Face3D)this.faces[i]).rotateZ(teta);
        }
    }
}
