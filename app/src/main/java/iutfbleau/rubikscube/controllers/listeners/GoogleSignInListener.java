package iutfbleau.rubikscube.controllers.listeners;

import android.view.View;
import android.view.animation.AlphaAnimation;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.GoogleSignInActivity;
import iutfbleau.rubikscube.models.GoogleAuthManager;
import iutfbleau.rubikscube.models.FileManager;

public class GoogleSignInListener implements View.OnClickListener {

    private GoogleSignInActivity gsa;

    public GoogleSignInListener(GoogleSignInActivity gsa) {

        this.gsa = gsa;

    }

    @Override
    public void onClick(View v) {

        v.startAnimation( new AlphaAnimation(1F, 0.8F));
        FileManager fileManager = new FileManager(gsa.getBaseContext());

        switch (v.getId()) {

            case R.id.signInButton: gsa.signIn(); break;

            case R.id.authenticate:

                if (gsa.getEnteredUsername() != null && !gsa.getEnteredUsername().isEmpty() && gsa.getEnteredUsername().length() > 3 ) {

                    //Correct username
                    gsa.updateUsernameInfoText(GoogleSignInActivity.UNAMECORRECT);

                    if (GoogleAuthManager.userConnected()) {

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
