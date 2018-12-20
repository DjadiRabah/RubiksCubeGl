package rubikscube.controler;

import android.media.MediaPlayer;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import fr.max_91490.rubikscubemenu.R;
import rubikscube.view.NavActivity;

public class DrawerManager implements BottomNavigationView.OnNavigationItemSelectedListener{

    private NavActivity navActivity;
    private MediaPlayer mediaPlayerFx;

    public DrawerManager(NavActivity navActivity){

        this.navActivity = navActivity;
        mediaPlayerFx = MediaPlayer.create(navActivity, R.raw.touch_sound);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        if (navActivity.fxSound){
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
