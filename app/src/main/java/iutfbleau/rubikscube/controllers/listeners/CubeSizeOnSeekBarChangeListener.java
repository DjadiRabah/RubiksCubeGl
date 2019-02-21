package iutfbleau.rubikscube.controllers.listeners;

import android.widget.SeekBar;

import iutfbleau.rubikscube.controllers.fragments.SolverFragment;

public class CubeSizeOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{

    private SolverFragment solverFragment;

    public CubeSizeOnSeekBarChangeListener(SolverFragment solverFragment){
        this.solverFragment = solverFragment;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        solverFragment.setSeekBarCurrentValue(progress + solverFragment.getSeekBarMinValue());
        solverFragment.getDisplayTextView().setText("Size : "+ solverFragment.getSeekBarCurrentValue());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
