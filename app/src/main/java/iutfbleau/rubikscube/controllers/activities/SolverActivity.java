package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    private TextView totalMovesDisplay;
    private Button prevMove, nextMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        prevMove = findViewById(R.id.prevMove);
        nextMove = findViewById(R.id.nextMove);
        totalMovesDisplay = findViewById(R.id.movesNumber);
        prevMove.setVisibility(View.INVISIBLE);

        SolverActivityOnClickListener solverActivityOnClickListener = new SolverActivityOnClickListener(this);
        prevMove.setOnClickListener(solverActivityOnClickListener);
        nextMove.setOnClickListener(solverActivityOnClickListener);

        Array3DTransmitter array3DTransmitter = getIntent().getParcelableExtra("cubeDescriptor");
        int[][][] cubeFaces = array3DTransmitter.getArray();

        Cube3D cube3d = new Cube3D(cubeFaces[0].length);

        for (int i = 0; i < cubeFaces.length; i++) {
            cube3d.setFace(i, cubeFaces[i]);
        }

        CubeGl cubegl = new CubeGl(cube3d);
        solution = new SolverBeginner().solve(cube3d);

        for (int i = 0; i < solution.size(); i++) {
            totalMoves += solution.get(i).size();
        }

        OpenGLRenderer openglRenderer = new OpenGLRenderer(cubegl);
        GLSurfaceView solverGlSurfaceView = findViewById(R.id.solverGlSurfaceView);
        solverGlSurfaceView.setOnTouchListener(new CubeMotionListener(cubegl));
        solverGlSurfaceView.setRenderer(openglRenderer);
    }

    public int getNumberOfMoves(){
        return totalMoves;
    }

    public TextView getMovesDisplay(){
        return totalMovesDisplay;
    }

    public Button getPrevMoveButton(){
        return prevMove;
    }
    public Button getNextMoveButton(){
        return nextMove;
    }

    public ArrayList<ArrayList<RotationEvent>> getSolverSolution(){
        return solution;
    }

}
