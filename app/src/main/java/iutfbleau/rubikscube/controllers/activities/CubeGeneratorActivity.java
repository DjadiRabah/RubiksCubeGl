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
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.handlers.ProgressHandler;
import iutfbleau.rubikscube.controllers.listeners.RBColorPickerOnClickListener;
import iutfbleau.rubikscube.controllers.listeners.CubeGeneratorOnClickListener;
import iutfbleau.rubikscube.models.BitmapToInt;
import iutfbleau.rubikscube.models.RBColor;
import iutfbleau.rubikscube.models.cube.cube.Cube3D;
import iutfbleau.rubikscube.view.CubeGl;
import iutfbleau.rubikscube.view.renderer.OpenGLRenderer;

public class CubeGeneratorActivity extends Activity {

    public static final int CAMERA_REQUEST = 1;
    private static final int ALL_PERMISSIONS = 2;

    private GLSurfaceView glSurfaceView;
    private Button btnCamera, btnNext, btnPrev, generate;
    private Cube3D cube3D;
    private CubeGl cube;
    private int cubeSize;
    private int[][] colors;
    private String takenImgPath;

    private int[] rbColor = {RBColor.GREEN, RBColor.ORANGE, RBColor.BLUE, RBColor.RED, RBColor.YELLOW, RBColor.WHITE};
    private String[] rbColorNames = {"green", "orange", "blue", "red", "yellow", "white"};
    private Button[] buttons = new Button[6];

    private static final int REQUEST_TAKE_PHOTO = 1;
    private final static int HANDLER_TASK_ID = 0;
    private CubeGeneratorOnClickListener cubeGeneratorOnClickListener;
    private ProgressHandler progressHandler;

    private int[][][] fullCubeColorsTab = new int[6][3][3];

    private boolean[] facesScanned = new boolean[6];

    private LinearLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_generator);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cubeSize = getIntent().getIntExtra("cube_size", 3);

        cube3D = new Cube3D(cubeSize);
        cube3D.disableColors();
        cube = new CubeGl(cube3D,glSurfaceView);
        cube.rotateX(90.0f);
        cube.rotateY(-15.0);
        cube.rotateX(15.0);

        cubeGeneratorOnClickListener = new CubeGeneratorOnClickListener((this));

        btnCamera = findViewById(R.id.analyze);
        btnNext = findViewById(R.id.next);
        btnPrev = findViewById(R.id.prev);
        loadingLayout = findViewById(R.id.activityGeneratorLoading);
        loadingLayout.setVisibility(View.GONE);
        generate = findViewById(R.id.generate);
        generate.setVisibility(View.GONE);

        LinearLayout colorPickerLayout = findViewById(R.id.colorPickerLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int buttonWidth = size.x / 6;

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

        btnNext.setOnClickListener(cubeGeneratorOnClickListener);
        btnPrev.setOnClickListener(cubeGeneratorOnClickListener);
        generate.setOnClickListener(cubeGeneratorOnClickListener);

        OpenGLRenderer openglRenderer = new OpenGLRenderer(cube);
        glSurfaceView.setRenderer(openglRenderer);

        // Check if the Camera permission is already available
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Camera permissions is already available, show the camera preview
            btnCamera.setOnClickListener(cubeGeneratorOnClickListener);

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

                getGenerateButton().setVisibility(View.GONE);
                getLoadingLayout().setVisibility(View.VISIBLE);

                takenImgPath = data.getStringExtra("img_path");

                Bitmap bitmap = BitmapFactory.decodeFile(takenImgPath);
                float[] coordinates = data.getFloatArrayExtra("coordinates");

                if (bitmap.getWidth() > bitmap.getHeight()) {
                    bitmap = rotateImage(bitmap, 90);
                }

                bitmap = Bitmap.createBitmap(bitmap, (int) coordinates[0], (int) coordinates[1] + 60, (int) coordinates[2], (int) coordinates[2]);
                final Bitmap finalBitmap = bitmap;

                progressHandler = new ProgressHandler(this);

                new Thread(new Runnable() {
                    public void run() {
                        colors = BitmapToInt.convert(finalBitmap, cubeSize);
                        Message msg = progressHandler.obtainMessage(HANDLER_TASK_ID, cubeGeneratorOnClickListener.getCurrentFace(), 0, cube3D);
                        progressHandler.sendMessage(msg);
                    }

                }).start();

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

    public int[][] getColorsTab() {
        return colors;
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

    public LinearLayout getLoadingLayout() {
        return loadingLayout;
    }

    public CubeGeneratorOnClickListener getCubeGeneratorOnClickListener() {
        return cubeGeneratorOnClickListener;
    }

    public String getTakenImagePath(){
        return takenImgPath;
    }

    public int[][][] getFullCubeColorsTab() {
        return fullCubeColorsTab;
    }

    public boolean[] getFacesScannedArray() {
        return facesScanned;
    }

    public boolean isAllFacesScanned() {
        for (int i = 0; i < facesScanned.length; i++) {
            if (!facesScanned[i]) {
                return false;
            }
        }
        return true;
    }

    public Button getGenerateButton(){
        return generate;
    }
}

