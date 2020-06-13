package com.example.beacon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class P_Class_Detail extends AppCompatActivity {


    SampleData sample = new SampleData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_class_detail);

        final Button start = findViewById(R.id.start_attd);
        final Button restart = findViewById(R.id.restart);
        ListView listView = findViewById(R.id.detail_list);


        Intent intent = getIntent();
        TextView classname = findViewById(R.id.classname);
        classname.setText(intent.getStringExtra("Classname"));

        final P_DetailAdapter myAdapter = new P_DetailAdapter(this, sample.getStudents());
        listView.setAdapter(myAdapter);


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), P_Student_Detail.class);
                intent.putExtra("Studentname", sample.getStudents().get(position).getName());
                intent.putExtra("StudentNum", Integer.toString(sample.getStudents().get(position).getUID()));
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }



    void show2()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("출석이 진행되었습니다.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //DataBase flag 변경
                    }
                });
        builder.show();
    }
}
