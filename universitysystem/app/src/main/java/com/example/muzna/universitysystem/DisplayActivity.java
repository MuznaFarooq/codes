package com.example.muzna.universitysystem;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.muzna.universitysystem.MainActivity.UNI_DATA;

public class DisplayActivity extends AppCompatActivity {
    private Button DelStd,AddStd;
    private GridView gridViewStd;
    private String studentName;int studentId;
    StdCustomAdapter customAdapter;
    University uni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initViews();
        initListeners();
        uni=(University) getIntent().getSerializableExtra(UNI_DATA);
        if(uni!=null){
            displayStudents();
        }
        displayStudents();
    }
    private void initViews(){
        AddStd=(Button) findViewById(R.id.addStd);
        DelStd=(Button) findViewById(R.id.delStd);
        gridViewStd=(GridView) findViewById(R.id.std_gridView);
    }
    private void initListeners(){
        AddStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog addDialog=new Dialog(DisplayActivity.this);
                addDialog.setContentView(R.layout.std_add);
                addDialog.setTitle("Add Dialog for Student");

                final EditText stdName=(EditText) addDialog.findViewById(R.id.std_name);
                Button okbutton=(Button) addDialog.findViewById(R.id.std_addOk);
                addDialog.show();

                okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       studentName=stdName.getText().toString();
                        addStudent(studentName);
                        stdName.setText("");
                        displayStudents();
                        addDialog.dismiss();
                    }
                });
            }
        });
        DelStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog delDialog=new Dialog(DisplayActivity.this);
                delDialog.setContentView(R.layout.std_del);
                delDialog.setTitle("Delete Dialog for Student");
                delDialog.show();

                final EditText stdId= (EditText) delDialog.findViewById(R.id.std_id);
                Button okbtn=(Button) delDialog.findViewById(R.id.std_ok);

                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studentId=Integer.parseInt(stdId.getText().toString());
                        deleteStudent(studentId);
                        if(deleteStudent(studentId)){
                            customAdapter.notifyDataSetChanged();
                        }
                        displayStudents();
                        delDialog.dismiss();;
                    }
                });


            }
        });
    }
    private void addStudent(String name){
        DBhelper db = new DBhelper(this);
        if (db.stdadd(name,uni.getId())) {
            Toast.makeText(DisplayActivity.this, "Data successfully inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DisplayActivity.this, "Data cannot be inserted", Toast.LENGTH_LONG).show();
        }
    }
    private Boolean deleteStudent(int id){
        DBhelper db = new DBhelper(this);
        boolean result=db.stddelete(id);
        if (result) {
            Toast.makeText(DisplayActivity.this, "Data has been deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DisplayActivity.this, "Data cannot be deleted", Toast.LENGTH_LONG).show();
        }
        return result;
    }
    private void displayStudents(){
        DBhelper db = new DBhelper(this);
        ArrayList<Student>arraylist= db.getAllStudents(uni.getId());
        customAdapter = new StdCustomAdapter(DisplayActivity.this, arraylist);
        gridViewStd.setAdapter(customAdapter);
    }
}
