package com.example.bitirme;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Bilgipenceresi {
    int[] bilgiler={R.drawable.oobp1,R.drawable.ic_info_black_24dp};
    public Bilgipenceresi( ) {
    }

    public void show(Context context,int i) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.bilgipenceresi);
        Button kapat = (Button) dialog.findViewById(R.id.pencereyikapat);

        ImageView ivResim = (ImageView) dialog.findViewById(R.id.Bilgiimage);
        ivResim.setImageResource(bilgiler[i]);
       kapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }
}

