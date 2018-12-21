package view.cube3d;

import model.cube.*;
import model.cube.piece.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Cube3D
{
	protected Cube cube;

	public Cube3D(Cube cube)
	{
		this.cube = cube;
	}

	private float[] setColor(int color)
	{
		float[] color;

		switch(color)
		{
			case Piece.WHITE  : color = new float[]{1.0f,1.0f,1.0f};                     break;
			case Piece.GREEN  : color = new float[]{0.0f,1.0f,0.0f};                     break;
			case Piece.RED    : color = new float[]{1.0f,0.0f,0.0f};                     break;
			case Piece.BLUE   : color = new float[]{0.0f,0.0f,1.0f};                     break;
			case Piece.ORANGE : color = new float[]{1.0f,127.0f/255.0f,39.0f/255.0f};    break;
			case Piece.YELLOW : color = new float[]{1.0f,242.0f/255.0f,0.0f};            break;
			default           : color = new float[]{0.0f,0.0f,0.0f};			 		 break;
		}
		return color;
	}

	/**
     * This function draws our square on screen.
     * @param gl
     */
	private void drawPiece(GL10 gl, Piece3D piece)
	{
		float[] color = this.setColor(piece.getColor());
		FloatBuffer vertexBuffer = piece.getFloatBuffer();
		short[] indices = piece.getIndices();
		ShortBuffer indexBuffer = piece.getShortBuffer();

        // Sélectionner couleur du carré
        gl.glColor4f(color[0], color[1], color[2], 0.0f);
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
	}

	private void drawSquare(GL10 gl, Square square)
    {
        Piece[][] pieces = square.getPieces();
        for(int i = 0; i < square.getSize(); i++)
        {
            for(int j = 0; j < square.getSize(); j++)
            {
            	this.drawPiece(gl, (Piece3D)pieces[i][j]);
            }
        }
    }

    public void drawCube(GL10 gl)
    {
        for(int i = 0; i < 6; i++)
        {
            this.drawPiece(gl, this.cube.getSquare(i));
        }
    }
}