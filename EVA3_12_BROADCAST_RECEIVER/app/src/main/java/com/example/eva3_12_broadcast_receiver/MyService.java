package com.example.eva3_12_broadcast_receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    Thread tHilo;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent inMensa = new Intent("MI_SERVICIO");
        inMensa.putExtra("MENSAJE", "onCreate");
        sendBroadcast(inMensa);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Intent inMensa = new Intent("MI_SERVICIO");
        inMensa.putExtra("MENSAJE", "onStart");
        sendBroadcast(inMensa);
        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Intent inMensa2 = new Intent("MI_SERVICIO");
                        inMensa2.putExtra("MENSAJE", "onStart");
                        sendBroadcast(inMensa2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tHilo.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent inMensa = new Intent("MI_SERVICIO");
        inMensa.putExtra("MENSAJE", "onDestroy");
        sendBroadcast(inMensa);
        tHilo.interrupt();
    }


}
