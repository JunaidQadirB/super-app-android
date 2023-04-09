package com.moonbear.carmarz.userschat.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moonbear.carmarz.R;

public class Alertviewholder extends RecyclerView.ViewHolder {


    public TextView message,datetxt;
    public View view;

    public Alertviewholder(View itemView) {
        super(itemView);
        view = itemView;
        this.message = view.findViewById(R.id.message);
        this.datetxt = view.findViewById(R.id.datetxt);
    }

}