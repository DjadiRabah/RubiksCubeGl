package iutfbleau.rubikscube.controllers.listeners;

import android.media.MediaPlayer;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;

public class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private NavActivity navActivity;
    private MediaPlayer mediaPlayerFx;

    public BottomNavigationViewListener(NavActivity navActivity) {

        this.navActivity = navActivity;
        mediaPlayerFx = MediaPlayer.create(navActivity, R.raw.touch_sound);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        if (navActivity.getFxSoundState()) {
            mediaPlayerFx.start();
        }

        switch (menuItem.getItemId()) {

            case R.id.nav_play:
                navActivity.setFragment(navActivity.getPlayFragment(), "playfragment");
                return true;

            case R.id.nav_generator:
                navActivity.setFragment(navActivity.getCubeGeneratorFragment(), "solverfragment");
                return true;

            case R.id.nav_stats:
                navActivity.setFragment(navActivity.getStatsFragment(), "statsfragment");
                return true;

            default:
                return false;
        }
    }
}
