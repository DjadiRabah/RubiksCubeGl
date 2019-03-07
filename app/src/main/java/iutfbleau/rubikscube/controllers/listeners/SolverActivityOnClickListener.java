package iutfbleau.rubikscube.controllers.listeners;

import android.util.Log;
import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.SolverActivity;

public class SolverActivityOnClickListener implements View.OnClickListener {

    private SolverActivity solverActivity;
    private int counter = 0;

    public SolverActivityOnClickListener(SolverActivity solverActivity) {
        this.solverActivity = solverActivity;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.nextMove:
                counter++;

                //Interface changes
                if (counter == 2) {
                    solverActivity.getPrevMoveButton().setVisibility(View.VISIBLE);
                } else if (counter == solverActivity.getNumberOfMoves()) {
                    solverActivity.getNextMoveButton().setVisibility(View.INVISIBLE);
                }
                solverActivity.getMovesDisplay().setText(counter + " / " + solverActivity.getNumberOfMoves());

                break;

            case R.id.prevMove:
                counter--;

                //Interface changes
                if (counter == 1) {
                    solverActivity.getPrevMoveButton().setVisibility(View.INVISIBLE);
                } else if (counter == solverActivity.getNumberOfMoves() - 1) {
                    solverActivity.getNextMoveButton().setVisibility(View.VISIBLE);
                }
                solverActivity.getMovesDisplay().setText(counter + " / " + solverActivity.getNumberOfMoves());

                break;
        }
    }
}
