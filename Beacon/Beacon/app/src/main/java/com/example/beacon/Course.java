package com.example.beacon;

public class Course {

    private String name;
    private String time;

    public Course(String name, String time)
    {
        this.name = name;
        this.time = time;
    }


    public String getName()
    {
        return this.name;
    }

    public String getTime()
    {
        return this.time;
    }
}
