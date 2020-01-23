package com.example.memey;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopSectionFragment extends Fragment {

    EditText toptext;
    EditText bottomtext;
    Button btngo;

    public interface TopSectionListener //This is done to ensure that the method inside this interface is compulsorily implemented
                                        //by whatever class implements this interface
    {
        void createMeme(String top, String bottom); //This is the method which will be compulsorily overridden by whatever class
                                                    //implements it
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try
        {
            activityCommander= (TopSectionListener)getActivity();
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString());
        }
    }

    TopSectionListener activityCommander; //activityCommander is the name of the reference variable of the interface (Interface object)




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.top_text_fragment,container,false);
        toptext=view.findViewById(R.id.top_text_input);
        bottomtext=view.findViewById(R.id.bottom_text_input);
        btngo=view.findViewById(R.id.go_button);

        btngo.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {

                        activityCommander.createMeme(toptext.getText().toString(),bottomtext.getText().toString());
                    }
                }
        );


        return view;
    }
}
