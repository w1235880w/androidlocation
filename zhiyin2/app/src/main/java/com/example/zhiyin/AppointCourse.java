package com.example.zhiyin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AppointCourse extends AppCompatActivity {
    private Button appoint;
    private EditText date;
    private EditText time;
    public static final int REQUESTCODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_course);
        appoint=(Button)findViewById(R.id.appoint);
        date=(EditText)findViewById(R.id.editTextday);
        time=(EditText)findViewById(R.id.editTexttime);
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appointdate = date.getText().toString();
                String appointtime=time.getText().toString();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("appointdate",appointdate);
                bundle.putString("appointtime",appointtime);
                bundle.putString("stuname","承墨");
                intent.putExtras(bundle);
                intent.setClass(AppointCourse.this,MainActivity.class);
                Toast.makeText(getBaseContext(),"预约成功",Toast.LENGTH_SHORT).show();
                startActivityForResult(intent,1);
            }
        });
    }

    public void onClick(View v){
        Toast.makeText(AppointCourse.this, "预约成功", Toast.LENGTH_SHORT).show();

    }
}
