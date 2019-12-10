package com.example.eva3_7_image_post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imagen;
    String URL = "https://www.pinpng.com/picture/bxbTxx_bb-8-star-wars-download-png-image-star/";
    Bitmap bitmap;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imagen.setImageBitmap(bitmap);
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imagen.setImageBitmap(bitmap);
        }
    };
    Thread hilo = new Thread(){
        @Override
        public void run() {
            super.run();
            bitmap = cargarImagen(URL);
            handler.post(runnable);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = findViewById(R.id.imageView);

        hilo.start();
    }

    private Bitmap cargarImagen(String url){
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

