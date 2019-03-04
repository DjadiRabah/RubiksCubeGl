package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;

import iutfbleau.rubikscube.controllers.activities.CubeGeneratorActivity;
import iutfbleau.rubikscube.controllers.fragments.SolverFragment;

public class SolverFragmentOnClickListener implements View.OnClickListener {

    private SolverFragment solverFragment;

    public SolverFragmentOnClickListener(SolverFragment solverFragment) {
        this.solverFragment = solverFragment;
    }

    @Override
    public void onClick(View v) {

        v.startAnimation( new AlphaAnimation(1F, 0.8F));
        Intent in = new Intent(solverFragment.getActivity(), CubeGeneratorActivity.class);
        in.putExtra("cube_size", solverFragment.getSeekBarCurrentValue());
        solverFragment.startActivity(in);

    }
}
