package com.example.zhiyin;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.zhiyin.PageAdapter;
import com.example.zhiyin.R;

public class MainActivity extends AppCompatActivity {
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        Intent intent=getIntent();
//        Bundle data=intent.getExtras();

//        String i = getIntent().getStringExtra("stuname");
//        username = (TextView) findViewById(R.id.username);
//        username.setText("i");
    }
}
