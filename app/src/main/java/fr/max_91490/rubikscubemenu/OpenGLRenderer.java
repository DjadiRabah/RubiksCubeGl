package fr.max_91490.rubikscubemenu;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLSurfaceView.RENDERMODE_CONTINUOUSLY;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private Cube mCube;
    private float mCubeRotation;

    public OpenGLRenderer(int cubeSize) {
        this.mCube = new Cube(cubeSize);

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        /* Couleur d'arrière plan */
        gl.glClearColor(105.0f/255.0f, 105.0f/255.0f, 105.0f/255.0f, 0.0f);

        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -10.0f);

        mCube.draw(gl);

        gl.glLoadIdentity();

        this.mCube.rotateY(Math.toRadians(1.0));
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}