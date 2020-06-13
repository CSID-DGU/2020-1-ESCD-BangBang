package com.example.beacon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class P_Student_Detail extends AppCompatActivity {


    String modified_attd = "";
    private int absent_cnt = 0;
    private String[][] sample;
    int position = 0;
    private ArrayList<TextView> textViews = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p__student__detail);

        final TableRow tableRow = new TableRow(this);// tablerow 생성
        final TableRow tableRow2 = new TableRow(this);// tablerow 생성
        TextView student_name = findViewById(R.id.p_student_detail_name);
        TextView student_num = findViewById(R.id.p_student_detail_num);
        TextView absent_count = findViewById(R.id.absent_count);
        final Button modify = findViewById(R.id.modify_attd);

        modify.setText("수  정");

        Intent intent = getIntent();
        student_name.setText(intent.getStringExtra("Studentname"));
        student_num.setText(intent.getStringExtra("StudentNum"));

        position = intent.getIntExtra("position", 0);

        TableLayout tablelayout = findViewById(R.id.p_student_detail_table);

        tableRow.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        tableRow.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if(position == 0)
            sample = Attendance.sample1;
        else if(position == 1)
            sample = Attendance.sample2;
        else if(position == 2)
            sample = Attendance.sample3;
        else if(position == 3)
            sample = Attendance.sample4;
        else
            sample = Attendance.sample1;


        for(int i = 0 ; i < 15 ; i++) {
            TextView textView = new TextView(this);
            textView.setText(sample[0][i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            if(textView.getText() == "X")
            {
                textView.setTextColor(Color.rgb(255,0,0));
                absent_cnt++;
            }
            textView.setHeight(150);
            textViews.add(textView);
            tableRow.addView(textViews.get(i));		// tableRow에 view 추가
        }
        for(int i = 0 ; i < 15 ; i++) {
            TextView textView = new TextView(this);
            textView.setText(sample[1][i]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            if(textView.getText() == "X")
            {
                textView.setTextColor(Color.rgb(255,0,0));
                absent_cnt++;
            }
            textView.setHeight(150);
            textViews.add(textView);
            tableRow2.addView(textViews.get(i+15));		// tableRow에 view 추가
        }


        tablelayout.addView(tableRow);// tableLayout에 tableRow 추가
        tablelayout.addView(tableRow2);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, tablelayout.getHeight());
        params.height = 3;

        View line = new View(this);
        line.setLayoutParams(params);
        line.setBackgroundColor(Color.parseColor("#FFAE90"));

        tablelayout.addView(line);

        absent_count.setText(Integer.toString(absent_cnt));


        //Click Listener
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "수  정")
                {
                    modify.setText("확  인");
                }
                else { }
            }
        });

        textViews.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인") {
                    EditDialog(0, 0);
                }
            }
        });
        textViews.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 1);
                }
            }
        });
        textViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if(modify.getText() == "확  인")
            {
                EditDialog(0, 2);
            }

            }
        });
        textViews.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 3);
                }
            }
        });
        textViews.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 4);
                }
            }
        });
        textViews.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 5);
                }
            }
        });
        textViews.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 6);
                }
            }
        });
        textViews.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 7);
                }
            }
        });
        textViews.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 8);
                }
            }
        });
        textViews.get(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 9);
                }
            }
        });
        textViews.get(10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 10);
                }
            }
        });
        textViews.get(11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 11);
                }
            }
        });
        textViews.get(12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 12);
                }
            }
        });
        textViews.get(13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 13);
                }
            }
        });
        textViews.get(14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(0, 14);
                }
            }
        });
        textViews.get(15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 0);
                }
            }
        });
        textViews.get(16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 1);
                }
            }
        });
        textViews.get(17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 2);
                }
            }
        });
        textViews.get(18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 3);
                }
            }
        });
        textViews.get(19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 4);
                }
            }
        });
        textViews.get(20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 5);
                }
            }
        });
        textViews.get(21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 6);
                }
            }
        });
        textViews.get(22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 7);
                }
            }
        });
        textViews.get(23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 8);
                }
            }
        });
        textViews.get(24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 9);
                }
            }
        });
        textViews.get(25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 10);
                }
            }
        });
        textViews.get(26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 11);
                }
            }
        });
        textViews.get(27).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 12);
                }
            }
        });
        textViews.get(28).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 13);
                }
            }
        });
        textViews.get(29).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modify.getText() == "확  인")
                {
                    EditDialog(1, 14);
                }
            }
        });
    }


    //Button Event Detailed
    void show1(final int i, final int j)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("수정하시겠습니까?");
        builder.setMessage("변경 된 내용이 수정됩니다.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sample[i][j] = modified_attd;
                        change_attd();
                        recreate();
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

    void EditDialog(final int i, final int j)
    {
        final String[] items = {"O", "X"};

        final int[] selectedIndex = {0};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("출석 변경");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                // 바뀐 것을 적용한다.
                selectedIndex[0] = which;
            }
        });

        // 확인 버튼 설정
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(selectedIndex[0] == 0)
                    modified_attd = "O";
                else
                    modified_attd = "X";
                dialog.dismiss();
                show1(i, j);
            }
        });

        // 취소 버튼 설정
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //닫기
            }
        });

        builder.show();
    }

    void change_attd()
    {
        if(position == 0)
            Attendance.sample1 = sample;
        else if(position == 1)
            Attendance.sample2 = sample;
        else if(position == 2)
            Attendance.sample3 = sample;
        else if(position == 3)
            Attendance.sample4 = sample;
        else
            Attendance.sample1 = sample;
    }
}
