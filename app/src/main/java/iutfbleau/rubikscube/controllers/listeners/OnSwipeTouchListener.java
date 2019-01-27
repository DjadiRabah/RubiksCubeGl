package iutfbleau.rubikscube.controllers.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import iutfbleau.rubikscube.view.CubeGl;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private CubeGl cube;
    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context ctx, CubeGl cube) {
        gestureDetector = new GestureDetector(ctx, new GestureListener(this));
        this.cube = cube;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipeRight() {
        this.cube.rotateY(45.0);
    }

    public void onSwipeLeft() {
        this.cube.rotateY(-45.0);
    }

    public void onSwipeTop() {
        this.cube.rotateX(-45.0);
    }

    public void onSwipeBottom() {
        this.cube.rotateX(45.0);
    }
}
