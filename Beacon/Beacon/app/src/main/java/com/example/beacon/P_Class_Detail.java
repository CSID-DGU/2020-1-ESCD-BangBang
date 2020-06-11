package com.example.beacon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class P_Class_Detail extends AppCompatActivity {

    SampleData sample = new SampleData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_class_detail);

        final Button modify = findViewById(R.id.modify_attd);
        Button start = findViewById(R.id.start_attd);

        Intent intent = getIntent();
        TextView classname = findViewById(R.id.classname);
        classname.setText(intent.getStringExtra("Classname"));

        ListView listView = findViewById(R.id.detail_list);
        final P_DetailAdapter myAdapter = new P_DetailAdapter(this, sample.getStudents());
        listView.setAdapter(myAdapter);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "수  정")
                {
                    modify.setText("확  인");
                }
                else
                {
                    modify.setText("수  정");
                }
            }
        });


    }
}
