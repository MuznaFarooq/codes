package com.example.muzna.parcelable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by muzna on 8/1/2017.
 */

public class CustomAdapter extends BaseAdapter{
    Context context;
    ArrayList<Studentclass>arrayList;
    public CustomAdapter(Context mycontext, ArrayList<Studentclass>arrayList)
    {
        this.context=mycontext;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount()
    {
        return arrayList.size();
    }
    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.custom_row, null);

        }

        TextView studentName = (TextView) v.findViewById(R.id.tv_name);
        TextView studentemail= (TextView) v.findViewById(R.id.tv_email);

        studentName.setText(arrayList.get(i).getName());
        studentemail.setText(arrayList.get(i).getEmail());

        return v;
    }

}
