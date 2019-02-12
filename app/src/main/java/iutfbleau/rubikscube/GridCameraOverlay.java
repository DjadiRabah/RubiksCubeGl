package iutfbleau.rubikscube;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

public class GridCameraOverlay extends View {
    private Paint paint = new Paint();
    private float[] coordTab = new float[4];


    public GridCameraOverlay(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) { // Override the onDraw() Method
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        paint.setStrokeWidth(3);

        float offset = 100.0f;
        float cubeWidth = getWidth()-2*offset;
        float middle = getHeight()/2;
        float halfSquareHeight = getWidth()/2;

        coordTab[0] = offset;
        coordTab[1] = middle - halfSquareHeight + offset;
        coordTab[2] = getWidth() - offset;
        coordTab[3] = middle + halfSquareHeight - offset;
        //draw guide box
        canvas.drawRect(coordTab[0], coordTab[1], coordTab[2], coordTab[3], paint);
        //left, top, right, bottom

        //Drawing vertical lines
        canvas.drawLine(offset + (0.33f * cubeWidth), coordTab[1], offset + (0.33f * cubeWidth), middle + halfSquareHeight - offset, paint);
        canvas.drawLine(offset + (0.66f * cubeWidth), coordTab[1], offset + (0.66f * cubeWidth), middle + halfSquareHeight - offset, paint);

        //Drawing horizontal lines
        canvas.drawLine(offset, middle - 0.5f * cubeWidth + 0.33f * cubeWidth, getWidth() - offset, middle - 0.5f * cubeWidth + 0.33f * cubeWidth, paint);
        canvas.drawLine(offset, middle - 0.5f * cubeWidth + 0.66f * cubeWidth, getWidth() - offset, middle - 0.5f * cubeWidth + 0.66f * cubeWidth, paint);

    }

    public float[] getOverlayCoordinates(){

        return this.coordTab;

    }
}