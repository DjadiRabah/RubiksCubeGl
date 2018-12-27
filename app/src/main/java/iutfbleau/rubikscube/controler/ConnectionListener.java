package iutfbleau.rubikscube.controler;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.model.Connection;
import iutfbleau.rubikscube.view.GoogleSignInActivity;
import iutfbleau.rubikscube.view.SettingsFragment;

public class ConnectionListener implements View.OnClickListener{

    final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private SettingsFragment settingsFragment;

    public ConnectionListener(SettingsFragment settingsFragment){

        this.settingsFragment = settingsFragment;

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.login:

                v.startAnimation(buttonClick);

                Intent i = new Intent(settingsFragment.getActivity().getApplicationContext(), GoogleSignInActivity.class);
                settingsFragment.startActivity(i);

                settingsFragment.getLogoutButton().setVisibility(View.VISIBLE);
                settingsFragment.getLoginButton().setVisibility(View.GONE);

                break;

            case R.id.logout:

                v.startAnimation(buttonClick);

                alertDialogBuilder();

                break;
        }
    }

    private void alertDialogBuilder(){

        AlertDialog.Builder builder = new AlertDialog.Builder(settingsFragment.getContext(), R.style.MyDialogTheme);

        builder.setMessage("Do you really want log out ?").setTitle("Log out ?");

        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Connection connection = Connection.getInstance();
                connection.signOut();
                Toast.makeText(settingsFragment.getActivity(), "You are now logged out.", Toast.LENGTH_SHORT).show();

                settingsFragment.getLoginButton().setVisibility(View.VISIBLE);
                settingsFragment.getLogoutButton().setVisibility(View.GONE);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}
