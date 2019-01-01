package iutfbleau.rubikscube.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controler.WelcomeActivityListener;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        Button yes = findViewById(R.id.yes);
        Button no = findViewById(R.id.no);

        yes.setOnClickListener(new WelcomeActivityListener(this));
        no.setOnClickListener(new WelcomeActivityListener(this));

    }

    public void launch(final Class<? extends Activity> activityToOpen){

        Intent intent = new Intent(getApplicationContext(), activityToOpen);
        startActivity(intent);

    }

    @Override
    public void onBackPressed(){

    }
}
