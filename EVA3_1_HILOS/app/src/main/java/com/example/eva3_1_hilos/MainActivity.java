package com.example.eva3_1_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtV;
    Thread tHilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtV = findViewById(R.id.textView);

        tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while(true) {
                    try {
                        Thread.sleep(1000);
                        Log.wtf("Hilo", "i = "+ i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.wtf("hilo interrumpido", "interrupcion");
                        break;
                    }
                }
            }
        };
        tHilo.start();
        Runnable rRun = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true) {
                    try {
                        Thread.sleep(1000);
                        Log.wtf("Runnable", "i = "+ i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread rHilo = new Thread(rRun);
        rHilo.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tHilo.interrupt();

    }
}
