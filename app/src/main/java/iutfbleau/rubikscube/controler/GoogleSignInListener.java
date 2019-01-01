package iutfbleau.rubikscube.controler;

import android.util.Log;
import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.view.GoogleSignInActivity;

public class GoogleSignInListener implements View.OnClickListener {

    private GoogleSignInActivity gsa;

    public GoogleSignInListener(GoogleSignInActivity gsa){

        this.gsa = gsa;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.signInButton:
                gsa.signIn();
                break;


            case R.id.authenticate:

                String username = gsa.getEnteredUsername();
                Log.e("Username", username);

                if (username != null) {
                    if (gsa.isUserAuthentified()) {

                        gsa.launchGameView();

                    } else {
                        gsa.updateGoogleAuthInfoText(gsa.getCurrentFirebaseUser());

                    }
                } else {
                    gsa.updateUsernameInfoText(gsa.isUserNameCorrect());
                }


                Log.e("authenticate", "click");

                break;

        }
    }


}
