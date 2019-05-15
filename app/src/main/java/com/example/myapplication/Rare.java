package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Rare extends AppCompatActivity implements Runnable{

    private final String TAG = "Rare";
    private float dollarRate = 0.1f;
    private float poundRate = 0.2f;
    private float wonRate = 0.3f;
    private String update="";
    Handler handler;
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rare);
        out=findViewById(R.id.show);
        SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        dollarRate=sharedPreferences.getFloat("dollarkey",0.0f);
        poundRate= sharedPreferences.getFloat("poundkey",0.0f);
        wonRate=sharedPreferences.getFloat("wonkey",0.0f);
        update = sharedPreferences.getString("update_date","");
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr =sdf.format(today);
        Log.i(TAG,"onCreate:dollarRate="+dollarRate);
        Log.i(TAG,"onCreate:poundRate="+poundRate);
        Log.i(TAG,"onCreate:wonRate="+wonRate);
        if(!todayStr.equals(update)){
            Thread t=new Thread(this);
            t.start();
        }

        handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==5){
                    Bundle bdl=(Bundle)msg.obj;
                    dollarRate=bdl.getFloat("dollar-rate");
                    poundRate=bdl.getFloat("pound-rate");
                    wonRate=bdl.getFloat("won-rate");
                    SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("update_date",todayStr);
                    editor.putFloat("dollarkey",dollarRate);
                    editor.putFloat("poundkey",poundRate);
                    editor.putFloat("wonkey",wonRate);
                    editor.apply();
                    Toast.makeText(Rare.this,"汇率已更新",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
    }

    public static String keepThree(float b) {
        DecimalFormat format = new DecimalFormat("#0.000");
        String str = format.format(b);
        return str;
    }
    public void abc(View v){
        EditText rmb=findViewById(R.id.rmb);
        String str=rmb.getText().toString();
        out=findViewById(R.id.show);
        if(str.length()>0){
            float b= Float.valueOf(str);
            if(v.getId()==R.id.dollar){
                out.setText("约为：" + keepThree(b*dollarRate) + "美元");
            }else if(v.getId()==R.id.pound){
                out.setText("约为：" + keepThree(b*poundRate) + "欧元");
            }else {
                out.setText("约为：" + keepThree(b*wonRate) + "韩元");
            }
        }else{
            out.setText("未输入数值");
        }
    }

    public void openOne(View v) {
        Intent hello=new Intent(this,Calculate.class);
        hello.putExtra("dollarkey",dollarRate);
        hello.putExtra("poundkey",poundRate);
        hello.putExtra("wonkey",wonRate);

        Log.i(TAG,"openOne:dollarRate="+dollarRate);
        Log.i(TAG,"openOne:poundRate="+poundRate);
        Log.i(TAG,"openOne:wonRate="+wonRate);
        startActivityForResult(hello,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rare,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent list=new Intent(this,MyListActivity.class);
            startActivity(list);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==2){
            Bundle bundle=data.getExtras();
            dollarRate=bundle.getFloat("keydollar",0.1f);
            poundRate=bundle.getFloat("keypound",0.2f);
            wonRate=bundle.getFloat("keywon",0.3f);
            Log.i(TAG,"onActivityResult:dollarRate="+dollarRate);
            Log.i(TAG,"onActivityResult:poundRate="+poundRate);
            Log.i(TAG,"onActivityResult:wonRate="+wonRate);
            SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putFloat("dollarkey",dollarRate);
            editor.putFloat("poundkey",poundRate);
            editor.putFloat("wonkey",wonRate);
            editor.commit();
            Log.i(TAG,"成功");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void run() {
        for (int i = 1; i < 3; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        URL url = null;
//        try {
//            url = new URL("http://www.usd-cny.com/icbc.htm");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getErrorStream();
//            String html = inputStream2String(in);
//            Log.i(TAG, "run:html" + html);
//            Document doc = Jsoup.parse(html);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Bundle bundle = getFormBOC();
        Message msg = handler.obtainMessage(5);
        msg.obj = bundle;
        handler.sendMessage(msg);

    }

    private Bundle getFormBOC() {
        Bundle bundle= new Bundle();
        Document doc;
        try {
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
//            Elements newsHeadlines = doc.select("#mp-itn b a");
            Elements tables=doc.getElementsByTag("table");
//            for(Element table :tables){
//                Log.i(TAG,"run:table="+table);
//            }
            Element table6=tables.get(0);
//            Log.i(TAG,"run:table6="+table6);
            Elements tds=table6.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                Log.i(TAG, "run: text="+td1.text());
                Log.i(TAG, "run: text="+td2.text());
                if("美元".equals(td1.text())){
                    bundle.putFloat("dollar-rate",100f/Float.parseFloat(td2.text()));
                }
                else if("欧元".equals(td1.text())){
                    bundle.putFloat("pound-rate",100f/Float.parseFloat(td2.text()));
                }else if("韩元".equals(td1.text())){
                    bundle.putFloat("won-rate",100f/Float.parseFloat(td2.text()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }

}
//    public void dollar(View v){
//        EditText rmb=findViewById(R.id.rmb);
//        String str=rmb.getText().toString();
//        TextView out=findViewById(R.id.show);
//        if(str.length()>0){
//            double b = Double.valueOf(str);
//            double a = b * 6.9;
//            out.setText("约为：" + keepThree(a) + "美元");
//        }else{
//            out.setText("未输入数值");
//        }
//    }
//    public void pound(View v){
//        EditText rmb=findViewById(R.id.rmb);
//        String str=rmb.getText().toString();
//        TextView out=findViewById(R.id.show);
//        if(str.length()>0){
//            double b = Double.valueOf(str);
//            double a = b * 10;
//            out.setText("约为：" + keepThree(a) + "欧元");
//        }else{
//            out.setText("未输入数值");
//        }
//    }
//    public void won(View v){
//        EditText rmb=findViewById(R.id.rmb);
//        String str=rmb.getText().toString();
//        TextView out=findViewById(R.id.show);
//        if(str.length()>0){
//            double b = Double.valueOf(str);
//            double a = b * 0.5;
//            out.setText("约为：" + keepThree(a) + "韩元");
//        }else{
//            out.setText("未输入数值");
//        }
//    }
//    public void abc(View v){
//        EditText rmb=findViewById(R.id.rmb);
//        String str=rmb.getText().toString();
//        TextView out=findViewById(R.id.show);
//        if(str.length()>0){
//            if(v.getId()==R.id.dollar){
//                double b = Double.valueOf(str);
//                double a = b / 6.7;
//                out.setText("约为：" + keepThree(a) + "美元");
//            }else if(v.getId()==R.id.pound){
//                double b = Double.valueOf(str);
//                double a = b / 11;
//                out.setText("约为：" + keepThree(a) + "欧元");
//            }else {
//                double b = Double.valueOf(str);
//                double a = b * 160;
//                out.setText("约为：" + keepThree(a) + "韩元");
//            }
//        }else{
//            out.setText("未输入数值");
//        }
//    }