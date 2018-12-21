package iutfbleau.rubikscube.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import fr.max_91490.rubikscubemenu.R;
import iutfbleau.rubikscube.audio.SoundService;
import iutfbleau.rubikscube.controler.DrawerManager;

public class NavActivity extends AppCompatActivity {

    public PlayFragment playFragment;
    public SolveFragment solveFragment;
    public AchievementsFragment achievementsFragment;
    public SettingsFragment settingsFragment;

    public boolean fxSound;
    public boolean backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.fxSound = true;
        this.backgroundMusic = true;

        startService(new Intent(getApplicationContext(), SoundService.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView mainNav = findViewById(R.id.main_nav);

        playFragment = new PlayFragment();
        solveFragment = new SolveFragment();
        achievementsFragment = new AchievementsFragment();
        settingsFragment = new SettingsFragment();

        setFragment(playFragment);

        mainNav.setOnNavigationItemSelectedListener(new DrawerManager(this));

    }

    protected void onDestroy() {
        //stop service and stop music
        stopService(new Intent(getApplicationContext(), SoundService.class));
        super.onDestroy();
    }


    public void setFxSound(boolean state)
    {
        this.fxSound = state;
    }

    public void setBackgroundMusic(boolean state)
    {
        this.backgroundMusic = state;
    }

    public void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed(){

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
    }
}
