package iutfbleau.rubikscube.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.max_91490.rubikscubemenu.R;
import iutfbleau.rubikscube.model.ProgressBarAnimation;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView percent_loaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        percent_loaded = findViewById(R.id.percent_loaded);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, percent_loaded, 0f, 100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }

    @Override
    public void onBackPressed(){}
}
