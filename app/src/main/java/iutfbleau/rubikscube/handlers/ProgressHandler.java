package iutfbleau.rubikscube.handlers;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import iutfbleau.rubikscube.controllers.activities.SolverActivity;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;

public class ProgressHandler extends Handler {

    private SolverActivity solverActivity;

    public ProgressHandler(SolverActivity solverActivity){
        this.solverActivity = solverActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Cube3D cube3D = (Cube3D)msg.obj;
        cube3D.setFace(solverActivity.getSolverOnClickListener().getCurrentFace(), solverActivity.getColorsTab());
        solverActivity.getLoadingLayout().setVisibility(View.GONE);
        Toast.makeText(solverActivity, "Done !", Toast.LENGTH_SHORT).show();
    }
}