package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
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
import iutfbleau.rubikscube.GridCameraOverlay;
import iutfbleau.rubikscube.CameraPreview;

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
        //flash.setOnClickListener(new CameraCaptureListener(this));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.frameLayout);

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            try {
                camera = Camera.open(); // attempt to get a Camera instance
                cameraPreview = new CameraPreview(this, camera, 0);
                frameLayout.addView(cameraPreview);
                GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(this);
                resultIntent.putExtra("coords", gridCameraOverlay.getOverlayCoordinates());
                addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
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
            camera = getCameraInstance();
            cameraPreview = new CameraPreview(this, camera, 0);
            frameLayout.addView(cameraPreview);
            GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(this);
            resultIntent.putExtra("coords", gridCameraOverlay.getOverlayCoordinates());
            addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
        }
    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                Log.d("FILE", "Error creating media file, check storage permissions");
                return;
            }else {

                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();

                    camera.startPreview();


                    Log.e("FILE", "IN TRY BLOCK");
                } catch (FileNotFoundException e) {
                    Log.d("FILE", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("FILE", "Error accessing file: " + e.getMessage());
                }

                resultIntent.putExtra("img", data);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }

        }
    };

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

    private static File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        } else {
            //https://www.youtube.com/watch?v=-W3qpuYr3lk

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + "rbcube_cache");
            // This location works best if you want the created images to be shared
            // between applications and persist after your app has been uninstalled.

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("MyCameraApp", "failed to create directory");
                    return null;
                }
            }

            // Create a media file name
            Log.e("PATHNAME", ""+mediaStorageDir.getPath());
            File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "capture.jpg");

            return mediaFile;

        }

    }

}


