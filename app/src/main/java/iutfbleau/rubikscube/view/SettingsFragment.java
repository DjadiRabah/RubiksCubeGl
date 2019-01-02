package iutfbleau.rubikscube.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
        Preference user = findPreference("user");

        Preference soundEffetcs = findPreference("switch_preference_fx");
        Preference background = findPreference("switch_preference_background");

        login.setOnPreferenceClickListener(new PreferenceListener(this));
        logout.setOnPreferenceClickListener(new PreferenceListener(this));
        revoke.setOnPreferenceClickListener(new PreferenceListener(this));

        soundEffetcs.setOnPreferenceChangeListener(new SwitchPreferenceListener(this, (NavActivity) getActivity()));
        background.setOnPreferenceChangeListener(new SwitchPreferenceListener(this, (NavActivity) getActivity()));


        if (Connection.isSignedIn()) {

            login.setEnabled(false);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
            if (acct != null) {

                user.setEnabled(true);
                user.setTitle("Actual user : "+acct.getDisplayName());
                user.setSummary(acct.getEmail());

            }


        } else {

            user.setEnabled(false);
            logout.setEnabled(false);
            revoke.setEnabled(false);
        }

    }
}
