package iutfbleau.rubikscube.controllers.activities;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.OpenGLRenderer;

public class CubeSolverActivity extends AppCompatActivity {

    private int cubeSize;
    private CubeGl cube;
    public GLSurfaceView glSurfaceView;
    private OpenGLRenderer openglRenderer;
    private Cube3D cube3D;

    public CubeSolverActivity(Cube3D cube3D){
        this.cube3D = cube3D;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_solver);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cubeSize = getIntent().getIntExtra("cube_size", 3);

        this.cube = new CubeGl(cube3D);
        this.openglRenderer = new OpenGLRenderer(cube);

        RelativeLayout relativeLayout = findViewById(R.id.rootLayout);

        relativeLayout.setOnTouchListener(new CubeMotionListener(cube));
        glSurfaceView = findViewById(R.id.solver_test_sv);
        glSurfaceView.setRenderer(openglRenderer);
    }
}
