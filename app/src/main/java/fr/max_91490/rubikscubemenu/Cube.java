package fr.max_91490.rubikscubemenu;

import javax.microedition.khronos.opengles.GL10;

import fr.max_91490.rubikscubemenu.shuffle.ShuffleRandom;

class Cube
{
    protected float offset;
    protected Face[] faces;

    public Cube(int n)
    {
        fr.max_91490.rubikscubemenu.cube.Cube c = new fr.max_91490.rubikscubemenu.cube.Cube(n);
        c.shuffle(new ShuffleRandom());
        int[][] colors = c.getSquare(fr.max_91490.rubikscubemenu.cube.Cube.TOP).getColors();

        this.faces = new Face[6];
        this.faces[0] = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, colors);
        this.faces[0].rotateX(Math.toRadians(-90.0));

        colors = c.getSquare(fr.max_91490.rubikscubemenu.cube.Cube.LEFT).getColors();
        this.faces[1] = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, colors);
        this.faces[1].rotateY(Math.toRadians(-90.0));

        this.faces[2] = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,0.0f,0.0f);

        this.faces[3] = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 0.0f,0.0f,1.0f);
        this.faces[3].rotateY(Math.toRadians(90.0));

        this.faces[4] = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,127.0f/255.0f,39.0f/255.0f);
        this.faces[4].rotateY(Math.toRadians(180.0));

        this.faces[5] = new Face(n,-1.0f,1.0f,1.0f,1.0f,0.05f, 1.0f,242.0f/255.0f,0);
        this.faces[5].rotateX(Math.toRadians(90.0));
    }

    public void rotateX(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            this.faces[i].rotateX(teta);
        }
    }

    public void rotateY(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            this.faces[i].rotateY(teta);
        }
    }

    public void rotateZ(double teta)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            this.faces[i].rotateZ(teta);
        }
    }

    public void draw(GL10 gl)
    {
        for(int i = 0; i < this.faces.length; i++)
        {
            this.faces[i].draw(gl);
        }
    }
}