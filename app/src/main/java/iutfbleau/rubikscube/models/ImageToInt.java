package iutfbleau.rubikscube.models;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class ImageToInt {
    private final static int ORANGE = Color.rgb(255, 128, 0);
    private final static int PINK = Color.rgb(255, 0, 255);
    private final static int[] TAB = {Color.WHITE, Color.GREEN, Color.RED, Color.BLUE, ORANGE, Color.YELLOW};
    private final static int TAKEN_IMG_PERCENTAGE = 56;

    private static Bitmap bitmap;

    public ImageToInt(Bitmap bitmap) {

        ImageToInt.bitmap = bitmap;

    }

    private static int getColor(int pixelColor) {
        int r = Color.red(pixelColor);
        int g = Color.green(pixelColor);
        int b = Color.blue(pixelColor);

        int s = 255 * 3;
        int color = PINK;
        for (int i = 0; i < ImageToInt.TAB.length; i++) {
            int x = ImageToInt.TAB[i];
            int n = (int) Math.pow(Math.pow(r - Color.red(x), 2) + Math.pow(g - Color.green(x), 2) + Math.pow(b - Color.blue(x), 2), 0.5);
            if (n < s) {
                color = x;
                s = n;
            }
        }
        return color;
    }

    private static int[][] getColors() {
        int[][] colors = null;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Log.e("WIDTH + HEIGHT", "" + bitmap.getWidth() + " " + bitmap.getHeight());

        colors = new int[height][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int c = getColor(bitmap.getPixel(i, j)); //
                int k = 0;
                while (c != (TAB[k]))
                    k++;
                colors[j][i] = k;
            }
        }

        return colors;
    }

    private static int getNumberColor(int[][] colors, int i1, int i2, int j1, int j2, int value) {
        int cpt = 0;
        for (int i = i1; i < i2; i++) {
            for (int j = j1; j < j2; j++) {

                if (colors[i][j] == value)
                    cpt++;
            }
        }
        return cpt;
    }

    public static int[][] getColors(int size) {
        int alpha = TAKEN_IMG_PERCENTAGE;
        int[][] colors = new int[size][size];
        int[][] allColors = getColors();

        int height = allColors.length * alpha / 100;
        int width = allColors[0].length * alpha / 100;
        int blockHeight = 9 * height / 30;
        int blockWidth = 9 * width / 30;
        int offsetBlockWidth = 5 * width / 100;
        int offsetBlockHeight = 5 * height / 100;
        int offsetX = allColors[0].length * (100 - alpha) / 200;
        int offsetY = allColors.length * (100 - alpha) / 200;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int max = getNumberColor(allColors, offsetY, blockHeight, offsetX, blockWidth, 0);
                int color = 0;
                for (int k = 1; k < 6; k++) {
                    int newNumber = getNumberColor(allColors, offsetY + i * blockHeight, offsetY + (i + 1) * blockHeight,
                            offsetX + j * blockWidth, offsetX + (j + 1) * blockWidth, k);
                    if (max < newNumber) {
                        max = newNumber;
                        color = k;
                    }
                }
                colors[i][j] = color;
            }
        }

        return colors;
    }

    private static int abs(int val) {
        return val < 0 ? val * -1 : val;
    }

}