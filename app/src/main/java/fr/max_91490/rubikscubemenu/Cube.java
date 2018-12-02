package fr.max_91490.rubikscubemenu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

class Cube
{
    protected float offset;
    private Face front;
    private Face back;
    private Face left;


    public Cube()
    {
        this.front = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 255f,255f,255f);
        this.back = new Face(3,-1.0f,1.0f,1.0f,-1.0f,0.1f, 255f,255f,0f);
    }

    public void draw(GL10 gl)
    {
        front.draw(gl);
        back.rotate(gl);
        back.draw(gl);

    }
}