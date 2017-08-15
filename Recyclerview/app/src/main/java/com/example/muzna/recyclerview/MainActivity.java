package com.example.muzna.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String name, email;
    private EditText etname, etemail;
    private ToggleButton toggle; //1_ material design 2_3 ways to implement onclick
    private Button addButton;
    private ArrayList<StudentClass> studentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initClickListeners();


    }

    private void initClickListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etname.getText().toString();
                email = etemail.getText().toString();
                if (check(name, email)) {
                    StudentClass student = new StudentClass(name, email);
                    studentArrayList.add(student);
                    reset();
                }
            }
        });
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Intent intent = new Intent(getApplicationContext(), DisplayRecyclerView.class);
                    intent.putExtra("data", studentArrayList);
                    startActivity(intent);
                } else {
                    // The toggle is disabled
                    Intent intent = new Intent(getApplicationContext(), DisplayListView.class);
                    intent.putExtra("data", studentArrayList);
                    startActivity(intent);
                }
            }
        });
    }

    public void initViews() {
        etname = (EditText) findViewById(R.id.et_name);
        etemail = (EditText) findViewById(R.id.et_email);
        toggle = (ToggleButton) findViewById(R.id.toggle_button);
        addButton = (Button) findViewById(R.id.add_button);
    }

    private Boolean check(String name, String email) {
        if (name != null && email != null && name.toString().replaceAll(" ", "").length() >= 0 && email.toString().replaceAll(" ", "").length() >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private void reset() {
        etemail.setText("");
        etname.setText("");
    }
}
