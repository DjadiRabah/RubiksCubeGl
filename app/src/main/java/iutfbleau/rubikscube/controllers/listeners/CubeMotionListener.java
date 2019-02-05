package iutfbleau.rubikscube.controllers.listeners;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import iutfbleau.rubikscube.view.CubeGl;

public class CubeMotionListener implements View.OnTouchListener
{
    private float initialX;
    private float initialY;
    private CubeGl cube;

    public CubeMotionListener(CubeGl cube)
    {
        this.cube = cube;
        this.initialX = 0.0f;
        this.initialY = 0.0f;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
        {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.initialX = x;
                this.initialY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                float finalX = event.getX();
                float finalY = event.getY();

                if ((finalX - initialX) > 50.0)
                {
                    this.cube.rotateY(4.0);
                }
                if ((finalX - initialX) < -50.0)
                {
                    this.cube.rotateY(-4.0);
                }
                if ((finalY - initialY) > 50.0)
                {
                    this.cube.rotateX(4.0);
                }
                if ((finalY - initialY) < -50.0)
                {
                    this.cube.rotateX(-4.0);
                }
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}