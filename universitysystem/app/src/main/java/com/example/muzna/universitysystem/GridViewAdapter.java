package com.example.muzna.universitysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by muzna on 8/15/2017.
 */

class GridViewAdapter extends BaseAdapter{
    Context c;
    ArrayList<University> array;
    public GridViewAdapter(Context context, ArrayList<University>arrayList) {
        this.c=context;
        this.array=arrayList;
    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            University university=array.get(position);
            grid = new View(c);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView idView = (TextView) grid.findViewById(R.id.uniid);
            TextView nameView = (TextView) grid.findViewById(R.id.uniname);
            idView.setText(university.getId());
            nameView.setText(university.getName());


        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
