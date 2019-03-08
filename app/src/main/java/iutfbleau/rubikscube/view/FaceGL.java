package iutfbleau.rubikscube.view;

import javax.microedition.khronos.opengles.GL10;

public class FaceGL
{
    protected PieceGL[][] pieces;

    public FaceGL(int[][] colors)
    {
        this.pieces = new PieceGL[colors.length][colors.length];
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                this.pieces[i][j] = new PieceGL(colors[i][j]);
            }
        }
    }

    public void setPosition(float x1, float x2, float y1, float z, float offset)
    {
        float width = (x2-x1 - (this.pieces.length-1) * offset)/this.pieces.length;
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                this.pieces[i][j].setPosition(x1 + j * (width + offset),y1 - i * (width + offset),z,x1 + (j+1) * width + j * offset ,y1 - (i+1) * width - i * offset,z);
            }
        }
    }

    public void draw(GL10 gl)
    {
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                this.pieces[i][j].draw(gl);
            }
        }
    }

    public void rotateX(double teta)
    {
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                this.pieces[i][j].rotateX(teta);
            }
        }
    }

    public void rotateY(double teta)
    {
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                this.pieces[i][j].rotateY(teta);
            }
        }
    }

    public void rotateZ(double teta)
    {
        for(int i = 0; i < this.pieces.length; i++)
        {
            for(int j = 0; j < this.pieces.length; j++)
            {
                this.pieces[i][j].rotateZ(teta);
            }
        }
    }
}
