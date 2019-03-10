package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.controllers.listeners.SolverActivityOnClickListener;
import iutfbleau.rubikscube.event.RotationEvent;
import iutfbleau.rubikscube.models.Array3DTransmitter;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.models.solver.solver.beginner.SolverBeginner;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.renderer.OpenGLRenderer;


public class SolverActivity extends Activity {

    private ArrayList<ArrayList<RotationEvent>> solution;
    private int totalMoves;
    private TextView totalMovesDisplay, currentSolverStep, stepGoal;
    private Button prevMove, nextMove;
    private Cube3D cube3d;
    private RelativeLayout solverInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        prevMove = findViewById(R.id.prevMove);
        nextMove = findViewById(R.id.nextMove);
        nextMove.setText("Start ❱");
        totalMovesDisplay = findViewById(R.id.movesNumber);
        currentSolverStep = findViewById(R.id.currentSolverStep);
        stepGoal = findViewById(R.id.stepGoal);
        solverInfo = findViewById(R.id.solverInfo);
        solverInfo.setVisibility(View.GONE);
        stepGoal.setText("Beginners Method Solver");

        prevMove.setVisibility(View.INVISIBLE);

        SolverActivityOnClickListener solverActivityOnClickListener = new SolverActivityOnClickListener(this);
        prevMove.setOnClickListener(solverActivityOnClickListener);
        nextMove.setOnClickListener(solverActivityOnClickListener);

        Array3DTransmitter array3DTransmitter = getIntent().getParcelableExtra("cubeDescriptor");
        int[][][] cubeFaces = array3DTransmitter.getArray();

        cube3d = new Cube3D(cubeFaces[0].length);

        for (int i = 0; i < cubeFaces.length; i++) {
            cube3d.setFace(i, cubeFaces[i]);
        }

        GLSurfaceView solverGlSurfaceView = findViewById(R.id.solverGlSurfaceView);
        CubeGl cubegl = new CubeGl(cube3d, solverGlSurfaceView);
        solution = new SolverBeginner().solve(cube3d);

        for (int i = 0; i < solution.size(); i++) {
            totalMoves += solution.get(i).size();
        }

        OpenGLRenderer openglRenderer = new OpenGLRenderer(cubegl);
        solverGlSurfaceView.setOnTouchListener(new CubeMotionListener(cubegl));
        solverGlSurfaceView.setRenderer(openglRenderer);
    }

    public int getNumberOfMoves() {
        return totalMoves;
    }

    public TextView getMovesDisplay() {
        return totalMovesDisplay;
    }

    public TextView getCurrentSolverStepDisplay() {
        return currentSolverStep;
    }

    public TextView getStepGoalDisplay() {
        return stepGoal;
    }

    public RelativeLayout getSolverInfoLayout() {
        return solverInfo;
    }

    public Button getPrevMoveButton() {
        return prevMove;
    }

    public Button getNextMoveButton() {
        return nextMove;
    }

    public ArrayList<ArrayList<RotationEvent>> getSolverSolution() {
        return solution;
    }

    public Cube3D getModelCube() {
        return cube3d;
    }

}
