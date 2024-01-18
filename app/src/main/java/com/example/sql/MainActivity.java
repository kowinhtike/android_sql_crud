package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText age ;

    Button button;

    Button showBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.inputName);
        age = findViewById(R.id.inputAge);
        button = findViewById(R.id.button);
        showBtn = findViewById(R.id.button2);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        button.setOnClickListener(View -> {
            dbHelper.insertData(name.getText().toString(), Integer.valueOf(age.getText().toString()));
            ArrayList<CustomItem> dataList = dbHelper.getAllData();
            Toast.makeText(this, "Total Data Size = " + String.valueOf(dataList.size()),Toast.LENGTH_LONG).show();
            name.setText("");
            age.setText("");
        });

        showBtn.setOnClickListener(View -> {
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
        });




    }
}