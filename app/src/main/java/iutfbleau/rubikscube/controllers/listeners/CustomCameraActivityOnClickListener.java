package iutfbleau.rubikscube.controllers.listeners;

import android.view.View;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.CustomCameraActivity;

public class CustomCameraActivityOnClickListener implements View.OnClickListener {

    private CustomCameraActivity customCameraActivity;

    public CustomCameraActivityOnClickListener(CustomCameraActivity customCameraActivity) {
        this.customCameraActivity = customCameraActivity;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.captureImageButton:
                customCameraActivity.captureImage(v);
                break;

            case R.id.flashImageButton:
                customCameraActivity.setFlashMode();
                break;
        }

    }
}
