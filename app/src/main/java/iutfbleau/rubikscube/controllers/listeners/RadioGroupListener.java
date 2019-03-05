package iutfbleau.rubikscube.controllers.listeners;

import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import iutfbleau.rubikscube.controllers.fragments.GameMenuFragment;

public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener  {

    private GameMenuFragment gameMenuFragment;

    public RadioGroupListener(GameMenuFragment gameMenuFragment){

        this.gameMenuFragment = gameMenuFragment;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int childCount = group.getChildCount(), x;
        Log.e("LOL", "test");
        for (x = 0; x < childCount; x++) {

            RadioButton btn = (RadioButton) group.getChildAt(x);

            if (btn.getId() == checkedId) {

                gameMenuFragment.updateSurfaceView(x+3);
                gameMenuFragment.getDialogFrame().dismiss();
                gameMenuFragment.closeFragment();
              //  this.fragment.setCube(x+3);
               // this.fragment.getOpenglRenderer().setCube(new Cube(x+3));

            }
        }

    }

}
