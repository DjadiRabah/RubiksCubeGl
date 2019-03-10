package iutfbleau.rubikscube.view;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

import iutfbleau.rubikscube.models.cube.Cube;

public class CubeGl
{
	protected Cube cube;
	protected GLSurfaceView surfaceView;
	protected FaceGL[] faces;

	public CubeGl(Cube cube, GLSurfaceView surfaceView)
	{
		this.cube = cube;
		this.surfaceView = surfaceView;
		this.faces = new FaceGL[6];
        for(int currentFace = 0; currentFace < 6; currentFace++)
        {
            int[][] colors = cube.getFace(currentFace).getColors();
            this.faces[currentFace] = new FaceGL(colors);
        }
        this.setPosition();
	}

	private void setPosition()
    {
        float position = 1.5f;
        float offset = 0.05f;
        this.faces[0].setPosition(-position,position,position,position,offset);
		this.faces[0].rotateX(Math.toRadians(-90.0));
        this.faces[1].setPosition(-position,position,position,position,offset);
		this.faces[1].rotateY(Math.toRadians(-90.0));
        this.faces[2].setPosition(-position,position,position,position,offset);
		this.faces[2].setPosition(-position,position,position,position,offset);
        this.faces[3].setPosition(-position,position,position,position,offset);
		this.faces[3].rotateY(Math.toRadians(90.0));
        this.faces[4].setPosition(-position,position,position,position,offset);
		this.faces[4].rotateY(Math.toRadians(180.0));
        this.faces[5].setPosition(-position,position,position,position,offset);
		this.faces[5].rotateX(Math.toRadians(90.0));
    }

    public void drawCube(GL10 gl)
    {
        for(int i = 0; i < 6; i++)
        {
            this.faces[i].draw(gl);
        }
    }

	public void rotate(int direction, int index)
	{
        this.cube.rotate(direction,index);
	}
	public void rotateX(double teta)
	{
		//this.cube.rotateX(Math.toRadians(teta));
	}

	public void rotateY(double teta)
	{
		//this.cube.rotateY(Math.toRadians(teta));
	}

	public void rotateZ(double teta)
	{
		//this.cube.rotateZ(Math.toRadians(teta));
	}

	public Cube getCube()
	{
		return this.cube;
	}
}