package iutfbleau.rubikscube.controllers.listeners;

import android.view.MotionEvent;
import android.view.View;

import iutfbleau.rubikscube.models.rotation.Rotation;
import iutfbleau.rubikscube.view.CubeGl;

public class CubeMotionListener implements View.OnTouchListener
{
    private float initialX;
    private float initialY;
    private CubeGl cube;
    private double speed;
    private boolean hasFaceMoved;
    public CubeMotionListener(CubeGl cube)
    {
        this.cube = cube;
        this.initialX = 0.0f;
        this.initialY = 0.0f;
        this.speed = 3.5;
        this.hasFaceMoved = false;
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

                if(this.initialY < 500)
                {
                    if(!this.hasFaceMoved)
                    {
                        if (finalX > initialX)
                        {
                            this.cube.rotate(Rotation.RIGHT,0);
                            this.hasFaceMoved = true;
                        }

                        else if (finalX  < initialX)
                        {
                            this.cube.rotate(Rotation.LEFT,0);
                            this.hasFaceMoved = true;
                        }
                    }
                }

                else if(this.initialY > 500)
                {
                    double alphaX = (finalX-initialX)/(finalY-initialY);
                    double alphaY = (finalY-initialY)/(finalX-initialX);

                    // Rotation vers la droite
                    if (finalX > initialX)
                    {
                        this.cube.rotateY( this.speed * Math.atan(Math.sqrt(alphaX*alphaX)));
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
                }
                break;
            case MotionEvent.ACTION_UP:
                this.hasFaceMoved = false;
                break;
        }
        return true;
    }
}