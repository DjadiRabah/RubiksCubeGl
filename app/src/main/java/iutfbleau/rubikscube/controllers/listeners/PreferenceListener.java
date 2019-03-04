package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.widget.Toast;

import iutfbleau.rubikscube.controllers.activities.GoogleSignInActivity;
import iutfbleau.rubikscube.controllers.fragments.SettingsFragment;
import iutfbleau.rubikscube.models.GoogleAuthManager;

public class PreferenceListener implements android.support.v7.preference.Preference.OnPreferenceClickListener {

    private SettingsFragment settingsFragment;
    private Intent googleSignInIntent;
    private android.support.v7.preference.Preference login, logout, revoke, user;

    public PreferenceListener(SettingsFragment settingsFragment) {

        this.settingsFragment = settingsFragment;

        this.login = settingsFragment.getPreferenceScreen().findPreference("preference_login");
        this.logout = settingsFragment.getPreferenceScreen().findPreference("preference_logout");
        this.revoke = settingsFragment.getPreferenceScreen().findPreference("preference_revoke");
        this.user = settingsFragment.getPreferenceScreen().findPreference("user");

        googleSignInIntent = new Intent(settingsFragment.getContext(), GoogleSignInActivity.class);

    }

    @Override
    public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {

        switch (preference.getKey()) {

            case "preference_login":

                settingsFragment.startActivity(googleSignInIntent);

                //La vue est
                if (GoogleAuthManager.userConnected()){
                    preference.setEnabled(false);
                    logout.setEnabled(true);
                    revoke.setEnabled(true);
                }

                break;

            case "preference_logout":

                GoogleAuthManager.signOut();
                preference.setEnabled(false);
                login.setEnabled(true);
                revoke.setEnabled(false);
                user.setEnabled(false);
                user.setTitle("Actual user : none");
                user.setSummary("Email : none");
                Toast.makeText(settingsFragment.getContext(), "You are now logged out.", Toast.LENGTH_SHORT).show();

                break;

            case "preference_revoke":

                GoogleAuthManager.revokeAccess();
                preference.setEnabled(false);
                login.setEnabled(true);
                logout.setEnabled(false);
                user.setTitle("Actual user : none");
                user.setSummary("Email : none");

                Toast.makeText(settingsFragment.getContext(), "You have revoked our access to your Google account.", Toast.LENGTH_SHORT).show();

                break;
        }
        return true;
    }
}

