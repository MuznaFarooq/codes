package com.example.muzna.parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
public class Display_data extends AppCompatActivity {
    ListView listView;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        listView=(ListView) findViewById(R.id.list_item);
        setlistview(fetchinfo());
    }
    private void setlistview(ArrayList<Studentclass>studentarraylist)
    {
        customAdapter=new CustomAdapter(this,studentarraylist);
        listView.setAdapter(customAdapter);
    }
    ArrayList<Studentclass>fetchinfo()
    {
        ArrayList<Studentclass>studentarraylist=(ArrayList<Studentclass>)getIntent().getSerializableExtra("data");
        return studentarraylist;
    }
}
