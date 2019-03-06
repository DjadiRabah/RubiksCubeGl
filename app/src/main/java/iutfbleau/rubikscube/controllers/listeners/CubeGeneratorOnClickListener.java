package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.CubeGeneratorActivity;
import iutfbleau.rubikscube.controllers.activities.GeneratedCubeDemoActivity;
import iutfbleau.rubikscube.controllers.activities.CustomCameraActivity;
import iutfbleau.rubikscube.models.CubeFaceColorDescriptor;

public class CubeGeneratorOnClickListener implements View.OnClickListener {

    private CubeGeneratorActivity cubeGeneratorActivity;
    private int compteur = 1;

    public CubeGeneratorOnClickListener(CubeGeneratorActivity cubeGeneratorActivity) {
        this.cubeGeneratorActivity = cubeGeneratorActivity;
    }

    public int getCurrentFace() {
        return this.compteur - 1;
    }

    @Override
    public void onClick(View v) {

        v.startAnimation(new AlphaAnimation(1F, 0.8F));
        switch (v.getId()) {

            case R.id.next:
                compteur++;
                //Interface changes

                if (compteur == 2) {
                    cubeGeneratorActivity.getPrevButton().setVisibility(View.VISIBLE);
                } else if (compteur == 6) {
                    cubeGeneratorActivity.getNextButton().setVisibility(View.INVISIBLE);
                }
                cubeGeneratorActivity.getShootButton().setText("Analyze Face " + compteur);

                //Cube rotations
                if (compteur == 2) {
                    cubeGeneratorActivity.get3DCube().rotateX(-90.0f);
                    cubeGeneratorActivity.get3DCube().rotateY(90.0f);
                } else if (compteur == 3) {
                    cubeGeneratorActivity.get3DCube().rotateY(-90.0f);
                } else if (compteur == 4) {
                    cubeGeneratorActivity.get3DCube().rotateY(-90.0f);
                } else if (compteur == 5) {
                    cubeGeneratorActivity.get3DCube().rotateY(-90.0f);
                } else if (compteur == 6) {
                    cubeGeneratorActivity.get3DCube().rotateY(-180.0f);
                    cubeGeneratorActivity.get3DCube().rotateX(-90.0f);
                }
                break;

            case R.id.prev:
                compteur--;
                //Interface changes
                if (compteur == 1) {
                    cubeGeneratorActivity.getPrevButton().setVisibility(View.INVISIBLE);
                } else if (compteur == 5) {
                    cubeGeneratorActivity.getNextButton().setVisibility(View.VISIBLE);
                }
                cubeGeneratorActivity.getShootButton().setText("Analyze Face " + compteur);

                //Cube rotations
                if (compteur == 1) {
                    cubeGeneratorActivity.get3DCube().rotateY(-90.0f);
                    cubeGeneratorActivity.get3DCube().rotateX(90.0f);
                } else if (compteur == 2) {
                    cubeGeneratorActivity.get3DCube().rotateY(90.0f);
                } else if (compteur == 3) {
                    cubeGeneratorActivity.get3DCube().rotateY(90.0f);
                } else if (compteur == 4) {
                    cubeGeneratorActivity.get3DCube().rotateY(90.0f);
                } else if (compteur == 5) {
                    cubeGeneratorActivity.get3DCube().rotateX(90.0f);
                    cubeGeneratorActivity.get3DCube().rotateY(180.0f);
                }
                break;

            case R.id.btnCamera:

                if (cubeGeneratorActivity.getChangeActionState()) {

                    cubeGeneratorActivity.setChangeActionState(false);

                    Intent intent = new Intent(cubeGeneratorActivity, GeneratedCubeDemoActivity.class);
                    intent.putExtra("cubeDescriptor", new CubeFaceColorDescriptor(cubeGeneratorActivity.getFullCubeColorsTab()));
                    intent.putExtra("task", GeneratedCubeDemoActivity.CAPTURE_CUBE);
                    cubeGeneratorActivity.startActivity(intent);

                } else {

                    Intent intent = new Intent(cubeGeneratorActivity, CustomCameraActivity.class);
                    intent.putExtra("cube_size", cubeGeneratorActivity.getCubeSize());
                    cubeGeneratorActivity.startActivityForResult(intent, CubeGeneratorActivity.CAMERA_REQUEST);

                }

                break;
        }
    }
}
