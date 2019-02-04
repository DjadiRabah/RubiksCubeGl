package iutfbleau.rubikscube;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GridCameraOverlay extends View {
    private Paint paint = new Paint();
    private int[] coordTab = new int[4];


    public GridCameraOverlay(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) { // Override the onDraw() Method
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        paint.setStrokeWidth(3);

        //center
        int middle = getHeight()/2;
        int halfSquareHeight = getWidth()/2;
        coordTab[0] = 0;
        coordTab[1] = middle-halfSquareHeight;
        coordTab[2] = getWidth();
        coordTab[3] = middle+halfSquareHeight;
        //draw guide box
        canvas.drawRect(coordTab[0], coordTab[1], coordTab[2], coordTab[3], paint);
    }

    public int[] getOverlayCoordinates(){

        return this.coordTab;

    }
}