package iutfbleau.rubikscube.controllers.fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CubeMotionListener;
import iutfbleau.rubikscube.controllers.listeners.PlayFragmentFABOnClickListener;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.OpenGLRenderer;

public class PlayFragment extends Fragment {

    private boolean isLocked = false;
    private MediaPlayer touchsound;
    private OpenGLRenderer openglRenderer;
    public GLSurfaceView glSurfaceView;
    private CubeGl cube;

    public PlayFragment() {
        this.cube = new CubeGl(new Cube3D(3));
        this.openglRenderer = new OpenGLRenderer(cube);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        touchsound = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.lock);

        FloatingActionButton undoFab = view.findViewById(R.id.undo_fab);
        undoFab.setOnClickListener(new PlayFragmentFABOnClickListener(this));

        FloatingActionButton playParams = view.findViewById(R.id.game_params_fab);
        playParams.setOnClickListener(new PlayFragmentFABOnClickListener(this));

        view.setOnTouchListener(new CubeMotionListener(cube));
        glSurfaceView = view.findViewById(R.id.glsurfaceview);
        glSurfaceView.setRenderer(openglRenderer);

        return view;
    }

    public MediaPlayer getTouchsound() {
        return this.touchsound;
    }

    public boolean getLockState() {
        return this.isLocked;
    }

    public void setLockState(boolean state) {
        this.isLocked = state;
    }
}
