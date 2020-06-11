package com.example.beacon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
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
        final Button start = findViewById(R.id.start_attd);

        modify.setText("수  정");
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
                    show1();
                    modify.setText("수  정");
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
            }
        });
    }

    void show1()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("수정하시겠습니까?");
        builder.setMessage("변경 된 내용이 수정됩니다.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //DataBase에 변경된 내용 송신
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //DataBase 건드리지 않음.
                    }
                });
        builder.show();
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
