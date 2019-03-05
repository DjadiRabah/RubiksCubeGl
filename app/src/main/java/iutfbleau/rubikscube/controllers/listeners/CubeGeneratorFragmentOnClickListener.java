package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;

import iutfbleau.rubikscube.controllers.activities.CubeGeneratorActivity;
import iutfbleau.rubikscube.controllers.fragments.CubeGeneratorFragment;

public class CubeGeneratorFragmentOnClickListener implements View.OnClickListener {

    private CubeGeneratorFragment cubeGeneratorFragment;

    public CubeGeneratorFragmentOnClickListener(CubeGeneratorFragment cubeGeneratorFragment) {
        this.cubeGeneratorFragment = cubeGeneratorFragment;
    }

    @Override
    public void onClick(View v) {

        v.startAnimation( new AlphaAnimation(1F, 0.8F));
        Intent in = new Intent(cubeGeneratorFragment.getActivity(), CubeGeneratorActivity.class);
        in.putExtra("cube_size", cubeGeneratorFragment.getSeekBarCurrentValue());
        cubeGeneratorFragment.startActivity(in);

    }
}
