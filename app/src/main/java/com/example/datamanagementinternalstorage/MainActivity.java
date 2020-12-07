package com.example.datamanagementinternalstorage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    private String fileName = "data.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check whether fileName already exists in directory used
        // by the openFileOutput() method.
        // If the text file doesn't exist, then create it now

        if (!getFileStreamPath(fileName).exists()) {

            try {

                writeFile();

            } catch (FileNotFoundException e) {
                Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_SHORT).show();
            }
        }


        // Read the data from the text file and display it
        try {

            readFile();

        } catch (IOException e) {
            Toast.makeText(this, "IOException", Toast.LENGTH_SHORT).show();
        }

    }

    private void writeFile() throws FileNotFoundException {

        FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);

        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(fos)));

        pw.println("Line 1: This is a test of the File Writing API");
        pw.println("Line 2: This is a test of the File Writing API");
        pw.println("Line 3: This is a test of the File Writing API");

        pw.close();

    }

    private void readFile() throws IOException {

        FileInputStream fis = openFileInput(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = "";

        while (null != (line = br.readLine())) {

            TextView tv = findViewById(R.id.fileOutTV);
            tv.append(line+"\n");

        }

        br.close();

    }
}
