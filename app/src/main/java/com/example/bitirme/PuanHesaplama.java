package com.example.bitirme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PuanHesaplama extends AppCompatActivity {
    RecyclerView rcv;
    TextView TYTpuanı,SAYpuanı,EApuanı,SozPuanı,YTYTpuanı,YSAYpuanı,YEApuanı,YSozPuanı,OOBP;
    EditText ortaogretimpuanı;
    Button puanhesapla;
    double[] puanlar={0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puan_hesaplama);

        rcv = findViewById(R.id.numbercycle);
        String[] s={"TYT TÜRKÇE NETİ","TYT SOSYAL NETİ","TYT MATEMATİK NETİ","TYT FEN NETİ",
                "AYT TARİH-2 NETİ","AYT COĞRAFYA-2 NETİ","AYT FELS.GR. NETİ","AYT D.KÜLTÜRÜ NETİ",
                "AYT EDEBİYAT NETİ","AYT TARİH-1 NETİ","AYT COĞRAFYA-1 NETİ","AYT MATEMATİK NETİ",
                "AYT FİZİK NETİ","AYT KİMYA NETİ","AYT BİYOLOJİ NETİ"};
        rcv.setLayoutManager(new GridLayoutManager(this, 4));
        final numberpickeradaptor adaptor=new numberpickeradaptor(s, this);
        rcv.setAdapter(adaptor);
        TYTpuanı=findViewById(R.id.TYT);
        SAYpuanı=findViewById(R.id.SAY);
        EApuanı=findViewById(R.id.EA);
        SozPuanı=findViewById(R.id.SOZ);
        YTYTpuanı=findViewById(R.id.YTYT);
        YSAYpuanı=findViewById(R.id.YSAY);
        YEApuanı=findViewById(R.id.YEA);
        YSozPuanı=findViewById(R.id.YSOZ);
        OOBP=findViewById(R.id.oobptxt);
        ortaogretimpuanı=findViewById(R.id.okulpuanı);
        OOBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bilgipenceresi pencere=new Bilgipenceresi();
                pencere.show(PuanHesaplama.this,0);
            }
        });



        puanhesapla=findViewById(R.id.puanhesapla);
        puanhesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int o_puan=30;
                final Integer[] netler=adaptor.getPickerValues();
                if(ortaogretimpuanı.getText().toString().equals("")) {
                    o_puan=30;
                    Toast.makeText(PuanHesaplama.this, "Ortaöğretim Başarı Puanı Girmediğiniz İçin , " +
                            "Endüşük değer olan 30 olarak alınacaktır", Toast.LENGTH_SHORT).show();

                }
                else o_puan=Integer.valueOf(ortaogretimpuanı.getText().toString());
                if(o_puan<30 || o_puan>60){
                    o_puan=30;
                    Toast.makeText(PuanHesaplama.this, "Ortaöğretim Başarı Puanı 30 dan küçük veya 60 dan büyük olamaz , " +
                            "Endüşük değer olan 30 olarak alınacaktır", Toast.LENGTH_SHORT).show();

                }


                puanlar[0]=((netler[0]+netler[2])*3.3+(netler[3]+netler[1])*3.4)/4+100;
                puanlar[1]=(puanlar[0]*1.6+netler[11]*3+netler[12]*2.85+(netler[13]+netler[14])*3.07)/4+60;
                puanlar[2]=(puanlar[0]*1.6+netler[9]*2.8+netler[10]*3.33+(netler[8]+netler[11])*3)/4+60;
                puanlar[3]=(puanlar[0]*1.6+netler[9]*2.8+netler[10]*3.33+netler[8]*3+netler[4]*2.91+netler[5]*2.91+netler[6]*3+netler[7]*3.33)/4+60;
                TYTpuanı.setText("TYT :"+String.format("%.2f",puanlar[0]));
                SAYpuanı.setText("SAY :"+String.format("%.2f",puanlar[1]));
                EApuanı.setText("EA :"+String.format("%.2f",puanlar[2]));
                SozPuanı.setText("SOZ :"+String.format("%.2f",puanlar[3]));
                YTYTpuanı.setText("Y_TYT :"+String.format("%.2f",(puanlar[0]+o_puan)));
                YSAYpuanı.setText("Y_SAY :"+String.format("%.2f",(puanlar[1]+o_puan)));
                YEApuanı.setText("Y_EA :"+String.format("%.2f",(puanlar[2]+o_puan)));
                YSozPuanı.setText("Y_SOZ:"+String.format("%.2f",(puanlar[3]+o_puan)));

            }}
        );
    }
    }

