package iutfbleau.rubikscube.handlers;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import iutfbleau.rubikscube.controllers.activities.CubeGeneratorActivity;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;

public class ProgressHandler extends Handler {

    private CubeGeneratorActivity cubeGeneratorActivity;

    public ProgressHandler(CubeGeneratorActivity cubeGeneratorActivity){
        this.cubeGeneratorActivity = cubeGeneratorActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Cube3D cube3D = (Cube3D)msg.obj;
        cube3D.setFace(cubeGeneratorActivity.getCubeGeneratorOnClickListener().getCurrentFace(), cubeGeneratorActivity.getColorsTab());
        cubeGeneratorActivity.getLoadingLayout().setVisibility(View.GONE);
        Toast.makeText(cubeGeneratorActivity, "Done !", Toast.LENGTH_SHORT).show();
    }
}