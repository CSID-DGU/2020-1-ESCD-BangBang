package com.example.beacon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class S_Class_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s__class__detail);

        Intent intent = getIntent();
        TextView classname = findViewById(R.id.classname);
        classname.setText(intent.getStringExtra("Classname"));
    }
}
