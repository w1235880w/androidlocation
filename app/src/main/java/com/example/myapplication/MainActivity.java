package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        TextView out=findViewById(R.id.textView2);
        EditText inp=findViewById(R.id.editText);
        String str=inp.getText().toString();
        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(this);
    }
    public static String keepTwo(double b) {
        DecimalFormat format = new DecimalFormat("#0.00");
        String str = format.format(b);
        return str;
    }
    @Override
    public void onClick(View v){
        EditText inp=findViewById(R.id.editText);
        String str=inp.getText().toString();
        TextView out=findViewById(R.id.textView2);
        if (inp == null) {
            out.setText("不行哦");
        }else if(inp.equals("")){
            out.setText("不可以哦");
        }
        double c=Double.valueOf(str);
        double a=c*1.8+32;
        out.setText("结果为："+keepTwo(a));

        }

}
