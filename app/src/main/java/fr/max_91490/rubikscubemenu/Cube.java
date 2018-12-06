package fr.max_91490.rubikscubemenu;

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


    public Cube(int n)
    {
        this.top = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,1.0f,1.0f);
        this.top.rotateX(Math.toRadians(-90.0));

        this.left = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 0.0f,1.0f,0.0f);
        this.left.rotateY(Math.toRadians(-90.0));

        this.front = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,0.0f,0.0f);

        this.right = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 0.0f,0.0f,1.0f);
        this.right.rotateY(Math.toRadians(90.0));

        this.back = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,127.0f/255.0f,39.0f/255.0f);
        this.back.rotateY(Math.toRadians(180.0));

        this.bot = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,242.0f/255.0f,0);
        this.bot.rotateX(Math.toRadians(90.0));
    }

    public void RotateX(int direction, int index)
    {
        if(direction == 0)
        {


        }

    }

    public void draw(GL10 gl)
    {
        top.draw(gl);
        left.draw(gl);
        front.draw(gl);
        right.draw(gl);
        back.draw(gl);
        bot.draw(gl);
    }
}