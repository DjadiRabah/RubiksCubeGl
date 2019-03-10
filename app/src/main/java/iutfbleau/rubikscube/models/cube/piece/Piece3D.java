package iutfbleau.rubikscube.models.cube.piece;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Piece3D extends Piece
{
	private float vertices;

	protected float width;

    // The order we like to connect them.
    protected short[] indices = {0, 1, 2, 0, 2, 3};

    // Our vertex buffer.
    protected FloatBuffer vertexBuffer;

    // Our index buffer.
    protected ShortBuffer indexBuffer;

	public Piece3D()
	{
		super();
	}

	public Piece3D(int color)
	{
		super(color);
	}

    /* x1 y1 z1 : Coordonnées du point haut gauche
       x2 y2 z2 : Coordonnées du point bas droit
     */
    public void setPosition(float x1, float y1, float z1, float x2, float y2, float z2)
    {
    	this.width = (float)Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1)) / (float)Math.sqrt(2.0f);
        float[] vertices = new float[]{
                x1,  y1, z1,  // 0, Top Left
                x1, y1 - this.width, z1,  // 1, Bottom Left
                x2, y2, z2,  // 2, Bottom Right
                x2,  y2 + this.width, z2,  // 3, Top Right
        };

        // a float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply the number if
        // vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    public FloatBuffer getFloatBuffer()
    {
        return this.vertexBuffer;
    }

    public short[] getIndices()
    {
        return this.indices;
    }

    public ShortBuffer getShortBuffer()
    {
        return this.indexBuffer;
    }

    public float getWidth()
    {
        return this.width;
    }

    public void rotateX(double teta)
    {
        for(int i = 0; i < 4; i++)
        {
            float newY = this.vertexBuffer.get(i*3 + 1) * (float)Math.cos(teta) - this.vertexBuffer.get(i*3 + 2) * (float)Math.sin(teta);
            float newZ = this.vertexBuffer.get(i*3 + 1) * (float)Math.sin(teta) + this.vertexBuffer.get(i*3 + 2) * (float)Math.cos(teta);
            this.vertexBuffer.put(i*3 + 1, newY);
            this.vertexBuffer.put(i*3 + 2, newZ);
        }
    }

    public void rotateY(double teta)
    {
        for(int i = 0; i < 4; i++)
        {
            float newX = this.vertexBuffer.get(i*3) * (float)Math.cos(teta) + this.vertexBuffer.get(i*3 + 2) * (float)Math.sin(teta);
            float newZ = (-1)*this.vertexBuffer.get(i*3) * (float)Math.sin(teta) + this.vertexBuffer.get(i*3 + 2) * (float)Math.cos(teta);
            this.vertexBuffer.put(i*3, newX);
            this.vertexBuffer.put(i*3 + 2, newZ);
        }
    }

    public void rotateZ(double teta)
    {
        for(int i = 0; i < 4; i++)
        {
            float newX = this.vertexBuffer.get(i*3) * (float)Math.cos(teta) - this.vertexBuffer.get(i*3 + 1) * (float)Math.sin(teta);
            float newY = this.vertexBuffer.get(i*3) * (float)Math.sin(teta) + this.vertexBuffer.get(i*3 + 1) * (float)Math.cos(teta);
            this.vertexBuffer.put(i*3, newX);
            this.vertexBuffer.put(i*3 + 1, newY);
        }
    }

    public void setVertices(float[] vertices)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            this.vertexBuffer.put(i,vertices[i]);
        }
    }

    public float[] getVertices()
    {
        float[] vertices = new float[12];
        for(int i = 0; i < vertices.length; i++)
        {
            vertices[i] = this.vertexBuffer.get(i);
        }
        return vertices;
    }
}
