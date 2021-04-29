package com.example.bitirme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BolumARA extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    int secilenradiobuttonu;
    Database db = new Database(this);
    private Spinner spin;
    RecyclerView rcvv;
    Button arabutonu;

    LinearLayout puanlar;
    EditText min,max;
    ArrayAdapter<String> dataAdapter;
    private String secilenBolum="";

    String[] list={"lütfen puan türü seçiniz"};
    ArrayList<String> TYT_Bolumleri=new ArrayList<String>();
    ArrayList<String> EA_Bolumleri=new ArrayList<String>();
    ArrayList<String> SAY_Bolumleri=new ArrayList<String>();
    ArrayList<String> SOZ_Bolumleri=new ArrayList<String>();

    public  ArrayList<String>  dosyadanbolumoku(String s)
    { ArrayList<String> Bolumisimleri=new ArrayList<>();
        BufferedReader bolumreader = null;
        try {
            bolumreader = new BufferedReader(new InputStreamReader(getAssets().open(s), "UTF-8"));
            String mLine;
            int i=0;
            while (((mLine = bolumreader.readLine()) != null ) ) {
                Bolumisimleri.add(mLine);
                System.out.print(mLine);
                System.out.println(Bolumisimleri.get(i));

                System.out.println(i);
                i++;

            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (bolumreader != null) {
                try {
                    bolumreader.close();
                } catch (IOException e) {
                }
            }
        }


        return Bolumisimleri;

    }

    public void uyari(int i)

    {
        Toast.makeText(BolumARA.this, "İşlem tamam "+ String.valueOf(i), Toast.LENGTH_SHORT).show();}
    public void spinlistele(int id)
    { secilenradiobuttonu=id;
        if(id==R.id.TYT_puan)
        {dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TYT_Bolumleri);}
        else if(id==R.id.Say_puan)
            dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SAY_Bolumleri);
        else if(id==R.id.Ea_puan)
            dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EA_Bolumleri);
        else if(id==R.id.Soz_puan)
            dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SOZ_Bolumleri);
        else
            dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BolumARA.this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                secilenBolum=spin.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void recyclelistele(int min,int max)
    {  Bolum BosBolum=new Bolum();
        BosBolum.setUniversite_adi("Aradığnız kritere uygun bölüm bulunamadı");
        BosBolum.setSehir("Bilinmiyor");
        BosBolum.setPuan("-1");
        System.out.println(secilenBolum);
        ArrayList<Bolum> listelenecekler=db.satıroku(secilenBolum,min,max,secilenradiobuttonu);

        if (listelenecekler.size()>0)
        { bolum_liste_adaptoru adaptor=new bolum_liste_adaptoru(listelenecekler, this);
            rcvv.setAdapter(adaptor);
        }
        else
        {listelenecekler.add(BosBolum);
            Toast.makeText(BolumARA.this, String.valueOf(listelenecekler.size()), Toast.LENGTH_SHORT).show();
            bolum_liste_adaptoru adaptor=new bolum_liste_adaptoru(listelenecekler, this);
            rcvv.setAdapter(adaptor);
        }
    }


    private RadioGroup puanturugrup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolum_ara);

        TYT_Bolumleri=dosyadanbolumoku("datatyt.txt");
        SAY_Bolumleri=dosyadanbolumoku("datasay.txt");
        EA_Bolumleri=dosyadanbolumoku("dataea.txt");
        SOZ_Bolumleri=dosyadanbolumoku("datasoz.txt");
        puanlar=findViewById(R.id.puanlar);



        rcvv=findViewById(R.id.recycle);
        arabutonu=findViewById(R.id.ARA);


        min=(findViewById(R.id.minpuan));
        max=(findViewById(R.id.maxpuan));
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcvv.setLayoutManager(layoutManager);



        Thread tytyükle = new Thread() {
            public void run() {
                BufferedReader reader = null;
                try {

                    reader = new BufferedReader(new InputStreamReader(getAssets().open("TYT.txt"), "UTF-8"));
                    String mLine;
                    while (((mLine = reader.readLine()) != null)) {
                        db.verigir(mLine);
                    }


                } catch (IOException e) {
                    //log the exception
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        };
        if (db.satıroku("listesonu",0,750,R.id.TYT_puan).size()!=1) {
            db.deletedatabase(R.id.TYT_puan);
            tytyükle.start();
            uyari(5);}

        Thread sayyükle = new Thread() {
            public void run() {
                BufferedReader reader = null;
                try {

                    reader = new BufferedReader(new InputStreamReader(getAssets().open("SAY.txt"), "UTF-8"));
                    String mLine;
                    while (((mLine = reader.readLine()) != null)) {
                        db.verigir(mLine);
                    }

                } catch (IOException e) {
                    //log the exception
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }
                }
                System.out.println("veri yüklendi");
            }
        };
        if (db.satıroku("listesonu",0,750,R.id.Say_puan).size()!=1) {
            db.deletedatabase(R.id.Say_puan);
            sayyükle.start();
            //  uyari(db.satıroku("listesonu",0,750,R.id.Say_puan).size());
        }
        Thread eayükle = new Thread() {
            public void run() {
                BufferedReader reader = null;
                try {

                    reader = new BufferedReader(new InputStreamReader(getAssets().open("EA.txt"), "UTF-8"));
                    String mLine;
                    while (((mLine = reader.readLine()) != null ) ) {
                        db.verigir(mLine);
                    }
                } catch (IOException e) {
                    //log the exception
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }
                }

            }
        };
        if (db.satıroku("listesonu",0,750,R.id.Ea_puan).size()!=1) {
            db.deletedatabase(R.id.Ea_puan);
            eayükle.start();}
        Thread sozyükle = new Thread() {
            public void run() {
                BufferedReader reader = null;
                try {

                    reader = new BufferedReader(new InputStreamReader(getAssets().open("SOZ.txt"), "UTF-8"));
                    String mLine;
                    while (((mLine = reader.readLine()) != null ) ) {
                        db.verigir(mLine);
                    }
                } catch (IOException e) {
                    //log the exception
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                    }
                }

            }
        };
        if (db.satıroku("listesonu",0,750,R.id.Soz_puan).size()!=1) {
            db.deletedatabase(R.id.Soz_puan);
            sozyükle.start();}
        puanturugrup= findViewById(R.id.radioGroup);
        spin = findViewById(R.id.Bolumler);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);



        arabutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int minumum=0;
                int maximum=600;
                if (!(min.getText().toString().equals(""))) minumum=Integer.valueOf(min.getText().toString());
                if (!(max.getText().toString().equals("")))  maximum=Integer.valueOf(max.getText().toString());
                if (minumum<=maximum && minumum<600 && maximum>0)
                    recyclelistele(minumum,maximum);
                    //System.out.println(minumum+"-"+maximum);
                else
                    Toast.makeText(BolumARA.this, "Hatalı giriş yapıldı", Toast.LENGTH_SHORT).show();


            }
        });
        System.out.println(secilenBolum);
        puanturugrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){


                spinlistele(checkedId);
                puanlar.setVisibility(View.VISIBLE);
                spin.setVisibility(View.VISIBLE);
                arabutonu.setVisibility(View.VISIBLE);
            }



        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    }

