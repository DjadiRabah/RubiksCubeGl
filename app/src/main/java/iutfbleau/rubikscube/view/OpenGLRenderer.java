package iutfbleau.rubikscube.view;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import iutfbleau.rubikscube.models.cube.cube.Cube3D;


public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private CubeGl cube;
    private float angleX;
    private float angleY;
    private float angleZ;

    public OpenGLRenderer(Cube3D cube)
    {
        this.cube = new CubeGl(cube);
        this.angleX = 0.0f;
        this.angleY = 0.0f;
        this.angleZ = 0.0f;
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


        cube.drawCube(gl);

        gl.glLoadIdentity();
        this.angleX = this.angleX - 0.15f;
        /*this.cube.rotateX(Math.toRadians(1.0));
        this.cube.rotateY(Math.toRadians(1.0));
        this.cube.rotateZ(Math.toRadians(1.0));*/
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

    public CubeGl getCube()
    {
        return this.cube;
    }
}