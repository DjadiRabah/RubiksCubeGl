package iutfbleau.rubikscube.controllers.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.listeners.RBColorPickerOnClickListener;
import iutfbleau.rubikscube.controllers.listeners.SolverOnClickListener;
import iutfbleau.rubikscube.models.BitmapToInt;
import iutfbleau.rubikscube.models.RBColor;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.OpenGLRenderer;

public class SolverActivity extends Activity {

    public static final int CAMERA_REQUEST = 1;
    private static final int ALL_PERMISSIONS = 2;

    public GLSurfaceView glSurfaceView;
    private Button btnCamera, btnNext, btnPrev;
    private TextView textView;
    private OpenGLRenderer openglRenderer;
    private CubeGl cube;
    private int cubeSize;

    private int[] rbColor = {RBColor.GREEN, RBColor.ORANGE, RBColor.BLUE, RBColor.RED, RBColor.YELLOW, RBColor.WHITE};
    private String[] rbColorNames = {"green", "orange", "blue", "red", "yellow", "white"};
    private Button[] buttons = new Button[6];

    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_solver);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cubeSize = getIntent().getIntExtra("cube_size", 3);
        Log.e("CUBE SIZE", "" + cubeSize);

        cube = new CubeGl(new Cube3D(cubeSize));

        SolverOnClickListener solverOnClickListener = new SolverOnClickListener((this));

        btnCamera = findViewById(R.id.btnCamera);
        btnNext = findViewById(R.id.next);
        btnPrev = findViewById(R.id.prev);

        LinearLayout colorPickerLayout = findViewById(R.id.colorPickerLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int buttonWidth = size.x / 6;

        Log.e("SCRENN WIDTH", "" + buttonWidth);

        RBColorPickerOnClickListener rbColorPickerOnClickListener = new RBColorPickerOnClickListener();

        for (int i = 0; i < 6; i++) {

            buttons[i] = new Button(this);
            buttons[i].setLayoutParams(new LinearLayout.LayoutParams(buttonWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttons[i].setTag(rbColorNames[i]);
            buttons[i].setBackgroundColor(rbColor[i]);
            buttons[i].setPadding(20, 20, 20, 20);
            colorPickerLayout.addView(buttons[i]);
            buttons[i].setOnClickListener(rbColorPickerOnClickListener);
        }

        glSurfaceView = findViewById(R.id.glsurfaceview);
        //textView = findViewById(R.id.textView);

        btnNext.setOnClickListener(solverOnClickListener);
        btnPrev.setOnClickListener(solverOnClickListener);

        openglRenderer = new OpenGLRenderer(cube);
        glSurfaceView.setRenderer(openglRenderer);

        // Check if the Camera permission is already available
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Camera permissions is already available, show the camera preview
            btnCamera.setOnClickListener(solverOnClickListener);

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
                float[] coordinates = data.getFloatArrayExtra("coordinates");

                if (bitmap.getWidth() > bitmap.getHeight()) {
                    bitmap = rotateImage(bitmap, 90);
                }

                Log.e("COORDS", "" + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]);
                Log.e("BITMAP SIZE", "WIDTH = " + bitmap.getWidth() + ", HEIGHT = " + bitmap.getHeight());

                bitmap = Bitmap.createBitmap(bitmap, (int) coordinates[0], (int) coordinates[1] + 60, (int) coordinates[2], (int) coordinates[2]);

                Log.e("BITMAP RESIZED", "WIDTH = " + bitmap.getWidth() + ", HEIGHT = " + bitmap.getHeight());

                int[][] colors = BitmapToInt.convert(bitmap, cubeSize);

                StringBuilder res = new StringBuilder();

                for (int[] color : colors) {

                    for (int j = 0; j < colors[0].length; j++) {

                        switch (color[j]) {
                            case 0:
                                res.append("blanc ");
                                break;
                            case 1:
                                res.append("vert ");
                                break;
                            case 2:
                                res.append("rouge ");
                                break;
                            case 3:
                                res.append("bleu ");
                                break;
                            case 4:
                                res.append("orange ");
                                break;
                            case 5:
                                res.append("jaune ");
                                break;
                            default:
                                break;
                        }
                    }
                }
               // textView.setText(res.toString());
            }
        }
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

    public Button getNextButton() {
        return btnNext;
    }

    public Button getPrevButton() {
        return btnPrev;
    }

    public Button getShootButton() {
        return btnCamera;
    }

    public int getCubeSize() {
        return cubeSize;
    }

    public CubeGl get3DCube() {
        return cube;
    }
}

