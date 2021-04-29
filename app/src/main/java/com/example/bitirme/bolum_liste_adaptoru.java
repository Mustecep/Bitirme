package com.example.bitirme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bolum_liste_adaptoru extends RecyclerView.Adapter<bolum_liste_adaptoru.ViewHolder> {

    ArrayList<Bolum> puan_sirali_liste= new ArrayList<Bolum>();
    LayoutInflater layoutInflater;
    Context context;

    public bolum_liste_adaptoru(ArrayList<Bolum> puan_sirali_liste, BolumARA context) {
        this.puan_sirali_liste = puan_sirali_liste;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    layoutInflater=layoutInflater.from(context);
    View v=layoutInflater.inflate(R.layout.puan_sirali_liste_layout,parent,false);
    ViewHolder vh= new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.universite_adı.setText(puan_sirali_liste.get(position).getUniversite_adi());
        holder.sehir_adı.setText("Açıklama : "+puan_sirali_liste.get(position).getSehir());
        if (Integer.valueOf(puan_sirali_liste.get(position).getPuan())==0)
        holder.taban_puan.setText("Kontenjan Dolmadı!!!");
        else if  (Integer.valueOf(puan_sirali_liste.get(position).getPuan())<0)
            holder.taban_puan.setText("-------");
        else holder.taban_puan.setText("Taban Puan : "+puan_sirali_liste.get(position).getPuan());
        if(position % 2==1)
        holder.layout.setBackgroundColor(0x22000062);
    else   holder.layout.setBackgroundColor(0x99000062);

    }

    @Override
    public int getItemCount() {
        return puan_sirali_liste.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView universite_adı,sehir_adı,taban_puan;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            universite_adı=itemView.findViewById(R.id.Universite_adi);
            sehir_adı=itemView.findViewById(R.id.Sehir_adi);
            taban_puan=itemView.findViewById(R.id.Taban_puan);
            layout=itemView.findViewById(R.id.linear);
        }
    }
}
