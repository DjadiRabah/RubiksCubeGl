package iutfbleau.rubikscube.controler;

import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import iutfbleau.rubikscube.view.PlayFragment;


public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener  {

    private PlayFragment fragment;

    public RadioGroupListener(PlayFragment fragment){

        this.fragment = fragment;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int childCount = group.getChildCount(), x;
        Log.e("LOL", "test");
        for (x = 0; x < childCount; x++) {

            RadioButton btn = (RadioButton) group.getChildAt(x);

            if (btn.getId() == checkedId) {

                this.fragment.updateSurfaceView(x+3);
              //  this.fragment.setCube(x+3);
               // this.fragment.getOpenglRenderer().setCube(new Cube(x+3));

            }
        }

    }

}
