package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Basketball extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketball);
    }
    int a=0;
    int b=0;
    public void myClickone (View v) {
        TextView out = findViewById(R.id.textView3);
//        if(v.getId()==R.id.button2){
            a = a + 1;
//        }else{
//            b = b + 1;
//        }
        out.setText("TeamA得分：" + a + "分");
    }
    public void myClicktwo (View v) {
        TextView out = findViewById(R.id.textView3);
        a = a + 2;
        out.setText("TeamA得分：" + a + "分");
    }
    public void myClickthree (View v) {
        TextView out = findViewById(R.id.textView3);
        a = a + 3;
        out.setText("TeamA得分：" + a + "分");
    }
    public void myClick1 (View v) {
        TextView out = findViewById(R.id.textView);
        b = b + 1;
        out.setText("TeamB得分：" + b + "分");
    }
    public void myClick2 (View v) {
        TextView out = findViewById(R.id.textView);
        b = b + 2;
        out.setText("TeamB得分：" + b + "分");
    }
    public void myClick3 (View v) {
        TextView out = findViewById(R.id.textView);
        b = b + 3;
        out.setText("TeamB得分：" + b + "分");
    }
    public void myClickreset (View v) {
        TextView out = findViewById(R.id.textView3);
        TextView inp = findViewById(R.id.textView);
        a=0;
        b=0;
        out.setText("TeamA得分："+a+"分");
        inp.setText("TeamB得分："+b+"分");
    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String scorea=((TextView)findViewById(R.id.textView3)).getText().toString();
        outState.putString("ascore",scorea);
        String scoreb=((TextView)findViewById(R.id.textView)).getText().toString();;
        outState.putString("bscore",scoreb);

    }
    protected void onRestoreInstanceState(Bundle saved){
        super.onRestoreInstanceState(saved);
        String scorea=saved.getString("ascore");
        String scoreb=saved.getString("bscore");
        ((TextView)findViewById(R.id.textView3)).setText(scorea);
        ((TextView)findViewById(R.id.textView)).setText(scoreb);
    }

}
