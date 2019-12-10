package com.example.eva3_3_hilo_ejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    ImageView imgV;
    Bitmap imagen = null;
    Handler handler = new Handler();


    Thread hilo = new Thread() {
        @Override
        public void run() {
            super.run();

            imagen = cargarImagen("https://www.pinpng.com/picture/bxbTxx_bb-8-star-wars-download-png-image-star/");
            handler.post(rModoficaUI);
        }
    };

    Runnable rModoficaUI = new Runnable() {
        @Override
        public void run() {
            //AQUI VA EL TRABAJO DE MODIFICAR LA UI
            imgV.setImageBitmap(imagen);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgV = findViewById(R.id.imageView);
        hilo.start();
    }

    private Bitmap cargarImagen(String url) {
        Bitmap imagen = null;
        try {
            InputStream input = (InputStream) new URL(url).getContent();
            imagen = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imagen;
    }
}
