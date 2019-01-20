package iutfbleau.rubikscube.controler;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.model.Connection;
import iutfbleau.rubikscube.model.FileManager;
import iutfbleau.rubikscube.view.GoogleSignInActivity;

public class GoogleSignInListener implements View.OnClickListener {

    private GoogleSignInActivity gsa;

    public GoogleSignInListener(GoogleSignInActivity gsa) {

        this.gsa = gsa;

    }

    @Override
    public void onClick(View v) {

        FileManager fileManager = new FileManager(gsa.getBaseContext());

        switch (v.getId()) {

            case R.id.signInButton: gsa.signIn(); break;

            case R.id.authenticate:

                if (gsa.getEnteredUsername() != null && !gsa.getEnteredUsername().isEmpty() && gsa.getEnteredUsername().length() > 3 ) {

                    //Correct username
                    gsa.updateUsernameInfoText(GoogleSignInActivity.UNAMECORRECT);

                    if (Connection.userConnected()) {

                        //User authenticated
                        gsa.launchGameView();
                        fileManager.write(FileManager.DONTSHOW);

                    } else {

                        //Error authenticating
                        gsa.updateGoogleAuthInfoText(GoogleSignInActivity.AUTHFAIL);

                    }

                } else {

                    //Empty username
                    gsa.updateUsernameInfoText(GoogleSignInActivity.UNAMEINCORRECT);

                }

                break;

        }
    }


}
