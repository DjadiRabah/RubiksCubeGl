package iutfbleau.rubikscube.controllers.activities;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.models.CubeFaceColorDescriptor;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.OpenGLRenderer;

public class CubeSolverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_solver);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CubeFaceColorDescriptor cubeFaceColorDescriptor = getIntent().getParcelableExtra("cubeDescriptor");

        int[][][] temp = cubeFaceColorDescriptor.getArray();

        Cube3D cube3d = new Cube3D(temp[0].length);

        for (int i = 0; i < temp.length; i++) {
            cube3d.setFace(i, temp[i]);
        }

        CubeGl cubegl = new CubeGl(cube3d);


        RelativeLayout relativeLayout = findViewById(R.id.rootLayout);

        OpenGLRenderer openglRenderer = new OpenGLRenderer(cubegl);
        relativeLayout.setOnTouchListener(new CubeMotionListener(cubegl));
        GLSurfaceView glSurfaceView = relativeLayout.findViewById(R.id.solver_test_sv);
        glSurfaceView.setRenderer(openglRenderer);
    }
}
