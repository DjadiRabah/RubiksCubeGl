package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.CameraSolverActivity;
import iutfbleau.rubikscube.controllers.activities.CustomCameraActivity;

public class CameraSolverOnClickListener implements View.OnClickListener {

    private CameraSolverActivity cameraSolverActivity;
    private int compteur = 1;

    public CameraSolverOnClickListener(CameraSolverActivity cameraSolverActivity) {
        this.cameraSolverActivity = cameraSolverActivity;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.next:
                compteur++;
                if (compteur == 2) {
                    cameraSolverActivity.getPrevButton().setVisibility(View.VISIBLE);
                } else if (compteur == 6) {
                    cameraSolverActivity.getNextButton().setVisibility(View.GONE);
                }
                cameraSolverActivity.getShootButton().setText("Shoot Face "+compteur);
                break;

            case R.id.prev:
                compteur--;
                if (compteur == 1) {
                    cameraSolverActivity.getPrevButton().setVisibility(View.GONE);
                }else if(compteur == 5){
                    cameraSolverActivity.getNextButton().setVisibility(View.VISIBLE);
                }
                cameraSolverActivity.getShootButton().setText("Shoot Face "+compteur);
                break;

            case R.id.btnCamera:
                Intent intent = new Intent(cameraSolverActivity, CustomCameraActivity.class);
                cameraSolverActivity.startActivityForResult(intent, CameraSolverActivity.CAMERA_REQUEST);
                break;
        }
    }
}
