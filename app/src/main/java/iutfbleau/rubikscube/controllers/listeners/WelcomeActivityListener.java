package iutfbleau.rubikscube.controllers.listeners;

import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.GoogleSignInActivity;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.activities.WelcomeActivity;

public class WelcomeActivityListener implements View.OnClickListener {

    private WelcomeActivity welcomeActivity;

    public WelcomeActivityListener(WelcomeActivity welcomeActivity){

        this.welcomeActivity = welcomeActivity;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.yes:
                welcomeActivity.launch(GoogleSignInActivity.class);

                break;

            case R.id.no:
                welcomeActivity.launch(NavActivity.class);
                break;

        }
    }
}
