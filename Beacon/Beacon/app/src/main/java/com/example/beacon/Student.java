package com.example.beacon;

public class Student {
    private String name;
    private int UID;

    public Student(String name, int UID)
    {
        this.name = name;
        this.UID = UID;
    }


    public String getName()
    {
        return this.name;
    }

    public int getUID()
    {
        return this.UID;
    }
}
