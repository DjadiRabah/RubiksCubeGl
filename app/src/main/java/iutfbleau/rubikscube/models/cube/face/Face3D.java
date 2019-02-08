package iutfbleau.rubikscube.models.cube.face;

import iutfbleau.rubikscube.models.cube.piece.Piece3D;

public class Face3D extends Face
{
    private double tetaX;
    private double tetaY;
    private double tetaZ;
    public Face3D(int size, int color)
    {
        super(size);
        this.tetaX = 0.0;
        this.tetaY = 0.0;
        this.tetaZ = 0.0;
        this.pieces = new Piece3D[this.size][this.size];
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces[i].length; j++)
            {
                this.pieces[i][j] = new Piece3D(color);
            }
        }
    }

    public void setPosition(float x1, float x2, float y1, float z, float offset)
    {
        float width = (x2-x1 - (this.size-1) * offset)/this.size;
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                ((Piece3D)this.pieces[i][j]).setPosition(x1 + j * (width + offset),y1 - i * (width + offset),z,x1 + (j+1) * width + j * offset ,y1 - (i+1) * width - i * offset,z);
            }
        }
    }

    public void rotateX(double teta)
    {
        this.tetaX = this.tetaX + teta;
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                ((Piece3D)this.pieces[i][j]).rotateX(teta);
            }
        }
    }

    public void rotateX(double teta, int i, int j)
    {
        ((Piece3D)this.pieces[i][j]).rotateX(teta);
    }

    public void rotateY(double teta)
    {
        this.tetaY = this.tetaY + teta;
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                ((Piece3D)this.pieces[i][j]).rotateY(teta);
            }
        }
    }

    public void rotateY(double teta, int i, int j)
    {
        ((Piece3D)this.pieces[i][j]).rotateY(teta);
    }

    public void rotateZ(double teta)
    {
        this.tetaZ = this.tetaZ + teta;
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                ((Piece3D)this.pieces[i][j]).rotateZ(teta);
            }
        }
    }

    public void rotateZ(double teta, int i, int j)
    {
        ((Piece3D)this.pieces[i][j]).rotateZ(teta);
    }

    public boolean isPointIn(int row, int index)
    {
        float[] first = ((Piece3D)this.pieces[row][0]).getVertices();
        float[] last = ((Piece3D)this.pieces[row][this.size-1]).getVertices();

        if(true)
        {
            return true;
        }
        return false;
    }

    public double getTetaX()
    {
        return this.tetaX;
    }
    public double getTetaY()
    {
        return this.tetaY;
    }
    public double getTetaZ()
    {
        return this.tetaZ;
    }
}
