package com.example.rabah.myapplication;

import javax.microedition.khronos.opengles.GL10;

public class Face
{
    private  Square[][] squares;

    public Face(int n, float x1, float x2, float y1, float z, float offset, float r, float g, float b)
    {
        float width = (x2-x1 - 2.0f * offset)/3.0f;
        this.squares = new Square[n][n];
        for(int i = 0; i < this.squares.length; i++)
        {
            for(int j = 0; j < this.squares.length; j++)
            {
                squares[i][j] = new Square(x1 + j * (width + offset),y1 - i * (width + offset),z,x1 + (j+1) * width + j * offset ,y1 - (i+1) * width - i * offset,z,
                r, g,  b);
            }
        }
    }

    public void rotate(GL10 gl)
    {
        /*gl.glRotatef(180,0.0f,1.0f,0.0f);
        gl.glTranslatef(0.0f, 0.0f, 1.0f);*/
    }

    public void draw(GL10 gl)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                squares[i][j].draw(gl);
            }
        }
    }
}
