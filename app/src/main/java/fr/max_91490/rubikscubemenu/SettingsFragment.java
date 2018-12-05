package fr.max_91490.rubikscubemenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    boolean isBackgroundmusicOn = true, isSoundEffectsOn = true;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_settings, container, false);

        Switch soundeffects = view.findViewById(R.id.soundeffects);
        Switch backgroundmusic = view.findViewById(R.id.backgroundmusic);

        soundeffects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                
                if(isChecked){ //the switch is in the On position

                    isSoundEffectsOn = true;
                    Toast.makeText(getActivity(), "Sounds effects are now On.", Toast.LENGTH_SHORT).show();
                    
                }else{ // the switch is in the Off position

                    isSoundEffectsOn = false;
                    Toast.makeText(getActivity(), "Sounds effects are now Off.", Toast.LENGTH_SHORT).show();
                    
                }
                
            }
        });

        backgroundmusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                
                if(isChecked){ //the switch is in the On position

                    isBackgroundmusicOn = true;
                    Toast.makeText(getActivity(), "Background music is now On.", Toast.LENGTH_SHORT).show();

                }else{ // the switch is in the Off position

                    Toast.makeText(getActivity(), "Background music is now Off.", Toast.LENGTH_SHORT).show();
                    isBackgroundmusicOn = false;
                    
                }
            }
        });




        return view;
    }

}
