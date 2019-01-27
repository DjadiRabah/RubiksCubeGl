package iutfbleau.rubikscube.controllers.listeners;

import android.support.v14.preference.SwitchPreference;
import android.widget.Toast;

import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.fragments.SettingsFragment;

public class SwitchPreferenceListener implements android.support.v7.preference.Preference.OnPreferenceChangeListener {

    private SettingsFragment settingsFragment;
    private NavActivity navActivity;


    public SwitchPreferenceListener(SettingsFragment settingsFragment, NavActivity navActivity) {

        this.settingsFragment = settingsFragment;
        this.navActivity = navActivity;

    }

    @Override
    public boolean onPreferenceChange(android.support.v7.preference.Preference preference, Object o) {

        switch (preference.getKey()) {

            case "switch_preference_fx":

                boolean switchState1 = ((SwitchPreference) preference).isChecked();

                if(switchState1) {

                    Toast.makeText(settingsFragment.getActivity(),"Sounds effects are now off.",Toast.LENGTH_SHORT).show();
                    ((SwitchPreference) preference).setChecked(false);

                }else{

                    Toast.makeText(settingsFragment.getActivity(),"Sounds effects are now on.",Toast.LENGTH_SHORT).show();
                    ((SwitchPreference) preference).setChecked(true);
                }

                navActivity.setFxSound(!switchState1);
                break;

            case "switch_preference_background":

                boolean switchState2 = ((SwitchPreference) preference).isChecked();

                if(switchState2){

                    Toast.makeText(settingsFragment.getActivity(),"Background music is now off.",Toast.LENGTH_SHORT).show();
                    ((SwitchPreference) preference).setChecked(false);

                }else {

                    Toast.makeText(settingsFragment.getActivity(),"Background music is now on.",Toast.LENGTH_SHORT).show();
                    ((SwitchPreference) preference).setChecked(true);

                }

                navActivity.setBackgroundMusic(!switchState2);
                break;
        }

        return false;
    }
}
