package com.example.beacon;

import android.app.Application;

import java.util.ArrayList;

public class SampleData extends Application {

    private ArrayList<Course> courses;
    private ArrayList<Student> students;


    public SampleData()
    {
        courses = new ArrayList<Course>();
        students = new ArrayList<Student>();

        Course database = new Course("데이터베이스", "월 13:00 - 15:00, 수 15:00 - 17:00");
        Course OS = new Course("운영체제", "화 09:00 - 11:00, 목 10:00 - 12:00");
        Course SW = new Course("소프트웨어 공학", "월 10:00 - 12:00, 금 15:00 - 17:00");
        Course C = new Course("기초프로그래밍", "수 15:30 - 17:00, 금 13:00 - 14:30");
        Course ma = new Course("기업사회맞춤형프로젝트", "수 11:00 - 13:00, 목 12:00 - 14:00");

        this.courses.add(database);
        this.courses.add(OS);
        this.courses.add(SW);
        this.courses.add(C);
        this.courses.add(ma);

        Student one = new Student("김경민", 2015112000);
        Student two = new Student("박재현", 2015112001);
        Student three = new Student("채현욱", 2015112002);
        Student four = new Student("이윤호", 2015112003);

        this.students.add(one);
        this.students.add(two);
        this.students.add(three);
        this.students.add(four);

    }

    public ArrayList<Course> getCourses()
    {
        return this.courses;
    }

    public ArrayList<Student> getStudents()
    {
        return this.students;
    }
}
