package com.company;

import java.util.ArrayList;

public class BookingsModel {
    BookingsDB db = new BookingsDB();   //add in external libraries

    BookingsModel() {
        db.cmd("drop table if exists lst1;");
        db.cmd("create table if not exists lst1 "+
                "(fld1 integer primary key autoincrement, fld2 text);");

        db.cmd("drop table if exists Courses;");
        db.cmd("create table if not exists Courses "+
                "(name text, stud integer);");
        addCourses("Software Development","50");
        addCourses("Essential Computing","90");

        db.cmd("drop table if exists Rooms;");
        db.cmd("create table if not exists Rooms "+
                "(name text, stud integer);");
        addRoom("10.2.49","60");
        addRoom("10.1.25","30");

        db.cmd("drop table if exists Timeslot;");
        db.cmd("create table if not exists Timeslot "+
                "(name text);");
//        for(int i=1;i<=10;i++)addTimeslot("Slot "+i);
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
//        for(int i=1;i<=10;i++)addTimeslot("Slot "+i);
        for(String day:days){addTimeslot(day+" AM");addTimeslot(day+" PM");}
        db.cmd("drop table if exists Teacher;");
        db.cmd("create table if not exists Teacher "+
                "(name text);");
        addLecturer("Mads Rosendahl");
        addLecturer("Mickey Mouse");
        addLecturer("Spiderman");

        db.cmd("drop table if exists Room Booking;");
        db.cmd("create table if not exists Room Booking "+
                "(name text, Course ID, Time ID, Room ID);");

        db.cmd("drop table if exists Teacher Booking;");
        db.cmd("create table if not exists Teacher Booking "+
                "(name text, Course ID, Time ID, Teacher ID);");


    }

    void addLecturer(String s){  db.cmd("insert into Lecturer (name) values ('"+s+"');");}
    ArrayList<String> getLecturer(){return db.query("select name from Lecturer;","name");}

    boolean hasLecturer(String s){
        ArrayList<String> lst= db.query("select name from Lecturer where name = '"+s+"';","name");
        System.out.println(lst);
        return lst.size()>0;
        //return getLecturer().contains(s);
    }

    void addRoom(String s,String stud){db.cmd("insert into Rooms (name,stud) values ('"+s+"',"+stud+");");}
    ArrayList<String> getRoom(){return db.query("select name from Rooms;","name");}

    void addCourses(String s,String stud){ db.cmd("insert into Courses (name,stud) values ('"+s+"',"+stud+");");}
    ArrayList<String> getCourses(){
        return db.query("select name from Courses;","name");
    }

    String findRoom(String c){
        ArrayList<String> lst= db.query(
                "select Rooms.name from Rooms inner join Courses"
                        +" where Courses.name = '"+c+"' and Rooms.stud > Courses.stud;","name");
        System.out.println(lst);
        if(lst.size()==0)return "";
        else return lst.get(0);
    }

    void addTimeslot(String s){ // remember to sanitize your data!
        db.cmd("insert into Timeslot (name) values ('"+s+"');");
    }
    ArrayList<String> getTimeslot(){
        return db.query("select name from Timeslot;","name");
    }

    void add(String s){ // remember to sanitize your data!
        db.cmd("insert into lst1 (fld2) values ('"+s+"');");
    }
    ArrayList<String> get(){
        return db.query("select fld2 from lst1 order by fld1;","fld2");
    }
}

