package iutfbleau.rubikscube.controler;

import android.widget.CompoundButton;
import android.widget.Toast;

import iutfbleau.rubikscube.view.NavActivity;

public class BackgroundSwitchListener implements CompoundButton.OnCheckedChangeListener {

    private NavActivity navActivity;

    public BackgroundSwitchListener(NavActivity navActivity){

            this.navActivity = navActivity;

    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) { //the switch is in the On position

            Toast.makeText(navActivity, "Background music is now On.", Toast.LENGTH_SHORT).show();

        } else { // the switch is in the Off position

            Toast.makeText(navActivity, "Background music is now Off.", Toast.LENGTH_SHORT).show();
        }
      navActivity.setBackgroundMusic(isChecked);
    }
}
