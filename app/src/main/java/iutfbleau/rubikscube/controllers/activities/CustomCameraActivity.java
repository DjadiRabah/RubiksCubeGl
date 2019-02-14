package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.view.GridCameraOverlay;
import iutfbleau.rubikscube.view.CameraPreview;

public class CustomCameraActivity extends AppCompatActivity {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;



    private Camera camera;
    private FrameLayout frameLayout;
    private Intent resultIntent = new Intent();
    private Button flash, capture;
    private CameraPreview cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        flash = findViewById(R.id.flash);
        capture = findViewById(R.id.capture);
        frameLayout = findViewById(R.id.frameLayout);
        //flash.setOnClickListener(new CameraCaptureListener(this));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            try {
                startPreview();
            } catch (Exception e) {
                e.printStackTrace();
                setResult(Activity.RESULT_CANCELED);
                finish();
            }

        } else {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        if (camera != null) {
            cameraPreview.getHolder().removeCallback(cameraPreview);
            camera.release();        // release the camera for other applications
            camera = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (camera == null) {
            startPreview();
        }
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };




    public void startPreview() {
        camera = getCameraInstance(); // attempt to get a Camera instance
        cameraPreview = new CameraPreview(this, camera, 0);
        frameLayout.addView(cameraPreview);
        GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(this, 6);
        resultIntent.putExtra("coords", gridCameraOverlay.getOverlayCoordinates());
        addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
    }

    public void captureImage(View v) {

        if (camera != null) {

            camera.takePicture(null, null, pictureCallback);

        }

    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}


