package com.example.muzna.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by muzna on 8/3/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private ArrayList<StudentClass>arrayList;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_name,tv_email;
        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.name);
            tv_email = (TextView) view.findViewById(R.id.email);
        }
    }
    public RecyclerViewAdapter(Context context, ArrayList<StudentClass>array) {
        this.arrayList = array;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_adapter, parent, false);
        MyViewHolder mvh=new MyViewHolder(itemView);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StudentClass studentclass= arrayList.get(position);
        holder.tv_name.setText(studentclass.getName());
        holder.tv_email.setText(studentclass.getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
