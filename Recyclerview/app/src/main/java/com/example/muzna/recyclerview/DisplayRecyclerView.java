package com.example.muzna.recyclerview;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;


public class DisplayRecyclerView extends AppCompatActivity {
    RecyclerView mrecyclerView;
    RecyclerViewAdapter Adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recyclerview);
        mrecyclerView=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        mrecyclerView.setLayoutManager(llm);

        Adapter= new RecyclerViewAdapter(this,fetchinfo());
        mrecyclerView.setAdapter(Adapter);
        mrecyclerView.setHasFixedSize(true);


    }
    private ArrayList<StudentClass>fetchinfo()
    {
        ArrayList<StudentClass>studentArraylist=(ArrayList<StudentClass>)getIntent().getSerializableExtra("data");
        return studentArraylist;
    }
}
