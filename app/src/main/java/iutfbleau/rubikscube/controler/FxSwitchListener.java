package iutfbleau.rubikscube.controler;

import android.widget.CompoundButton;
import android.widget.Toast;

import iutfbleau.rubikscube.view.NavActivity;

public class FxSwitchListener implements CompoundButton.OnCheckedChangeListener {

    private NavActivity navActivity;

    public FxSwitchListener(NavActivity navActivity){

        this.navActivity = navActivity;

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) { //the switch is in the On position

            Toast.makeText(navActivity, "Sounds effects are now On.", Toast.LENGTH_SHORT).show();

        } else { // the switch is in the Off position
            Toast.makeText(navActivity, "Sounds effects are now Off.", Toast.LENGTH_SHORT).show();
        }
        navActivity.setFxSound(isChecked);
    }
}
