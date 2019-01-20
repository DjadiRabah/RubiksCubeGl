package iutfbleau.rubikscube.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileManager {

    public static int DONTSHOW = 1;
    public static int SHOW = 0;

    private Context context;
    private String filename = "user_pref";

    public FileManager(Context context){

        this.context = context;

    }

    public void write(int value) {

        try {
            FileOutputStream outputStream = this.context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(Integer.toString(value).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int read(){

        int res = FileManager.SHOW;

        try {
            FileInputStream inputStream = context.openFileInput(filename);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;

            while ((line = r.readLine()) != null) {
                total.append(line);
                res = Integer.parseInt(line);
            }

            r.close();
            inputStream.close();
            Log.e("File", "File contents: " + total);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR", "ergerg");
        }

        return res;

    }
}
