package com.example.muzna.universitysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by muzna on 8/16/2017.
 */

class StdCustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Student> array;
    public StdCustomAdapter(Context context, ArrayList<Student>arrayList) {
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
            Student student=array.get(position);
            grid = new View(c);
            grid = inflater.inflate(R.layout.std_grid, null);
            TextView idView = (TextView) grid.findViewById(R.id.stdid);
            TextView nameView = (TextView) grid.findViewById(R.id.stdname);
            idView.setText(student.getId());
            nameView.setText(student.getName());


        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
