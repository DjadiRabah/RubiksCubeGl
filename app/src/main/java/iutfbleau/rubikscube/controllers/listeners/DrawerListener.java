package iutfbleau.rubikscube.controllers.listeners;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.fragments.PlayFragment;

public class DrawerListener implements NavigationView.OnNavigationItemSelectedListener {

    private PlayFragment playFragment;
    private NavActivity navActivity;

    public DrawerListener(PlayFragment playFragment, NavActivity navActivity) {

        this.playFragment = playFragment;
        this.navActivity = navActivity;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle Navigation Item Click
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_restart:
                Toast.makeText(navActivity, "Cube restarted.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_mix:
                Toast.makeText(navActivity, "Cube mixed.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rbsize:
                playFragment.showRadioButtonDialog();
                break;

            case R.id.nav_lockrotation:

                if (navActivity.fxSound) {
                    playFragment.getTouchsound().start();
                }

                if (playFragment.getLockState()) {

                    Toast.makeText(navActivity, "The cube rotation is now locked.", Toast.LENGTH_SHORT).show();
                    //Lock method
                    item.setIcon(navActivity.getResources().getDrawable(R.drawable.outline_lock_open_black_24dp));
                    item.setTitle("Unlock Rotation");

                } else {

                    Toast.makeText(navActivity, "The cube rotation is now unlocked.", Toast.LENGTH_SHORT).show();
                    //Unlock method
                    item.setIcon(navActivity.getResources().getDrawable(R.drawable.outline_lock_black_24dp));
                    item.setTitle("Lock Rotation");

                }

                playFragment.setLockState(!playFragment.getLockState());

                break;

            case R.id.play_website:
                playFragment.launchWebsiteIntent();
                break;
            case R.id.play_credits:
                Toast.makeText(navActivity, "À propos", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        playFragment.getDrawerLayout().closeDrawer(GravityCompat.START);

        return false;
    }
}
