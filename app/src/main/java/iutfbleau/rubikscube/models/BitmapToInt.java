package iutfbleau.rubikscube.models;

import android.graphics.Bitmap;

public class BitmapToInt {

    private final static int[] TAB = {RBColor.WHITE, RBColor.GREEN, RBColor.RED, RBColor.BLUE, RBColor.ORANGE, RBColor.YELLOW};
    private final static int TAKEN_IMG_PERCENTAGE = 80;

    private static Bitmap bitmap;

    public static int[][] convert(Bitmap bitmap, int size, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {

        BitmapToInt.bitmap = bitmap;

        int[][] colors = new int[size][size];
        int[][] allColors = getColors();

        int height = bottomRightY -topLeftY;
        int width = bottomRightX - topLeftX;
        int blockHeight = height / size;
        int blockWidth = width / size;
        int offsetX = topLeftX;
        int offsetY = topLeftY;

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

    private static int getColor(int pixelColor) {
        int r = RBColor.red(pixelColor);
        int g = RBColor.green(pixelColor);
        int b = RBColor.blue(pixelColor);

        int s = 255 * 3;
        int color = RBColor.PINK;
        for (int i = 0; i < BitmapToInt.TAB.length; i++) {
            int x = BitmapToInt.TAB[i];
            int n = (int) Math.pow(Math.pow(r - RBColor.red(x), 2) + Math.pow(g - RBColor.green(x), 2) + Math.pow(b - RBColor.blue(x), 2), 0.5);
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

    private static int abs(int val) {
        return val < 0 ? val * -1 : val;
    }

}