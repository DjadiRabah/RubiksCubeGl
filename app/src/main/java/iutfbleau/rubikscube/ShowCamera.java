package iutfbleau.rubikscube;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceHolder surfaceHolder;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Camera.Parameters params = camera.getParameters();

        //change camera orientation
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {

            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);

        } else {

            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);

        }

        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


    }
}
