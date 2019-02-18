package iutfbleau.rubikscube.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import iutfbleau.rubikscube.R;

public class GridCameraOverlay extends View {

    private Paint paint = new Paint();
    private float[] coordTab = new float[3];
    private int cubeSize;

    public GridCameraOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridCameraOverlay(Context context, int cubeSize) {
        super(context);
        this.cubeSize = cubeSize;
    }

    @Override
    protected void onDraw(Canvas canvas) { // Override the onDraw() Method
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        paint.setStrokeWidth(3);

        float offset = 100.0f;
        float cubeWidth = getWidth() - 2 * offset;
        float middle = getHeight() / 2.0f;
        float halfSquareHeight = getWidth() / 2.0f;

        //draw guide box
        canvas.drawRect(offset, middle - halfSquareHeight + offset, getWidth() - offset, middle + halfSquareHeight - offset, paint);
        //left, top, right, bottom

        canvas.drawLine(0.0f, getHeight()/2.0f, getWidth(), getHeight()/2.0f, paint);

        //Dynamically generate grid overlay
        for (int i = 0; i < cubeSize - 1; i++) {

            float ratio = (1 / ((float) cubeSize)) * (i + 1);
            //Drawing vertical lines
            canvas.drawLine(offset + (ratio * cubeWidth), middle - halfSquareHeight + offset, offset + (ratio * cubeWidth), middle + halfSquareHeight - offset, paint);
            //Drawing horizontal lines
            canvas.drawLine(offset, middle - 0.5f * cubeWidth + ratio * cubeWidth, getWidth() - offset, middle - 0.5f * cubeWidth + ratio * cubeWidth, paint);

        }

        Log.e("CANVAS", "WIDTH = " + getWidth() + ", HEIGHT = " + getHeight());

        coordTab[0] = offset;
        coordTab[1] = getHeight()/2.0f - cubeWidth/2.0f;
        coordTab[2] = getWidth() - (2.0f*offset);
    }

    public float[] getOverlayCoordinates() {

        return this.coordTab;

    }
}