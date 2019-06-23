package com.example.zhiyin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity {
    private Button btnlogin;

    private Button btnzc;

    private EditText stuusername;

    private EditText stupassword;

    private String ObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        stupassword = (EditText) findViewById(R.id.edittextstupass);
        stuusername = (EditText) findViewById(R.id.edittextstuname);
        btnlogin = (Button) findViewById(R.id.buttonlogin);
        btnzc = (Button) findViewById(R.id.buttonzc);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuname =  stuusername.getText().toString();
                Intent intent2=new Intent();
                Bundle bundle2=new Bundle();
                bundle2.putString("stuname",stuname);
                intent2.putExtras(bundle2);
                intent2.setClass(Login.this,MainActivity.class);
                startActivityForResult(intent2,1);
            }
        });
        btnzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuname =  stuusername.getText().toString();
                Intent intent3=new Intent();
                Bundle bundle3=new Bundle();
                bundle3.putString("stuname",stuname);
                intent3.putExtras(bundle3);
                intent3.setClass(Login.this,MainActivity.class);
                startActivityForResult(intent3,1);
            }
        });
    }
    public void btnLogin(View v) {
        final String username = stuusername.getText().toString();
        final String password = stupassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
    public void btnZc(View v) {
        final String username = stuusername.getText().toString();
        final String password = stupassword.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(Login.this, "注册成功", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}