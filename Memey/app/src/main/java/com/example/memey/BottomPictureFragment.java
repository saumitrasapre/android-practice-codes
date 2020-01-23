package com.example.memey;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.TextView;

public class BottomPictureFragment extends Fragment {

    private static TextView txttp;
    private static TextView txtbtm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_picture_fragment,container,false);

        txttp=view.findViewById(R.id.topText);
        txtbtm=view.findViewById(R.id.bottomText);

        return view;
    }
    void setMemeText(String top,String bottom)
    {
        txttp.setText(top);
        txtbtm.setText(bottom);

    }
}
