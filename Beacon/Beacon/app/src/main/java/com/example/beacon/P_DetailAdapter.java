package com.example.beacon;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class P_DetailAdapter  extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Student> students;

    public P_DetailAdapter(Context context, ArrayList<Student> students)
    {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.students = students;
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.professor_detail_listitem, null);

        TextView movieName = view.findViewById(R.id.professor_detail_student);
        TextView grade = view.findViewById(R.id.professor_detail_number);
        TableLayout tablelayout = view.findViewById(R.id.professor_detail_table);

        TableRow tableRow = new TableRow(mContext);// tablerow 생성
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TableRow tableRow2 = new TableRow(mContext);// tablerow 생성
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if(position == 0)
        {
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample1[0][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow.addView(textView);		// tableRow에 view 추가
            }
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample1[1][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow2.addView(textView);		// tableRow에 view 추가
            }
        }
        if(position == 1)
        {
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample2[0][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow.addView(textView);		// tableRow에 view 추가
            }
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample2[1][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow2.addView(textView);		// tableRow에 view 추가
            }
        }
        if(position == 2)
        {
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample3[0][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow.addView(textView);		// tableRow에 view 추가
            }
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample3[1][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow2.addView(textView);		// tableRow에 view 추가
            }
        }
        if(position == 3)
        {
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample4[0][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow.addView(textView);		// tableRow에 view 추가
            }
            for(int i = 0 ; i < 15 ; i++) {
                TextView textView = new TextView(mContext);
                textView.setText(Attendance.sample4[1][i]);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(20);
                if(textView.getText() == "X")
                    textView.setTextColor(Color.rgb(255,0,0));
                textView.setHeight(150);
                tableRow2.addView(textView);		// tableRow에 view 추가
            }
        }
        tablelayout.addView(tableRow);// tableLayout에 tableRow 추가
        tablelayout.addView(tableRow2);


        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                 TableLayout.LayoutParams.MATCH_PARENT, tablelayout.getHeight());
        params.height = 3;

        View line = new View(mContext);
        line.setLayoutParams(params);
        line.setBackgroundColor(Color.parseColor("#FFAE90"));

        tablelayout.addView(line);

        movieName.setText(students.get(position).getName());
        grade.setText(Integer.toString(students.get(position).getUID()));

        return view;
    }
}
