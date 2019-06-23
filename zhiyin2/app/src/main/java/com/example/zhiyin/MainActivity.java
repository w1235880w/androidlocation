package com.example.zhiyin;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.zhiyin.PageAdapter;
import com.example.zhiyin.R;

public class MainActivity extends AppCompatActivity {
    private String date,time;
    private TextView appointdate,appointtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        Bundle bundle =  this.getIntent().getExtras();
        Bundle bundle2 =  this.getIntent().getExtras();
        Bundle bundle3 =  this.getIntent().getExtras();
//        date=bundle.getString("appointdate","");
//        time=bundle.getString("appointtime","");
//        appointdate=(TextView)findViewById(R.id.appointdate);
//        appointtime=(TextView)findViewById(R.id.editTexttime);
//        appointdate.setText(date);
//        appointtime.setText(time);
    }
}
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        Bundle bundle =  this.getIntent().getExtras();
//        date=bundle.getString("appointdate");
//        this.date=date;
//    }
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        Bundle bundle =  this.getIntent().getExtras();
//        time=bundle.getString("appointtime");
//        this.time = time;
//    }
//}
