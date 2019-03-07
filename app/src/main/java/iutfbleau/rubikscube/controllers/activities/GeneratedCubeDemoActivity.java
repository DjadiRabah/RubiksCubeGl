package iutfbleau.rubikscube.controllers.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.models.CubeFaceColorDescriptor;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.models.shuffle.ShuffleRandom;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.renderer.OpenGLRenderer;

public class GeneratedCubeDemoActivity extends AppCompatActivity {

    public static int RANDOMIZE_CUBE = 0;
    public static int CAPTURE_CUBE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_cube_demo);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Cube3D cube3d = null;

        Intent intent = getIntent();

        if (intent.getIntExtra("task", RANDOMIZE_CUBE) == RANDOMIZE_CUBE) {

            cube3d = new Cube3D(intent.getIntExtra("cube_size", 3));
            cube3d.shuffle(new ShuffleRandom());

        } else if (intent.getIntExtra("task", RANDOMIZE_CUBE) == CAPTURE_CUBE) {

            CubeFaceColorDescriptor cubeFaceColorDescriptor = getIntent().getParcelableExtra("cubeDescriptor");
            int[][][] temp = cubeFaceColorDescriptor.getArray();

            cube3d = new Cube3D(temp[0].length);

            for (int i = 0; i < temp.length; i++) {
                cube3d.setFace(i, temp[i]);
            }

        }

        CubeGl cubegl = new CubeGl(cube3d);

        RelativeLayout relativeLayout = findViewById(R.id.rootLayout);

        OpenGLRenderer openglRenderer = new OpenGLRenderer(cubegl);
        relativeLayout.setOnTouchListener(new CubeMotionListener(cubegl));
        GLSurfaceView glSurfaceView = relativeLayout.findViewById(R.id.solver_test_sv);
        glSurfaceView.setRenderer(openglRenderer);
    }
}
