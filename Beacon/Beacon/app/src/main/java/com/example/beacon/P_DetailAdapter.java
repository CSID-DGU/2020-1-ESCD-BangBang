package com.example.beacon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        movieName.setText(students.get(position).getName());
        grade.setText(Integer.toString(students.get(position).getUID()));

        return view;
    }
}
