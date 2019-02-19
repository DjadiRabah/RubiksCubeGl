package iutfbleau.rubikscube.controllers.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.models.BitmapToInt;

public class CameraSolverActivity extends Activity {

    private static final int CAMERA_REQUEST = 1;
    private static final int ALL_PERMISSIONS = 2;

    private ImageView imageView;
    private Button btnCamera;

    static final int REQUEST_TAKE_PHOTO = 1;

    //CAMERA 2 API : https://github.com/googlesamples/android-Camera2Basic

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_solver);

        btnCamera = findViewById(R.id.btnCamera);
        imageView = findViewById(R.id.imageView);

        // Check if the Camera permission is already available
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                displayInfoToast();
            }
            //Request camera permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, ALL_PERMISSIONS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == REQUEST_TAKE_PHOTO) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Bitmap bitmap = BitmapFactory.decodeFile(data.getStringExtra("img_path"));

                if (bitmap.getWidth() > bitmap.getHeight()) {
                    bitmap = rotateImage(bitmap, 90);
                }

                float[] coordinates = data.getFloatArrayExtra("coordinates");
                Log.e("COORDS", "" + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]);

                Log.e("BITMAP SIZE", "WIDTH = " + bitmap.getWidth() + ", HEIGHT = " + bitmap.getHeight());

                bitmap = Bitmap.createBitmap(bitmap, (int)coordinates[0], (int)coordinates[1]+60, (int)coordinates[2], (int)coordinates[2]);

                Log.e("IMG VIEW", "WIDTH = " + imageView.getWidth() + ", HEIGHT = " + imageView.getHeight());
                Log.e("BITMAP RESIZED", "WIDTH = " + bitmap.getWidth() + ", HEIGHT = " + bitmap.getHeight());

                //BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
                //imageView.setBackground(drawable);
                imageView.setImageBitmap(bitmap);

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
            }
        }
    }

    private Bitmap bmpResize(ImageView target, String src_path) {
        // Get the dimensions of the View
        int targetW = target.getWidth();
        int targetH = target.getHeight();
        Log.e("IMG VIEW SIZE", "WIDTH = " + targetW + ", HEIGHT = " + targetH);

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(src_path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        Log.e("SCALE FACTOR", ""+scaleFactor);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(src_path, bmOptions);

        if (bitmap.getWidth() > bitmap.getHeight()) {
            bitmap = rotateImage(bitmap, 90);
        }
        Log.e("BITMAP SIZE", "WIDTH = " + bitmap.getWidth() + ", HEIGHT = " + bitmap.getHeight());

        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        boolean isPermissionForAllGranted = false;

        if (requestCode == ALL_PERMISSIONS) {
            //Received permission result for camera permission

            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && permissions.length == grantResults.length) {

                for (int i = 0; i < permissions.length; i++) {
                    isPermissionForAllGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                }

                if (isPermissionForAllGranted) {

                    btnCamera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CustomCameraActivity.class);
                            startActivityForResult(intent, CAMERA_REQUEST);
                        }
                    });
                } else {
                    displayInfoToast();
                    finish();
                }
            }
        } else {
            displayInfoToast();
            finish();
        }
    }

    private void displayInfoToast() {
        Toast.makeText(this, "Camera and External Storage Write permissions are required to use the solver !", Toast.LENGTH_LONG).show();
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}

