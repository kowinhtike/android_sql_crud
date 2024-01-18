package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText name;
    EditText age ;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = findViewById(R.id.inputName);
        age = findViewById(R.id.inputAge);
        button = findViewById(R.id.button);

        name.setText(getIntent().getStringExtra("name"));
        age.setText(getIntent().getStringExtra("age"));
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        button.setOnClickListener(View -> {
            dbHelper.updateData(Integer.valueOf(getIntent().getStringExtra("id")),name.getText().toString(),Integer.valueOf(age.getText().toString()));
            finish();
        });
    }
}