package iutfbleau.rubikscube.view;

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

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controler.DrawerListener;
import iutfbleau.rubikscube.controler.OnSwipeTouchListener;
import iutfbleau.rubikscube.controler.RadioGroupListener;
import iutfbleau.rubikscube.model.cube.cube.Cube3D;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private boolean isLocked = false;
    private MediaPlayer touchsound;
    private OpenGLRenderer openglRenderer;
    public GLSurfaceView glSurfaceView;
    private Cube3D cube;
    private int size;

    public PlayFragment()
    {
      this.cube = new Cube3D(3);
      this.openglRenderer = new OpenGLRenderer(cube);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        view.setOnTouchListener(new OnSwipeTouchListener(this.getContext(), this.openglRenderer.getCube()));

        touchsound = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.lock);

        FloatingActionButton fab = view.findViewById(R.id.undo_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Undo", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        // 1 - Configure Toolbar
        Toolbar toolbar = view.findViewById(R.id.play_toolbar);
        ((NavActivity)getActivity()).setSupportActionBar(toolbar);
        ((NavActivity)getActivity()).getSupportActionBar().setTitle(null);

        // 2 - Configure Drawer Layout
        drawerLayout = view.findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 3 - Configure NavigationView
        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new DrawerListener(this,  (NavActivity) getActivity()));

        glSurfaceView = view.findViewById(R.id.glsurfaceview);
        glSurfaceView.setRenderer(openglRenderer);

        return view;
    }

    public void showRadioButtonDialog() {

        int min = 2;
        int max = 8;

        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        List<String> stringList=new ArrayList<>();  // here is list

        for(int i=min;i<max;i++) {

            stringList.add("Rubik's Cube " + (i + 1)+" x "+(i + 1));

        }
        RadioGroup rg = dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(getActivity()); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }

        rg.setOnCheckedChangeListener(new RadioGroupListener(this));


        dialog.show();

    }

    public void updateSurfaceView(int nFaces){

        Log.e("Size", String.valueOf(nFaces));

    }

    public void launchWebsiteIntent(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }

    public MediaPlayer getTouchsound(){ return this.touchsound; }

    public boolean getLockState(){ return this.isLocked; }
    public void setLockState(boolean state){ this.isLocked = state; }

    public DrawerLayout getDrawerLayout(){ return this.drawerLayout; }
}
