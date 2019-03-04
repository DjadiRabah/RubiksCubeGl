package iutfbleau.rubikscube.controllers.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.fragments.PlayFragment;
import iutfbleau.rubikscube.controllers.fragments.SolverFragment;
import iutfbleau.rubikscube.controllers.fragments.StatsFragment;
import iutfbleau.rubikscube.controllers.listeners.BottomNavigationViewListener;
import iutfbleau.rubikscube.models.FileManager;

public class NavActivity extends AppCompatActivity {

    public PlayFragment playFragment;
    public SolverFragment solverFragment;
    public StatsFragment statsFragment;

    public boolean fxSound;
    public boolean backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        fxSound = prefs.getBoolean("switch_preference_fx", false);
        backgroundMusic = prefs.getBoolean("switch_preference_background", false);

        //startService(new Intent(this, SoundService.class));

        BottomNavigationView mainNav = findViewById(R.id.main_nav);

        playFragment = new PlayFragment();
        solverFragment = new SolverFragment();
        statsFragment = new StatsFragment();

        setFragment(playFragment);

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationViewListener(this));

        PreferenceManager.setDefaultValues(this, R.xml.preferences, true);

        FileManager fm = new FileManager(this);
        //Toast.makeText(this, ""+fm.read(), Toast.LENGTH_LONG).show();
    }

    protected void onDestroy() {
        super.onDestroy();
        //stop service and stop music
        //stopService(new Intent(getApplicationContext(), SoundService.class));
    }

    public void setFxSound(boolean state) {
        this.fxSound = state;
    }

    public void setBackgroundMusic(boolean state) {
        this.backgroundMusic = state;
    }

    public void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count < 1) {

            Log.e("CUBE", "CUBE");

            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

            builder.setMessage("Do you really want to quit ?").setTitle("Quit ?");

            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finishAffinity();
                    System.exit(0);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            Log.e("FRAG", "FRAG");
            getSupportFragmentManager().popBackStack();
        }

    }

    public PlayFragment getPlayFragment(){
        return playFragment;
    }
}
