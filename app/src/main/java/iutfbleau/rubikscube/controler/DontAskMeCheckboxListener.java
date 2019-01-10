package iutfbleau.rubikscube.controler;

import android.widget.CompoundButton;

import iutfbleau.rubikscube.model.FileManager;
import iutfbleau.rubikscube.view.WelcomeActivity;

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
