package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.CustomCameraActivityOnClickListener;
import iutfbleau.rubikscube.view.GridCameraOverlay;
import iutfbleau.rubikscube.view.CameraPreview;

public class CustomCameraActivity extends Activity {

    private Camera camera;
    private FrameLayout frameLayout;
    private Intent resultIntent = new Intent();
    private ImageButton flashImageButton;
    private CameraPreview cameraPreview;
    private GridCameraOverlay gridCameraOverlay;
    private int cubeSize;
    private int fmi = 0; //flash mode index
    //Flash modes
    private final String[] flashModes = {Camera.Parameters.FLASH_MODE_OFF, Camera.Parameters.FLASH_MODE_ON, Camera.Parameters.FLASH_MODE_AUTO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cubeSize = getIntent().getIntExtra("cube_size", 3);

        flashImageButton = findViewById(R.id.flashImageButton);
        ImageButton captureImageButton = findViewById(R.id.captureImageButton);
        frameLayout = findViewById(R.id.frameLayout);

        camera = getCameraInstance();
        Camera.Parameters params = camera.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        setCameraDisplayOrientation(this, Camera.CameraInfo.CAMERA_FACING_BACK, camera);

        flashImageButton.setOnClickListener(new CustomCameraActivityOnClickListener(this));
        captureImageButton.setOnClickListener(new CustomCameraActivityOnClickListener(this));

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

    public void setFlashMode() {
        Camera.Parameters params = camera.getParameters();
        switch (fmi) {
            case 0: //IF Flash OFF
            {
                fmi = 1; //Flash ON
                flashImageButton.setImageResource(R.drawable.baseline_flash_on_black_24dp);
                break;
            }
            case 1: //IF Flash ON
            {
                fmi = 2; //Flash AUTO
                flashImageButton.setImageResource(R.drawable.baseline_flash_auto_black_24dp);
                break;
            }
            case 2: //IF Flash AUTO
            {
                fmi = 0; //Flash OFF
                flashImageButton.setImageResource(R.drawable.baseline_flash_off_black_24dp);
                break;
            }
            default: {
                fmi = 0; //Flash OFF
                flashImageButton.setImageResource(R.drawable.baseline_flash_off_black_24dp);
                break;
            }
        }

        params.setFlashMode(flashModes[fmi]);
        Log.i("FLASH MODE", "" + params.getFlashMode());

        //Set the new parameters to the camera:
        camera.setParameters(params);
    }

    private void setCameraDisplayOrientation(Activity activity, int cameraId, android.hardware.Camera camera) {
        //Camera info
        Camera.CameraInfo info = new Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}


