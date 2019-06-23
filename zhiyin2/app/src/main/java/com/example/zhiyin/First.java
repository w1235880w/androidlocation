package com.example.zhiyin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class First extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;  //延迟3秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(First.this, Login.class);
                First.this.startActivity(intent);
                First.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
