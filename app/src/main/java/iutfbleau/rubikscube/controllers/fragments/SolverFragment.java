package iutfbleau.rubikscube.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.listeners.CubeSizeOnSeekBarChangeListener;
import iutfbleau.rubikscube.controllers.listeners.SolverFragmentOnClickListener;

public class SolverFragment extends Fragment {

    private int min = 2, max = 8, current = 2;
    private TextView sizeDisp;

    public SolverFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_solve, container, false);

        Button startSolver = view.findViewById(R.id.start_solver);
        SeekBar seekBar = view.findViewById(R.id.size_seekBar);
        sizeDisp = view.findViewById(R.id.cubeSizeDisp);

        seekBar.setMax(max-min);
        seekBar.setProgress(current-min);
        sizeDisp.setText("Size : "+current);

        Toolbar toolbar = view.findViewById(R.id.solver_toolbar);
        ((NavActivity) getActivity()).setSupportActionBar(toolbar);
        ((NavActivity) getActivity()).getSupportActionBar().setTitle(null);

        seekBar.setOnSeekBarChangeListener(new CubeSizeOnSeekBarChangeListener(this));
        startSolver.setOnClickListener(new SolverFragmentOnClickListener(this));

        return view;
    }

    public int getSeekBarMinValue() { return min; }

    public int getSeekBarCurrentValue() { return current; }

    public void setSeekBarCurrentValue(int value) { current = value ; }

    public TextView getDisplayTextView () { return sizeDisp; }
}
