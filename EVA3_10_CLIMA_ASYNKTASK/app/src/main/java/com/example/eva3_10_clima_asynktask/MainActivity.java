package com.example.eva3_10_clima_asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ClimaAsynk().execute();
    }

    class ClimaAsynk extends AsyncTask<Void, Void, String> {
        final String ruta = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null) {
                Log.wtf("cadena", s);
            }
                
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String sResu = null;
            try {
                URL url = new URL(ruta);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                if(http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String linea;
                    StringBuffer lineas = new StringBuffer();
                    InputStream inputStream = http.getInputStream();
                    InputStreamReader isReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(isReader);
                    while ((linea = bufferedReader.readLine()) != null){
                        lineas.append(linea);
                    }
                    sResu = lineas.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sResu;
        }
    }

}
