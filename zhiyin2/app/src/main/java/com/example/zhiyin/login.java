package com.example.zhiyin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import rx.Subscription;

public class Login extends AppCompatActivity {
    private Button btnlogin;

    private Button btnupdate;

    private EditText txtusername;

    private EditText txtpassword;

    private String ObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtpassword = (EditText) findViewById(R.id.edittextstupass);
        txtusername = (EditText) findViewById(R.id.edittextstuname);
        btnlogin =(Button)findViewById(R.id.buttonlogin);
        btnupdate = (Button)findViewById(R.id.buttonupdate);

        btnlogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doLogin();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doUpdate();
            }
        });
    }

    private void doLogin(){//登录
        BmobQuery<Student> query=new BmobQuery<Student>();
        String userName=txtusername.getText().toString();
        //String userName="123";
        query.addWhereEqualTo("stuName",userName);//增加查询条件
        query.setLimit(2);
        query.findObjects(new FindListener<Student>() {

            @Override
            public void done(List<Student> list, BmobException e) {
                onError();
                onSuccess();
            }

            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(Login.this, arg1, 20 * 1000).show();
            }

            public void onSuccess(List<Student> list) {
                // TODO Auto-generated method stub
                Student user = list.get(0);//获取Student对象
                if (user.getPassword().equals(txtpassword.getText().toString())) {
                    Toast.makeText(Login.this, "login success", 3 * 1000).show();
                    ObjectId = list.get(0).getObjectId();
                } else {
                    Toast.makeText(Login.this, "password error", 3 * 1000).show();
                }

            }
        });
    }


    private void doUpdate(){//更新密码
        Student user =new Student();
        user.setObjectId(ObjectId);
        user.setPassword(txtpassword.getText().toString());
        user.update(new UpdateListener() {


            @Override
            public void done(BmobException e) {
                onSuccess();
                onFailure();
            }

            public void onSuccess() {
                // TODO Auto-generated method stub
                Toast.makeText(Login.this, "update success", 3 * 1000).show();
            }

            public void onFailure(int arg0, String arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(Login.this, arg1, 3 * 1000).show();
            }
        });
    }


}
