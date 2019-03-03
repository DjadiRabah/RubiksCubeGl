package iutfbleau.rubikscube.controllers.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v14.preference.SwitchPreference;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.listeners.PreferenceListener;
import iutfbleau.rubikscube.controllers.listeners.SwitchPreferenceListener;
import iutfbleau.rubikscube.models.GoogleAuthManager;

public class PreferenceFragment extends PreferenceFragmentCompat {

    public PreferenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        addPreferencesFromResource(R.xml.preferences);

        Preference login = findPreference("preference_login");
        Preference logout = findPreference("preference_logout");
        Preference revoke = findPreference("preference_revoke");
        Preference user = findPreference("user");

        SwitchPreference soundEffetcs = (SwitchPreference) findPreference("switch_preference_fx");
        SwitchPreference background = (SwitchPreference) findPreference("switch_preference_background");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        soundEffetcs.setChecked(prefs.getBoolean("switch_preference_fx", false));
        background.setChecked(prefs.getBoolean("switch_preference_background", false));

        login.setOnPreferenceClickListener(new PreferenceListener(this));
        logout.setOnPreferenceClickListener(new PreferenceListener(this));
        revoke.setOnPreferenceClickListener(new PreferenceListener(this));

        soundEffetcs.setOnPreferenceChangeListener(new SwitchPreferenceListener(this, (NavActivity) getActivity()));
        background.setOnPreferenceChangeListener(new SwitchPreferenceListener(this, (NavActivity) getActivity()));

        if (GoogleAuthManager.userConnected()) {

            login.setEnabled(false);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());

            if (acct != null) {

                user.setEnabled(true);
                user.setTitle("Actual user : " + acct.getDisplayName());
                user.setSummary(acct.getEmail());

            }

        } else {

            user.setEnabled(false);
            logout.setEnabled(false);
            revoke.setEnabled(false);
        }

    }
}
