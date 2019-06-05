package com.example.vote;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="vote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View btn){
        if(btn.getId()==R.id.button1){
            new VoteTask().execute("赞成");
        }else if(btn.getId()==R.id.button2){
            new VoteTask().execute("反对");
        }else {
            new VoteTask().execute("弃权");
        }
    }
    public String executeHttpGet() {
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            url = new URL(" http://192.168.1.102:8080/vote/vote.jsp");
            connection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    private String doVote(String voteStr) {
        String retStr = "";
        Log.i("vote", "dovote() voteStr" + voteStr);
        try {
            StringBuffer stringBuffer = new StringBuffer();           //存储封装好的请求体信息
            stringBuffer.append("r=").append(URLEncoder.encode(voteStr, "utf-8"));

            byte[] data = stringBuffer.toString().getBytes();
            String urlPath = "http://192.168.43.55:8080/vote/GetVote";
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     //设置超时时间
            httpURLConnection.setDoInput(true);        //打开输入流，获取数据
            httpURLConnection.setDoOutput(true);       //打开输出流，提交数据
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);     //使用post方式不能缓存
            //文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //像服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();   //获得响应码
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                retStr = inputStreamToString(inputStream);       //处理响应结果
                Log.i("vote", "retStr:" + retStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr;
    }
    public static String inputStreamToString(InputStream inputStream) {
        String resultData=null;
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] data=new byte[1024];
        int len=0;
        try {
            while((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    private class VoteTask extends AsyncTask<String,Void,String>{
        protected String doInBackground(String...params){
            for (String p:params){
                Log.i(TAG,"doInBackground"+p);
            }
            String ret=doVote(params[0]);
            return ret;
        }

        protected void onPostExcute(String s){
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
        }
    }


}
