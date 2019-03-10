package iutfbleau.rubikscube.controllers.fragments;

import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.models.cube.Cube;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.renderer.OpenGLRenderer;

public class PlayFragment extends Fragment {

    private boolean cubeLockState = false;
    private MediaPlayer touchsound;
    private Cube cube3D;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        touchsound = MediaPlayer.create(getActivity(), R.raw.lock);

        Toolbar toolbar = view.findViewById(R.id.play_toolbar);
        toolbar.setTitle("");
        ((NavActivity) getActivity()).setSupportActionBar(toolbar);
        ((NavActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_white_24dp);
        ((NavActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new GameMenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        FloatingActionButton undoFab = view.findViewById(R.id.undo_fab);
        undoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Undo", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        cube3D = new Cube(3);
        GLSurfaceView glSurfaceView = view.findViewById(R.id.glsurfaceview);
        CubeGl cube = new CubeGl(cube3D, glSurfaceView);
        OpenGLRenderer openglRenderer = new OpenGLRenderer(cube);
        view.setOnTouchListener(new CubeMotionListener(cube));
        glSurfaceView.setRenderer(openglRenderer);

        return view;
    }

    public MediaPlayer getTouchsound() {
        return this.touchsound;
    }

    public boolean isCubeLocked() {
        return this.cubeLockState;
    }

    public void setCubeLockState(boolean state) {
        this.cubeLockState = state;
    }

    public int[][][] getPlayFragmentCubeFaces() {
        int[][][] resArray = new int[6][3][3];
        for (int i = 0; i < resArray.length; i++) {
            resArray[i] = cube3D.getFace(i).getColors();
        }
        return resArray;
    }
}