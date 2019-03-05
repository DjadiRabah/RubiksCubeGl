package iutfbleau.rubikscube.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class CubeFaceColorDescriptor implements Parcelable {

    private int[][][] array;

    public CubeFaceColorDescriptor(int[][][] array) {

        this.array = array;

    }

    protected CubeFaceColorDescriptor(Parcel in) {
        array = read3DimArray(in);
    }

    public static final Creator<CubeFaceColorDescriptor> CREATOR = new Creator<CubeFaceColorDescriptor>() {
        @Override
        public CubeFaceColorDescriptor createFromParcel(Parcel in) {
            return new CubeFaceColorDescriptor(in);
        }

        @Override
        public CubeFaceColorDescriptor[] newArray(int size) {
            return new CubeFaceColorDescriptor[size];
        }
    };

    public int[][][] getArray() {
        return array;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        write3DimArray(array, dest);
    }

    private void write3DimArray(int[][][] array, Parcel parcel) {
        parcel.writeInt(array.length);
        for (int i = 0; i < array.length; i++) {
            parcel.writeInt(array[i].length);
            for (int j = 0; j < array[0].length; j++) {
                parcel.writeInt(array[i][j].length);
                parcel.writeIntArray(array[i][j]);
            }

        }
        Log.e("WRITE", ""+array.length);
    }

    private int[][][] read3DimArray(Parcel parcel) {
        int arrayL = parcel.readInt();
        int[][][] array = new int[arrayL][][];
        for (int i = 0; i < array.length; i++)
        {
            int array1Size = parcel.readInt();
            array[i] = new int[array1Size][];
            for (int j = 0; j < array1Size; j++)
            {
                int array2Size = parcel.readInt();
                array[i][j] = new int[array2Size];
                parcel.readIntArray(array[i][j]);
            }
        }
        return array;
    }
}
