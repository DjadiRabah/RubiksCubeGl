package com.example.rabah.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

class Cube
{
    protected float offset;
    private  Face front;
    private  Face back;


    public Cube()
    {
        this.front = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 1.0f,0.0f,0.0f);
        this.back = new Face(3,-1.0f,1.0f,1.0f,-1.0f,0.1f, 1.0f,127.0f/255.0f,39.0f/255.0f);
    }

    public void draw(GL10 gl)
    {
        front.draw(gl);
        back.rotate(gl);
        back.draw(gl);
    }
}