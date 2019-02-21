package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.SolverActivity;
import iutfbleau.rubikscube.controllers.activities.CustomCameraActivity;

public class SolverOnClickListener implements View.OnClickListener {

    private SolverActivity solverActivity;
    private int compteur = 1;

    public SolverOnClickListener(SolverActivity solverActivity) {
        this.solverActivity = solverActivity;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.next:
                compteur++;
                if (compteur == 2) {
                    solverActivity.getPrevButton().setVisibility(View.VISIBLE);
                } else if (compteur == 6) {
                    solverActivity.getNextButton().setVisibility(View.GONE);
                }
                solverActivity.getShootButton().setText("Shoot Face "+compteur);
                break;

            case R.id.prev:
                compteur--;
                if (compteur == 1) {
                    solverActivity.getPrevButton().setVisibility(View.GONE);
                }else if(compteur == 5){
                    solverActivity.getNextButton().setVisibility(View.VISIBLE);
                }
                solverActivity.getShootButton().setText("Shoot Face "+compteur);
                break;

            case R.id.btnCamera:
                Intent intent = new Intent(solverActivity, CustomCameraActivity.class);
                intent.putExtra("cube_size", solverActivity.getCubeSize());
                solverActivity.startActivityForResult(intent, SolverActivity.CAMERA_REQUEST);
                break;
        }
    }
}
