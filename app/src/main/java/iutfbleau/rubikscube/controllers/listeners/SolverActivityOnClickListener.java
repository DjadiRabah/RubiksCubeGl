package iutfbleau.rubikscube.controllers.listeners;

import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.SolverActivity;

public class SolverActivityOnClickListener implements View.OnClickListener {

    private SolverActivity solverActivity;
    private int counter = 1;

    public SolverActivityOnClickListener(SolverActivity solverActivity) {
        this.solverActivity = solverActivity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.nextMove:

                if(counter == 10){
                    solverActivity.getNextMoveButton().setVisibility(View.INVISIBLE);
                }


                solverActivity.getMovesDisplayer().setText(counter+"/"+solverActivity.getNumberOfMoves());
                counter++;
                break;

            case R.id.prevMove:
                counter--;
                solverActivity.getMovesDisplayer().setText(counter+"/"+solverActivity.getNumberOfMoves());
                break;

        }

    }
}
