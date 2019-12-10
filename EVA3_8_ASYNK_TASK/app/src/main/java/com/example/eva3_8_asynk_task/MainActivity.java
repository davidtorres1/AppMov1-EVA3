package com.example.eva3_8_asynk_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtV = findViewById(R.id.textView);
        MiClaseAsin miClase = new MiClaseAsin();
        miClase.execute(20, 500);
    }

    class MiClaseAsin extends AsyncTask<Integer, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtV.setText("AsyncTask Start ");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int iVeces = integers[0];
            int iDemora = integers[1];
            for (int i = 0; i < iVeces; i++) {
                try {
                    Thread.sleep(iDemora);
                    publishProgress(i + " - ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "AsyncTask End";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtV.append(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtV.append(s);
        }
    }

}




