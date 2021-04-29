package com.example.bitirme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button puanhesapla,sıralama,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        puanhesapla=findViewById(R.id.Puan_btn);
        puanhesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( new Intent(MainActivity.this, PuanHesaplama.class));
                startActivity(intent);
            }
        });
        sıralama=findViewById(R.id.Sıralama_btn);
        sıralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( new Intent(MainActivity.this, BolumARA.class));
                startActivity(intent);
            }
        });
        image=findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bilgipenceresi pencere=new Bilgipenceresi();
                pencere.show(MainActivity.this,0);
            }
        });

    }
}
