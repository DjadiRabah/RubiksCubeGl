package iutfbleau.rubikscube.controllers.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import iutfbleau.rubikscube.GridCameraOverlay;
import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.models.BitmapToInt;

public class CameraSolverActivity extends AppCompatActivity {

    private ImageView imageView;
    static final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_solver);

        Button btnCamera = findViewById(R.id.btnCamera);
        imageView = findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomCameraActivity.class);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == CAMERA_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

               // Bitmap bitmap = BitmapFactory.decodeResource(getApplication().getBaseContext().getResources(),R.drawable.cubeface);
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);

                GridCameraOverlay gridCameraOverlay = new GridCameraOverlay(getApplicationContext());
                int[] coords = gridCameraOverlay.getOverlayCoordinates();

                int[][] colors = BitmapToInt.convert(bitmap,3, coords[0], coords[1], coords[2], coords[3]);

                for(int i = 0; i < colors.length; i++)
                {

                    for(int j = 0; j < colors[0].length; j++)
                    {
                        Log.e("YES", i+" "+j);

                        switch(colors[i][j])
                        {
                            case 0 : Log.e("COLOR", "blanc"); break;
                            case 1 : Log.e("COLOR", "vert"); break;
                            case 2 : Log.e("COLOR", "rouge "); break;
                            case 3 : Log.e("COLOR", "bleu "); break;
                            case 4 : Log.e("COLOR","orange "); break;
                            case 5 : Log.e("COLOR", "jaune "); break;
                            default : break;
                        }
                    }
                }

            }
        }
    }
}
