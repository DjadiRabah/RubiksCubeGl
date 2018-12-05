package fr.max_91490.rubikscubemenu;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView percent_loaded;
    private float from;
    private float to;


    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView percent_loaded, float from, float to){

        this.context = context;
        this.progressBar = progressBar;
        this.percent_loaded = percent_loaded;
        this.from = from;
        this.to = to;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int)value);
        percent_loaded.setText((int)value + " %");

        if(value == to){
            context.startActivity(new Intent(context, NavActivity.class));
        }

    }
}
