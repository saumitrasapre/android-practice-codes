package com.example.listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class Custom_Adapter extends ArrayAdapter <String> {

    public Custom_Adapter(@NonNull Context context, String dogs[]) {


        super(context,R.layout.custom_row, dogs);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater= LayoutInflater.from(getContext());
        View customView= myInflater.inflate(R.layout.custom_row,parent,false);

        String singleDogItem=getItem(position);
        TextView listText=customView.findViewById(R.id.listText);
        ImageView listImage=customView.findViewById(R.id.listImage);
        listImage.setImageResource(R.drawable.catstorm2);

        listText.setText(singleDogItem);

        return customView;

    }
}
