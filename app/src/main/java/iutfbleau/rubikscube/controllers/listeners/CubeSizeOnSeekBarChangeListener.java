package iutfbleau.rubikscube.controllers.listeners;

import android.widget.SeekBar;

import iutfbleau.rubikscube.controllers.fragments.CubeGeneratorFragment;

public class CubeSizeOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{

    private CubeGeneratorFragment cubeGeneratorFragment;

    public CubeSizeOnSeekBarChangeListener(CubeGeneratorFragment cubeGeneratorFragment){
        this.cubeGeneratorFragment = cubeGeneratorFragment;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        cubeGeneratorFragment.setSeekBarCurrentValue(progress + cubeGeneratorFragment.getSeekBarMinValue());
        cubeGeneratorFragment.getDisplayTextView().setText("Size : "+ cubeGeneratorFragment.getSeekBarCurrentValue());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
