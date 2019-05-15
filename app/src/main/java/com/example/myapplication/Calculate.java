package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Calculate extends AppCompatActivity {
    private final String TAG="Calculate";

    EditText DOLLARTEXT;
    EditText POUNDTEXT;
    EditText WONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate);
        Intent intent=getIntent();
        float dollar2=intent.getFloatExtra("dollarkey",0.0f);
        float pound2=intent.getFloatExtra("poundkey",0.0f);
        float won2=intent.getFloatExtra("wonkey",0.0f);

        Log.i(TAG,"onCreate:dollar2="+dollar2);
        Log.i(TAG,"onCreate:pound2="+pound2);
        Log.i(TAG,"onCreate:won2="+won2);

        DOLLARTEXT=(EditText)findViewById(R.id.DOLLAR);
        POUNDTEXT=(EditText)findViewById(R.id.POUND);
        WONTEXT=(EditText)findViewById(R.id.WON);

        DOLLARTEXT.setText(String.valueOf(dollar2));
        POUNDTEXT.setText(String.valueOf(pound2));
        WONTEXT.setText(String.valueOf(won2));

    }
    public void save(View v){
        Log.i(TAG,"save:");
        float newDOLLAR=Float.parseFloat(DOLLARTEXT.getText().toString());
        float newPOUND=Float.parseFloat(POUNDTEXT.getText().toString());
        float newWON=Float.parseFloat(WONTEXT.getText().toString());

        Log.i(TAG,"save:获取到新的值");
        Log.i(TAG,"oncreate:newDOLLAR="+newDOLLAR);
        Log.i(TAG,"oncreate:newPOUND="+newPOUND);
        Log.i(TAG,"oncreate:newWON="+newWON);

        Intent intent=getIntent();
        Bundle bdl=new Bundle();
        bdl.putFloat("keydollar",newDOLLAR);
        bdl.putFloat("keypound",newPOUND);
        bdl.putFloat("keywon",newWON);
        intent.putExtras(bdl);
        setResult(2,intent);
        finish();
    }
}
