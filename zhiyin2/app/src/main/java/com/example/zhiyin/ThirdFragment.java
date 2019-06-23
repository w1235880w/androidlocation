package com.example.zhiyin;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private Button exitlogin,appointcourse;
    private TextView username;
    String stuName="";


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third,container,false);
        exitlogin=(Button)view.findViewById(R.id.exitlogin);
        exitlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });
        appointcourse=(Button)view.findViewById(R.id.appointcourse);
        appointcourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AppointCourse.class);
                startActivity(intent);
            }
        });
        username=(TextView)view.findViewById(R.id.username);
        stuName= String.valueOf(getActivity().getIntent().getStringExtra("stuname"));
        username.setText(stuName);
        return view;
        }

}
