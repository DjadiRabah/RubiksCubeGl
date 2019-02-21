package iutfbleau.rubikscube.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import iutfbleau.rubikscube.R;
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

        seekBar.setOnSeekBarChangeListener(new CubeSizeOnSeekBarChangeListener(this));

        startSolver.setOnClickListener(new SolverFragmentOnClickListener(this));

        return view;
    }

    public int getSeekBarMinValue() { return min; }

    public int getSeekBarCurrentValue() { return current; }

    public void setSeekBarCurrentValue(int value) { current = value ; }

    public TextView getDisplayTextView () { return sizeDisp; }
}
