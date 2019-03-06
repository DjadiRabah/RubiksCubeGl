package iutfbleau.rubikscube.models.collide;

import android.util.Log;

import iutfbleau.rubikscube.models.cube.piece.Piece3D;

public class CollidePiece3D
{
    public CollidePiece3D()
    {
    }



    private float[][] getBoundingBox(Piece3D piece)
    {
        float[][] box = new float[4][2];
        float[] vertices = piece.getVertices();
        box[0][0] = vertices[3];
        box[0][1] = vertices[1];
        box[1][0] = vertices[3];
        box[1][1] = vertices[7];
        box[2][0] = vertices[9];
        box[2][1] = vertices[7];
        box[3][0] = vertices[9];
        box[3][1] = vertices[1];
        return box;
    }

    public boolean isInBoundingBox(Piece3D piece, float x, float y)
    {
        float[][] box = this.getBoundingBox(piece);
        Log.e("lol", "ICCCCCCIIIIII " + " " + box[0][0] + " " + ((x -640.0f)/640.0f));
        if (((x -640.0f)/640.0f) >= box[0][0])
        {
            return true;
        }
        /*
         && (x <= box[3][0])
            && (y >= box[0][1]) && (x <= box[1][1])*/
        return false;
    }

    public boolean isInPiece(Piece3D piece, float x, float y)
    {
        Log.e("lol", "ICCCCCCIIIIII " + x + " " + y);
        /*
        float[] vertices = new float[]{
                x1,  y1, z1,  // 0, Top Left
                x1, y1 - this.width, z1,  // 1, Bottom Left
                x2, y2, z2,  // 2, Bottom Right
                x2,  y2 + this.width, z2,  // 3, Top Right
        };
        */

        float[] vertices = piece.getVertices();
        Function function12 = new Function(-1.0f * vertices[4] / vertices[0],vertices[1] + vertices[4] + vertices[0]*vertices[4]/vertices[0]);
        Log.e("lol", "ICCCCCCIIIIII " + (-1.0f * vertices[4] / vertices[0]) + " " + vertices[4]);
        if(function12.isUnder(x,y))
        {
            return true;
        }
        return false;
    }
}
