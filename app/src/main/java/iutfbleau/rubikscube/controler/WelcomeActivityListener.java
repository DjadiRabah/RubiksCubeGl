package iutfbleau.rubikscube.controler;

import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.model.FileManager;
import iutfbleau.rubikscube.view.GoogleSignInActivity;
import iutfbleau.rubikscube.view.NavActivity;
import iutfbleau.rubikscube.view.WelcomeActivity;

public class WelcomeActivityListener implements View.OnClickListener {

    private WelcomeActivity welcomeActivity;

    public WelcomeActivityListener(WelcomeActivity welcomeActivity){

        this.welcomeActivity = welcomeActivity;

    }

    @Override
    public void onClick(View v) {
        FileManager fileManager = new FileManager(welcomeActivity.getBaseContext());

        switch (v.getId()){

            case R.id.yes:
                welcomeActivity.launch(GoogleSignInActivity.class);
                fileManager.write(FileManager.DONTSHOW);
                break;

            case R.id.no:
                welcomeActivity.launch(NavActivity.class);
                break;

        }
    }
}
