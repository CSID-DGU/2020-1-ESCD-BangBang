package com.example.beacon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class P_menu_Adapter extends BaseAdapter {

     Context mContext;
     LayoutInflater mLayoutInflater = null;
     ArrayList<Course> courses;

    public P_menu_Adapter(Context context, ArrayList<Course> courses) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.courses = courses;
    }
    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.professor_listitem, null);

        TextView movieName = view.findViewById(R.id.profeccor_classname);
        TextView grade = view.findViewById(R.id.professor_classtime);

        movieName.setText(courses.get(position).getName());
        grade.setText(courses.get(position).getTime());

        return view;
    }
}
