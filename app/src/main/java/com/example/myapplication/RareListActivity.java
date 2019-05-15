package com.example.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
public class RareListActivity extends ListActivity implements Runnable{
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rare_list);
        List<String> list1=new ArrayList<String>();
        for (int i=1;i<=28;i++){
            list1.add("item"+1);
        }

        final ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        setListAdapter(adapter);
        Thread t=new Thread(this);
        t.start();
        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==7){
                    List<String> list2=(List<String>)msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(RareListActivity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }
    public void run(){
        List<String> retList=new ArrayList<String>();
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
                retList.add(td1.text()+":"+td2.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }
}
