package com.example.demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    InputStream inputStream = new URL("https://www.baidu.com/").openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

                    String line = null;
                    StringBuffer content = new StringBuffer();
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                    reader.close();

                    return content.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String s){
                super.onPostExecute(s);

                if(s!=null){
                    tv.setText(s);
                }
            }

        }.execute();
    }
}
