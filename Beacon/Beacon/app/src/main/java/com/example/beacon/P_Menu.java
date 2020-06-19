package com.example.beacon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class P_Menu extends AppCompatActivity {

    SampleData sample = new SampleData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p__menu);

        ListView listView = findViewById(R.id.professor_list);
        final P_menu_Adapter myAdapter = new P_menu_Adapter(this, sample.getCourses());

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), P_Class_Detail.class);
                intent.putExtra("Classname", sample.getCourses().get(position).getName());
                startActivity(intent);
            }
        });
    }
}
