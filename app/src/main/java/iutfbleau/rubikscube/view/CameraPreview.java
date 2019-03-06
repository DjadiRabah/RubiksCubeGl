package iutfbleau.rubikscube.view;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private Camera.Parameters params;
    private Camera.Size bestSize;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    public CameraPreview(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

       params = camera.getParameters();

        // set preview size and make any resize, rotate or
        // reformatting changes here

        List<Camera.Size> sizeList = params.getSupportedPreviewSizes();

        bestSize = sizeList.get(0);
        for(int i = 1; i < sizeList.size(); i++){
            if((sizeList.get(i).width * sizeList.get(i).height) > (bestSize.width * bestSize.height)){
                bestSize = sizeList.get(i);
            }
        }

        params.setPictureSize(bestSize.width, bestSize.height);
        params.setPreviewSize(bestSize.width, bestSize.height);

        params.set("orientation", "portrait");
        camera.setDisplayOrientation(90);


        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        // params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);

        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("CAMERA", "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        params.setPictureSize(bestSize.width, bestSize.height);
        params.setPreviewSize(bestSize.width, bestSize.height);

        // start preview with new settings
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("CAMERA", "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
