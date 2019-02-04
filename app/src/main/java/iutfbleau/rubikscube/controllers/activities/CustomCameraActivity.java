package iutfbleau.rubikscube.controllers.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.GridCameraOverlay;
import iutfbleau.rubikscube.ShowCamera;

public class CustomCameraActivity extends AppCompatActivity {

    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        camera = Camera.open();
        ShowCamera showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);
        GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(this);

        addContentView(gridCameraOverlay, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));

    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            //this is where you crop your image
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inMutable = true;
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opt);
            bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            //do the cropping here, bitmap is the image you will use to crop

            Intent resultIntent = new Intent();
            // TODO Add extras or a data URI to this intent as appropriate.
            resultIntent.putExtra("data", bitmap);
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
