package fr.max_91490.rubikscubemenu;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private boolean isLocked = false;
    private MediaPlayer touchsound;
    private int size;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        touchsound = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.lock);

        FloatingActionButton fab = view.findViewById(R.id.undo_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Clic sur retour !", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        // 1 - Configure Toolbar
        //FOR DESIGN
        Toolbar toolbar = view.findViewById(R.id.play_toolbar);
       ((NavActivity)getActivity()).setSupportActionBar(toolbar);
       ((NavActivity)getActivity()).getSupportActionBar().setTitle(null);

        // 2 - Configure Drawer Layout
        this.drawerLayout = view.findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 3 - Configure NavigationView
        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        GLSurfaceView glSurfaceView = view.findViewById(R.id.glsurfaceview);
        glSurfaceView.setRenderer(new OpenGLRenderer(3));

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.nav_restart :
                Toast.makeText(getActivity(), "Recommencer le Rubik's Cube !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_mix:
                Toast.makeText(getActivity(), "Mélanger le Rubik's Cube !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rbsize:

                showRadioButtonDialog();

                break;

            case R.id.nav_lockrotation:

             touchsound.start();

                if(this.isLocked) {

                    Toast.makeText(getActivity(), "The cube rotation is now locked.", Toast.LENGTH_SHORT).show();
                    //Lock method
                    item.setIcon(getResources().getDrawable(R.drawable.outline_lock_open_black_24dp));
                    item.setTitle("Unlock Rotation");

                }else{

                    Toast.makeText(getActivity(), "The cube rotation is now unlocked.", Toast.LENGTH_SHORT).show();
                    //Unlock method
                    item.setIcon(getResources().getDrawable(R.drawable.outline_lock_black_24dp));
                    item.setTitle("Lock Rotation");

                }

                this.isLocked = !this.isLocked;

                break;

            case R.id.play_website:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
                break;
            case R.id.play_credits:
                Toast.makeText(getActivity(), "À propos", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showRadioButtonDialog() {

        int min = 2;
        int max = 17;

        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        List<String> stringList=new ArrayList<>();  // here is list

        for(int i=min;i<max;i++) {

            stringList.add("Rubik's Cube " + (i + 1)+" x "+(i + 1));

        }
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(getActivity()); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroupListener(this));

        dialog.show();

    }

    public void updateSurfaceView(int cubeSize){

        Log.e("selected RadioButton->", String.valueOf(cubeSize));
        //    glSurfaceView.setRenderer(new OpenGLRenderer(cubeSize));
        }
}
