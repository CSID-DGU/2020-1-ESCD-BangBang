package com.example.beacon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class S_Class_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s__class__detail);

        Intent intent = getIntent();
        TextView classname = findViewById(R.id.classname);
        classname.setText(intent.getStringExtra("Classname"));
        TableLayout tablelayout = findViewById(R.id.s_class_detail_table);

        TableRow tableRow = new TableRow(this);// tablerow 생성
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TableRow tableRow2 = new TableRow(this);// tablerow 생성
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        for(int i = 0 ; i < 15 ; i++) {
            TextView textView = new TextView(this);
            textView.setText(Attendance.sample1[0][i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            if(textView.getText() == "X")
                textView.setTextColor(Color.rgb(255,0,0));
            textView.setHeight(150);
            tableRow.addView(textView);		// tableRow에 view 추가
        }
        for(int i = 0 ; i < 15 ; i++) {
            TextView textView = new TextView(this);
            textView.setText(Attendance.sample1[1][i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            if(textView.getText() == "X")
                textView.setTextColor(Color.rgb(255,0,0));
            textView.setHeight(150);
            tableRow2.addView(textView);		// tableRow에 view 추가
        }

        tablelayout.addView(tableRow);// tableLayout에 tableRow 추가


        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, tablelayout.getHeight());
        params.height = 3;

        View line = new View(this);
        line.setLayoutParams(params);
        line.setBackgroundColor(Color.parseColor("#FFAE90"));

        tablelayout.addView(line);
        tablelayout.addView(tableRow2);


    }
}
