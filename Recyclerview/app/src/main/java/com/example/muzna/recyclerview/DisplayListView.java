package com.example.muzna.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayListView extends AppCompatActivity {
    ListView listView;
    private ListViewAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_listview);
        listView=(ListView) findViewById(R.id.list_item);
        setlistview(fetchinfo());
    }
    private void setlistview(ArrayList<StudentClass> studentarraylist)
    {
        Adapter=new ListViewAdapter(this,studentarraylist);
        listView.setAdapter(Adapter);
    }
    ArrayList<StudentClass>fetchinfo()
    {
        ArrayList<StudentClass>studentarraylist=(ArrayList<StudentClass>)getIntent().getSerializableExtra("data");
        return studentarraylist;
    }
}
