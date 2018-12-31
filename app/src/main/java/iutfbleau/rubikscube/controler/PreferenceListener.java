package iutfbleau.rubikscube.controler;

import android.content.Intent;

import iutfbleau.rubikscube.model.Connection;
import iutfbleau.rubikscube.view.GoogleSignInActivity;
import iutfbleau.rubikscube.view.SettingsFragment;

public class PreferenceListener implements android.support.v7.preference.Preference.OnPreferenceClickListener {

    private SettingsFragment settingsFragment;
    private Intent googleSignInIntent;

    public PreferenceListener(SettingsFragment settingsFragment) {

        this.settingsFragment = settingsFragment;
        googleSignInIntent = new Intent(settingsFragment.getActivity().getApplicationContext(), GoogleSignInActivity.class);

    }

    @Override
    public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {

        switch (preference.getKey()) {

            case "preference_login":

                settingsFragment.startActivity(googleSignInIntent);
                settingsFragment.getPreferenceScreen().findPreference("preference_login").setEnabled(false);
                settingsFragment.getPreferenceScreen().findPreference("preference_logout").setEnabled(true);
                settingsFragment.getPreferenceScreen().findPreference("preference_revoke").setEnabled(true);

                break;

            case "preference_logout":

                Connection.signOut();
                settingsFragment.getPreferenceScreen().findPreference("preference_login").setEnabled(true);
                settingsFragment.getPreferenceScreen().findPreference("preference_logout").setEnabled(false);
                settingsFragment.getPreferenceScreen().findPreference("preference_revoke").setEnabled(false);

                break;

            case "preference_revoke":

                Connection.revokeAccess();
                settingsFragment.getPreferenceScreen().findPreference("preference_login").setEnabled(true);
                settingsFragment.getPreferenceScreen().findPreference("preference_logout").setEnabled(false);
                settingsFragment.getPreferenceScreen().findPreference("preference_revoke").setEnabled(false);

                break;
        }
        return true;
    }
}

