package iutfbleau.rubikscube.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controler.PreferenceListener;
import iutfbleau.rubikscube.controler.SwitchPreferenceListener;
import iutfbleau.rubikscube.model.Connection;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        addPreferencesFromResource(R.xml.preferences);

        Preference login = findPreference("preference_login");
        Preference logout = findPreference("preference_logout");
        Preference revoke = findPreference("preference_revoke");

        Preference soundEffetcs = findPreference("switch_preference_fx");
        Preference background = findPreference("switch_preference_background");

        login.setOnPreferenceClickListener(new PreferenceListener(this));
        logout.setOnPreferenceClickListener(new PreferenceListener(this));
        revoke.setOnPreferenceClickListener(new PreferenceListener(this));

        soundEffetcs.setOnPreferenceChangeListener(new SwitchPreferenceListener(this, (NavActivity) getActivity()));
        background.setOnPreferenceChangeListener(new SwitchPreferenceListener(this, (NavActivity) getActivity()));

        if (Connection.isSignedIn()) {

            getPreferenceScreen().findPreference("preference_login").setEnabled(false);

        } else {

            getPreferenceScreen().findPreference("preference_logout").setEnabled(false);
            getPreferenceScreen().findPreference("preference_revoke").setEnabled(false);
        }

    }
}
