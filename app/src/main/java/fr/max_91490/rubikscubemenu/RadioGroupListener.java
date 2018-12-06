package fr.max_91490.rubikscubemenu;

import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener  {

    private PlayFragment fragment;

    public RadioGroupListener(PlayFragment fragment){

        this.fragment = fragment;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int childCount = group.getChildCount(), x;

        for (x = 0; x < childCount; x++) {

            RadioButton btn = (RadioButton) group.getChildAt(x);

            if (btn.getId() == checkedId) {

                this.fragment.updateSurfaceView(x+3);

            }
        }

    }

}
