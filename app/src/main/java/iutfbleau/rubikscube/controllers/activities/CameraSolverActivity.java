package iutfbleau.rubikscube.controllers.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.models.BitmapToInt;

public class CameraSolverActivity extends AppCompatActivity {

    private ImageView imageView;
    static final int CAMERA_REQUEST = 1;
    private static final int GET_CAMERA_ACCESS = 1;
    private Button btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_solver);

        btnCamera = findViewById(R.id.btnCamera);
        imageView = findViewById(R.id.imageView);

        // Check if the Camera permission is already available
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Camera permissions is already available, show the camera preview

            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CustomCameraActivity.class);
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
            });

        } else {
            //Camera permission has not been granted
            //Provide an additional rationale to the user if the permission was not granted
            //and the user would benefit from additional context for the use of the permission

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera permission is needed to show the camera preview !", Toast.LENGTH_LONG).show();
            }

            //Request camera permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, GET_CAMERA_ACCESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == CAMERA_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                byte[] resData = data.getByteArrayExtra("img");

              //  Bitmap bitmap = BitmapFactory.decodeByteArray(resData, 0, resData.length);


                //Try ... catch ???
                File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + "rbcube_cache");

                File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "capture.jpg");
                Bitmap bitmap = BitmapFactory.decodeFile(mediaFile.getAbsolutePath());
                Log.e("CAPTURED_IMG_DIM", "WIDTH = "+bitmap.getWidth()+", HEIGHT = "+bitmap.getHeight());

                if (bitmap.getWidth() > bitmap.getHeight()) {
                    bitmap = rotateImage(bitmap, 90);
                }

                Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.7), (int)(bitmap.getHeight()*0.7), true);

                float[] coords = data.getFloatArrayExtra("coords");
                Log.e("COORDS", "" + coords[0] + " " + coords[1] + " " + coords[2] + " " + coords[3]);
                Log.e("COORDS", "" + bitmap.getWidth() + " " + bitmap.getHeight());

                imageView.setImageBitmap(resized);

                int[][] colors = BitmapToInt.convert(bitmap, 3, 0, 0, bitmap.getWidth(), bitmap.getHeight());

                for (int i = 0; i < colors.length; i++) {

                    for (int j = 0; j < colors[0].length; j++) {
                        Log.e("YES", i + " " + j);

                        switch (colors[i][j]) {
                            case 0:
                                Log.e("COLOR", "blanc");
                                break;
                            case 1:
                                Log.e("COLOR", "vert");
                                break;
                            case 2:
                                Log.e("COLOR", "rouge ");
                                break;
                            case 3:
                                Log.e("COLOR", "bleu ");
                                break;
                            case 4:
                                Log.e("COLOR", "orange ");
                                break;
                            case 5:
                                Log.e("COLOR", "jaune ");
                                break;
                            default:
                                break;
                        }
                    }
                }

            } else {
                Toast.makeText(this, "An error occurred while trying to access the camera", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == GET_CAMERA_ACCESS) {
            //Received permission result for camera permission

            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CustomCameraActivity.class);
                        startActivityForResult(intent, CAMERA_REQUEST);
                    }
                });

            } else {

                Toast.makeText(this, "Camera permission is needed to show the camera preview !", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}

