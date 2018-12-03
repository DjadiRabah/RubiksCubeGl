package com.example.rabah.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

class Cube
{
    protected float offset;
    private  Face top;
    private  Face front;
    private  Face left;
    private  Face right;
    private  Face back;
    private  Face bot;


    public Cube()
    {
        this.top = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 1.0f,1.0f,1.0f);
        this.front = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 1.0f,0.0f,0.0f);
        this.left = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 0.0f,1.0f,0.0f);
        this.right = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 0.0f,0.0f,1.0f);
        this.back = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 1.0f,127.0f/255.0f,39.0f/255.0f);
        this.bot = new Face(3,-1.0f,1.0f,1.0f,1.0f,0.1f, 1.0f,242.0f/255.0f,0);
    }

    public void draw(GL10 gl)
    {
        gl.glRotatef(-90,1.0f,0.0f,0.0f);
        top.draw(gl);
        gl.glRotatef(90,1.0f,0.0f,0.0f);

        gl.glRotatef(-90,0.0f,1.0f,0.0f);
        left.draw(gl);
        gl.glRotatef(90,0.0f,1.0f,0.0f);

        front.draw(gl);

        gl.glRotatef(90,0.0f,1.0f,0.0f);
        right.draw(gl);
        gl.glRotatef(-90,0.0f,1.0f,0.0f);

        gl.glRotatef(180,0.0f,1.0f,0.0f);
        back.draw(gl);
        gl.glRotatef(180,0.0f,1.0f,0.0f);

        gl.glRotatef(90,1.0f,0.0f,0.0f);
        bot.draw(gl);
        gl.glRotatef(-90,1.0f,0.0f,0.0f);
    }
}