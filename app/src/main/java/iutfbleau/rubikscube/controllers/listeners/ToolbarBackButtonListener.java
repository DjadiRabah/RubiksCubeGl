package iutfbleau.rubikscube.controllers.listeners;

import android.support.v4.app.FragmentManager;
import android.view.View;

import iutfbleau.rubikscube.controllers.fragments.PlayMenuFragment;

public class ToolbarBackButtonListener implements View.OnClickListener {

    private PlayMenuFragment playMenuFragment;

    public ToolbarBackButtonListener(PlayMenuFragment playMenuFragment) {
        this.playMenuFragment = playMenuFragment;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = playMenuFragment.getActivity().getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }
}
