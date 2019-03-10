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
        Log.e("curr", "" + currentSolver);

        ArrayList<ArrayList<RotationEvent>> solution = solverActivity.getSolverSolution();
        ArrayList<RotationEvent> currentRotations = solution.get(currentSolver);
        RotationEvent rotation;

        switch (v.getId()) {

            case R.id.nextMove:

                //Rotations
                rotation = currentRotations.get(currentRotation);
                solverActivity.getModelCube().rotate(rotation.getDirection(), rotation.getIndex());
                currentRotation++;

                if (currentRotation == solution.get(currentSolver).size()) {
                    if (currentSolver < solution.size() - 1) {
                        currentSolver++;
                        currentRotation = 0;
                    }
                }

                //Interface changes
                solverActivity.getCurrentSolverStepDisplay().setText("Step " + (currentSolver + 1));
                solverActivity.getStepGoalDisplay().setText(getCurrentSolverStep(currentSolver));

                counter++;
                if (counter == 1) {
                    solverActivity.getSolverInfoLayout().setVisibility(View.VISIBLE);
                    solverActivity.getNextMoveButton().setText(solverActivity.getString(R.string.next_button_text));
                    solverActivity.getStepGoalDisplay().setPadding(10, 0, 10, 10);
                }

                if (counter > 1) {
                    solverActivity.getPrevMoveButton().setVisibility(View.VISIBLE);
                }
                if (counter == solverActivity.getNumberOfMoves()) {
                    solverActivity.getNextMoveButton().setVisibility(View.INVISIBLE);
                    solverActivity.getStepGoalDisplay().setText("Cube solved !");
                }
                solverActivity.getMovesDisplay().setText("Move " + counter + " of " + solverActivity.getNumberOfMoves());

                break;

            case R.id.prevMove:

                //Rotations
                currentRotation--;
                if (currentRotation == -1) {
                    if (currentSolver > 0) {
                        currentSolver--;
                        currentRotations = solution.get(currentSolver);
                        currentRotation = currentRotations.size() - 1;
                    } else
                        currentRotation = 0;
                }
                rotation = currentRotations.get(currentRotation);
                solverActivity.getModelCube().rotate(rotation.getOppositeDirection(), rotation.getIndex());


                //Interface changes
                solverActivity.getCurrentSolverStepDisplay().setText("Step " + (currentSolver + 1));
                solverActivity.getStepGoalDisplay().setText(getCurrentSolverStep(currentSolver));

                counter--;
                if (counter == 1) {
                    solverActivity.getPrevMoveButton().setVisibility(View.INVISIBLE);
                }
                if (counter <= solverActivity.getNumberOfMoves() - 1) {
                    solverActivity.getNextMoveButton().setVisibility(View.VISIBLE);
                }
                solverActivity.getMovesDisplay().setText("Move " + counter + " of " + solverActivity.getNumberOfMoves());

                break;
        }
    }

    private String getCurrentSolverStep(int pos) {

        switch (pos) {

            case 0:
                return "Construction of a cross on one of the faces :"; //Construction de la croix sur une des faces
            case 1:
                return "Construction of the first crown :"; //Construction de la première couronne
            case 2:
                return "Construction of the second crown :"; //Construction de la deuxième couronne
            case 3:
                return "Construction of a cross on the opposite side to the first :"; //Croix sur la dernière face
            case 4:
                return "Place the edges :"; //Placer les arêtes
            case 5:
                return "Place the corners :"; //Placer les coins
            case 6:
                return "Orient the corners :"; //Orienter les coins
            default:
                return "";

        }
    }
}
