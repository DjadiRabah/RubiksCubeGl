package iutfbleau.rubikscube.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import iutfbleau.rubikscube.controler.BackgroundSwitchListener;
import iutfbleau.rubikscube.controler.ConnectionListener;
import iutfbleau.rubikscube.controler.FxSwitchListener;
import iutfbleau.rubikscube.model.Connection;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private MediaPlayerManager mediaPlayerManager;
    private Button loginButton;
    private Button logoutButton;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        loginButton = view.findViewById(R.id.login);
        logoutButton = view.findViewById(R.id.logout);

        Switch soundeffects = view.findViewById(R.id.soundeffects);
        Switch backgroundmusic = view.findViewById(R.id.backgroundmusic);

        soundeffects.setOnCheckedChangeListener(new FxSwitchListener((NavActivity) getActivity()));
        backgroundmusic.setOnCheckedChangeListener(new BackgroundSwitchListener((NavActivity) getActivity()));

        Connection connection = Connection.getInstance();

        if(connection.isSignedIn()){
            loginButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
        }else{
            logoutButton.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
        }

        loginButton.setOnClickListener(new ConnectionListener(this));
        logoutButton.setOnClickListener(new ConnectionListener(this));

        return view;
    }

    public Button getLoginButton(){

        return this.loginButton;

    }

    public Button getLogoutButton(){

        return this.logoutButton;

    }
}
