package iutfbleau.rubikscube.controllers.listeners;

import android.content.Context;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import iutfbleau.rubikscube.models.ActionBarDrawerToggler;
import iutfbleau.rubikscube.view.CubeGl;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private CubeGl cube;
    private final GestureDetector gestureDetector;
    private Context context;

    public OnSwipeTouchListener(Context ctx, CubeGl cube) {
        gestureDetector = new GestureDetector(ctx, new GestureListener(this));
        this.cube = cube;
        this.context = ctx;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (ActionBarDrawerToggler.drawerLayoutClosed) {

            return gestureDetector.onTouchEvent(event);

        } else {

            return false;

        }
    }

    public void onSwipeRight() {
        this.cube.rotateY(45.0);
        Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeLeft() {
        this.cube.rotateY(-45.0);
        Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeTop() {
        this.cube.rotateX(-45.0);
        Toast.makeText(context, "Top", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeBottom() {
        this.cube.rotateX(45.0);
        Toast.makeText(context, "Bottom", Toast.LENGTH_SHORT).show();
    }
}
