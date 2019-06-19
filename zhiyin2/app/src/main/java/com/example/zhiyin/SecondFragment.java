package com.example.zhiyin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
//        View view = inflater.inflate(R.layout.fragment_second,container,false);
//        TextView textView = (TextView)view.findViewById(R.id.first);
//        Bundle bundle =this.getArguments();//得到从Activity传来的数据
//        String mess = null;
//        if(bundle!=null){
//            mess = bundle.getString("data"+"time");
//        }
//        textView.setText(mess);
//        return view;
    }

}
