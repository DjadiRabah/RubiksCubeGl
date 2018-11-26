package com.example.rabah.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square
{
    protected float r;
    protected float g;
    protected float b;
    // The order we like to connect them.
    protected short[] indices = { 0, 1, 2, 0, 2, 3 };

    // Our vertex buffer.
    protected FloatBuffer vertexBuffer;

    // Our index buffer.
    protected ShortBuffer indexBuffer;

    /* x1 y1 z1 : Coordonnées du point haut gauche
       x2 y2 z2 : Coordonnées du point bas droit
     */
    public Square(float x1, float y1, float z1, float x2, float y2, float z2, float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        float d = (float)Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1)) / (float)Math.sqrt(2.0f);
        float vertices[] = {
                x1,  y1, z1,  // 0, Top Left
                x1, y1 - d, z1,  // 1, Bottom Left
                x2, y2, z2,  // 2, Bottom Right
                x2,  y2 + d, z2,  // 3, Top Right
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

    /**
     * This function draws our square on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        // Sélectionner couleur du carré
        gl.glColor4f(this.r, this.g, this.b, 0.0f);
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
}
