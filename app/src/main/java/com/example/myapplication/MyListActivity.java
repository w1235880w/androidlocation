package com.example.myapplication;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyListActivity extends ListActivity implements Runnable, AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private String TAG="ml";
    Handler handler;
    private List<HashMap<String,String>> listItems;//存放文字，图片
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);
        Thread t=new Thread(this);
        t.start();
        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==7){
                    listItems=(List<HashMap<String,String>>)msg.obj;
                    listItemAdapter=new SimpleAdapter(MyListActivity.this,listItems,
                            R.layout.activity_my_list,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail}
                            );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }
    private void initListView(){
        listItems=new ArrayList<HashMap<String,String>>();
        for (int i=0;i<=28;i++){
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("ItemTitle","Rate:"+i);
            map.put("ItemDetail","Detail:"+i);
            listItems.add(map);
        }
        listItemAdapter =new SimpleAdapter(this,listItems,
                R.layout.activity_my_list,
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
                );
    }
    public void run(){
        List<HashMap<String,String>> retList=new ArrayList< HashMap<String,String>>();
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
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("ItemTitle",td1.text());
                map.put("ItemDetail",td2.text());
                retList.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }
    public void onItemClick(AdapterView<?> parent, View view,int position,long id){
        HashMap<String,String> map=(HashMap<String,String>)getListView().getItemAtPosition(position);
        String titleStr=map.get("ItemTitle");
        String detailStr=map.get("ItemDetail");
        //打开新的页面，传入参数
        Intent rateCalc=new Intent(this,RateCalActivity.class);
        rateCalc.putExtra("title",titleStr);
        rateCalc.putExtra("rate",Float.parseFloat(detailStr));
        startActivity(rateCalc);
    }
    public boolean onItemLongClick(AdapterView<?> parent,View view,final int position,long id){

        //构造对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        return true;
    }
}
