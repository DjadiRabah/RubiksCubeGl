package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;

import iutfbleau.rubikscube.controllers.activities.GeneratedCubeDemoActivity;
import iutfbleau.rubikscube.controllers.activities.SolverActivity;
import iutfbleau.rubikscube.models.Array3DTransmitter;

public class GeneratedCubeDemoActivityOnClickListener implements View.OnClickListener {

    private GeneratedCubeDemoActivity generatedCubeDemoActivity;

    public GeneratedCubeDemoActivityOnClickListener(GeneratedCubeDemoActivity generatedCubeDemoActivity) {
        this.generatedCubeDemoActivity = generatedCubeDemoActivity;
    }

    @Override
    public void onClick(View v) {

        int[][][] cubeFaces = new int[6][3][3];

        for (int i = 0; i < cubeFaces.length; i++) {
            cubeFaces[i] = generatedCubeDemoActivity.getCubeModel().getFace(i).getColors();
        }

        Intent intent = new Intent(generatedCubeDemoActivity, SolverActivity.class);
        intent.putExtra("task", GeneratedCubeDemoActivity.BUILD_FROM_ARRAY);
        intent.putExtra("cubeDescriptor", new Array3DTransmitter(cubeFaces));
        generatedCubeDemoActivity.startActivity(intent);
    }
}
