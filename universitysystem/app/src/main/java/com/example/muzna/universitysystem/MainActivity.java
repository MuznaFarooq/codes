package com.example.muzna.universitysystem;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add_button,del_button;
    GridView gridView;
    GridViewAdapter adapter;
    String uni_name;int uni_id;
    public  static final String UNI_DATA="uniArrayList";
    private ArrayList<University> uniArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        initclicklisteners();
        displayUnidata();
    }
    private void initviews()
    {
        add_button=(Button) findViewById(R.id.add);
        del_button=(Button) findViewById(R.id.del);
        gridView =(GridView) findViewById(R.id.gv);
    }
    private void initclicklisteners()
    {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DisplayActivity.class);
                intent.putExtra(UNI_DATA,fetchUnList().get(position));
                startActivity(intent);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog add_dialog= new Dialog(MainActivity.this);
                add_dialog.setContentView(R.layout.add_dialog);
                add_dialog.setTitle("Dialog for Add Buttton");
                add_dialog.show();

                final EditText add_name= (EditText) add_dialog.findViewById(R.id.add_name);
                Button add_ok=(Button) add_dialog.findViewById(R.id.add_ok);
                reset(add_name);
                add_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uni_name=add_name.getText().toString();
                        add(uni_name);
                        displayUnidata();
                        add_dialog.dismiss();

                    }
                });

            }
        });
        del_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog del_dialog= new Dialog(MainActivity.this);
                del_dialog.setContentView(R.layout.del_dialog);
                del_dialog.setTitle("Dialog for Delete Button");
                del_dialog.show();

                final EditText del_id=(EditText) del_dialog.findViewById(R.id.del_id);
                Button del_ok=(Button) del_dialog.findViewById(R.id.del_ok);
                del_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uni_id= Integer.parseInt(del_id.getText().toString());
                        delete(uni_id);
                        if(delete(uni_id)){
                            adapter.notifyDataSetChanged();
                        }
                        displayUnidata();
                        del_dialog.dismiss();
                    }
                });
            }
        });
    }
    private void reset(EditText addname){
        addname.setText("");
    }
    public void add(String name) {
        DBhelper db = new DBhelper(this);
        if (db.uniadd(name)) {
            Toast.makeText(MainActivity.this, "Data successfully inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data cannot be inserted", Toast.LENGTH_LONG).show();
        }
    }
    public Boolean delete(int id) {
        DBhelper db = new DBhelper(this);
        boolean result=db.unidelete(id);
        if (db.unidelete(id)) {
            Toast.makeText(MainActivity.this, "Data has been deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Data cannot be deleted", Toast.LENGTH_LONG).show();
        }
        return result;
    }
    public ArrayList<University>fetchUnList(){
        DBhelper db = new DBhelper(this);
        Cursor data = db.getlist();
        int i = 0;
        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                University university = new University(data.getString(0), data.getString(1));
                uniArrayList.add(i,university);
                i++;
            }
        }
            return uniArrayList;
    }

    public void displayUnidata() {
        DBhelper db = new DBhelper(this);
        ArrayList<University> arraylist = new ArrayList<>();
        arraylist=db.getAllUni();
        adapter = new GridViewAdapter(MainActivity.this, arraylist);
        gridView.setAdapter(adapter);

    }
}
