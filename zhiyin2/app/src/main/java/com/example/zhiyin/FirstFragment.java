package com.example.zhiyin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstFragment extends Fragment {
    private Button teacher,pay,guitar,other,course,market,june;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first,null);
        teacher=(Button)view.findViewById(R.id.buttonteacher);
        teacher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Teacher.class);
                startActivity(intent);
            }
        });
        pay=(Button)view.findViewById(R.id.buttonpay);
        pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Pay.class);
                startActivity(intent);
            }
        });
        guitar=(Button)view.findViewById(R.id.buttonguitar);
        guitar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Guitar.class);
                startActivity(intent);
            }
        });
        other=(Button)view.findViewById(R.id.buttonother);
        other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Other.class);
                startActivity(intent);
            }
        });
        course=(Button)view.findViewById(R.id.buttoncourse);
        course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Cour.class);
                startActivity(intent);
            }
        });
        market=(Button)view.findViewById(R.id.buttonmarket);
        market.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Market.class);
                startActivity(intent);
            }
        });
        june=(Button)view.findViewById(R.id.buttonjune);
        june.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),June.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
