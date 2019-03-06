package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.CubeGeneratorActivity;
import iutfbleau.rubikscube.controllers.activities.GeneratedCubeDemoActivity;
import iutfbleau.rubikscube.controllers.fragments.CubeGeneratorFragment;

public class CubeGeneratorFragmentOnClickListener implements View.OnClickListener {

    private CubeGeneratorFragment cubeGeneratorFragment;

    public CubeGeneratorFragmentOnClickListener(CubeGeneratorFragment cubeGeneratorFragment) {
        this.cubeGeneratorFragment = cubeGeneratorFragment;
    }

    @Override
    public void onClick(View v) {

        v.startAnimation(new AlphaAnimation(1F, 0.8F));
        Intent in;

        switch (v.getId()) {

            case R.id.randomize:

                cubeGeneratorFragment.getfragmentGeneratorLoadingLayout().setVisibility(View.VISIBLE);
                in = new Intent(cubeGeneratorFragment.getActivity(), GeneratedCubeDemoActivity.class);
                in.putExtra("task", GeneratedCubeDemoActivity.RANDOMIZE_CUBE);
                in.putExtra("cube_size", cubeGeneratorFragment.getSeekBarCurrentValue());
                cubeGeneratorFragment.startActivity(in);

                break;

            case R.id.cameraCapture:

                in = new Intent(cubeGeneratorFragment.getActivity(), CubeGeneratorActivity.class);
                in.putExtra("cube_size", cubeGeneratorFragment.getSeekBarCurrentValue());
                cubeGeneratorFragment.startActivity(in);
                break;

        }
    }
}
