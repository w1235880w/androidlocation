package com.example.zhiyin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstFragment extends Fragment {
    private Button teacher,pay;


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
        return view;
    }

    //    public void onViewCreated(View view, Bundle savedInstanceState){
//        super.onViewCreated(view,savedInstanceState);
//    public void onClickTeacher(View view) {
//        Intent i = new Intent(getActivity(), Teacher.class);
//        startActivity(i);
//    }

//}
    
    public void onClickPay(View v) {
        Intent i = new Intent(getActivity(),Pay.class);
        startActivity(i);
    }

}
