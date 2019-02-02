package iutfbleau.rubikscube.controllers.activities;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.GridCameraOverlay;
import iutfbleau.rubikscube.ShowCamera;

public class CustomCameraActivity extends AppCompatActivity {

    private Camera camera;
    private FrameLayout frameLayout;
    private ShowCamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.frameLayout);

        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);
        GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(this);

        addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

    }

}
