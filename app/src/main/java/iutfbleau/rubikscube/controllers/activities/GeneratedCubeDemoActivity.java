package iutfbleau.rubikscube.controllers.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.controllers.listeners.GeneratedCubeDemoActivityOnClickListener;
import iutfbleau.rubikscube.models.Array3DTransmitter;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.models.shuffle.ShuffleRandom;
import iutfbleau.rubikscube.models.solver.solver.beginner.SolverBeginner;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.renderer.OpenGLRenderer;

public class GeneratedCubeDemoActivity extends AppCompatActivity {

    public static int RANDOMIZE_CUBE = 0;
    public static int BUILD_FROM_ARRAY = 1;
    private Cube3D cube3d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_cube_demo);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();

        if (intent.getIntExtra("task", RANDOMIZE_CUBE) == RANDOMIZE_CUBE) {

            cube3d = new Cube3D(intent.getIntExtra("cube_size", 3));
            cube3d.shuffle(new ShuffleRandom());

        } else if (intent.getIntExtra("task", RANDOMIZE_CUBE) == BUILD_FROM_ARRAY) {

            Array3DTransmitter array3DTransmitter = getIntent().getParcelableExtra("cubeDescriptor");
            int[][][] cubeFaces = array3DTransmitter.getArray();

            cube3d = new Cube3D(cubeFaces[0].length);

            for (int i = 0; i < cubeFaces.length; i++) {
                cube3d.setFace(i, cubeFaces[i]);
            }

        }

        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        GLSurfaceView glSurfaceView = rootLayout.findViewById(R.id.solver_test_sv);
        CubeGl cubegl = new CubeGl(cube3d, glSurfaceView);


        Button launchSolver = findViewById(R.id.launchSolver);
        launchSolver.setOnClickListener(new GeneratedCubeDemoActivityOnClickListener(this));

        OpenGLRenderer openglRenderer = new OpenGLRenderer(cubegl);

        glSurfaceView.setOnTouchListener(new CubeMotionListener(cubegl));
        glSurfaceView.setRenderer(openglRenderer);
    }

    public Cube3D getCubeModel() {
        return cube3d;
    }

}
