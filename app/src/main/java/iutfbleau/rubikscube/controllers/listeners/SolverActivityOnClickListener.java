package iutfbleau.rubikscube.controllers.listeners;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.SolverActivity;
import iutfbleau.rubikscube.event.RotationEvent;

public class SolverActivityOnClickListener implements View.OnClickListener {

    private SolverActivity solverActivity;
    private int counter = 0;
    private int currentSolver = 0;
    private int currentRotation = 0;

    public SolverActivityOnClickListener(SolverActivity solverActivity) {
        this.solverActivity = solverActivity;
    }

    @Override
    public void onClick(View v) {

        ArrayList<ArrayList<RotationEvent>> solution = solverActivity.getSolverSolution();
        ArrayList<RotationEvent> currentRotations = solution.get(currentSolver);
        RotationEvent rotation;

        switch (v.getId()) {

            case R.id.nextMove:

                //Rotations
                rotation = currentRotations.get(currentRotation);
                solverActivity.getModelCube().rotate(rotation.getDirection(), rotation.getIndex());
                currentRotation++;

                if (currentRotation == solution.get(currentSolver).size())
                {
                    if(currentSolver < solution.size() - 1)
                    {
                        currentSolver++;
                        currentRotation = 0;
                    }
                }

                //Interface changes
                counter++;
                if (counter >= 1) {
                    solverActivity.getPrevMoveButton().setVisibility(View.VISIBLE);
                }
                if (counter == solverActivity.getNumberOfMoves()) {
                    solverActivity.getNextMoveButton().setVisibility(View.INVISIBLE);
                }
                solverActivity.getMovesDisplay().setText("Move "+counter + " of " + solverActivity.getNumberOfMoves());

                break;

            case R.id.prevMove:
                currentRotation--;
                if(currentRotation == -1)
                {
                    if(currentSolver > 0)
                    {
                        currentSolver--;
                        currentRotations = solution.get(currentSolver);
                        currentRotation = currentRotations.size() - 1;
                    }
                    else
                        currentRotation = 0;
                }
                //Rotations
                rotation = currentRotations.get(currentRotation);
                solverActivity.getModelCube().rotate(rotation.getOppositeDirection(), rotation.getIndex());


                //Interface changes
                counter--;
                if (counter == 1) {
                    solverActivity.getPrevMoveButton().setVisibility(View.INVISIBLE);
                }
                if (counter <= solverActivity.getNumberOfMoves() - 1) {
                    solverActivity.getNextMoveButton().setVisibility(View.VISIBLE);
                }
                solverActivity.getMovesDisplay().setText("Move "+counter + " of " + solverActivity.getNumberOfMoves());

                break;
        }
    }
}
