package iutfbleau.rubikscube.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import fr.max_91490.rubikscubemenu.R;
import iutfbleau.rubikscube.audio.MediaPlayerManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private MediaPlayerManager mediaPlayerManager;

    public SettingsFragment()
    {
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

                    Toast.makeText(getActivity(), "Sounds effects are now On.", Toast.LENGTH_SHORT).show();
                    
                }else{ // the switch is in the Off position
                    Toast.makeText(getActivity(), "Sounds effects are now Off.", Toast.LENGTH_SHORT).show();
                }
                NavActivity activity = (NavActivity)getActivity();
                activity.setFxSound(isChecked);
            }
        });

        backgroundmusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                
                if(isChecked){ //the switch is in the On position

                    Toast.makeText(getActivity(), "Background music is now On.", Toast.LENGTH_SHORT).show();

                }else{ // the switch is in the Off position

                    Toast.makeText(getActivity(), "Background music is now Off.", Toast.LENGTH_SHORT).show();
                }
                NavActivity activity = (NavActivity)getActivity();
                activity.setBackgroundMusic(isChecked);
            }
        });

        return view;
    }

}
