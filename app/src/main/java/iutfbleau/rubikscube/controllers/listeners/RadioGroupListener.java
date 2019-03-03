package iutfbleau.rubikscube.controllers.listeners;

import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import iutfbleau.rubikscube.controllers.fragments.PlayFragment;
import iutfbleau.rubikscube.controllers.fragments.PlayMenuFragment;

public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener  {

    private PlayMenuFragment playMenuFragment;

    public RadioGroupListener(PlayMenuFragment playMenuFragment){

        this.playMenuFragment = playMenuFragment;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int childCount = group.getChildCount(), x;
        Log.e("LOL", "test");
        for (x = 0; x < childCount; x++) {

            RadioButton btn = (RadioButton) group.getChildAt(x);

            if (btn.getId() == checkedId) {

                playMenuFragment.updateSurfaceView(x+3);
                playMenuFragment.getDialogFrame().dismiss();
                playMenuFragment.closeFragment();
              //  this.fragment.setCube(x+3);
               // this.fragment.getOpenglRenderer().setCube(new Cube(x+3));

            }
        }

    }

}
