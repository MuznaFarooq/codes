package com.example.muzna.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class InputData extends AppCompatActivity {
    private static final String NULL = "";
    //private static final String STUDENT_DATA="student_data";
    EditText etname,etemail;
    Button addbutton,displaybutton;
    String name,email;
    private  ArrayList<Studentclass>studentarraylist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        etname =(EditText) findViewById(R.id.et_name);
        etemail=(EditText) findViewById(R.id.et_email);
        addbutton =(Button) findViewById(R.id.button1);
        displaybutton = (Button) findViewById(R.id.button2);
        onclicklisteners();
    }
    void onclicklisteners()
    {
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etname.getText().toString();
                email=etemail.getText().toString();
                if(check(name,email))
                {
                    Studentclass student=new Studentclass(name,email);
                    studentarraylist.add(student);
                    setdata();
                }
            }
        });
        displaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),Display_data.class);
                intent.putExtra("data",studentarraylist);
                startActivity(intent);
            }
        });
    }
    void setdata()
    {
        etname.setText("");
        etemail.setText("");
    }
    private boolean check(String name, String email)
    {
        if(name!=NULL&&email!=NULL&&name.toString().replaceAll(" ","").length()>0&&email.toString().replaceAll(" ","").length()>0 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}



