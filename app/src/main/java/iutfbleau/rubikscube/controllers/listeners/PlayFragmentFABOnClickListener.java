package iutfbleau.rubikscube.controllers.listeners;

import android.support.design.widget.Snackbar;
import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.fragments.PlayFragment;
import iutfbleau.rubikscube.controllers.fragments.PlayMenuFragment;

public class PlayFragmentFABOnClickListener implements View.OnClickListener {

    private PlayFragment playFragment;

    public PlayFragmentFABOnClickListener(PlayFragment playFragment) {
        this.playFragment = playFragment;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.game_params_fab:

                playFragment.getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new PlayMenuFragment())
                        .addToBackStack(null)
                        .commit();

                break;

            case R.id.undo_fab:

                Snackbar.make(v, "Undo", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                break;


        }

    }
}
