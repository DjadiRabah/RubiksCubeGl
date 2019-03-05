package iutfbleau.rubikscube.controllers.listeners;

import android.support.v4.app.FragmentManager;
import android.view.View;

import iutfbleau.rubikscube.controllers.fragments.GameMenuFragment;

public class ToolbarBackButtonListener implements View.OnClickListener {

    private GameMenuFragment gameMenuFragment;

    public ToolbarBackButtonListener(GameMenuFragment gameMenuFragment) {
        this.gameMenuFragment = gameMenuFragment;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = gameMenuFragment.getActivity().getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }
}
