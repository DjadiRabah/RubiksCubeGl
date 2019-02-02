package iutfbleau.rubikscube;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GridCameraOverlay extends View {
    private Paint paint = new Paint();

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
        //draw guide box
        canvas.drawRect(0, middle-halfSquareHeight, getWidth(), middle+halfSquareHeight, paint);
    }
}