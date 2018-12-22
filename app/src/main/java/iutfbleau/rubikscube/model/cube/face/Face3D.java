package iutfbleau.rubikscube.model.cube.face;

import iutfbleau.rubikscube.model.cube.piece.Piece3D;

public class Face3D extends Face
{
    public Face3D(int size)
    {
        super(size);
    }

    public void setPosition(float x1, float x2, float y1, float z, float offset, int[][] colors)
    {
        float width = (x2-x1 - (this.size-1) * offset)/this.size;
        this.pieces = new Piece3D[this.size][this.size];
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                this.pieces[i][j] = new Piece3D(colors[i][j]);
                ((Piece3D)this.pieces[i][j]).setPosition(x1 + j * (width + offset),y1 - i * (width + offset),z,x1 + (j+1) * width + j * offset ,y1 - (i+1) * width - i * offset,z);
            }
        }
    }
}
