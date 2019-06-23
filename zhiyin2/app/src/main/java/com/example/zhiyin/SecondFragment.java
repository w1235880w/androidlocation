package com.example.zhiyin;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private TextView appointdate;
    private TextView appointtime;
    String date="";
    String time="";
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        appointdate=(TextView)view.findViewById(R.id.appointdate);
        appointtime=(TextView)view.findViewById(R.id.appointtime);
        date= String.valueOf(getActivity().getIntent().getStringExtra("appointdate"));
        appointdate.setText(date);
        time= String.valueOf(getActivity().getIntent().getStringExtra("appointtime"));
        appointtime.setText(time);
        return view;
    }

}
