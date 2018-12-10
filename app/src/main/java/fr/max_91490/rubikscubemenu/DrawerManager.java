package fr.max_91490.rubikscubemenu;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class DrawerManager implements BottomNavigationView.OnNavigationItemSelectedListener{

    NavActivity navActivity;
    MediaPlayer mediaPlayerFx;

    public DrawerManager(NavActivity navActivity){

        this.navActivity = navActivity;
        mediaPlayerFx = MediaPlayer.create(navActivity, R.raw.touch_sound);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(navActivity.fxSound){
            mediaPlayerFx.start();
        }

        switch(menuItem.getItemId()){

            case R.id.nav_play :
                navActivity.setFragment(navActivity.playFragment);
                return true;

            case R.id.nav_solve :
                navActivity.setFragment(navActivity.solveFragment);
                return true;

            case R.id.nav_achievements :
                navActivity.setFragment(navActivity.achievementsFragment);
                return true;

            case R.id.nav_settings :
                navActivity.setFragment(navActivity.settingsFragment);
                return true;

            default:
                return false;
        }
    }
}
