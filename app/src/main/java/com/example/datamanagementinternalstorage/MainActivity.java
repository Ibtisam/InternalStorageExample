package com.example.datamanagementinternalstorage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    private String fileName = "data";
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    private void writeToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)));

        pw.println(editText.getText().toString());
        pw.close();

    }

    private void readFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = "";
        while (null != (line = br.readLine())) {
            editText.append(line);
        }
        br.close();
    }

    public void filesWBClicked(View v){
        try {
            File file = new File(getFilesDir(), fileName);
            writeToFile(file);
        } catch (IOException e) {
            Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_SHORT).show();
        }
    }

    public void cacheWBClicked(View v){
        try {
            File file = new File(getCacheDir(), fileName);
            writeToFile(file);
        } catch (IOException e) {
            Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_SHORT).show();
        }
    }

    public void filesRBClicked(View v){
        try {
            File file = new File(getFilesDir(), fileName);
            readFromFile(file);
        } catch (IOException e) {
            Toast.makeText(this, "IOException"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cacheRBClicked(View v){
        try {
            File file = new File(getCacheDir(), fileName);
            readFromFile(file);
        } catch (IOException e) {
            Toast.makeText(this, "IOException"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void resetBClicked(View v){
        editText.setText("");
    }

    public void delBClicked(View v){
        File cacheDir = getCacheDir();
        for(File file:cacheDir.listFiles()){
            file.delete();
        }
        File filesDir = getFilesDir();
        for(File file:filesDir.listFiles()){
            file.delete();
        }
        Toast.makeText(this, "All files deleted from internal storage", Toast.LENGTH_SHORT).show();
    }

}
