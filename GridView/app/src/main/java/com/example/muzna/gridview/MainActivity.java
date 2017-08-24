package com.example.muzna.gridview;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add_button, delete_button;
    GridView gridView;
    String name, schoolname;
    int id;
    EditText etname, etschool;
    EditText etid;
    Student school;
    GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        initlisteners();
        fetchdata();
    }

    public void initlisteners() {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Custom Dialog");
                dialog.show();

                etname = (EditText) dialog.findViewById(R.id.name);
                etschool = (EditText) dialog.findViewById(R.id.school);

                Button okbutton = (Button) dialog.findViewById(R.id.ok);
                //id=Integer.parseInt(etid.getText().toString());
                reset();
                okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = etname.getText().toString();
                        schoolname = etschool.getText().toString();
                        add(name, schoolname);
                        dialog.dismiss();
                    }
                });
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setContentView(R.layout.dialog2);
                dialog2.setTitle("Custom Dialog2");
                dialog2.show();

                etid = (EditText) dialog2.findViewById(R.id.et_id);
                Button okbutton = (Button) dialog2.findViewById(R.id.okbutton);
                okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        id = Integer.parseInt(etid.getText().toString());
                        delete(id);
                        dialog2.dismiss();
                    }
                });
            }
        });
    }

    public void initviews() {
        add_button = (Button) findViewById(R.id.b_add);
        delete_button = (Button) findViewById(R.id.b_delete);
        gridView = (GridView) findViewById(R.id.grid_view);
    }

    public void add(String name, String school) {
        DBhelper db = new DBhelper(this);
        if (db.add(name, school)) {
            Toast.makeText(MainActivity.this, "Data successfully inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data cannot be inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void delete(int id) {
        DBhelper db = new DBhelper(this);
        if (db.delete(id)) {
            Toast.makeText(MainActivity.this, "Data has been deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data cannot be deleted", Toast.LENGTH_LONG).show();
        }
    }
    public void fetchdata() {
        DBhelper db = new DBhelper(this);
        Cursor data = db.getlist();
        ArrayList<Student> arraylist = new ArrayList<>();
        int i = 0;
        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                Student student = new Student(data.getString(0), data.getString(1), data.getString(2));
                arraylist.add(i, student);
                i++;
            }
            GridViewAdapter adapter = new GridViewAdapter(MainActivity.this, arraylist);
            gridView.setAdapter(adapter);
        }
    }

    private void reset() {
        etname.setText("");
        etschool.setText("");
    }
}
