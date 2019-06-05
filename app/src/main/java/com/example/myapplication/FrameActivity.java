package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FrameActivity extends FragmentActivity {
    private Fragment mFragment[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome,rbtFunc,rbtSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mFragment=new Fragment[3];
        fragmentManager=getSupportFragmentManager();
        mFragment[0]=fragmentManager.findFragmentById(R.id.fragment_main);
        mFragment[1]=fragmentManager.findFragmentById(R.id.fragment_func);
        mFragment[2]=fragmentManager.findFragmentById(R.id.fragment_setting);
        fragmentTransaction=fragmentManager.beginTransaction().hide(mFragment[0]).hide(mFragment[1]).hide(mFragment[2]);
        fragmentTransaction.show(mFragment[0]).commit();

        rbtHome=(RadioButton)findViewById(R.id.radioHome);
        rbtFunc=(RadioButton)findViewById(R.id.radioFunc);
        rbtSetting=(RadioButton)findViewById(R.id.radioSetting);
        rbtHome.setBackgroundResource(R.drawable.shape);
        radioGroup=(RadioGroup)findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
           public void onCheckedChanged(RadioGroup group,int checkedId){
               fragmentTransaction=fragmentManager.beginTransaction().hide(mFragment[0]).hide(mFragment[1]).hide(mFragment[2]);
               rbtHome.setBackgroundResource(R.drawable.shape2);
               rbtFunc.setBackgroundResource(R.drawable.shape2);
               rbtSetting.setBackgroundResource(R.drawable.shape2);
               switch (checkedId){
                   case R.id.radioHome:fragmentTransaction.show(mFragment[0]).commit();
                       rbtHome.setBackgroundResource(R.drawable.shape);
                       break;
                   case R.id.radioFunc:fragmentTransaction.show(mFragment[1]).commit();
                       rbtFunc.setBackgroundResource(R.drawable.shape);
                       break;
                   case R.id.radioSetting:fragmentTransaction.show(mFragment[2]).commit();
                       rbtSetting.setBackgroundResource(R.drawable.shape);
                       break;
                       default:break;
               }
           }
        });

    }
}
