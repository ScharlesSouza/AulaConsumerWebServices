package com.example.labmacmini01.androidwebservices.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmacmini01.androidwebservices.R;

/**
 * Created by labmacmini01 on 02/05/18.
 */


public class ItemHolder extends RecyclerView.ViewHolder {




     ImageView imageCarro = null;
     TextView textoNome = null;
     TextView textoTipo = null;
     TextView textoDesc = null;


    public ItemHolder(View view){
        super(view);

        imageCarro = (ImageView)view.findViewById(R.id.imageViewCarro);
        textoNome = (TextView)view.findViewById(R.id.textViewNome);
        textoTipo = (TextView)view.findViewById(R.id.textViewTipo);
        textoDesc = (TextView)view.findViewById(R.id.textViewDesc);


    }



    public ImageView getImageCarro() {
        return imageCarro;
    }

    public TextView getTextoNome() {
        return textoNome;
    }

    public TextView getTextoTipo() {
        return textoTipo;
    }

    public TextView getTextoDesc() {
        return textoDesc;
    }

}
