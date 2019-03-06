package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
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

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.view.GridCameraOverlay;
import iutfbleau.rubikscube.view.CameraPreview;

public class CustomCameraActivity extends Activity {

    private Camera camera;
    private FrameLayout frameLayout;
    private Intent resultIntent = new Intent();
    private Button flash, capture;
    private CameraPreview cameraPreview;
    private GridCameraOverlay gridCameraOverlay;
    private int cubeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        cubeSize = getIntent().getIntExtra("cube_size", 3);

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

            File pictureFile = getOutputMediaFile();
            FileOutputStream fos;
            if (pictureFile != null) {
                try {
                    fos = new FileOutputStream(pictureFile);
                    try {
                        fos.write(data);
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("img_path", pictureFile.getPath());
            resultIntent.putExtra("coordinates", gridCameraOverlay.getOverlayCoordinates());
            Log.e("PATH", "" + pictureFile.getPath());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    };

    private File getOutputMediaFile() {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            File mediaStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.e("MyCameraApp", "failed to create directory");
                    return null;
                }
            }

            String imageFileName = "capture";
            File image = null;
            try {
                image = File.createTempFile(imageFileName, ".jpg", mediaStorageDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;

        } else {
            return null;
        }
    }

    public void startPreview() {
        camera = getCameraInstance(); // attempt to get a Camera instance
        cameraPreview = new CameraPreview(this, camera);
        frameLayout.addView(cameraPreview);
        gridCameraOverlay = new GridCameraOverlay(this, cubeSize);
        resultIntent.putExtra("coords", gridCameraOverlay.getOverlayCoordinates());
        addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
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


