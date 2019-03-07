package iutfbleau.rubikscube.models.collide;

import android.opengl.GLU;
import android.opengl.Matrix;
import android.util.Log;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import iutfbleau.rubikscube.models.cube.piece.Piece3D;

public class CollidePiece3D
{
    public CollidePiece3D()
    {
    }

    public float[] getWorldSpaceFromMouseCoordinates(float mouseX, float mouseY)
    {
        float[] farCoord = { 0.0f, 0.0f, 0.0f, 0.0f };
        float[] nearCoord = { 0.0f, 0.0f, 0.0f, 0.0f };

        // mouse Y needs to be inverted
        //mouseY = (float) _viewport[3] - mouseY;

        // calling glReadPixels() with GL_DEPTH_COMPONENT is not supported in
        // GLES so now i will try to implement ray picking
        int result = GLU.gluUnProject(mouseX, mouseY, 1.0f, mViewMatrix, 0, mProjectionMatrix, 0, _viewport, 0,
                farCoord, 0);

        if (result == GL10.GL_TRUE)
        {
            farCoord[0] = farCoord[0] / farCoord[3];
            farCoord[1] = farCoord[1] / farCoord[3];
            farCoord[2] = farCoord[2] / farCoord[3];
        }

        result = GLU.gluUnProject(mouseX, mouseY, 0.0f, mViewMatrix, 0, mProjectionMatrix, 0, _viewport, 0, nearCoord,
                0);

        if (result == GL10.GL_TRUE)
        {
            nearCoord[0] = nearCoord[0] / nearCoord[3];
            nearCoord[1] = nearCoord[1] / nearCoord[3];
            nearCoord[2] = nearCoord[2] / nearCoord[3];
        }

        float [] dirVector = Vector.normalize(Vector.minus(farCoord, nearCoord));
        float [] rayOrigin =  {0.0f, 0.0f, 3.0f};

        Log.d("Far Coordinate:", "" + farCoord[0] + "," + farCoord[1] + "," + farCoord[2]);
        Log.d("Near Coordinate:", "" + nearCoord[0] + "," + nearCoord[1] + "," + nearCoord[2]);
        float [] vertices = { -0.5f, 0.5f, 0.0f, // top left
                -0.5f, -0.5f, 0.0f, // bottom left
                0.5f, -0.5f, 0.0f, // bottom right
                0.5f, 0.5f, 0.0f }; // top right

        // calculate normal for square
        float[] v1 = { vertices[3] - vertices[0], vertices[4] - vertices[1], vertices[5] - vertices[2]};
        float[] v2 = { vertices[9] - vertices[0], vertices[10] - vertices[1], vertices[11] - vertices[2]};

        float[] n = Vector.normalize(Vector.crossProduct(v1, v2));

        // now calculate intersection point as per following link
        // http://antongerdelan.net/opengl/raycasting.html

        // our plane passes through origin so findint 't' ll be

        float t = -(Vector.dot(rayOrigin, n) / Vector.dot(dirVector, n));

        // now substitute above t in ray equation gives us intersection point
        float [] intersectionPoint = Vector.addition(rayOrigin, Vector.scalarProduct(t, dirVector));
        Log.d("Ipoint:", "" + intersectionPoint[0] + "," + intersectionPoint[1] + "," + intersectionPoint[2]);
        return intersectionPoint;
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
