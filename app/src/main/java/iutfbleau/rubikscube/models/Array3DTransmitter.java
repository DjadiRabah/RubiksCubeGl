package iutfbleau.rubikscube.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Array3DTransmitter implements Parcelable {

    private int[][][] arrayToTransmit;

    public Array3DTransmitter(int[][][] arrayToTransmit) {

        this.arrayToTransmit = arrayToTransmit;

    }

    protected Array3DTransmitter(Parcel in) {
        arrayToTransmit = read3DimArray(in);
    }

    public static final Creator<Array3DTransmitter> CREATOR = new Creator<Array3DTransmitter>() {
        @Override
        public Array3DTransmitter createFromParcel(Parcel in) {
            return new Array3DTransmitter(in);
        }

        @Override
        public Array3DTransmitter[] newArray(int size) {
            return new Array3DTransmitter[size];
        }
    };

    public int[][][] getArray() {
        return arrayToTransmit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        write3DimArray(arrayToTransmit, dest);
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
    }

    private int[][][] read3DimArray(Parcel parcel) {
        int arrayL = parcel.readInt();
        int[][][] array = new int[arrayL][][];
        for (int i = 0; i < array.length; i++) {
            int array1Size = parcel.readInt();
            array[i] = new int[array1Size][];
            for (int j = 0; j < array1Size; j++) {
                int array2Size = parcel.readInt();
                array[i][j] = new int[array2Size];
                parcel.readIntArray(array[i][j]);
            }
        }
        return array;
    }
}
