package iutfbleau.rubikscube.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.audio.MediaPlayerManager;
import iutfbleau.rubikscube.model.Connection;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private MediaPlayerManager mediaPlayerManager;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        final Button loginButton = view.findViewById(R.id.login);
        final Button logoutButton = view.findViewById(R.id.logout);

        Switch soundeffects = view.findViewById(R.id.soundeffects);
        Switch backgroundmusic = view.findViewById(R.id.backgroundmusic);


        soundeffects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { //the switch is in the On position

                    Toast.makeText(getActivity(), "Sounds effects are now On.", LENGTH_SHORT).show();

                } else { // the switch is in the Off position
                    Toast.makeText(getActivity(), "Sounds effects are now Off.", LENGTH_SHORT).show();
                }
                NavActivity activity = (NavActivity) getActivity();
                activity.setFxSound(isChecked);
            }
        });

        backgroundmusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) { //the switch is in the On position

                    Toast.makeText(getActivity(), "Background music is now On.", LENGTH_SHORT).show();

                } else { // the switch is in the Off position

                    Toast.makeText(getActivity(), "Background music is now Off.", LENGTH_SHORT).show();
                }
                NavActivity activity = (NavActivity) getActivity();
                activity.setBackgroundMusic(isChecked);
            }
        });

        loginButton.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);

                Toast.makeText(getActivity(), "login", Toast.LENGTH_SHORT).show();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);

                Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
                Connection connection = Connection.getInstance();
                connection.signOut();

            }
        });

        return view;
    }

}
