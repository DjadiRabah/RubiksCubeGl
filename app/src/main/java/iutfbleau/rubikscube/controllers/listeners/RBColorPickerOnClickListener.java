package iutfbleau.rubikscube.controllers.listeners;

import android.util.Log;
import android.view.View;

public class RBColorPickerOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        switch (v.getTag().toString()) {

            case "green":
                Log.e("Click", "green");
                break;

            case "orange":
                Log.e("Click", "orange");
                break;

            case "blue":
                Log.e("Click", "blue");
                break;

            case "red":
                Log.e("Click", "red");
                break;


            case "yellow":
                Log.e("Click", "yellow");
                break;

            case "white":
                Log.e("Click", "white");
                break;

        }
    }
}
