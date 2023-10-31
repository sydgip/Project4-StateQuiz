package edu.uga.cs.project4;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVreader {

    public static void readCSV(Context context) {
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open("states_capitals.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String state = parts[0].trim();
                String capital = parts[1].trim();

                // Now, you can insert 'state' and 'capital' into your SQLite database
                // using the DBHelper class we created earlier.
            }

        } catch (IOException e) {
            Log.e("CSVReader", "Error reading CSV file", e);
        }
    }
}
