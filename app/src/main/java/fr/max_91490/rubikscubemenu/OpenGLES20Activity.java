package fr.max_91490.rubikscubemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OpenGLES20Activity extends AppCompatActivity {

    private MyGLSurfaceView myGLSurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_open_gl);
        myGLSurfaceView = (MyGLSurfaceView) findViewById(R.id.openGLView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        myGLSurfaceView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        myGLSurfaceView.onPause();
    }

}