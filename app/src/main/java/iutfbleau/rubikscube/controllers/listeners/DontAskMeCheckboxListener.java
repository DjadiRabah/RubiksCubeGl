package iutfbleau.rubikscube.controllers.listeners;

import android.widget.CompoundButton;

import iutfbleau.rubikscube.controllers.activities.WelcomeActivity;
import iutfbleau.rubikscube.models.FileManager;

public class DontAskMeCheckboxListener implements CompoundButton.OnCheckedChangeListener{

    private WelcomeActivity welcomeActivity;

    public DontAskMeCheckboxListener(WelcomeActivity welcomeActivity) {

        this.welcomeActivity = welcomeActivity;

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

        FileManager fm = new FileManager(welcomeActivity.getBaseContext());

        if(isChecked){
            fm.write(FileManager.DONTSHOW);
        }else{
            fm.write(FileManager.SHOW);
        }

    }
}
