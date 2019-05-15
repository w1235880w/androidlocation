package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyList2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> data=new ArrayList<String>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list2);

        ListView listView=(ListView)findViewById(R.id.mylist);
        for(int i=0;i<10;i++){
            data.add("item"+i);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.textno));
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view,int position,long id){
        adapter.remove(parent.getItemAtPosition(position));
//        adapter.notifyDataSetChanged();
    }
}
