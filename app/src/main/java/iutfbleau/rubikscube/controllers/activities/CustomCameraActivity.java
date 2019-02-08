package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.GridCameraOverlay;
import iutfbleau.rubikscube.ShowCamera;

public class CustomCameraActivity extends AppCompatActivity {

    private Camera camera;
    private FrameLayout frameLayout;
    private Intent resultIntent = new Intent();
    private Button flash, capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        flash = findViewById(R.id.flash);
        capture = findViewById(R.id.capture);
        //flash.setOnClickListener(new CameraCaptureListener(this));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.frameLayout);

        camera = Camera.open();
        ShowCamera showCamera = new ShowCamera(this, camera, 0);
        frameLayout.addView(showCamera);
        GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(this);
        resultIntent.putExtra("coords", gridCameraOverlay.getOverlayCoordinates());
        addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            resultIntent.putExtra("img", data);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();

        }
    };

    public void captureImage(View v) {

        if (camera != null) {

            camera.takePicture(null, null, pictureCallback);

        }

    }

}


