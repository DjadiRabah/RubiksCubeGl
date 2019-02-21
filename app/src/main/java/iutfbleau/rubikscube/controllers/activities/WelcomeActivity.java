package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.DontAskMeCheckboxListener;
import iutfbleau.rubikscube.controllers.listeners.WelcomeActivityListener;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button yes = findViewById(R.id.yes);
        Button no = findViewById(R.id.no);

        CheckBox dontaskme = findViewById(R.id.dontaskme);

        yes.setOnClickListener(new WelcomeActivityListener(this));
        no.setOnClickListener(new WelcomeActivityListener(this));
        dontaskme.setOnCheckedChangeListener(new DontAskMeCheckboxListener(this));

    }

    public void launch(final Class<? extends Activity> activityToOpen){

        Intent intent = new Intent(getApplicationContext(), activityToOpen);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed(){

    }
}
