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
    private double speed;

    public CubeMotionListener(CubeGl cube)
    {
        this.cube = cube;
        this.initialX = 0.0f;
        this.initialY = 0.0f;
        this.speed = 3.0;
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

                double alphaX = (finalX-initialX)/(finalY-initialY);
                double alphaY = (finalY-initialY)/(finalX-initialX);
                if (finalX > initialX)
                {
                    this.cube.rotateY(this.speed * Math.atan(Math.sqrt(alphaX*alphaX)));
                }
                if (finalX  < initialX)
                {
                    this.cube.rotateY(- this.speed * Math.atan(Math.sqrt(alphaX*alphaX)));
                }
                if (finalY > initialY)
                {
                    this.cube.rotateX(this.speed * Math.atan(Math.sqrt(alphaY*alphaY)));
                }
                if (finalY < initialY)
                {
                    this.cube.rotateX(- this.speed * Math.atan(Math.sqrt(alphaY*alphaY)));
                }
                this.initialX = event.getX();
                this.initialY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}