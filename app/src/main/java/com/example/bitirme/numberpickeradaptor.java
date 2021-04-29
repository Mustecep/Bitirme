package com.example.bitirme;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class numberpickeradaptor extends RecyclerView.Adapter<numberpickeradaptor.ViewHolder> {

    LayoutInflater layoutInflater;
    Context context;
    String[] testler;
    Integer[] netler1={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Integer[] netler2={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

  public OnNumberPickerValueChangeListener valueChangeListener1=new OnNumberPickerValueChangeListener() {
      @Override
      public void onValueChange(int position, int value) {
              netler1[position]=4*value;
      }
  };
    public OnNumberPickerValueChangeListener valueChangeListener2=new OnNumberPickerValueChangeListener() {
        @Override
        public void onValueChange(int position, int value) {

            netler2[position]=value;
        }
    };

    public numberpickeradaptor(String[] liste, PuanHesaplama context) {
        testler=liste;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=layoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.numberpicker,parent,false);
        ViewHolder vh= new ViewHolder(v,valueChangeListener1,valueChangeListener2);
        return vh;

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GradientDrawable gd1 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xaaddddaa,0xffffffff,0xaaddddaa});
        gd1.setCornerRadius(0f);
        GradientDrawable gd2 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0x55555599,0xffffffff,0x55555599});
        gd2.setCornerRadius(0f);
        GradientDrawable gd3 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0x3300aa55,0xffffffff,0x3300aa55});
        gd3.setCornerRadius(0f);
        GradientDrawable gd4 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0x660000ff,0xffffffff,0x660000ff});
        gd4.setCornerRadius(0f);
        GradientDrawable gd5 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0x447700ff,0xffffffff,0x447700ff});
        gd5.setCornerRadius(0f);
     int renk1=0xaaddddaa,renk2=0x55555599,renk3=0x3300aa55,renk4=0x660000ff,renk5=0x447700ff;
        final String[] kusuratlar = {"00", "25", "50", "75"};
        final String[] nokta={"."};
        holder.testname.setText(testler[position]);
        holder.onlar.setMinValue(0);
        holder.onlar.setMaxValue(40);
        holder.kusurat.setMinValue(0);
        holder.kusurat.setMaxValue(3);
        holder.nokta.setDisplayedValues(nokta);
        holder.kusurat.setDisplayedValues(kusuratlar);

        holder.layout.setBackgroundDrawable(gd1);

        switch (position){

            case 1:
                holder.onlar.setMaxValue(20);
                break;
            case 3:
                holder.onlar.setMaxValue(20);
                break;
            case 4:
                holder.puanturu.setText("SOZ Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd2);
                holder.onlar.setMaxValue(11);

                break;
            case 5:
                holder.puanturu.setText("SOZ Puanlarını etkiler!");
                holder.onlar.setMaxValue(11);
                holder.layout.setBackgroundDrawable(gd2);
                break;
            case 6:
                holder.puanturu.setText("SOZ Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd2);
                holder.onlar.setMaxValue(12);

                break;
            case 7:
                holder.puanturu.setText("SOZ Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd2);                holder.onlar.setMaxValue(6);
                break;
            case 8:
                holder.puanturu.setText("SOZ-EA Puanlarını etkiler!");
                holder.onlar.setMaxValue(24);
                holder.layout.setBackgroundDrawable(gd3);                break;

            case 9:
                holder.puanturu.setText("SOZ-EA Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd3);                holder.onlar.setMaxValue(10);

                break;
            case 10:
                holder.puanturu.setText("SOZ-EA Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd3);                holder.onlar.setMaxValue(6);

                break;
            case 11:
                holder.puanturu.setText("SAY-EA Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd5);
                break;
            case 12:
                holder.puanturu.setText("SAY Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd4);
                holder.onlar.setMaxValue(14);
                 break;
            case 13:
                holder.puanturu.setText("SAY Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd4);
                holder.onlar.setMaxValue(13);
                break;
            case 14:
                holder.puanturu.setText("SAY Puanlarını etkiler!");
                holder.layout.setBackgroundDrawable(gd4);
                holder.onlar.setMaxValue(13);
                break;

        }





    }

    @Override
    public int getItemCount() {
        return testler.length;
    }
    public Integer[] getPickerValues(){
        Integer[] netler={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=0;i<15;i++)
            netler[i]=netler1[i]+netler2[i];
        return netler;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView testname,puanturu;
        LinearLayout layout;
        NumberPicker onlar,kusurat,nokta;

        public ViewHolder(@NonNull View itemView,final OnNumberPickerValueChangeListener listener1,final OnNumberPickerValueChangeListener listener2) {
            super(itemView);
           onlar=itemView.findViewById(R.id.onlar);
            kusurat=itemView.findViewById(R.id.kusurat);
            nokta=itemView.findViewById(R.id.nokta);
            testname=itemView.findViewById(R.id.testname);
            puanturu=itemView.findViewById(R.id.puanturu);
            layout=itemView.findViewById(R.id.mainlayout);
            onlar.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        listener1.onValueChange(getAbsoluteAdapterPosition(),newVal);
                    }
            });
            kusurat.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    listener2.onValueChange(getAbsoluteAdapterPosition(),newVal);
                }
            });

        }
    }
    interface OnNumberPickerValueChangeListener{
        void onValueChange(int position, int value);
    }
}

